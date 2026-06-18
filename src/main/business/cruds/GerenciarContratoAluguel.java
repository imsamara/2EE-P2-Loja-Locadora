import java.util.ArrayList;
import java.util.List;

public class GerenciarContratoAluguel implements CrudContratoAluguel {

    private List<ContratoAluguel> contratos = new ArrayList<>();

    @Override
    public int gerarProximoId() {
        if (contratos.isEmpty()) {
            return 1;
        }

        int maiorId = contratos.get(0).getId();

        for (ContratoAluguel contrato : contratos) {

            if (contrato.getId() > maiorId) {
                maiorId = contrato.getId();
            }
        }

        return maiorId + 1;
    }

    @Override
    public void cadastrarContrato(ContratoAluguel contrato) {
        contratos.add(contrato);
    }

    @Override
    public ContratoAluguel lerContrato(int id) {
        for (ContratoAluguel contrato : contratos) {
            if (contrato.getId() == id) {
                return contrato;
            }
        }
        return null;
    }

    @Override
    public boolean atualizarContrato(ContratoAluguel contratoAtualizado) {
        for (int i = 0; i < contratos.size(); i++) {
            if (contratos.get(i).getId() == contratoAtualizado.getId()) {
                contratos.set(i, contratoAtualizado);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cancelarContrato(int id) {
        for (ContratoAluguel contrato : contratos) {
            if (contrato.getId() == id) {
                contrato.cancelarContrato();
                return true;
            }
        }
        return false;
    }


    public List<ContratoAluguel> listarContratos() {
        //nao sei se vai precisar formatar lembrar de testar isso
        return contratos;
    }

}
