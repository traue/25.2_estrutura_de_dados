package projeto.br.mack.vetores.util;

public class VetorUtils {

    public static void imprimirVetor(int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            System.out.print(v[i] + " ");
        }
        System.out.println();
    }

}
