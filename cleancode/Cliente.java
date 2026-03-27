package cleancode;

public class Cliente {
    public int id;
    public String nome;
    public String email;
    public int tipo; // 1 comum, 2 premium, 3 vip

    public String pegarTipoCliente() {
        if (tipo == 1) {
            return "cliente comum";
        } else if (tipo == 2) {
            return "cliente premium";
        } else if (tipo == 3) {
            return "cliente vip";
        } else {
            return "cliente desconhecido";
        }
    }
}
