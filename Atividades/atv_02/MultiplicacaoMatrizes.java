public class MultiplicacaoMatrizes {
    public static void main(String[] args) {
        int[][] A = new int[64][64];
        int[][] B = new int[64][64];

        // preenche as matrizes A e B com números aleatórios
        preencheA(A);
        preencheB(B);

        // cria a matriz C para receber o resultado
        int[][] C = new int[A.length][B[0].length];
        C = multiplica(A, B);

        exibeResult(C);
    }

    public static void preencheA(int A[][]) {
        // implementação a ser realizada
    }

    public static void preencheB(int B[][]) {
        // implementação a ser realizada
    }

    public static int[][] multiplica(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                C[i][j] = 0;
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static void exibeResult(int C[][]) {
        // implementação a ser feita
    }
}