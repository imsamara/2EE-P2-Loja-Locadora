public interface IContratoAluguel {
    public void cadastrarContrato(ContratoAluguel contrato);
    public ContratoAluguel lerContrato(int id);
    public boolean atualizarContrato(ContratoAluguel contrato);
    public boolean cancelarContrato(int id);
    public int gerarProximoId();

}

