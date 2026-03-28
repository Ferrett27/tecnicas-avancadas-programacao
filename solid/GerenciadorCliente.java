package solid;
// Classe para criar Clientes (Princípio da Responsabilidade Única)
public class GerenciadorCliente {
    // Princípio Aberto/Fechado
    public static Cliente criarCliente(int id, String nome, int tipo) {
        Cliente cliente;

        if(tipo == 3){
            cliente = new ClienteVip();
        } else if (tipo == 2) {
            cliente = new ClientePremium();
        } else {
            cliente = new ClienteComum();
        }

        cliente.id = id;
        cliente.nome = nome;
        cliente.tipo = tipo;
        cliente.email = cliente.gerarEmail();

        return cliente;
    }
}
