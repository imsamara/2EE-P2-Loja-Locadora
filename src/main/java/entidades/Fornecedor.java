package entidades;
public class Fornecedor {

    private int id;
    private String razaoSocial;
    private String cnpj;
    private String email;
    private String telefone;
    private boolean statusAtivo;

    public Fornecedor(int id, String razaoSocial, String cnpj, String email, String telefone, boolean statusAtivo){

        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.statusAtivo = true;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public void desativar() {
        this.statusAtivo = false;
    }

    public void ativar() {
        this.statusAtivo = true;
    }
    public boolean estaDisponivel() {
        return statusAtivo;
    }

    
}
