package business.cruds;

import java.util.List;

import business.interfaces.IGerenciamentoFornecedor;
import entidades.Fornecedor;
import repositories.FornecedorRepositorio;

public class GerenciarFornecedor implements IGerenciamentoFornecedor {

    private FornecedorRepositorio repositorio;

    public GerenciarFornecedor(FornecedorRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public boolean cadastrarFornecedor(Fornecedor fornecedor) {

        if (repositorio.buscarPorId(fornecedor.getId()) != null) {
            return false;
        }

        if (repositorio.buscarPorCnpj(fornecedor.getCnpj()) != null) {
            return false;
        }

        repositorio.adicionar(fornecedor);

        return true;
    }

    @Override
    public Fornecedor buscarFornecedor(int id) {
        return repositorio.buscarPorId(id);
    }

    @Override
    public List<Fornecedor> listarFornecedores() {
        return repositorio.listarTodos();
    }

    @Override
    public boolean atualizarFornecedor(int id, String razaoSocial, String email, String telefone) {

        Fornecedor fornecedor = repositorio.buscarPorId(id);

        if (fornecedor == null) {
            return false;
        }

        fornecedor.setRazaoSocial(razaoSocial);
        fornecedor.setEmail(email);
        fornecedor.setTelefone(telefone);
        repositorio.salvar();

        return true;
    }

    @Override
    public boolean desativarFornecedor(int id) {

        Fornecedor fornecedor = repositorio.buscarPorId(id);

        if (fornecedor == null) {
            return false;
        }

        repositorio.desativar(id); // desativar já chama salvar()

        return true;
    }

    @Override
    public int gerarProximoId() {
        return repositorio.gerarProximoId();
    }
}