package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entidades.Categoria;

public class SalvaArquivoCategorias {

    private String pasta;

    public SalvaArquivoCategorias(String pasta) {
        this.pasta = pasta;

        File pastaVazia = new File(pasta);

        if (!pastaVazia.exists()) {

            if (pastaVazia.mkdirs()) {

                System.out.println("Pasta '" + pasta + "' criada");

            }
        }
    }

    public void salvar(List<Categoria> categorias) {

        File arquivo = new File(pasta, "categorias.csv");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {

            for (Categoria categoria : categorias) {

                escritor.write(categoria.getId() + ";" + categoria.getNome() + ";" + categoria.getDescricao() + ";" + categoria.isAtiva());

                escritor.newLine();

            }

        } catch (IOException e) {

            System.out.println("Erro ao salvar categorias");

        }

    }

}