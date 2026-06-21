package business.interfaces;
import java.util.List;

import entidades.ContratoAluguel;

public interface IGerenciamentoContratos {

    public abstract boolean cadastrarContrato(ContratoAluguel contrato);

    public abstract ContratoAluguel buscarContrato(int id);

    public abstract List<ContratoAluguel> listarContratos();

    public abstract boolean finalizarContrato(int id);

    public abstract boolean cancelarContrato(int id);

    public abstract int gerarProximoId();

}