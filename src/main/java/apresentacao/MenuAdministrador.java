package apresentacao;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import entidades.Administrador;
import entidades.ContratoAluguel;
import entidades.Funcionario;
import entidades.Item;
import entidades.Usuario;
import facade.SistemaFacade;
public class MenuAdministrador {

    private SistemaFacade sistema;
    private Administrador administrador;
    private Scanner scanner;

    public MenuAdministrador(SistemaFacade sistema, Administrador administrador) {
        this.sistema = sistema;
        this.administrador = administrador;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {

        int opcao;

        do {

            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("Bem-vindo, " + administrador.getNome());

            System.out.println("========== USUÁRIOS ==========");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Cadastrar Administrador");
            System.out.println("3 - Listar Usuários");
            System.out.println("4 - Buscar Usuário");
            System.out.println("5 - Atualizar Usuário");
            System.out.println("6 - Desativar Usuário");

            System.out.println("=========== ITENS ============");
            System.out.println("7 - Cadastrar Item");
            System.out.println("8 - Buscar Item");
            System.out.println("9 - Listar Itens");
            System.out.println("10 - Atualizar Item");
            System.out.println("11 - Desativar Item");

            System.out.println("========= CONTRATOS ==========");
            System.out.println("12 - Buscar Contrato");
            System.out.println("13 - Listar Contratos");
            System.out.println("14 - Finalizar Contrato");
            System.out.println("15 - Cancelar Contrato");

            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarFuncionario();
                    break;

                case 2:
                    cadastrarAdministrador();
                    break;

                case 3:
                    listarUsuarios();
                    break;

                case 4:
                    buscarUsuario();
                    break;

                case 5:
                    atualizarUsuario();
                    break;

                case 6:
                    desativarUsuario();
                    break;

                case 7:
                    cadastrarItem();
                    break;

                case 8:
                    buscarItem();
                    break;

                case 9:
                    listarItens();
                    break;

                case 10:
                    atualizarItem();
                    break;

                case 11:
                    desativarItem();
                    break;

                case 12:
                    buscarContrato();
                    break;

                case 13:
                    listarContratos();
                    break;

                case 14:
                    finalizarContrato();
                    break;

                case 15:
                    cancelarContrato();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void cadastrarFuncionario() {

        System.out.println("\n=== CADASTRAR FUNCIONÁRIO ===");

        int id = sistema.gerarProximoIdUsuario();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Funcionario funcionario = new Funcionario(id, nome, email, cpf, senha);

        if (sistema.cadastrarUsuario(funcionario)) {

            System.out.println("Funcionário cadastrado com sucesso.");
            System.out.println("ID gerado: " + id);

        } else {

            System.out.println("Erro ao cadastrar funcionário.");

        }
    }

    private void cadastrarAdministrador() {

        System.out.println("\n=== CADASTRAR ADMINISTRADOR ===");

        int id = sistema.gerarProximoIdUsuario();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Administrador administrador = new Administrador(id, nome, email, cpf, senha);

        if (sistema.cadastrarUsuario(administrador)) {

            System.out.println("Administrador cadastrado com sucesso.");
            System.out.println("ID gerado: " + id);

        } else {

            System.out.println("Erro ao cadastrar administrador.");

        }
    }

    private void listarUsuarios() {

        List<Usuario> usuarios = sistema.listarUsuarios();

        System.out.println("\n===== USUÁRIOS =====");

        if (usuarios.isEmpty()) {

            System.out.println("Nenhum usuário cadastrado.");
            //mesma coisa, perguntar a jackson a quebra de loop

        }

        for (Usuario usuario : usuarios) {

            System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail() + " | Ativo: " + usuario.isAtivo());
        }
    }

    private void buscarUsuario() {

        System.out.print("ID: ");

        int id = lerInteiro();

        Usuario usuario = sistema.buscarUsuario(id);

        if (usuario == null) {

            System.out.println("Usuário não encontrado.");
            return;

        }

        System.out.println("\n===== DADOS DO USUÁRIO =====");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Ativo: " + usuario.isAtivo());
    }

    private void atualizarUsuario() {

        System.out.print("ID do usuário: ");
        int id = lerInteiro();

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo email: ");
        String email = scanner.nextLine();

        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();

        if (sistema.atualizarUsuario(id, nome, email, senha)) {

            System.out.println("Usuário atualizado.");

        } else {

            System.out.println("Usuário não encontrado.");

        }
    }

    private void desativarUsuario() {

        System.out.print("ID do usuário: ");

        int id = lerInteiro();

        if (sistema.desativaUsuario(id)) {

            System.out.println("Usuário desativado");

        } else {

            System.out.println("Usuário não encontrado");

        }
    }

    private void buscarContrato() {

        System.out.print("ID do contrato: ");

        int id = lerInteiro();

        ContratoAluguel contrato = sistema.buscarContrato(id);

        if (contrato == null) {

            System.out.println("Contrato não encontrado.");
            return;

        }

        System.out.println("\n===== CONTRATO =====");
        System.out.println("ID: " + contrato.getId());
        System.out.println("Cliente: " + contrato.getCliente().getNome());
        System.out.println("Item: " + contrato.getItem().getNome());
        System.out.println("Status: " + contrato.getStatus());
        System.out.println("Data retirada: " + contrato.getDataRetirada());
        System.out.println("Data devolução prevista: " + contrato.getDataDevolucaoPrevista());
        System.out.println("Valor total: R$ " + contrato.getValorTotal());
    }

    public void listarContratos() {

        List<ContratoAluguel> contratos = sistema.listarContratos();

        System.out.println("\n===== CONTRATOS =====");

        if (contratos.isEmpty()) {

            System.out.println("Nenhum contrato cadastrado.");
            return;

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
            return; //perguntar se pode

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
            return; //perguntar se pode

        }

        contrato.setDataDevolucaoReal(dataDevolucaoReal);

        long diasAtraso =
                ChronoUnit.DAYS.between(dataPrevista,dataReal);

        if (diasAtraso > 0) {

            double multaFixa = 10.0;

            double multaPercentual =
                    diasAtraso * (contrato.getItem().getTaxaDiaria() * 0.05);

            double multa =multaFixa + multaPercentual;

            contrato.setValorMulta(multa);

            
            contrato.setMultaPaga(false);

            System.out.println("Dias de atraso: " + diasAtraso);

            System.out.println("Multa fixa: R$ " + multaFixa);

            System.out.println("Multa percentual: R$ "+ multaPercentual);

            System.out.println("Multa total: R$ " + multa);

        }

        contrato.getItem().devolver();

        if (sistema.finalizarContrato(id)) {

            System.out.println("Contrato finalizado com sucesso");

            System.out.println( "Item devolvido ao estoque");

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

            System.out.println("ID: " + item.getId()
                    + " | Nome: " + item.getNome()
                    + " | Status: " + item.getStatus()
                    + " | Ativo: " + item.isAtivo());
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
    public void menuRelatorios() {

        int opcao;

        do {

            System.out.println("\n===== RELATÓRIOS =====");
            System.out.println("1 - Itens disponíveis por categoria");
            System.out.println("2 - Histórico de aluguéis por cliente");
            System.out.println("3 - Itens alugados atualmente");
            System.out.println("4 - Relatório de faturamento");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {

                case 1:

                    sistema.gerarRelatorioItensDisponiveis();
                    System.out.println("Relatório gerado com sucesso");
                    break;

                case 2:

                    System.out.print("ID do cliente: ");
                    int idCliente = lerInteiro();

                    sistema.gerarHistoricoCliente(idCliente);

                    System.out.println("Relatório gerado com sucesso");
                    break;

                case 3:

                    sistema.gerarRelatorioItensAlugados();

                    System.out.println("Relatório gerado com sucesso");
                    break;

                case 4:

                    System.out.print("Data inicial (AAAA-MM-DD): ");
                    String dataInicial = scanner.nextLine();

                    System.out.print("Data final (AAAA-MM-DD): ");
                    String dataFinal = scanner.nextLine();

                    sistema.gerarRelatorioFaturamento(
                            dataInicial,
                            dataFinal);

                    System.out.println("Relatório gerado com sucesso");
                    break;

                case 0:

                    System.out.println("Retornando...");
                    break;

                default:

                    System.out.println("Opção inválida");

            }

        } while (opcao != 0);

    }
}