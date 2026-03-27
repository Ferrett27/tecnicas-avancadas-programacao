package solid;

import java.util.List;

public class Relatorio {

    int pedidosCancelados = 0;
    double somaPedidos = 0;
    int clientesComuns = 0;
    int clientesPremiums = 0;
    int clientesVips = 0;
    int quantidade = 0;

    public void exibirDetalhesPedidos(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            System.out.println("Pedido " + pedido.id + " - " + pedido.cliente.nome + " - " + pedido.total + " - " + pedido.status);

            for (Item item : pedido.itens) {
                System.out.println("  Item: " + item.nome + " Quantidade: " + item.quantidade + " Preço: " + item.preco);
            }
        }
    }

    public void avaliacaoRelatorio(double soma) {
        if (soma > 1000) {
            System.out.println("Resultado muito bom");
        } else if (soma > 500) {
            System.out.println("Resultado ok");
        } else {
            System.out.println("Resultado fraco");
        }
    }

    public void calcularSoma(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            somaPedidos +=  pedido.valorItensEmPedido();
        }
    }

    public void calcularCancelamentos(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            if (pedido.status.equals("CANCELADO")) {
                pedidosCancelados++;
            }
        }
    }

    public void calcularTiposClientes(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            if (pedido.cliente.tipo == 1) {
                clientesComuns++;
            } else if (pedido.cliente.tipo == 2) {
                clientesPremiums++;
            } else if (pedido.cliente.tipo == 3) {
                clientesVips++;
            }
        }
    }

    public void exibirDetalhesRelatorio(){
        System.out.println("--------------------");
        System.out.println("Quantidade de pedidos: " + quantidade);
        System.out.println("Valor total: " + somaPedidos);
        System.out.println("Cancelados: " + pedidosCancelados);
        System.out.println("Clientes comuns: " + clientesComuns);
        System.out.println("Clientes premium: " + clientesPremiums);
        System.out.println("Clientes vip: " + clientesVips);
        System.out.println("Media: " + (somaPedidos / quantidade));
    }

    public void gerar(List<Pedido> pedidos) {
        System.out.println("======= RELATORIO =======");

        if(pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            System.out.println("Media: 0");
            return;
        }

        calcularSoma(pedidos);
        calcularCancelamentos(pedidos);
        calcularTiposClientes(pedidos);
        quantidade = pedidos.size();

        exibirDetalhesPedidos(pedidos);

        exibirDetalhesRelatorio();

        avaliacaoRelatorio(somaPedidos);
    }
}
