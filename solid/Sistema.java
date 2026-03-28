package solid;

import java.util.List;

// Atualizada a classe Sistema para apenas coordenar as funções (Princípio da Responsabilidade Única)
public class Sistema {
    private InteracaoDeUsuario interfaceUsuario;
    private ServicoDePedido gerenciadorDePedido;
    private CalculadoraPrecoPedido calculadoraPedido;
    private Relatorio relatorio;

    public Sistema(InteracaoDeUsuario interfaceUsuario, GerenciadorPedido gerenciadorDePedido, CalculadoraPrecoPedido calculadoraPedido, Relatorio relatorio) {
        this.interfaceUsuario = interfaceUsuario;
        this.gerenciadorDePedido = gerenciadorDePedido;
        this.calculadoraPedido = calculadoraPedido;
        this.relatorio = relatorio;
    }

    public void executarSistema() {
        int operacao = -1;
        while (operacao != 0) {
            interfaceUsuario.exibirOpcoesMenu();

            operacao = interfaceUsuario.receberOperacao();

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
                interfaceUsuario.exibirMenssagem("Fim");
            } else {
                interfaceUsuario.exibirMenssagem("Opção invalida");
            }
        }
    }

    public void criarNovoPedido() {
        String nomeCliente = interfaceUsuario.escolherNomeCliente();
        int tipoCliente = interfaceUsuario.escolherTipoDeCliente();
        int id = gerenciadorDePedido.listarTodosPedidos().size() + 1;
        Cliente cliente = GerenciadorCliente.criarCliente(id, nomeCliente, tipoCliente);

        List<Item> itensEscolhidos = interfaceUsuario.pedirListaDeItens();

        Pedido pedido = gerenciadorDePedido.criarPedido(cliente, itensEscolhidos);
        interfaceUsuario.confirmarPedido(pedido);
    }

    public void listarPedidos() {
        List<Pedido> listaDePedidos = gerenciadorDePedido.listarTodosPedidos();
        if (listaDePedidos.isEmpty()) {
            interfaceUsuario.exibirMenssagem("Sem pedidos");
        } else {
            for (Pedido pedido : listaDePedidos) {
                interfaceUsuario.exibirPedidos(pedido);
            }
        }
    }

    public void buscarPedidoPorId() {
        int id = interfaceUsuario.escolherIdPedido();
        Pedido pedido = gerenciadorDePedido.pedidoPorId(id);
        if (pedido != null) {
            double subtotal = calculadoraPedido.calcularSubtotalPedido(pedido);
            String tipoCliente = pedido.cliente.pegarTipoCliente();
            interfaceUsuario.exibirPedidoPorId(pedido,subtotal,tipoCliente);
        } else {
            interfaceUsuario.exibirMenssagem("Não achou");
        }
    }

    public void criarRelatorio() {
        DadosRelatorio dados = relatorio.gerar(gerenciadorDePedido.listarTodosPedidos());
        interfaceUsuario.exibirRelatorio(dados);
    }

    public void cancelarPedido() {
        int id = interfaceUsuario.escolherIdPedido();
        String resultado = gerenciadorDePedido.cancelar(id);
        interfaceUsuario.exibirMenssagem(resultado);
    }
}
