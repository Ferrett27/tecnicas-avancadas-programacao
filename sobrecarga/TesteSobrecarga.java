package sobrecarga;

import java.util.Scanner;

public final class TesteSobrecarga {
    private TesteSobrecarga() {
    }
    /**
     * O metodo roda a main.
     * @param args erm sla.
     */
    public static void main(final String[] args) {
        Calculadora calc = new Calculadora();
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc.somar(scanner.nextInt(), scanner.nextInt()));
        System.out.println(calc.somar(
                scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        System.out.println(calc.somar(
                scanner.nextDouble(), scanner.nextDouble()));
    }
}
