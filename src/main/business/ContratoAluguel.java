
public class ContratoAluguel {

    private int id;
    private Cliente cliente;
    private Item item;
    private String dataRetirada;
    private String dataDevolucaoPrevista;
    private String dataDevolucaoReal;
    private double valorTotal;
    private String status;
    private boolean ativo;
    private double valorMulta;

    public ContratoAluguel(int id, Cliente cliente, Item item, String dataRetirada, String dataDevolucaoPrevista, String dataDevolucaoReal, double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.item = item;
        this.dataRetirada = dataRetirada;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.valorTotal = valorTotal;
        this.status = "ATIVO";
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Item getItem() {
        return item;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public String getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(String dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    public String getDataDevolucaoReal(){
        return dataDevolucaoReal;
    }
    public void setDataDevolucaoReal(String dataDevolucaoReal){
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getStatus() {
        return status;
    }
    public boolean getAtivo() {
        return ativo;
    }
    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }

    public void finalizarContrato() {
        this.status = "FINALIZADO";
        this.ativo = false;
    }
    public void cancelarContrato() {
        this.ativo = false;
        this.status = "CANCELADO";
    }

}