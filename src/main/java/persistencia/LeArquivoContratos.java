package persistencia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import repositories.*;
import entidades.*;

public class LeArquivoContratos {

    private String pasta;
    private IUsuarioRepositorio usuarioRepositorio;
    private IItemRepositorio itemRepositorio;

    public LeArquivoContratos(String pasta, IUsuarioRepositorio usuarioRepositorio, IItemRepositorio itemRepositorio) {
        this.pasta = pasta;
        this.usuarioRepositorio = usuarioRepositorio;
        this.itemRepositorio = itemRepositorio;
    }

    public List<ContratoAluguel> carregar() {

        List<ContratoAluguel> contratos = new ArrayList<>();

        File arquivo = new File(pasta, "contratos.csv");

        if (!arquivo.exists()) {

            return contratos;

        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {

                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                int idCliente = Integer.parseInt(dados[1]);
                int idItem = Integer.parseInt(dados[2]);

                Cliente cliente = (Cliente) usuarioRepositorio.buscarPorId(idCliente);

                Item item = itemRepositorio.buscarPorId(idItem);

                String dataRetirada = dados[3];
                String dataPrevista = dados[4];
                String dataReal = dados[5];

                double valorTotal = Double.parseDouble(dados[6]);

                ContratoAluguel contrato = new ContratoAluguel(id, cliente, item, dataRetirada, dataPrevista, valorTotal);

                if (dados.length > 9) {
                    contrato.setValorMulta(Double.parseDouble(dados[9]));

                }

                if ("FINALIZADO".equals(dados[7])) {

                    contrato.finalizarContrato();

                } else if ("CANCELADO".equals(dados[7])) {

                    contrato.cancelarContrato();

                }

                contratos.add(contrato);
            }

        } catch (Exception e) {

            System.out.println("Erro ao carregar contratos.");

        }

        return contratos;
    }
}