package ed.view;

import java.util.Scanner;

import ed.tad.Pilha;

public class Principal {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			Pilha pilha = new Pilha();
			String msg = "1 - Adicionar\n2 - Remover\n3 - Exibir\n0 - Sair";
			int opc, e;

			do {
				System.out.println(msg);
				opc = sc.nextInt();

				switch (opc) {
				case 1:
					System.out.print("Elemento: ");
					e = sc.nextInt();
					pilha.adiciona(e);
					break;
				case 2:
					System.out.println("Removido: " + pilha.remove());
					break;
				case 3:
					pilha.exibe();
					break;
				}
			} while (opc != 0);
		}
	}
}
