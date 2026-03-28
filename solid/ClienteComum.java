package solid;

public class ClienteComum extends Cliente{
    @Override
    public String pegarTipoCliente() {
        return "comum";
    }

    @Override
    public double pegarDesconto(double valorItens) {
        if (valorItens > 300) {
            valorItens = valorItens - (valorItens * 0.05);
        }
        return valorItens;
    }
}
