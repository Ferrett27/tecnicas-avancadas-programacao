package solid;

import java.util.List;

public class RelatorioPedidos implements Relatorio {

    @Override
    public DadosRelatorio gerar(List<Pedido> pedidos) {
        DadosRelatorio dados = new DadosRelatorio();
        dados.listaDePedidos = pedidos;
        dados.quantidadePedidos = pedidos.size();

        if (pedidos.isEmpty()) {
            return dados;
        }

        for (Pedido pedido : pedidos) {
            dados.valorTotal += pedido.valorItensEmPedido();

            if (pedido.status.equals("CANCELADO")) {
                dados.pedidosCancelados++;
            }

            if (pedido.cliente.tipo == 1) {
                dados.clientesComuns++;
            }
            else if (pedido.cliente.tipo == 2) {
                dados.clientesPremiums++;
            }
            else if (pedido.cliente.tipo == 3){
                dados.clientesVips++;
            }
        }

        dados.media = dados.valorTotal / dados.quantidadePedidos;

        if (dados.valorTotal > 1000) {
            dados.avaliacao = "Resultado muito bom";
        } else if (dados.valorTotal > 500) {
            dados.avaliacao = "Resultado ok";
        } else {
            dados.avaliacao = "Resultado fraco";
        }

        return dados;
    }
}
