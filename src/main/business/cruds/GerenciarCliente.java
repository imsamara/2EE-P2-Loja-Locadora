import java.util.ArrayList;
import java.util.List;

public class GerenciarCliente implements CrudCliente {

    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public void cadastrarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    @Override
    public Cliente lerCliente(String cpf){
        for (Cliente cliente: clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }
    @Override
    public boolean atualizarCliente(Cliente cliente){
        for (int i = 0 ; i < clientes.size() ; i ++){
            if(clientes.get(i).getCpf().equals(cliente.getCpf())){
                clientes.set(i, cliente);
                return true;
            }

        }
        return false;
    }
    @Override
    public boolean deletarCliente(String cpf){
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }
}
