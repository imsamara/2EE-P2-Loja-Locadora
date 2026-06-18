public interface CrudFuncionario {
    public abstract void cadastrarFuncionario(Funcionario funcionario);
    public abstract Funcionario lerFuncionario(String cpf);
    public abstract boolean atualizarFuncionario(Funcionario funcionario);
    public abstract boolean deletarFuncionario(String cpf);

}
