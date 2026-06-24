package business.cruds;
import java.util.List;

import business.interfaces.IGerenciamentoContratos;
import entidades.ContratoAluguel;
import persistencia.SalvaArquivoContratos;
import repositories.IContratoRepositorio;

public class GerenciamentoContratos implements IGerenciamentoContratos {

    private IContratoRepositorio repositorio;
    private SalvaArquivoContratos salvaArquivo;

    public GerenciamentoContratos(IContratoRepositorio repositorio, SalvaArquivoContratos salvaArquivo) {

        this.repositorio = repositorio;
        this.salvaArquivo = salvaArquivo;
    }

    public void salvarDados() {

        salvaArquivo.salvar(repositorio.listarTodos());

    }

    @Override
    public int gerarProximoId() {
        return repositorio.gerarProximoId();
    }

    @Override
    public boolean cadastrarContrato(ContratoAluguel contrato) {
        
        if (repositorio.buscarPorId(
                contrato.getId()) != null) {

            return false;
        }

        
        if (repositorio.buscarContratoAtivoPorItem(
                contrato.getItem().getId()) != null) {

            return false;
        }

        repositorio.adicionar(contrato);

        salvarDados();

        return true;
    }

    @Override
    public ContratoAluguel buscarContrato(int id) {

        return repositorio.buscarPorId(id);

    }

    @Override
    public List<ContratoAluguel> listarContratos() {

        return repositorio.listarTodos();

    }

    @Override
    public boolean finalizarContrato(int id) {

        ContratoAluguel contrato = repositorio.buscarPorId(id);

        if (contrato == null) {
            return false;

        }

        contrato.finalizarContrato();
        
        salvarDados();

        return true;
    }

    @Override
    public boolean cancelarContrato(int id) {

        ContratoAluguel contrato = repositorio.buscarPorId(id);

        if (contrato == null) {
            return false;

        }

        contrato.cancelarContrato();

        salvarDados();

        return true;
    }
    
    @Override
    public boolean clientePossuiMultaPendente(int idCliente) {

        for (ContratoAluguel contrato : repositorio.listarTodos()) {

            if (contrato.getCliente().getId() == idCliente && contrato.getValorMulta() > 0 &&!contrato.isMultaPaga()) {

                return true;

            }
        }

        return false;
    }

    @Override
    public boolean clientePossuiHistorico(int idCliente) {

    for (ContratoAluguel contrato : repositorio.listarTodos()) {

        if (contrato.getCliente().getId() == idCliente) {

            return true;

        }

    }

    return false;

}

}