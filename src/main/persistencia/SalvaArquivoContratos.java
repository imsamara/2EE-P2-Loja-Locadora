import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalvaArquivoContratos {

    private String pasta;

    public SalvaArquivoContratos(String pasta) {

        this.pasta = pasta;

        File pastaVazia = new File(pasta);

        if (!pastaVazia.exists()) {

            if (pastaVazia.mkdirs()) {

                System.out.println("Pasta '" + pasta + "' criada.");

            }
        }
    }

    public void salvar(List<ContratoAluguel> contratos) {

        File arquivo = new File(pasta, "contratos.csv");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {

            for (ContratoAluguel contrato : contratos) {

                escritor.write(contrato.getId() + ";" + contrato.getCliente().getId() + ";" + contrato.getItem().getId() + ";" + contrato.getDataRetirada() + ";" + contrato.getDataDevolucaoPrevista() + ";" + contrato.getDataDevolucaoReal() + ";" + contrato.getValorTotal() + ";" + contrato.getStatus() + ";" + contrato.getAtivo() + ";" + contrato.getValorMulta());

                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println("Erro ao salvar contratos.");

        }
    }
}