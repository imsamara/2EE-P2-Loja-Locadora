import java.util.List;
import java.util.Scanner;

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
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Cadastrar Administrador");
            System.out.println("3 - Listar Usuários");
            System.out.println("4 - Buscar Usuário");
            System.out.println("5 - Atualizar Usuário");
            System.out.println("6 - Desativar Usuário");
            System.out.println("7 - Buscar Contrato");
            System.out.println("8 - Listar Contratos");
            System.out.println("9 - Finalizar Contrato");
            System.out.println("10 - Cancelar Contrato");
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
                    buscarContrato();
                    break;

                case 8:
                    listarContratos();
                    break;

                case 9:
                    finalizarContrato();
                    break;

                case 10:
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

        if (sistema.excluirUsuario(id)) {

            System.out.println("Usuário desativado.");

        } else {

            System.out.println("Usuário não encontrado.");

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

    private void listarContratos() {

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