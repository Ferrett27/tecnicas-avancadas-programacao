package solid;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    Scanner teclado = new Scanner(System.in);
    Db bancoDeDados = new Db();
    CalculadoraPedido calculadoraDePedido = new CalculadoraPedido();

    public void exibirOpcoes() {
        System.out.println("==== SISTEMA ====");
        System.out.println("1 - Novo pedido");
        System.out.println("2 - Listar pedidos");
        System.out.println("3 - Buscar pedido por id");
        System.out.println("4 - Relatorio");
        System.out.println("5 - Cancelar pedido");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
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

    public void executarSistema() {
        int operacao = -1;

        while (operacao != 0) {
            exibirOpcoes();

            operacao = receberOperacao();

            if (operacao == 1) {
                criarNovoPedido();
            } else if (operacao == 2) {
                listarPedidos();
            } else if (operacao == 3) {
                buscarPedidoPorId();
            } else if (operacao == 4) {
                criarRelatorio();
            } else if (operacao == 5) {
                cancelarPedido();
            } else if (operacao == 0) {
                System.out.println("Fim");
            } else {
                System.out.println("Opção invalida");
            }
        }
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

    public String escolherNomeCliente() {
        System.out.println("Nome cliente:");
        return teclado.nextLine();
    }

    public Cliente criarCliente() {
        Cliente cliente;

        String nome = escolherNomeCliente();
        int tipo = escolherTipoDeCliente();

        if(tipo == 3){
            cliente = new ClienteVip();
        } else if (tipo == 2) {
            cliente = new ClientePremium();
        } else {
            cliente = new ClienteComum();
        }

        cliente.id = bancoDeDados.pegarListaPedidos().size() + 1;
        cliente.nome = nome;
        cliente.tipo = tipo;
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

    public double pegarPrecoItem() {
        System.out.println("Preço item:");
        try {
            return Double.parseDouble(teclado.nextLine());
        } catch (Exception e) {
            return 0;
        }
    }

    public int pegarQuantidadeItem() {
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
        item.preco = pegarPrecoItem();
        item.quantidade = pegarQuantidadeItem();

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

    public void confirmarPedido(Pedido pedido) {
        System.out.println("Pedido criado com sucesso");
        System.out.println("Id: " + pedido.id);
        System.out.println("Cliente: " + pedido.cliente.nome);
        System.out.println("Total: " + pedido.total);

        if (pedido.total > 500) {
            System.out.println("Pedido importante!!!");
        }
    }

    public void criarNovoPedido() {
        Cliente cliente = criarCliente();

        Pedido pedido = criarPedido(cliente);

        adicionarItemsEmPedido(pedido);

        pedido.total = calculadoraDePedido.calcularPedido(pedido);

        bancoDeDados.salvarPedido(pedido);

        confirmarPedido(pedido);
    }

    public void listarPedidos() {
        if (bancoDeDados.pegarListaPedidos().isEmpty()) {
            System.out.println("Sem pedidos");
        } else {
            for (Pedido pedido : bancoDeDados.pegarListaPedidos()) {
                System.out.println("---------------");
                pedido.informacaoPedido();
            }
        }
    }

    public void mostrarPedidoPorId(Pedido pedido) {
        System.out.println("Pedido encontrado");
        System.out.println("Id: " + pedido.id);
        System.out.println("Cliente: " + pedido.cliente.nome);
        System.out.println("Status: " + pedido.status);
        System.out.println("Total: " + pedido.total);

        double subtotal = 0;
        for (Item item : pedido.itens) {
            subtotal += item.calcularPreco();
        }
        System.out.println("Subtotal calculado novamente: " + subtotal);

        String tipoCliente = pedido.cliente.pegarTipoCliente();
        System.out.println("Cliente " + tipoCliente);

        int contador = 1;
        for (Item item : pedido.itens) {
            System.out.println("Item " + contador + ": " + item.nome + " / " + item.quantidade + " / " + item.preco);
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
            System.out.println("Não achou");
        }
    }

    public void criarRelatorio() {
        Relatorio relatorio = new Relatorio();
        relatorio.gerar(bancoDeDados.pegarListaPedidos());
    }

    public void cancelarPedido() {
        System.out.println("Digite id do pedido");
        int id = Integer.parseInt(teclado.nextLine());

        Pedido pedido = bancoDeDados.pegarPorId(id);

        if (pedido != null) {
            if (pedido.status.equals("CANCELADO")) {
                System.out.println("Já cancelado");
            } else {
                pedido.status = "CANCELADO";
                System.out.println("Cancelado");
            }
        } else {
            System.out.println("Pedido não existe");
        }
    }
}
