/*
 * Inplementar o processo de declaração de uma matriz 3x3.
 * Realizar a soma dos elementos da diagonal principal 
 * Usar métodos separados para cada operação
 *
 */

import java.util.Scanner;

public class Exemplo3 {
    public static void main(String[] args) {
        // declaração do que precisamos
        int m[][] = new int[3][3];
        int soma;

        // chama o método de preenchimento
        preencherMatriz(m);

        // faz o cálculo da diagonal principal
        soma = calculaSomaDiagonalPrincipal(m);

        imprimirMatriz(m);

        // mostra o valor da diagonal principal
        System.out.println("Soma da diagonal: " + soma);
    }

    // método para ler uma matriz MxM:
    public static void preencherMatriz(int m[][]) {
        Scanner sc = new Scanner(System.in);
        for (int linha = 0; linha < m.length; linha++) {
            for (int coluna = 0; coluna < m.length; coluna++) {
                System.out.print("m[" + linha + "][" + coluna + "] = ");
                m[linha][coluna] = sc.nextInt();
            }
        }
    }

    // método que calcula a soma da diagonal principal de uma matriz MxM
    public static int calculaSomaDiagonalPrincipal(int m[][]) {
        int soma = 0;
        for (int i = 0; i < m.length; i++) {
            soma += m[i][i];
        }
        return soma;
    }

    // impressão da matriz
    public static void imprimirMatriz(int m[][]) {
        for (int linha = 0; linha < m.length; linha++) {
            System.out.print("[ ");
            for (int coluna = 0; coluna < m.length; coluna++) {
                System.out.print(m[linha][coluna] + " ");
            }
            System.out.println("]");
        }
    }
}