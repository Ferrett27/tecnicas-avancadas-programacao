package solid;

public class CalculadoraPedido implements CalculadoraPrecoPedido {
    public double calcularFrete(double total){
        if (total < 100) {
            total = total + 25;
        } else if (total >= 100 && total < 300) {
            total = total + 15;
        }
        return total;
    }

    @Override
    public double calcularPedido(Pedido pedido) {
        double total = pedido.valorItensEmPedido();
        total = pedido.cliente.pegarDesconto(total);
        total = calcularFrete(total);
        return total;
    }

    @Override
    public double calcularSubtotalPedido(Pedido pedido){
        double subtotal = 0;
        for (Item item : pedido.itens) {
            subtotal += item.calcularPreco();
        }
        return subtotal;
    }
}