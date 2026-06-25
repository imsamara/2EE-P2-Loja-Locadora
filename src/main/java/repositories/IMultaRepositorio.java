package repositories;
import java.util.List;

import entidades.Multa;

public interface IMultaRepositorio {

    public abstract void gerar(Multa multa);

    public abstract List<Multa> listarTodas();

    public abstract Multa buscarPorNomeCliente(String nomeCliente);

    public abstract Multa buscarPorId(int idMulta);
    
    public abstract void remover(Multa multa);

    public abstract int gerarProximoId();

}
