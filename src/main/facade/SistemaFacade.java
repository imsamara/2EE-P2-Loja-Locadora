import java.util.List;

public class SistemaFacade {
    private IGerenciamentoUsuarios gerenciamentoUsuarios;

//===============
//Parte dos usuarios
//===============

    public SistemaFacade(IGerenciamentoUsuarios gerenciamentoUsuarios) {
        this.gerenciamentoUsuarios = gerenciamentoUsuarios;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        return gerenciamentoUsuarios.cadastrarUsuario(usuario);
    }

    public Usuario buscarUsuario(int id) {
        return gerenciamentoUsuarios.buscarUsuario(id);
    }

    public List<Usuario> listarUsuarios() {
        return gerenciamentoUsuarios.listarUsuarios();
    }

    public boolean atualizarUsuario(int id, String nome, String email, String senha) {
        return gerenciamentoUsuarios.atualizarUsuario(id, nome, email, senha);
    }

    public boolean excluirUsuario(int id) {
        return gerenciamentoUsuarios.excluirUsuario(id);
    }

    public Usuario realizarLogin(String email, String senha) {
        return gerenciamentoUsuarios.realizarLogin(email, senha);
    }

    public int gerarProximoId() {
        return gerenciamentoUsuarios.gerarProximoId();
    }
    //lembrar de alterar o construtor quando fizer as proximas coisas.
    //colocar o resto das coisas aqui
}
