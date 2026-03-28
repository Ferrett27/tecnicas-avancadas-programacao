package solid;

public class Main {
    public static void main(String[] args) {
        InteracaoDeUsuario interfaceUsuario = new InteracaoDeUsuario();
        Db bancoDeDados = new Db();
        CalculadoraPedido calculadora = new CalculadoraPedido();
        RelatorioPedidos relatorio = new RelatorioPedidos();

        GerenciadorPedido gerenciador = new GerenciadorPedido(bancoDeDados, bancoDeDados, calculadora);

        // Princípio da Inversão de Dependência
        Sistema sistema = new Sistema(interfaceUsuario, gerenciador, calculadora, relatorio);

        sistema.executarSistema();
    }
}

