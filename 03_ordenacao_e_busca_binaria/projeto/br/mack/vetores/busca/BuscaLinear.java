package projeto.br.mack.vetores.busca;

public class BuscaLinear {
    public static int buscar(int[] vetor, int valor) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == valor) {
                return i;
            }
        }
        return -1;
    }
}
