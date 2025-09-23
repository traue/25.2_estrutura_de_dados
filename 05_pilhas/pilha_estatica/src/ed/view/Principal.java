package ed.view;

import java.util.Scanner;

import ed.tad.Pilha;

public class Principal {

	public static void main(String[] args) {
		Pilha pilha;
		try (Scanner sc = new Scanner(System.in)) {
			int opc, e, removido, c;
			String msg = "1 - Adicionar\n" + "2 - Remover\n" + "3 - Exibir a Pilha\n" + "0 - Sair\n";

			System.out.print("Tamanho da pilha: ");
			c = sc.nextInt();
			pilha = new Pilha(c);

			do {
				System.out.println(msg);
				opc = sc.nextInt();

				switch (opc) {
				case 1:
					System.out.print("\nInforme o elemento: ");
					e = sc.nextInt();
					pilha.adiciona(e);
					break;
				case 2:
					removido = pilha.remove();
					System.out.println("Elemento removido: " + removido);
					break;
				case 3:
					pilha.exibe();
				}
			} while (opc != 0);
		}
	}
}