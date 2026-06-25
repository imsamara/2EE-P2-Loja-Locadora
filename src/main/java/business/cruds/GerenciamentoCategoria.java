package business.cruds;
 
import java.util.List;
 
import business.interfaces.IGerenciamentoCategoria;
import entidades.Categoria;
import persistencia.SalvaArquivoCategorias;
import repositories.ICategoriaRepositorio;
 
public class GerenciamentoCategoria implements IGerenciamentoCategoria {
 
    private ICategoriaRepositorio repositorio;
    private SalvaArquivoCategorias salvaArquivo;
 
    public GerenciamentoCategoria(ICategoriaRepositorio repositorio, SalvaArquivoCategorias salvaArquivo) {
 
        this.repositorio = repositorio;
        this.salvaArquivo = salvaArquivo;
 
    }
 
    private void salvarDados() {
 
        salvaArquivo.salvar(repositorio.listarTodos());
 
    }
 
    @Override
    public boolean cadastrarCategoria(Categoria categoria) {
 
        if (repositorio.buscarPorId(categoria.getId()) != null) {
 
            return false;
 
        }
 
        if (repositorio.buscarPorNome(categoria.getNome()) != null) {
 
            return false;
 
        }
 
        repositorio.adicionar(categoria);
 
        salvarDados();
 
        return true;
    }
 
    @Override
    public Categoria buscarCategoria(int id) {
 
        return repositorio.buscarPorId(id);
 
    }
 
    @Override
    public List<Categoria> listarCategorias() {
 
        return repositorio.listarTodos();
 
    }
 
    @Override
    public boolean atualizarCategoria(int id, String nome, String descricao) {
 
        Categoria categoria = repositorio.buscarPorId(id);
 
        if (categoria == null) {
 
            return false;
 
        }
 
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
 
        salvarDados();
 
        return true;
    }
 
    @Override
    public boolean desativarCategoria(int id) {
 
        Categoria categoria = repositorio.buscarPorId(id);
 
        if (categoria == null) {
 
            return false;
 
        }
 
        categoria.desativar();
 
        salvarDados();
 
        return true;
    }
 
    @Override
    public int gerarProximoId() {
 
        return repositorio.gerarProximoId();
 
    }
 
}