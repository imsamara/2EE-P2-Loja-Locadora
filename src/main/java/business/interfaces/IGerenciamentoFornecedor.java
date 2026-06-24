package business.interfaces;
import java.util.List;

import entidades.Fornecedor;

public interface IGerenciamentoFornecedor{

    public abstract boolean cadastrarFornecedor(Fornecedor fornecedor);
    public abstract Fornecedor buscarFornecedor(int id);
    public abstract List<Fornecedor> listarFornecedores();
    public abstract boolean atualizarFornecedor(int id, String razaoSocial, String email, String telefone);
    public abstract boolean desativarFornecedor(int id);
    public abstract int gerarProximoId();
    

}