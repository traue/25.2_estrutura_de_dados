package ed.view;

import java.util.Scanner;

import ed.tad.Fila;

public class Principal {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("\nInforme o tamanho maximo da fila: ");
			int tamanho = sc.nextInt();

			if (tamanho <= 0) {
				System.out.println("OPS...: tamanho invalido: " + tamanho);
				return;
			}

			Fila fila = new Fila(tamanho);

			while (true) {
				System.out.println("[1] - Inserir elemento");
				System.out.println("[2] - Remover elemento");
				System.out.println("[3] - Imprimir a fila");
				System.out.println("[4] - Informações da fila");
				System.out.println("[9] - Encerrar");

				System.out.print("Opcao: ");

				int opc = sc.nextInt();

				if (opc == 9) {
					break;
				} else if (opc == 1) {
					System.out.print("Elemento a inserir: ");
					int elem = sc.nextInt();
					System.out.println(fila.enqueue(elem));
				} else if (opc == 2) {
					Integer elemto = fila.dequeue();
					if (elemto == null) {
						System.out.println("OPS: a fila está vazia.");
					} else {
						System.out.println("Valor removido: " + elemto);
					}
				} else if (opc == 3) {
					System.out.print("Fila atual: ");
					System.out.println(fila.print());
				} else if (opc == 4) {

					System.out.println("Informacoes da fila:\n");
					System.out.println("Capacidade: " + fila.capa);
					System.out.println("Tamanho: " + fila.size());
					System.out.println("Primeiro elemento: " + fila.peek());
					System.out.println("Ponteiro INICIO:" + fila.inicio);
					System.out.println("Ponteiro FIM: " + fila.fim);
					System.out.println("Ultima operacao: " + fila.operacao);

				}

			}
			System.out.println("Programa finalizado");
		}

	}

}
