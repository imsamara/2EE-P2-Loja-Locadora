import java.util.List;

public class GerenciamentoUsuarios implements IGerenciamentoUsuarios {

    private IUsuarioRepositorio repositorio;
    private SalvaArquivoUsuarios salvaArquivo;

    public GerenciamentoUsuarios(IUsuarioRepositorio repositorio, SalvaArquivoUsuarios salvaArquivo) {
        this.repositorio = repositorio;
        this.salvaArquivo = salvaArquivo;
    }

    public void salvarDados() {
        salvaArquivo.salvar(repositorio.listarTodos());
    }

    @Override
    public boolean cadastrarUsuario(Usuario usuario) {

        if (repositorio.buscarPorId(usuario.getId()) != null) {
            return false;
        }

        if (repositorio.buscarPorCpf(usuario.getCpf()) != null) {
            return false;
        }

        if (repositorio.buscarPorEmail(usuario.getEmail()) != null) {
            return false;
        }

        repositorio.adicionar(usuario);

        salvarDados();

        return true;
    }

    @Override
    public Usuario buscarUsuario(int id) {
        return repositorio.buscarPorId(id);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return repositorio.listarTodos();
    }

    @Override
    public boolean atualizarUsuario(int id, String nome, String email, String senha) {

        Usuario usuario = repositorio.buscarPorId(id);

        if (usuario == null) {
            return false;
        }

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        salvarDados();

        return true;
    }

    @Override
    public boolean excluirUsuario(int id) {

        Usuario usuario = repositorio.buscarPorId(id);

        if (usuario == null) {
            return false;
        }
        usuario.setAtivo(false);
        salvarDados();
        return true;
    }

    @Override
    public Usuario realizarLogin(String email, String senha) {

        Usuario usuario = repositorio.buscarPorEmail(email);

        if (usuario == null) {
            return null;
        }

        if (!usuario.isAtivo()) {
            return null;
        }

        if (!usuario.getSenha().equals(senha)) {
            return null;
        }

        return usuario;
    }
    @Override
    public int gerarProximoId() {
        return repositorio.gerarProximoId();
    }
}