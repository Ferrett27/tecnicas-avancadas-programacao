package solid;

// Classe pra criar Itens (Princípio da Responsabilidade Única)
public class GerenciadorItem {
    public static Item criarItem(String nome, double preco, int quantidade) {
        Item item = new Item();
        item.nome = nome;
        item.preco = preco;
        item.quantidade = quantidade;

        return item;
    }
}
