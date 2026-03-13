package sobrecarga;

public class Calculadora {
    /**
     * O metodo faz um calculo de 2 valores inteiros.
     * @param a valor 1 para a soma.
     * @param b valor 2 para a soma.
     * @return a soma entre a e b.
     */
    public int somar(final int a, final int b) {
        return a + b;
    }
    /**
     * O metodo faz um calculo de 3 valores inteiros.
     * @param a valor 1 para a soma.
     * @param b valor 2 para a soma.
     * @param c valor 3 para a soma.
     * @return a soma entre a, b e c.
     */
    public int somar(final int a, final int b, final int c) {
        return a + b + c;
    }
    /**
     * O metodo faz um calculo de 2 valores double.
     * @param a valor 1 para a soma.
     * @param b valor 2 para a soma.
     * @return a soma entre a e b.
     */
    public double somar(final double a, final double b) {
        return a + b;
    }
}
