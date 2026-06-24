package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import entidades.Categoria;

public class LeArquivoCategorias {

    private String pasta;

    public LeArquivoCategorias(String pasta) {
        this.pasta = pasta;
    }

    public List<Categoria> carregar() {

        List<Categoria> categorias = new ArrayList<>();

        File arquivo = new File(pasta, "categorias.csv");

        if (!arquivo.exists()) {
            return categorias;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String descricao = dados[2];
                boolean ativa = Boolean.parseBoolean(dados[3]);

                Categoria categoria = new Categoria(id, nome, descricao, ativa);

                categorias.add(categoria);

            }

        } catch (Exception e) {

            System.out.println("Erro ao carregar categorias");

        }

        return categorias;

    }

}