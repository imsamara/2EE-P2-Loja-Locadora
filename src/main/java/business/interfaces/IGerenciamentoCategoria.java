package business.interfaces;

import java.util.List;
import entidades.Categoria;

public interface IGerenciamentoCategoria{
	public abstract boolean cadastrarCategoria(Categoria categoria);
	public abstract Categoria buscarCategoria(String nome);
	public abstract List<Categoria> listarCategorias();
	public abstract boolean atualizarCategoria(int idCategoria, String nome);
	public abstract boolean excluirCategoria(int idCategoria);
	public abstract int gerarProximoId();
}

