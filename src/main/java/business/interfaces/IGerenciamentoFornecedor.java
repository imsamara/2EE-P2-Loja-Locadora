package business.interfaces;
import java.util.List;

import entidades.Fornecedor;

public interface IGerenciamentoFornecedor{

    public abstract boolean cadastrarFornecedor();
    public abstract Fornecedor buscarFornecedor(int id);
    public abstract List<Fornecedores> listarFornecedores();
    public abstract boolean atualizarFornecedor(int id);
    public abstract boolean desativarFornecedor(int id);
    public abstract boolean excluirFornecedor();

}