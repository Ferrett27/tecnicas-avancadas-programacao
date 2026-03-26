package cleancode;

import java.util.List;

public class Relatorio {

    public void exibirDetalhesPedido(Pedido pedido) {
        System.out.println("Pedido " +  pedido.id + " - " +  pedido.cliente.nome + " - " +  pedido.total + " - " +  pedido.status);

        for (Item it : pedido.itens) {
            System.out.println("  item: " + it.nome + " quantidade:" + it.quantidade + " preco:" + it.preco);
        }
    }

    public void avaliacaoRelatorio(double soma) {
        if (soma > 1000) {
            System.out.println("resultado muito bom");
        } else if (soma > 500) {
            System.out.println("resultado ok");
        } else {
            System.out.println("resultado fraco");
        }
    }

    public void gerar(List<Pedido> pedidos) {
        System.out.println("======= RELATORIO =======");

        if(pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            System.out.println("Media: 0");
            return;
        }

        int quantidade = 0;
        double soma = 0;
        int cancelados = 0;
        int comuns = 0;
        int premiums = 0;
        int vips = 0;

        for (Pedido pedido : pedidos) {
            quantidade++;

            soma +=  pedido.calcularPrecoPedido();

            if (pedido.status.equals("CANCELADO")) {
                cancelados++;
            }

            if (pedido.cliente.tipo == 1) {
                comuns++;
            } else if (pedido.cliente.tipo == 2) {
                premiums++;
            } else if (pedido.cliente.tipo == 3) {
                vips++;
            }

            exibirDetalhesPedido(pedido);
        }

        System.out.println("--------------------");
        System.out.println("quantidade de pedidos: " + quantidade);
        System.out.println("valor total: " + soma);
        System.out.println("cancelados: " + cancelados);
        System.out.println("clientes comuns: " + comuns);
        System.out.println("clientes premium: " + premiums);
        System.out.println("clientes vip: " + vips);
        System.out.println("media: " + (soma / quantidade));

        avaliacaoRelatorio(soma);
    }
}
