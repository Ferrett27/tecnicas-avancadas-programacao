package solid;

import java.util.List;

public class Pedido {
    public int id;
    public Cliente cliente;
    public List<Item> itens;
    public double total;
    public String status;

    public double valorItensEmPedido() {
        double valorPedido = 0;
        for (Item item : itens) {
            valorPedido = valorPedido + (item.preco * item.quantidade);
        }
        return valorPedido;
    }

    public void informacaoPedido() {
        System.out.println("id: " + id);
        System.out.println("cliente: " + cliente.nome);
        System.out.println("email: " + cliente.email);
        System.out.println("tipo: " + cliente.tipo);
        System.out.println("status: " + status);
        System.out.println("total: " + total);
        System.out.println("itens:");

        for (Item item : itens) {
            System.out.println(item.nome + " - " + item.quantidade + " - " + item.preco);
        }
    }
}

