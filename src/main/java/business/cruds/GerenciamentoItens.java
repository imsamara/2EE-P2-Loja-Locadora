package business.cruds;

import java.util.List;

import business.interfaces.IGerenciamentoItens;
import entidades.Item;
import repositories.ItemRepositorio;

public class GerenciamentoItens implements IGerenciamentoItens {

    private ItemRepositorio repositorio;

    public GerenciamentoItens(ItemRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public boolean cadastrarItem(Item item) {

        if (repositorio.buscarPorId(item.getId()) != null) {
            return false;
        }

        repositorio.adicionar(item);

        return true;
    }

    @Override
    public Item buscarItem(int id) {
        return repositorio.buscarPorId(id);
    }

    @Override
    public List<Item> listarItens() {
        return repositorio.listarTodos();
    }

    @Override
    public boolean atualizarItem(int id, String nome, String descricao,
            double taxaDiaria, String estadoConservacao, double valorReposicao) {

        Item item = repositorio.buscarPorId(id);

        if (item == null) {
            return false;
        }

        item.setNome(nome);
        item.setDescricao(descricao);
        item.setTaxaDiaria(taxaDiaria);
        item.setEstadoConservacao(estadoConservacao);
        item.setValorReposicao(valorReposicao);

        repositorio.salvar();

        return true;
    }

    @Override
    public boolean excluirItem(int id) {

        Item item = repositorio.buscarPorId(id);

        if (item == null) {
            return false;
        }

        item.desativar();

        repositorio.salvar();

        return true;
    }

    @Override
    public int gerarProximoId() {
        return repositorio.gerarProximoId();
    }
}