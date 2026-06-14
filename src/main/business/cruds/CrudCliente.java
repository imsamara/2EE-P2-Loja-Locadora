public interface CrudCliente {
    public abstract void cadastrarCliente(Cliente cliente);
    public abstract Cliente lerCliente(Cliente cliente);
    public abstract boolean atualizarCliente(Cliente cliente);
    public abstract boolean deletarCliente(Cliente cliente);
}
