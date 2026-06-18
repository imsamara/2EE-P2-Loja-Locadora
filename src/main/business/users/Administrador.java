public class Administrador extends Usuario {

    public Administrador(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha);
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        // lógica para cadastrar funcionário
    }

    public void removerFuncionario(String cpf) {
        // lógica para remover funcionário
    }

    public void emitirRelatorioGeral() {
        // lógica para emitir relatório
    }

    public void gerenciarCatalogo() {
        // lógica para gerenciar itens
    }
}