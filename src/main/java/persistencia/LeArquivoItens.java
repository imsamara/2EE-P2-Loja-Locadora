package persistencia;

import entidades.Categoria;
import entidades.Fornecedor;
import entidades.Item;
import repositories.ICategoriaRepositorio;
import repositories.IFornecedorRepositorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeArquivoItens {

    private String pasta;
    private ICategoriaRepositorio categoriaRepositorio;
    private IFornecedorRepositorio fornecedorRepositorio;

    public LeArquivoItens(String pasta, ICategoriaRepositorio categoriaRepositorio, IFornecedorRepositorio fornecedorRepositorio) {

        this.pasta = pasta;
        this.categoriaRepositorio = categoriaRepositorio;
        this.fornecedorRepositorio = fornecedorRepositorio;
    }

    public List<Item> carregar() {

        List<Item> itens = new ArrayList<>();

        File arquivo = new File(pasta + "/itens.csv");

        if (!arquivo.exists()) {

            return itens;

        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {

                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String descricao = dados[2];
                double taxaDiaria = Double.parseDouble(dados[3]);
                String estadoConservacao = dados[4];
                double valorReposicao = Double.parseDouble(dados[5]);

                int idCategoria = Integer.parseInt(dados[6]);
                int idFornecedor = Integer.parseInt(dados[7]);

                String status = dados[8];
                boolean ativo = Boolean.parseBoolean(dados[9]);

                Categoria categoria = categoriaRepositorio.buscarPorId(idCategoria);
                Fornecedor fornecedor = fornecedorRepositorio.buscarPorId(idFornecedor);

                Item item = new Item(id, nome, descricao, taxaDiaria, estadoConservacao, valorReposicao, categoria, fornecedor);

                item.setStatus(status);
                item.setAtivo(ativo);

                itens.add(item);
            }

        } catch (IOException e) {

            System.out.println("Erro ao carregar itens");

        }

        return itens;
    }
}