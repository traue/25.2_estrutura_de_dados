public class Ex6 {
    public static void main(String[] args) {
        int[] vetor = new int[10];
        int pares = 0;
        int impares = 0;

        java.util.Random rand = new java.util.Random();

        // Inicializa o vetor com valores aleatórios entre 1 e 20
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rand.nextInt(20) + 1;
            if (vetor[i] % 2 == 0) {
                pares++;
            } else {
                impares++;
            }
        }

        // Exibe os valores do vetor
        System.out.print("Vetor: ");
        for (int v : vetor) {
            System.out.print(v + " ");
        }
        System.out.println();

        System.out.println("Quantidade de pares: " + pares);
        System.out.println("Quantidade de ímpares: " + impares);
    }
}
