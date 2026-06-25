package repositories;

import java.util.ArrayList;
import java.util.List;

import entidades.Categoria;
import persistencia.LeArquivoCategorias;

public class CategoriaRepositorio implements ICategoriaRepositorio {

    private List<Categoria> categorias;

    public CategoriaRepositorio() {

        LeArquivoCategorias leitor = new LeArquivoCategorias("dados");

        categorias = leitor.carregar();

        if (categorias == null) {

            categorias = new ArrayList<>();

        }
    }

    @Override
    public void adicionar(Categoria categoria) {
        categorias.add(categoria);
    }

    @Override
    public List<Categoria> listarTodos() {
        return categorias;

    }

    @Override
    public Categoria buscarPorId(int id) {

        for (Categoria categoria : categorias) {

            if (categoria.getId() == id) {

                return categoria;

            }

        }

        return null;
    }

    @Override
    public Categoria buscarPorNome(String nome) {

        for (Categoria categoria : categorias) {

            if (categoria.getNome()
                    .equalsIgnoreCase(nome)) {

                return categoria;

            }

        }

        return null;
    }

    @Override
    public int gerarProximoId() {

        int maiorId = 0;

        for (Categoria categoria : categorias) {

            if (categoria.getId() > maiorId) {

                maiorId = categoria.getId();

            }

        }

        return maiorId + 1;
    }
}