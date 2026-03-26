package cleancode;

public class Item {
    public String nome;
    public double preco;
    public int quantidade;

    public double calcularPreco() {
        return preco * quantidade;
    }
}
