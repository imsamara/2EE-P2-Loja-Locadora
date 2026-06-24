package repositories;

import java.util.List;

import entidades.Item;
import persistencia.LeArquivoItens;

public class ItemRepositorio implements IItemRepositorio {

    private List<Item> itens;

    public ItemRepositorio(ICategoriaRepositorio categoriaRepositorio, IFornecedorRepositorio fornecedorRepositorio) {

        LeArquivoItens leitor = new LeArquivoItens("dados", categoriaRepositorio, fornecedorRepositorio);

        itens = leitor.carregar();
    }

    @Override
    public void adicionar(Item item) {
        itens.add(item);
    }

    @Override
    public List<Item> listarTodos() {
        return itens;
    }

    @Override
    public Item buscarPorId(int id) {

        for (Item item : itens) {

            if (item.getId() == id) {
                return item;
            }

        }

        return null;
    }

    @Override
    public void remover(Item item) {
        itens.remove(item);
    }

    @Override
    public int gerarProximoId() {

        int maiorId = 0;

        for (Item item : itens) {

            if (item.getId() > maiorId) {
                maiorId = item.getId();
            }

        }

        return maiorId + 1;
    }
}