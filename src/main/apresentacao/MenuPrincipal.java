import java.util.Scanner;

public class MenuPrincipal {

    private SistemaFacade sistema;
    private Scanner scanner;

    public MenuPrincipal(SistemaFacade sistema) {

        this.sistema = sistema;
        this.scanner = new Scanner(System.in);

    }

    public void exibir() {

        int opcao;

        do {

            System.out.println("\n=================================");
            System.out.println("     LOJA QUE ALUGA DE UM TUDO");
            System.out.println("=================================");
            System.out.println("1 - Login");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {

                case 1:
                    realizarLogin();
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void realizarLogin() {

        System.out.println("\n=== LOGIN ===");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = sistema.realizarLogin(email, senha);

        if (usuario == null) {

            System.out.println("\nEmail ou senha inválidos.");
            //ver como dar um "break" aqui com jackson

        }

        System.out.println("\nBem-vindo, " + usuario.getNome());

        if (usuario instanceof Cliente) {

            MenuCliente menuCliente = new MenuCliente(sistema, (Cliente) usuario);
            menuCliente.exibir();

        } else if (usuario instanceof Funcionario) {

            MenuFuncionario menuFuncionario = new MenuFuncionario(sistema, (Funcionario) usuario);
            menuFuncionario.exibir();

        } else if (usuario instanceof Administrador) {

            MenuAdministrador menuAdministrador = new MenuAdministrador(sistema, (Administrador) usuario);
            menuAdministrador.exibir();

        }
    }

    private int lerInteiro() {
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
    }
}