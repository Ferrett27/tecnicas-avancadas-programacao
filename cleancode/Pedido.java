package cleancode;

import java.util.List;

public class Pedido {
    public int id;
    public Cliente cliente;
    public List<Item> itens;
    public double total;
    public String status;

    public double calcularPrecoPedido() {
        double valorPedido = 0;
        for (int i = 0; i < itens.size(); i++) {
            valorPedido = valorPedido + (itens.get(i).preco * itens.get(i).quantidade);
        }
        return valorPedido;
    }

    public void p() {
        System.out.println("Pedido " + id);
        System.out.println("Cliente " + cliente.nome);
        for (int i = 0; i < itens.size(); i++) {
            System.out.println(itens.get(i).nome);
        }
        System.out.println(total);
    }
}

