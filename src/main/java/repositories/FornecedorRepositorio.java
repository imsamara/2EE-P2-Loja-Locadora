package repositories;

import java.util.ArrayList;

import java.util.List;

import entidades.Fornecedor;
import persistencia.LeArquivoFornecedor;


public class FornecedorRepositorio implements IFornecedorRepositorio {

    private List<Fornecedor> fornecedores;

    public FornecedorRepositorio() {
        LeArquivoFornecedor leitor = new LeArquivoFornecedor("fornecedores.csv");
        fornecedores = leitor.carregar();

        if (fornecedores == null) {
            fornecedores = new ArrayList<>();
        }
    }

    @Override
    public void adicionar(Fornecedor fornecedor){
        fornecedores.add(fornecedor);
    }

    @Override
    public List<Fornecedor> listarTodos(){
        return fornecedores;
    }

    @Override
    public void desativar(int id){
        Fornecedor fornecedor = buscarPorId(id);

        if (fornecedor != null){
            fornecedor.desativar();
        }
    }


    @Override
    public Fornecedor buscarPorCnpj(String cnpj){
        for (Fornecedor fornecedor : fornecedores){
            if (fornecedor.getCnpj().trim().equals(cnpj.trim())){
                return fornecedor;
            }
        }
        return null;
    }

    @Override
    public Fornecedor buscarPorId(int id){
        for (Fornecedor fornecedor : fornecedores){
            if (fornecedor.getId() == id){
                return fornecedor;
            }
        }
        return null;
    }

    @Override
    public int gerarProximoId(){
        if (fornecedores.isEmpty()){
            return 1;
        }
        int maiorId = 0;

        for (Fornecedor fornecedor : fornecedores){
            if (fornecedor.getId() > maiorId){
                maiorId = fornecedor.getId();
            }
        }
        return maiorId + 1;
    }

}
