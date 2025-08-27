/*
 * Essa classe demonstra como gerar e exibir 10
 * inteiros aleatórios entre 0 e 6 usando o Random
 */

import java.util.Random;

public class Exemplo4 {
    public static void main(String args[]) {
        Random r = new Random();
        int valor;
        for (int i = 1; i <= 10; i++) {
            valor = r.nextInt(7);
            System.out.print(valor + " ");
        }
    }
}
