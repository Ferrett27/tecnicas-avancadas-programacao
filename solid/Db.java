package solid;

import java.util.ArrayList;
import java.util.List;

public class Db implements EscritorDePedidos, LeitorDePedidos {

    public static List<Pedido> banco = new ArrayList<>();

    @Override
    public void salvarPedido(Pedido pedido) {
        try {
            banco.add(pedido);
            System.out.println("salvou no banco");
        } catch (Exception e) {
            System.out.println("erro ao salvar");
        }
    }

    @Override
    public List<Pedido> pegarListaPedidos() {
        return banco;
    }

    @Override
    public Pedido pegarPorId(int id) {
        for (Pedido pedido : banco) {
            if (pedido.id == id) {
                return pedido;
            }
        }
        return null;
    }
}
