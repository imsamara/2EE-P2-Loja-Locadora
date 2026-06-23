package facade;

import java.util.List;

import business.interfaces.IGerenciamentoContratos;
import business.interfaces.IGerenciamentoItens;
import business.interfaces.IGerenciamentoUsuarios;
import entidades.ContratoAluguel;
import entidades.Item;
import entidades.Usuario;

public class SistemaFacade {

    private IGerenciamentoUsuarios gerenciamentoUsuarios;
    private IGerenciamentoContratos gerenciamentoContratos;
    private IGerenciamentoItens gerenciamentoItens;

    public SistemaFacade(IGerenciamentoUsuarios gerenciamentoUsuarios, IGerenciamentoContratos gerenciamentoContratos, IGerenciamentoItens gerenciamentoItens) {

        this.gerenciamentoUsuarios = gerenciamentoUsuarios;
        this.gerenciamentoContratos = gerenciamentoContratos;
        this.gerenciamentoItens = gerenciamentoItens;
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

    // =========================
    // ITENS
    // =========================

    public boolean cadastrarItem(Item item) {
        return gerenciamentoItens.cadastrarItem(item);
    }

    public Item buscarItem(int id) {
        return gerenciamentoItens.buscarItem(id);
    }

    public List<Item> listarItens() {
        return gerenciamentoItens.listarItens();
    }

    public boolean atualizarItem(int id, String nome, String descricao, double taxaDiaria, String estadoConservacao, double valorReposicao) {
        return gerenciamentoItens.atualizarItem(id, nome, descricao, taxaDiaria, estadoConservacao, valorReposicao);
    }

    public boolean excluirItem(int id) {
        return gerenciamentoItens.excluirItem(id);
    }

    public int gerarProximoIdItem() {
        return gerenciamentoItens.gerarProximoId();
    }
}