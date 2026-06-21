package apresentacao;
import java.util.List;
import java.util.Scanner;

import entidades.Cliente;
import entidades.ContratoAluguel;
import facade.SistemaFacade;
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

            System.out.println("\n===============");
            System.out.println(" MENU CLIENTE ");
            System.out.println("===============");
            System.out.println("Bem-vindo, " + cliente.getNome());
            System.out.println("1 - Ver itens disponíveis");
            System.out.println("2 - Meus aluguéis ativos");
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
                    listarAlugueisAtivos();
                    break;

                case 3:
                    listarHistorico();
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

    private void listarAlugueisAtivos() {

        List<ContratoAluguel> contratos = sistema.listarContratos();

        System.out.println("\n=== MEUS ALUGUÉIS ATIVOS ===");

        boolean encontrou = false;

        for (ContratoAluguel contrato : contratos) {

            if (contrato.getCliente().getId() == cliente.getId() && contrato.estaAtivo()) {

                System.out.println("Contrato: " + contrato.getId());
                System.out.println("Item: " + contrato.getItem().getNome());
                System.out.println("Retirada: " + contrato.getDataRetirada());
                System.out.println("Devolução prevista: " + contrato.getDataDevolucaoPrevista());
                System.out.println("--------------------------------");

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum aluguel ativo encontrado.");
        }
    }

    private void listarHistorico() {

        List<ContratoAluguel> contratos = sistema.listarContratos();

        System.out.println("\n=== HISTÓRICO DE ALUGUÉIS ===");

        boolean encontrou = false;

        for (ContratoAluguel contrato : contratos) {

            if (contrato.getCliente().getId() == cliente.getId()) {

                System.out.println("Contrato: " + contrato.getId());
                System.out.println("Item: " + contrato.getItem().getNome());
                System.out.println("Status: " + contrato.getStatus());
                System.out.println("Valor: R$ " + contrato.getValorTotal());
                System.out.println("--------------------------------");

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum contrato encontrado.");
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