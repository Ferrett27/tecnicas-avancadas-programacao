package solid;

public abstract class Cliente {
    public int id;
    public String nome;
    public String email;
    public int tipo; // 1 comum, 2 premium, 3 vip

    public abstract String pegarTipoCliente();
    public abstract double pegarDesconto(double valorItens);
}

