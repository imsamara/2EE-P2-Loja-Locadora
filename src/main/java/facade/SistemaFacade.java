package facade;
import java.util.List;
import business.interfaces.*;
import business.cruds.*;
import entidades.*;
import repositories.*;
public class SistemaFacade {

    private IGerenciamentoUsuarios gerenciamentoUsuarios;
    private IGerenciamentoContratos gerenciamentoContratos;

    public SistemaFacade(IGerenciamentoUsuarios gerenciamentoUsuarios, IGerenciamentoContratos gerenciamentoContratos) {
        this.gerenciamentoUsuarios = gerenciamentoUsuarios;
        this.gerenciamentoContratos = gerenciamentoContratos;
    }

    // =========================
    // USUÁRIOS
    // =========================

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

    public int gerarProximoIdUsuario() {
        return gerenciamentoUsuarios.gerarProximoId();
    }

    // =========================
    // CONTRATOS
    // =========================

    public boolean cadastrarContrato(ContratoAluguel contrato) {
        return gerenciamentoContratos.cadastrarContrato(contrato);
    }

    public ContratoAluguel buscarContrato(int id) {
        return gerenciamentoContratos.buscarContrato(id);
    }

    public List<ContratoAluguel> listarContratos() {
        return gerenciamentoContratos.listarContratos();
    }

    public boolean finalizarContrato(int id) {
        return gerenciamentoContratos.finalizarContrato(id);
    }

    public boolean cancelarContrato(int id) {
        return gerenciamentoContratos.cancelarContrato(id);
    }

    public int gerarProximoIdContrato() {
        return gerenciamentoContratos.gerarProximoId();
    }
}