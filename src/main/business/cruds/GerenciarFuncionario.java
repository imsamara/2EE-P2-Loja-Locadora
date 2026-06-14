import java.util.ArrayList;
import java.util.List;

public class GerenciarFuncionario implements CrudFuncionario {
    private List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void cadastrarFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);   
    }

    @Override
    public Funcionario lerFuncionario(String cpf){
        for (Funcionario funcionario: funcionarios){
            if(funcionario.getCpf().equals(cpf)){
                return funcionario;
            }
        }
        return null;
    }

    @Override
    public boolean atualizarFuncionario(Funcionario funcionario){
        for (int i = 0 ; i < funcionarios.size() ; i ++){
            if(funcionarios.get(i).getCpf().equals(funcionario.getCpf())){
                funcionarios.set(i, funcionario);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletarFuncionario(String cpf){
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getCpf().equals(cpf)) {
                funcionarios.remove(i);
                return true;
            }
        }
        return false;
    }



}
