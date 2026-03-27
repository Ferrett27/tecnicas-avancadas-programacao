package solid;

public class ClientePremium extends Cliente {
    @Override
    public String pegarTipoCliente() {
        return "premium";
    }

    @Override
    public double pegarDesconto(double valorItens) {
        if (valorItens > 200) {
            valorItens = valorItens - (valorItens * 0.10);
        } else {
            valorItens = valorItens - (valorItens * 0.03);
        }
        return valorItens;
    }
}
