package apresentacao;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import entidades.Cliente;
import entidades.ContratoAluguel;
import entidades.Funcionario;
import entidades.Item;
import entidades.Usuario;
import facade.SistemaFacade;
public class MenuFuncionario {

    private SistemaFacade sistema;
    private Funcionario funcionario;
    private Scanner scanner;

    public MenuFuncionario(SistemaFacade sistema, Funcionario funcionario) {
        this.sistema = sistema;
        this.funcionario = funcionario;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {

        int opcao;

        do {

            System.out.println("\n===== MENU FUNCIONÁRIO =====");
            System.out.println("Bem-vindo, " + funcionario.getNome());
            System.out.println("========== USUÁRIOS ==========");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Usuários");
            System.out.println("=========== ITENS ============");
            System.out.println("3 - Cadastrar Item");
            System.out.println("4 - Buscar Item");
            System.out.println("5 - Listar Itens");
            System.out.println("6 - Atualizar Item");
            System.out.println("7 - Desativar Item");
            System.out.println("========= CONTRATOS ==========");
            System.out.println("8 - Registrar Aluguel");
            System.out.println("9 - Buscar Contrato");
            System.out.println("10 - Listar Contratos");
            System.out.println("11 - Finalizar Contrato");
            System.out.println("12 - Cancelar Contrato");
            System.out.println("0 - Sair");

            opcao = lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarCliente();
                    break;

                case 2:
                    listarUsuarios();
                    break;

                case 3:
                    cadastrarItem();
                    break;

                case 4:
                    buscarItem();
                    break;

                case 5:
                    listarItens();
                    break;

                case 6:
                    atualizarItem();
                    break;

                case 7:
                    desativarItem();
                    break;

                case 8:
                    registrarAluguel();
                    break;

                case 9:
                    buscarContrato();
                    break;

                case 10:
                    listarContratos();
                    break;

                case 11:
                    finalizarContrato();
                    break;

                case 12:
                    cancelarContrato();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }

        } while (opcao != 0);
    }

    public void cadastrarCliente() {

        System.out.println("\n=== CADASTRAR CLIENTE ===");

        int id = sistema.gerarProximoIdUsuario();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = new Cliente(id, nome, email, cpf, senha);

        if (sistema.cadastrarUsuario(cliente)) {

            System.out.println("Cliente cadastrado com sucesso");
            System.out.println("ID gerado: " + id);

        } else {

            System.out.println("Erro ao cadastrar cliente");

        }
    }

    public void listarUsuarios() {

        List<Usuario> usuarios = sistema.listarUsuarios();

        System.out.println("\n===== USUÁRIOS =====");

        if (usuarios.isEmpty()) {

            System.out.println("Nenhum usuário cadastrado");
            //ver como quebrar o loop com jackson

        }

        for (Usuario usuario : usuarios) {

            System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail() + " | Ativo: " + usuario.isAtivo());
        }
    }

    public void registrarAluguel() {

        System.out.println("\n=== REGISTRAR ALUGUEL ===");

        System.out.print("ID do cliente: ");
        int idCliente = lerInteiro();

        Usuario usuario = sistema.buscarUsuario(idCliente);

        if (usuario == null || !(usuario instanceof Cliente)) {

            System.out.println("Cliente não encontrado.");
            return;

        }

        Cliente cliente = (Cliente) usuario;

        System.out.print("ID do item: ");
        int idItem = lerInteiro();

        Item item = sistema.buscarItem(idItem);

        if (item == null) {

            System.out.println("Item não encontrado.");
            return;

        }

        if (!item.estaDisponivel()) {

            System.out.println("Item indisponível para aluguel.");
            return;

        }

        System.out.print("Data de retirada (AAAA-MM-DD): ");
        String dataRetirada = scanner.nextLine();

        System.out.print("Data de devolução prevista (AAAA-MM-DD): ");
        String dataDevolucaoPrevista = scanner.nextLine();

        LocalDate retirada;
        LocalDate devolucao;

        try {

            retirada = LocalDate.parse(dataRetirada);
            devolucao = LocalDate.parse(dataDevolucaoPrevista);

        } catch (Exception e) {

            System.out.println("Formato de data inválido.");
            return;

        }

        long quantidadeDias = ChronoUnit.DAYS.between(retirada, devolucao);

        if (quantidadeDias <= 0) {

            System.out.println("A data de devolução deve ser posterior à data de retirada.");
            return;

        }

        double valorTotal = quantidadeDias * item.getTaxaDiaria();

        int idContrato = sistema.gerarProximoIdContrato();

        ContratoAluguel contrato = new ContratoAluguel(idContrato, cliente, item, dataRetirada, dataDevolucaoPrevista, valorTotal);

        if (sistema.cadastrarContrato(contrato)) {

            item.alugar();

            System.out.println("\n=== ALUGUEL REGISTRADO ===");
            System.out.println("ID do contrato: " + idContrato);
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Item: " + item.getNome());
            System.out.println("Período: " + quantidadeDias + " dia(s)");
            System.out.println("Taxa diária: R$ " + item.getTaxaDiaria());
            System.out.println("Valor total: R$ " + valorTotal);

        } else {

            System.out.println("Erro ao registrar aluguel.");

        }
    }

    public void buscarContrato() {

        System.out.print("ID do contrato: ");

        int id = lerInteiro();

        ContratoAluguel contrato = sistema.buscarContrato(id);

        if (contrato == null) {

            System.out.println("Contrato não encontrado");
            return;

        }

        System.out.println("\n=== CONTRATO ===");
        System.out.println("ID: " + contrato.getId());
        System.out.println("Cliente: " + contrato.getCliente().getNome());
        System.out.println("Item: " + contrato.getItem().getNome());
        System.out.println("Status: " + contrato.getStatus());
        System.out.println("Data Retirada: " + contrato.getDataRetirada());
        System.out.println("Devolução Prevista: " + contrato.getDataDevolucaoPrevista());
        System.out.println("Valor Total: R$ " + contrato.getValorTotal());
    }

    public void listarContratos() {

        List<ContratoAluguel> contratos = sistema.listarContratos();

        System.out.println("\n===== CONTRATOS =====");

        if (contratos.isEmpty()) {

            System.out.println("Nenhum contrato cadastrado");
            //perguntar a jackson novamente para quebrar o loop

        }

        for (ContratoAluguel contrato : contratos) {

            System.out.println("ID: " + contrato.getId() + " | Cliente: " + contrato.getCliente().getNome() + " | Item: " + contrato.getItem().getNome() + " | Status: " + contrato.getStatus());
        }
    }

    public void finalizarContrato() {

        System.out.print("ID do contrato: ");

        int id = lerInteiro();

        ContratoAluguel contrato = sistema.buscarContrato(id);

        if (contrato == null) {

            System.out.println("Contrato não encontrado");
            //finalizar o fluxo aqui com o return; perguntar a jackson

        }

        System.out.print("Data real da devolução (AAAA-MM-DD): ");

        String dataDevolucaoReal = scanner.nextLine();

        LocalDate dataPrevista;
        LocalDate dataReal;

        try {

            dataPrevista = LocalDate.parse(contrato.getDataDevolucaoPrevista());
            dataReal = LocalDate.parse(dataDevolucaoReal);

        } catch (Exception e) {

            System.out.println("Formato de data inválido");
            return; //dar a quebra do fluxo perguntar pra jackson se pode fazer isso


        }

        contrato.setDataDevolucaoReal(dataDevolucaoReal);

        long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataReal);

        if (diasAtraso > 0) {

            double multa = diasAtraso * contrato.getItem().getTaxaDiaria();

            contrato.setValorMulta(multa);

            System.out.println("Dias de atraso: " + diasAtraso);
            System.out.println("Multa aplicada: R$ " + multa);

        }

        contrato.getItem().devolver();

        if (sistema.finalizarContrato(id)) {

            System.out.println("Contrato finalizado com sucesso");
            System.out.println("Item devolvido ao estoque");

        } else {

            System.out.println("Erro ao finalizar contrato");

        }
    }

    public void cancelarContrato() {

        System.out.print("ID do contrato: ");

        int id = lerInteiro();

        ContratoAluguel contrato = sistema.buscarContrato(id);

        if (contrato == null) {

            System.out.println("Contrato não encontrado");
            return;//perguntar se pode usar o return;

        }


        if (sistema.cancelarContrato(id)) {
            contrato.getItem().devolver();

            System.out.println("Contrato cancelado com sucesso");
            System.out.println("Item devolvido ao estoque");

        } else {

            System.out.println("Erro ao cancelar contrato");

        }
    }

    public int lerInteiro() {
        boolean valida = true;
        while (valida) {

            try {

                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {

                System.out.println("Digite apenas números: ");
                scanner.nextLine();

            }
        }
        return -1;
    }
    
    public void cadastrarItem() {

        System.out.println("\n=== CADASTRAR ITEM ===");

        int id = sistema.gerarProximoIdItem();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Taxa diária: ");
        double taxaDiaria = Double.parseDouble(scanner.nextLine());

        System.out.print("Estado de conservação: ");
        String estado = scanner.nextLine().toUpperCase();

        System.out.print("Valor de reposição: ");
        double valorReposicao = Double.parseDouble(scanner.nextLine());
        //Ajustar aqui quando tiver pronto categoria e fornecedor
        Item item = new Item(id, nome, descricao, taxaDiaria, estado, valorReposicao, null, null);

        if (sistema.cadastrarItem(item)) {

            System.out.println("Item cadastrado com sucesso");
            System.out.println("ID gerado: " + id);

        } else {

            System.out.println("Erro ao cadastrar item");

        }
    }
    public void buscarItem() {

        System.out.print("ID do item: ");

        int id = lerInteiro();

        Item item = sistema.buscarItem(id);

        if (item == null) {

            System.out.println("Item não encontrado");
            return;

        }

        System.out.println("\n===== ITEM =====");
        System.out.println("ID: " + item.getId());
        System.out.println("Nome: " + item.getNome());
        System.out.println("Descrição: " + item.getDescricao());
        System.out.println("Taxa diária: " + item.getTaxaDiaria());
        System.out.println("Estado: " + item.getEstadoConservacao());
        System.out.println("Status: " + item.getStatus());
    }
    public void listarItens() {

        List<Item> itens = sistema.listarItens();

        System.out.println("\n===== ITENS =====");

        if (itens.isEmpty()) {

            System.out.println("Nenhum item cadastrado");
            //quebra de loop colocar aq 

        }

        for (Item item : itens) {

            System.out.println("ID: " + item.getId() + " | Nome: " + item.getNome() + " | Status: " + item.getStatus() + " | Ativo: " + item.isAtivo());
        }
    }
    public void atualizarItem() {

        System.out.print("ID do item: ");

        int id = lerInteiro();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Taxa diária: ");
        double taxaDiaria = Double.parseDouble(scanner.nextLine());

        System.out.print("Estado de conservação: ");
        String estado = scanner.nextLine().toUpperCase();

        System.out.print("Valor de reposição: ");
        double valorReposicao = Double.parseDouble(scanner.nextLine());

        if (sistema.atualizarItem(id, nome, descricao, taxaDiaria, estado, valorReposicao)) {

            System.out.println("Item atualizado com sucesso");

        } else {

            System.out.println("Item não encontrado");

        }
    }
    public void desativarItem() {

        System.out.print("ID do item: ");

        int id = lerInteiro();

        if (sistema.excluirItem(id)) {

            System.out.println("Item desativado com sucesso");

        } else {

            System.out.println("Item não encontrado");

        }
    }
}