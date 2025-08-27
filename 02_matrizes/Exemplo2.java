/*
 * Vamos criar um programa que permita preencher uma matriz de inteiros 
 * com linhas de tamanhos diferentes (matriz irregular).
 * O programa deve solicitar ao usuário que insira os valores para cada posicao e, 
 * em seguida, imprimir a matriz
 * 
 */
import java.util.Scanner;

public class Exemplo2 {

    public static void main(String[] args) {
        int m[][] = new int[3][];
        Scanner sc = new Scanner(System.in);
        m[0] = new int[2];
        m[1] = new int[4];
        m[2] = new int[8];

        // preenchimento da matriz:
        for (int linha = 0; linha < m.length; linha++) {
            for (int coluna = 0; coluna < m[linha].length; coluna++) {
                System.out.print("m[" + linha + "][" + coluna + "] = ");
                m[linha][coluna] = sc.nextInt();
            }
        }

        // impressão da matriz
        for (int linha = 0; linha < m.length; linha++) {
            System.out.print("[ ");
            for (int coluna = 0; coluna < m[linha].length; coluna++) {
                System.out.print(m[linha][coluna] + " ");
            }
            System.out.println("]");
        }
        sc.close();

    }
    
}
