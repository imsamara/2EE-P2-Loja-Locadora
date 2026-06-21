import java.util.ArrayList;
import java.util.List;

public class ContratoRepositorio implements IContratoRepositorio {

    private List<ContratoAluguel> contratos;

    public ContratoRepositorio(IUsuarioRepositorio usuarioRepositorio, IItemRepositorio itemRepositorio) {
        LeArquivoContratos leitor = new LeArquivoContratos("dados", usuarioRepositorio, itemRepositorio);
        contratos = leitor.carregar();

        if (contratos == null) {
            contratos = new ArrayList<>();
        }
    }

    @Override
    public void adicionar(ContratoAluguel contrato) {
        contratos.add(contrato);
    }

    @Override
    public List<ContratoAluguel> listarTodos() {
        return contratos;
    }

    @Override
    public ContratoAluguel buscarPorId(int id) {

        for (ContratoAluguel contrato : contratos) {

            if (contrato.getId() == id) {
                return contrato;
            }

        }

        return null;
    }

    @Override
    public void remover(ContratoAluguel contrato) {
        contratos.remove(contrato);
    }

    @Override
    public List<ContratoAluguel> buscarPorCliente(int idCliente) {

        List<ContratoAluguel> contratosCliente =
                new ArrayList<>();

        for (ContratoAluguel contrato : contratos) {

            if (contrato.getCliente().getId() == idCliente) {

                contratosCliente.add(contrato);

            }
        }

        return contratosCliente;
    }

    @Override
    public ContratoAluguel buscarContratoAtivoPorItem(int idItem) {

        for (ContratoAluguel contrato : contratos) {

            if (contrato.getItem().getId() == idItem
                    && contrato.estaAtivo()) {

                return contrato;
            }

        }

        return null;
    }

    @Override
    public int gerarProximoId() {

        if (contratos.isEmpty()) {
            return 1;
        }

        int maiorId = 0;

        for (ContratoAluguel contrato : contratos) {

            if (contrato.getId() > maiorId) {
                maiorId = contrato.getId();
            }
        }

        return maiorId + 1;
    }
}