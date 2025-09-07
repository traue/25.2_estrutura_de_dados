package projeto.br.mack.vetores.busca;

public class BuscaBinaria {
    public static int buscar(int[] vetor, int valor) {
        int esquerda = 0;
        int direita = vetor.length - 1;
        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            if (vetor[meio] == valor) {
                return meio;
            } else if (vetor[meio] < valor) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return -1;
    }

    public static int buscaBinariaRecursiva(int[] vetor, int valor, int esquerda, int direita) {
        if (esquerda > direita) {
            return -1;
        }
        int meio = (esquerda + direita) / 2;
        if (vetor[meio] == valor) {
            return meio;
        } else if (vetor[meio] < valor) {
            return buscaBinariaRecursiva(vetor, valor, meio + 1, direita);
        } else {
            return buscaBinariaRecursiva(vetor, valor, esquerda, meio - 1);
        }
    }
}

