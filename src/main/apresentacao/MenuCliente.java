import java.util.Scanner;

public class MenuCliente {

private SistemaFacade sistema;
private Cliente cliente;
private Scanner scanner;

public MenuCliente(SistemaFacade sistema, Cliente cliente) {
    this.sistema = sistema;
    this.cliente = cliente;
    this.scanner = new Scanner(System.in);
}

public void exibir() {

    int opcao;

    do {

        System.out.println("\n=============");
        System.out.println("MENU CLIENTE");
        System.out.println("===============");
        System.out.println("Bem-vindo, " + cliente.getNome());
        System.out.println("1 - Ver itens disponíveis");
        System.out.println("2 - Meus aluguéis");
        System.out.println("3 - Histórico de aluguéis");
        System.out.println("4 - Consultar multas");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");

        opcao = lerInteiro();

        switch (opcao) {

            case 1:
                System.out.println("Funcionalidade em desenvolvimento.");
                break;

            case 2:
                System.out.println("Funcionalidade em desenvolvimento.");
                break;

            case 3:
                System.out.println("Funcionalidade em desenvolvimento.");
                break;

            case 4:
                System.out.println("Funcionalidade em desenvolvimento.");
                break;

            case 0:
                System.out.println("Saindo...");
                break;

            default:
                System.out.println("Opção inválida.");
        }

    } while (opcao != 0);
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
    return -1;
}

}