import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio implements IUsuarioRepositorio {

    private List<Usuario> usuarios;

    public UsuarioRepositorio() {

        LeArquivoUsuarios leitor = new LeArquivoUsuarios("dados");

        usuarios = leitor.carregar();

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
    }

    @Override
    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }


    @Override
    public void remover(Usuario usuario) {
        usuarios.remove(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }

        }
        return null;
    }
    @Override
    public Usuario buscarPorCpf(String cpf) {

        for (Usuario usuario : usuarios) {

            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }

        }

        return null;
    }

    @Override
    public Usuario buscarPorId(int id) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }

}
