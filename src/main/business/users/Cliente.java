import java.util.ArrayList;
import java.util.List;
public class Cliente extends Usuario {
    private List<Item> itensAlugados = new ArrayList<>();

    public Cliente(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha);
    }

    public List<Item> getItensAlugados() {
        return itensAlugados;
    }

    public void alugarItem(Item item) {
        //colocar a regra de negocio
        itensAlugados.add(item);
    }

    public void devolverItem(Item item) {
        //colocar a regra de negocio
        itensAlugados.remove(item);
    }
}