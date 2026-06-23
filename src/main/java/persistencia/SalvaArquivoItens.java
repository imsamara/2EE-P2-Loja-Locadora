package persistencia;

import entidades.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalvaArquivoItens {

    private String pasta;

    public SalvaArquivoItens(String pasta) {

        this.pasta = pasta;

        File pastaDados = new File(pasta);

        if (!pastaDados.exists()) {

            if (pastaDados.mkdirs()) {

                System.out.println("Pasta criada");

            }
        }
    }

    public void salvar(List<Item> itens) {

        File arquivo = new File(pasta + "/itens.csv");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {

            for (Item item : itens) {

                escritor.write(item.getId() + ";" + item.getNome() + ";" + item.getDescricao() + ";" + item.getTaxaDiaria() + ";" + item.getEstadoConservacao() + ";" + item.getValorReposicao() + ";" + item.getCategoria().getId() + ";" + item.getFornecedor().getId() + ";" + item.getStatus() + ";" + item.isAtivo());

                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println("Erro ao salvar itens");

        }
    }
}