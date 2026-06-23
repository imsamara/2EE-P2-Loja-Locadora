package business.cruds;

import java.util.List;

import business.interfaces.IGerenciamentoItens;
import entidades.Item;
import persistencia.SalvaArquivoItens;
import repositories.IItemRepositorio;

public class GerenciamentoItens implements IGerenciamentoItens {

    private IItemRepositorio repositorio;
    private SalvaArquivoItens salvaArquivo;

    public GerenciamentoItens(IItemRepositorio repositorio, SalvaArquivoItens salvaArquivo) {

        this.repositorio = repositorio;
        this.salvaArquivo = salvaArquivo;

    }

    private void salvarDados() {

        salvaArquivo.salvar(repositorio.listarTodos());

    }

    @Override
    public boolean cadastrarItem(Item item) {

        if (repositorio.buscarPorId(item.getId()) != null) {

            return false;

        }

        repositorio.adicionar(item);

        salvarDados();

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
    public boolean atualizarItem(int id, String nome, String descricao, double taxaDiaria, String estadoConservacao, double valorReposicao) {

        Item item = repositorio.buscarPorId(id);

        if (item == null) {

            return false;

        }

        item.setNome(nome);
        item.setDescricao(descricao);
        item.setTaxaDiaria(taxaDiaria);
        item.setEstadoConservacao(estadoConservacao);
        item.setValorReposicao(valorReposicao);

        salvarDados();

        return true;
    }

    @Override
    public boolean excluirItem(int id) {

        Item item = repositorio.buscarPorId(id);

        if (item == null) {

            return false;

        }

        item.desativar();

        salvarDados();

        return true;
    }

    @Override
    public int gerarProximoId() {

        return repositorio.gerarProximoId();

    }
}