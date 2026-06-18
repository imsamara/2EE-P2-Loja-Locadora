import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ContratoAluguelNegocio {

    private CrudContratoAluguel contratoCrud;

    private double multaFixa = 10.0;
    
    private double percentualMulta = 0.05;

    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ContratoAluguelNegocio(CrudContratoAluguel contratoCrud) {
        this.contratoCrud = contratoCrud;
    }

    public ContratoAluguel criarContrato(Cliente cliente, Item item, String dataRetirada, String dataDevolucaoPrevista) {
        if (cliente == null) {
             System.out.println("Cliente inválido.");
             return null;
        }

        if (item == null) {
             System.out.println("Item inválido.");
             return null;
        }

        if (!itemDisponivel(item)) {
             System.out.println("Item indisponível para aluguel.");
             return null;
        }

        if (clienteEstaInadimplente(cliente)) {
             System.out.println("Cliente possui multas pendentes.");
             return null;
        }

        LocalDate retirada = LocalDate.parse(dataRetirada, formato);

        LocalDate devolucaoPrevista = LocalDate.parse(dataDevolucaoPrevista, formato);

        long diasAluguel = ChronoUnit.DAYS.between( retirada, devolucaoPrevista);

        if (diasAluguel <= 0) {
             System.out.println("A data de devolução deve ser posterior à data de retirada.");
             return null;
        }
        
        int novoId = contratoCrud.gerarProximoId();
        
        double valorTotal = item.getTaxaDiaria() * diasAluguel;

        ContratoAluguel contrato = new ContratoAluguel( novoId, cliente, item, dataRetirada, dataDevolucaoPrevista, null, valorTotal);

        contratoCrud.cadastrarContrato(contrato);

        item.setStatus("ALUGADO");

        System.out.println("Contrato criado com sucesso.");

        return contrato;
    }

    public double processarDevolucao(int contratoId, String dataDevolucaoReal) {

        ContratoAluguel contrato = contratoCrud.lerContrato(contratoId);
        if (contrato == null) {
            System.out.println("Contrato não encontrado.");
            return 0;
        }
        contrato.setDataDevolucaoReal(dataDevolucaoReal);

        if (!contrato.getStatus().equals("ATIVO")) {
            System.out.println("Contrato já finalizado ou cancelado.");
            return 0;
        }

        LocalDate dataPrevista = LocalDate.parse( contrato.getDataDevolucaoPrevista(), formato);

        LocalDate dataReal = LocalDate.parse(contrato.getDataDevolucaoReal(), formato);

        long diasAtraso = ChronoUnit.DAYS.between(dataPrevista, dataReal);

        double valorMulta = 0;

        if (diasAtraso > 0) {

            valorMulta = multaFixa + (contrato.getItem().getTaxaDiaria() * percentualMulta * diasAtraso);

            contrato.setValorMulta(valorMulta);

            System.out.println("Multa aplicada: R$ " + valorMulta);
        }

        contrato.finalizarContrato();
        
        contrato.getItem().setStatus("DISPONIVEL");

        contratoCrud.atualizarContrato(contrato);

        System.out.println("Devolução realizada com sucesso.");

        return valorMulta;
    }

    public boolean cancelarContrato(int contratoId) {

        ContratoAluguel contrato = contratoCrud.lerContrato(contratoId);

        if (contrato == null) {
            System.out.println("Contrato não encontrado.");
            


            return false;
        }

        if (!contrato.getStatus().equals("ATIVO")) {
            System.out.println("Somente contratos ativos podem ser cancelados.");
            
            return false;
        }

        contrato.cancelarContrato();

        contrato.getItem().setStatus("DISPONIVEL");
        
        contratoCrud.atualizarContrato(contrato);

        System.out.println("Contrato cancelado com sucesso.");
        return true;
    }

    public boolean itemDisponivel(Item item) {

        return item.getStatus().equals("DISPONIVEL");
    }

    // Implementar quando a classe Multa estiver pronta
    public boolean clienteEstaInadimplente(Cliente cliente) {
        return false;
    }
}