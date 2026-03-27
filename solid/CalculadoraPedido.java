package solid;

public class CalculadoraPedido {
    public double calcularFrete(double total){
        if (total < 100) {
            total = total + 25;
        } else if (total >= 100 && total < 300) {
            total = total + 15;
        }
        return total;
    }
    
    public double calcularPedido(Pedido pedido) {
        double total = pedido.valorItensEmPedido();
        total = pedido.cliente.pegarDesconto(total);
        total = calcularFrete(total);
        return total;
    }
}