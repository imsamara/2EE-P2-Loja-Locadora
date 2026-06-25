package business.interfaces;

import java.util.List;
import entidades.Multa;

public interface IGerenciamentoMulta{
	public abstract boolean gerarMulta(Multa multa);
	public abstract Multa buscarMulta(String nomeCliente);
	public abstract List<Multa> listarMultas();
	public abstract boolean atualizarMulta(int idMulta, double valorMulta, String nomeCliente, String dataVencimento);
	public abstract boolean excluirMulta(int idMulta);
	public abstract int gerarProximoId();
}

