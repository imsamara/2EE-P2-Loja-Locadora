public interface CrudAdministrador {
    public void cadastrarAdministrador(Administrador administrador);
    public Administrador lerAdministrador(String cpf);
    public boolean atualizarAdministrador(Administrador administrador);
    public boolean deletarAdministrador(String cpf);

}
