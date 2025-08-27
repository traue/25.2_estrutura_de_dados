import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        int[] vetor = new int[5];
        Scanner sc = new Scanner(System.in);
        int soma = 0;

        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Digite o valor para a posição " + i + ": ");
            vetor[i] = sc.nextInt();
            soma += vetor[i];
        }

        System.out.println("A soma dos valores do vetor é: " + soma);
        sc.close();
    }
}