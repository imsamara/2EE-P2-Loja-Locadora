public class Funcionario extends Usuario {

    public Funcionario(String nome, String email, String cpf, String senha){
        super(nome, email, cpf, senha);
    }
      public void cadastrarCliente(Cliente cliente) {
        // lógica para cadastrar cliente
    }

    public void registrarAluguel(Aluguel aluguel) {
        // lógica para registrar aluguel
    }

    public void processarDevolucao(Aluguel aluguel) {
        // lógica para processar devolução
    }

    public void aplicarMulta(Aluguel aluguel, double valor) {
        // lógica para aplicar multa
    }

    public void emitirRelatorioOperacional() {
        // lógica para gerar relatório
    }
}
    

