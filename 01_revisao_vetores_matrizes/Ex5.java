import java.util.Random;

public class Ex5 {
    public static void main(String[] args) {
        int[] vetor = new int[10];
        Random rand = new Random();

        // Inicializa o vetor com valores aleatórios entre 1 e 100
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rand.nextInt(100) + 1;
        }

        // Exibe os valores do vetor
        System.out.print("Vetor: ");
        for (int v : vetor) {
            System.out.print(v + " ");
        }
        System.out.println();

        // Encontra maior e menor valor
        int maior = vetor[0];
        int menor = vetor[0];
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] > maior) maior = vetor[i];
            if (vetor[i] < menor) menor = vetor[i];
        }

        System.out.println("Maior valor: " + maior);
        System.out.println("Menor valor: " + menor);
    }
}