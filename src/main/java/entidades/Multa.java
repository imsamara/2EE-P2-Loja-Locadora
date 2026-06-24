package entidades;

public class Multa {
	private int idMulta;
	private double valorMulta;
	private String nomeCliente;
	private String dataVencimento;
	private boolean statusPendencia;
	
	public Multa (int idMulta, double valorMulta, String nomeCliente, String dataVencimento, boolean statusPendecia) {
		this.idMulta = idMulta;
		this.valorMulta = valorMulta;
		this.nomeCliente = nomeCliente;
		this.dataVencimento = dataVencimento;
		this.statusPendencia = true;
	}

	public int getIdMulta() {
		return idMulta;
	}

	public void setIdMulta(int idMulta) {
		this.idMulta = idMulta;
	}

	public double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void pendenciaAtivada() {
		this.statusPendencia = true;
	}
	
	public void pendenciaDesativada() {
		this.statusPendencia = false;
	}
	
	public boolean isStatusPendencia() {
		return statusPendencia;
	}
	
}
