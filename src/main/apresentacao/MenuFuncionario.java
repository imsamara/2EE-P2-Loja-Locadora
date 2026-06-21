import java.util.List;
import java.util.Scanner;

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
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Registrar Aluguel");
            System.out.println("4 - Buscar Contrato");
            System.out.println("5 - Listar Contratos");
            System.out.println("6 - Finalizar Contrato");
            System.out.println("7 - Cancelar Contrato");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {

                case 1:
                    cadastrarCliente();
                    break;

                case 2:
                    listarUsuarios();
                    break;

                case 3:
                    registrarAluguel();
                    break;

                case 4:
                    buscarContrato();
                    break;

                case 5:
                    listarContratos();
                    break;

                case 6:
                    finalizarContrato();
                    break;

                case 7:
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

    private void cadastrarCliente() {

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

            System.out.println("Cliente cadastrado com sucesso.");
            System.out.println("ID gerado: " + id);

        } else {

            System.out.println("Erro ao cadastrar cliente.");

        }
    }

    private void listarUsuarios() {

        List<Usuario> usuarios = sistema.listarUsuarios();

        System.out.println("\n===== USUÁRIOS =====");

        if (usuarios.isEmpty()) {

            System.out.println("Nenhum usuário cadastrado.");
            //ver como quebrar o loop com jackson

        }

        for (Usuario usuario : usuarios) {

            System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail() + " | Ativo: " + usuario.isAtivo());
        }
    }

    private void registrarAluguel() {

        System.out.println("\n=== REGISTRAR ALUGUEL ===");
        System.out.println("Funcionalidade será implementada após o CRUD de Itens.");
    }

    private void buscarContrato() {

        System.out.print("ID do contrato: ");

        int id = lerInteiro();

        ContratoAluguel contrato = sistema.buscarContrato(id);

        if (contrato == null) {

            System.out.println("Contrato não encontrado.");
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

    private void listarContratos() {

        List<ContratoAluguel> contratos = sistema.listarContratos();

        System.out.println("\n===== CONTRATOS =====");

        if (contratos.isEmpty()) {

            System.out.println("Nenhum contrato cadastrado.");
            //perguntar a jackson novamente para quebrar o loop

        }

        for (ContratoAluguel contrato : contratos) {

            System.out.println("ID: " + contrato.getId() + " | Cliente: " + contrato.getCliente().getNome() + " | Item: " + contrato.getItem().getNome() + " | Status: " + contrato.getStatus());
        }
    }

    private void finalizarContrato() {

        System.out.print("ID do contrato: ");

        int id = lerInteiro();

        if (sistema.finalizarContrato(id)) {

            System.out.println("Contrato finalizado com sucesso.");

        } else {

            System.out.println("Contrato não encontrado.");

        }
    }

    private void cancelarContrato() {

        System.out.print("ID do contrato: ");

        int id = lerInteiro();

        if (sistema.cancelarContrato(id)) {

            System.out.println("Contrato cancelado com sucesso.");

        } else {

            System.out.println("Contrato não encontrado.");

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
}