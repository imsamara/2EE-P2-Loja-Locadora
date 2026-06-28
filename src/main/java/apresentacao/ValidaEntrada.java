package apresentacao;

import java.util.Scanner;


public class ValidaEntrada {

    public static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite apenas números: ");
            }
        }
    }

    public static double lerDouble(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor numérico válido: ");
            }
        }
        return -1;
    }

    public static String lerData(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            String entrada = scanner.nextLine().trim();
            if (entrada.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return entrada;
            }
            System.out.print("Formato inválido. Use AAAA-MM-DD: ");
        }
        return "";
    }
}