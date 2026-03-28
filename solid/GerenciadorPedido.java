package solid;
import java.util.List;

// Classe para gerenciar pedidos (Princípio da Responsabilidade Única)
public class GerenciadorPedido implements ServicoDePedido {
    private CalculadoraPrecoPedido calculadoraPrecoPedidoDePedido;
    private EscritorDePedidos escritorDePedidos;
    private LeitorDePedidos leitorDePedidos;

    // O Gerenciador estava muito dependente do Db e CalculadoraPedido. (Princípio da Inversão de Dependência)
    public GerenciadorPedido(EscritorDePedidos escritorDePedidos, LeitorDePedidos leitorDePedidos, CalculadoraPrecoPedido calculadoraPrecoPedido) {
        this.leitorDePedidos = leitorDePedidos;
        this.escritorDePedidos = escritorDePedidos;
        this.calculadoraPrecoPedidoDePedido = calculadoraPrecoPedido;
    }

    @Override
    public Pedido criarPedido(Cliente cliente, List<Item> itensEscolhidos) {
        Pedido pedido = new Pedido();

        pedido.id = leitorDePedidos.pegarListaPedidos().size() + 1;
        pedido.cliente = cliente;
        pedido.status = "NOVO";
        pedido.itens = itensEscolhidos;

        pedido.total = calculadoraPrecoPedidoDePedido.calcularPedido(pedido);

        escritorDePedidos.salvarPedido(pedido);
        return pedido;
    }

    @Override
    public String cancelar(int id) {
        Pedido pedido = leitorDePedidos.pegarPorId(id);

        if (pedido != null) {
            if (pedido.status.equals("CANCELADO")) {
                return  "Já cancelado";
            } else {
                pedido.status = "CANCELADO";
                return  "Cancelado";
            }
        } else {
            return  "Pedido não existe";
        }
    }

    @Override
    public Pedido pedidoPorId(int id) {
        return leitorDePedidos.pegarPorId(id);
    }

    @Override
    public List<Pedido> listarTodosPedidos() {
        return leitorDePedidos.pegarListaPedidos();
    }
}
