/*
 * Como declarar e preencher uma matriz 3x3 de inteiros
 * O programa deve solicitar ao usuário os valores
 * de cada posicão.
 * Ao final o programa deve exibir a matriz formatada
 */

import java.util.Scanner;

public class Exemplo1 {
    
    public static void main(String[] args) {
        
        // variáveis necessárias
        int m[][] = new int[3][3];
        Scanner sc = new Scanner(System.in);

        // preenchimento da matriz
        for (int linha = 0; linha < m.length; linha++) {
            for (int coluna = 0; coluna < m.length; coluna++) {
                System.out.print("m[" + linha + "][" + coluna + "] = ");
                m[linha][coluna] = sc.nextInt();
            }
        }

        // impressão da matriz
        for (int linha = 0; linha < m.length; linha++) {
            System.out.print("[ ");
            for (int coluna = 0; coluna < m.length; coluna++) {
                System.out.print(m[linha][coluna] + " ");
            }
            System.out.println("]");
        }
        sc.close();
    }
}
