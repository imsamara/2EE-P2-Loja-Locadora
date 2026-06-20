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
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Usuários");
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

        int id = sistema.gerarProximoId();

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

        for (Usuario usuario : usuarios) {

            System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome() + " | Ativo: " + usuario.isAtivo());
        }
    }

    private int lerInteiro() {
        boolean valida = true;

        while (valida) {

            try {

                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (Exception e) {

                System.out.println("Digite apenas números.");
                scanner.nextLine();

            }
        }
        return -1;
    }
}