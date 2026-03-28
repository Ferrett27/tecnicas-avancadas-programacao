package solid;

public class ClienteVip extends Cliente {
    @Override
    public String pegarTipoCliente() {
        return "vip";
    }

    @Override
    public double pegarDesconto(double valorItens) {
        return valorItens - (valorItens * 0.15);
    }
}
