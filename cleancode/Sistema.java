package cleancode;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    Scanner teclado = new Scanner(System.in);
    Db bancoDeDados = new Db();

    public void exibirOpcoes() {
        System.out.println("==== SISTEMA ====");
        System.out.println("1 - Novo pedido");
        System.out.println("2 - Listar pedidos");
        System.out.println("3 - Buscar pedido por id");
        System.out.println("4 - Relatorio");
        System.out.println("5 - Cancelar pedido");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }

    public void run() {
        int operacao = -1;

        while (operacao != 0) {
            exibirOpcoes();

            try {
                operacao = Integer.parseInt(teclado.nextLine());
            } catch (Exception e) {
                System.out.println("erro");
                operacao = -1;
            }

            if (operacao == 1) {
                novoPedido();
            } else if (operacao == 2) {
                listarPedidos();
            } else if (operacao == 3) {
                buscarPedidoPorId();
            } else if (operacao == 4) {
                relatorio();
            } else if (operacao == 5) {
                cancelarPedido();
            } else if (operacao == 0) {
                System.out.println("fim");
            } else {
                System.out.println("opcao invalida");
            }
        }
    }

    public int tipoDeCliente() {
        System.out.println("Tipo cliente (1 comum, 2 premium, 3 vip):");
        int tipoCliente;
        try {
            tipoCliente = Integer.parseInt(teclado.nextLine());
            return tipoCliente;
        } catch (Exception e) {
            System.out.println("tipo errado, vai comum");
            tipoCliente = 1;
            return tipoCliente;
        }
    }

    public Cliente criarCliente() {
        Cliente cliente = new Cliente();

        System.out.println("Nome cliente:");
        String nome = teclado.nextLine();

        cliente.id = bancoDeDados.pegarListaPedidos().size() + 1;
        cliente.nome = nome;
        cliente.tipo = tipoDeCliente();
        cliente.email = nome.replace(" ", "").toLowerCase() + "@email.com";

        return cliente;
    }

    public Pedido criarPedido(Cliente cliente) {
        Pedido pedido = new Pedido();

        pedido.id = bancoDeDados.pegarListaPedidos().size() + 1;
        pedido.cliente = cliente;
        pedido.status = "NOVO";
        pedido.itens = new ArrayList<>();

        return pedido;
    }

    public double precoItem() {
        System.out.println("Preço item:");
        try {
            return Double.parseDouble(teclado.nextLine());
        } catch (Exception e) {
            return 0;
        }
    }

    public int quantidadeItem() {
        System.out.println("Quantidade item:");
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (Exception e) {
            return 1;
        }
    }

    public Item criarItem() {
        System.out.println("Nome item:");
        String nomeItem = teclado.nextLine();

        Item item = new Item();
        item.nome = nomeItem;
        item.preco = precoItem();
        item.quantidade = quantidadeItem();

        return item;
    }

    public void adicionarItemsEmPedido(Pedido pedido) {
        String continua = "s";
        while (continua.equalsIgnoreCase("s")) {
            Item item = criarItem();
            pedido.itens.add(item);

            System.out.println("Adicionar mais item? s/n");
            continua = teclado.nextLine();
        }
    }

    public double calcularDesconto(double total, Cliente cliente) {
        if (cliente.tipo == 1) {
            if (total > 300) {
                total = total - (total * 0.05);
            }
        } else if (cliente.tipo == 2) {
            if (total > 200) {
                total = total - (total * 0.10);
            } else {
                total = total - (total * 0.03);
            }
        } else if (cliente.tipo == 3) {
            total = total - (total * 0.15);
        }
        return total;
    }

    public double calcularFrete(double total){
        if (total < 100) {
            total = total + 25;
        } else if (total >= 100 && total < 300) {
            total = total + 15;
        }
        return total;
    }

    public double calcularTotal(Pedido pedido, Cliente cliente) {
        double total = pedido.calcularPrecoPedido();
        total = calcularDesconto(total, cliente);
        total = calcularFrete(total);
        return total;
    }

    public void confirmacaoPedido(Pedido pedido) {
        System.out.println("Pedido criado com sucesso");
        System.out.println("Id: " + pedido.id);
        System.out.println("Cliente: " + pedido.cliente.nome);
        System.out.println("Total: " + pedido.total);

        if (pedido.total > 500) {
            System.out.println("Pedido importante!!!");
        }
    }

    public void novoPedido() {
        Cliente cliente = criarCliente();

        Pedido pedido = criarPedido(cliente);

        adicionarItemsEmPedido(pedido);

        pedido.total = calcularTotal(pedido, cliente);

        bancoDeDados.salvarNoBanco(pedido);

        confirmacaoPedido(pedido);
    }

    public void listarPedidos() {
        if (bancoDeDados.pegarListaPedidos().isEmpty()) {
            System.out.println("sem pedidos");
        } else {
            for (Pedido pedido : bancoDeDados.pegarListaPedidos()) {
                System.out.println("---------------");
                pedido.informacaoPedido();
            }
        }
    }

    public void mostrarPedidoPorId(Pedido pedido) {
        System.out.println("Pedido encontrado");
        System.out.println("id: " + pedido.id);
        System.out.println("cliente: " + pedido.cliente.nome);
        System.out.println("status: " + pedido.status);
        System.out.println("total: " + pedido.total);

        double subtotal = 0;
        for (Item item : pedido.itens) {
            subtotal += item.calcularPreco();
        }
        System.out.println("subtotal calculado novamente: " + subtotal);

        System.out.println(pedido.cliente.pegarTipoCliente());

        int contador = 1;
        for (Item item : pedido.itens) {
            System.out.println("item " + contador + ": " + item.nome + " / " + item.quantidade + " / " + item.preco);
            contador++;
        }
    }

    public void buscarPedidoPorId() {
        System.out.println("Digite o id:");
        int id = Integer.parseInt(teclado.nextLine());
        Pedido pedido = bancoDeDados.pegarPorId(id);

        if (pedido != null) {
            mostrarPedidoPorId(pedido);
        } else {
            System.out.println("não achou");
        }
    }

    public void relatorio() {
        Relatorio relatorio = new Relatorio();
        relatorio.gerar(bancoDeDados.pegarListaPedidos());
    }

    public void cancelarPedido() {
        System.out.println("Digite id do pedido");
        int id = Integer.parseInt(teclado.nextLine());

        Pedido pedido = bancoDeDados.pegarPorId(id);

        if (pedido != null) {
            if (pedido.status.equals("CANCELADO")) {
                System.out.println("ja cancelado");
            } else {
                pedido.status = "CANCELADO";
                System.out.println("cancelado");
            }
        } else {
            System.out.println("pedido nao existe");
        }
    }
}
