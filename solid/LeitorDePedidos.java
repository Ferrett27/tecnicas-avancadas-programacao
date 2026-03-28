package solid;

import java.util.List;

// Princípio da Segregação de Interfaces
public interface LeitorDePedidos {
    List<Pedido> pegarListaPedidos();
    Pedido pegarPorId(int id);
}
