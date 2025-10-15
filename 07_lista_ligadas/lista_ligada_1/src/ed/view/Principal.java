package ed.view;

import java.util.Scanner;

import ed.tad.ListaLigada;
import ed.tad.No;

public class Principal {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			ListaLigada ll = new ListaLigada();

			while (true) {

				System.out.println("LISTA LIGADA - Primeira implementação");
				System.out.println("[1] - Inserir elemento");
				System.out.println("[2] - Remover elemento");
				System.out.println("[9] - Encerrar\n");
				System.out.print("Opção: ");

				int opc = sc.nextInt();

				if (opc == 9) {
					break;
				} else if (opc == 1) {
					System.out.print("Nome: ");
					String nm = sc.next();
					System.out.print("Valor: ");
					int nr = sc.nextInt();
					No e = new No(nm, nr);
					if (ll.push(e)) {
						System.out.println("Elemento inserido com sucesso!");
					} else {
						System.err.println("Falha na inserção do elemento!");
					}
				} else if (opc == 2) {
					No e = ll.pop();
					if (e == null) {
						System.err.println("A Lista está vazia!");
					} else {
						System.out.println("O elemento foi removido com sucesso!");
						System.out.println(" > Nome: " + e.getNome());
						System.out.println(" > Número: " + e.getNumero());
					}
				}
			}
		}

	}

}
