package solid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe nova para pegar input e exibir coisas na tela (Princípio da Responsabilidade Única)
public class InteracaoDeUsuario {
    Scanner teclado = new Scanner(System.in);

    public void exibirMenssagem(String mensagem) {
        System.out.println(mensagem);
    }

    public int receberOperacao() {
        int operacao;
        try {
            operacao = Integer.parseInt(teclado.nextLine());
        } catch (Exception e) {
            System.out.println("Erro");
            operacao = -1;
        }
        return operacao;
    }

    public void exibirOpcoesMenu() {
        System.out.println("==== SISTEMA ====");
        System.out.println("1 - Novo pedido");
        System.out.println("2 - Listar pedidos");
        System.out.println("3 - Buscar pedido por id");
        System.out.println("4 - Relatorio");
        System.out.println("5 - Cancelar pedido");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    public String escolherNomeCliente() {
        System.out.println("Nome cliente:");
        return teclado.nextLine();
    }

    public int escolherIdPedido() {
        System.out.println("Digite id do pedido");
        return Integer.parseInt(teclado.nextLine());
    }

    public List<Item> pedirListaDeItens() {
        List<Item> itens = new ArrayList<>();
        String continua = "s";

        while (continua.equalsIgnoreCase("s")) {
            System.out.println("Nome item:");
            String nomeItem = teclado.nextLine();

            System.out.println("Preço item:");
            double preco = Double.parseDouble(teclado.nextLine());

            System.out.println("Quantidade item:");
            int quantidade = Integer.parseInt(teclado.nextLine());

            Item item = GerenciadorItem.criarItem(nomeItem,preco,quantidade);

            itens.add(item);

            System.out.println("Adicionar mais item? s/n");
            continua = teclado.nextLine();
        }
        return itens;
    }

    public int escolherTipoDeCliente() {
        System.out.println("Tipo cliente (1 comum, 2 premium, 3 vip):");
        int tipoCliente;
        try {
            tipoCliente = Integer.parseInt(teclado.nextLine());
            return tipoCliente;
        } catch (Exception e) {
            System.out.println("Tipo errado, vai comum");
            tipoCliente = 1;
            return tipoCliente;
        }
    }

    public void exibirPedidos(Pedido pedido){
        System.out.println("---------------");
        pedido.informacaoPedido();
    }

    public void confirmarPedido(Pedido pedido) {
        System.out.println("Pedido criado com sucesso");
        System.out.println("Id: " + pedido.id);
        System.out.println("Cliente: " + pedido.cliente.nome);
        System.out.println("Total: " + pedido.total);

        if (pedido.total > 500) {
            System.out.println("Pedido importante!!!");
        }
    }

    public void exibirPedidoPorId(Pedido pedido, double subtotal, String tipoCliente) {
        System.out.println("Pedido encontrado");
        System.out.println("Id: " + pedido.id);
        System.out.println("Cliente: " + pedido.cliente.nome);
        System.out.println("Status: " + pedido.status);
        System.out.println("Total: " + pedido.total);

        System.out.println("Subtotal calculado novamente: " + subtotal);

        System.out.println("Cliente " + tipoCliente);

        int contador = 1;
        for (Item item : pedido.itens) {
            System.out.println("Item " + contador + ": " + item.nome + " / " + item.quantidade + " / " + item.preco);
            contador++;
        }
    }

    public void exibirRelatorio(DadosRelatorio dados){
        System.out.println("======= RELATORIO =======");

        if(dados.listaDePedidos.isEmpty()){
            System.out.println("Nenhum pedido encontrado.");
            System.out.println("Media: 0");
            return;
        }

        for (Pedido pedido : dados.listaDePedidos) {
            System.out.println("Pedido " + pedido.id + " - " + pedido.cliente.nome + " - " + pedido.total + " - " + pedido.status);

            for (Item item : pedido.itens) {
                System.out.println("  Item: " + item.nome + " Quantidade: " + item.quantidade + " Preço: " + item.preco);
            }
        }

        System.out.println("--------------------");
        System.out.println("Quantidade de pedidos: " + dados.quantidadePedidos);
        System.out.println("Valor total: " + dados.valorTotal);
        System.out.println("Cancelados: " + dados.pedidosCancelados);
        System.out.println("Clientes comuns: " + dados.clientesComuns);
        System.out.println("Clientes premium: " + dados.clientesPremiums);
        System.out.println("Clientes vip: " + dados.clientesVips);
        System.out.println("Media: " + dados.media);

        System.out.println(dados.avaliacao);
    }
}
