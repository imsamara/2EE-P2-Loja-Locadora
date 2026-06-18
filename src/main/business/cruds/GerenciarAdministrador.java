import java.util.ArrayList;
import java.util.List;

public class GerenciarAdministrador implements CrudAdministrador {

    private List<Administrador> administradores = new ArrayList<>();

    @Override
    public void cadastrarAdministrador(Administrador administrador) {
        administradores.add(administrador);
    }

    @Override
    public Administrador lerAdministrador(String cpf) {
        for (Administrador administrador : administradores) {
            if (administrador.getCpf().equals(cpf)) {
                return administrador;
            }
        }
        return null;
    }

    @Override
    public boolean atualizarAdministrador(Administrador administrador) {
        for (int i = 0; i < administradores.size(); i++) {
            if (administradores.get(i).getCpf().equals(administrador.getCpf())) {
                administradores.set(i, administrador);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletarAdministrador(String cpf) {
        for (int i = 0; i < administradores.size(); i++) {
            if (administradores.get(i).getCpf().equals(cpf)) {
                administradores.remove(i);
                return true;
            }
        }
        return false;
    }
}