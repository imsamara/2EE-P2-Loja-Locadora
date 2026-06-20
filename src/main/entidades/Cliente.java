public class Cliente extends Usuario {

    public Cliente(String nome, String email, String cpf, String senha) {

        super(nome, email, cpf, senha);
    }
    @Override
    public String getTipo() {
        return "CLIENTE";
    }

}