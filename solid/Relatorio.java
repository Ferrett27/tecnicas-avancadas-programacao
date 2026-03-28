package solid;

import java.util.List;

public interface Relatorio {
    DadosRelatorio gerar(List<Pedido> pedidos);
}
