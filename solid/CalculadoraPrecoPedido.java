package solid;

// Interface Segregation Principle
public interface CalculadoraPrecoPedido {
    double calcularPedido(Pedido pedido);
    double calcularSubtotalPedido(Pedido pedido);
}
