package solid;

import java.util.List;

public interface ServicoDePedido {
    public Pedido criarPedido(Cliente cliente, List<Item> itensEscolhidos);
    public String cancelar(int id);
    public Pedido pedidoPorId(int id);
    public List<Pedido> listarTodosPedidos();
}
