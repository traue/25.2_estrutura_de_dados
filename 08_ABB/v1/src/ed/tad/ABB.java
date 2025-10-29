package ed.tad;

import java.util.Stack;

public class ABB {

	private No<?> raiz;
	private int qtd;

	public No<?> getRaiz() {
		return raiz;
	}

	public int getQtd() {
		return qtd;
	}

	// inserção não recursiva
	public boolean inserir(No<?> no) {

		if (raiz == null) {
			raiz = no;
			qtd = 1;
			return true;
		}

		No<?> cursor = raiz;

		while (true) {
			// verifica se há existe nó com o mesmo id
			if (no.getId() == cursor.getId()) {
				return false;
			}

			// caminho à esquerda
			if (no.getId() < cursor.getId()) {
				if (cursor.getEsq() == null) {
					cursor.setEsq(no);
					qtd++;
					return true;
				}
				cursor = cursor.getEsq();
				continue;
			}

			// caminho à direita
			if (no.getId() > cursor.getId()) {
				if (cursor.getDir() == null) {
					cursor.setDir(no);
					qtd++;
					return true;
				}
				cursor = cursor.getDir();
			}
		}
	}

	public No<?> remover(int id) {

		// verifica se a árvore está vazia
		if (qtd == 0) {
			return null;
		}

		// verifica se o nó é a raiz e não há filhos
		if (qtd == 1 && id == raiz.getId()) {
			No<?> no = raiz;
			raiz = null;
			qtd = 0;
			return no;
		}

		No<?>[] no = localizarNo(id);

		if (no[1] == null) {
			return null;
		}

		No<?> noPai = no[0]; // nó pai do nó a ser removido
		No<?> noId = no[1]; // nó a ser removido

		// filhos do nó a ser removido
		No<?> filhoIdEsq = noId.getEsq();
		No<?> filhoIdDir = noId.getDir();

		// 1º caso: O nó não possui filhos, ou seja, é um nó folha
		if (filhoIdEsq == null && filhoIdDir == null) {
			if (noId.getId() < noPai.getId()) { // caso nó a esquerda do pai
				noPai.setEsq(null);
			} else {
				noPai.setDir(null);
			}
		}
		// 2º caso: O nó possui apenas um filho
		else if (filhoIdEsq == null && filhoIdDir != null || filhoIdEsq != null && filhoIdDir == null) {

			// caso nó removido seja raiz
			if (noId == raiz) {
				if (filhoIdEsq != null) {
					raiz = filhoIdEsq;
				} else {
					raiz = filhoIdDir;
				}
			}
			// nó não é raiz...
			else {
				if (noId.getId() > noPai.getId()) { // nó removido é filho da direita
					if (filhoIdEsq != null) {
						noPai.setDir(filhoIdEsq);
					} else {
						noPai.setDir(filhoIdDir);
					}
				} else { // nó removido é filho da esquerda
					if (filhoIdEsq != null) {
						noPai.setEsq(filhoIdEsq);
					} else {
						noPai.setEsq(filhoIdDir);
					}
				}
			}

		}
		// Caso 3: o nó possui dois filho
		else if (filhoIdEsq != null && filhoIdDir != null) {

			// verifica se é nó raiz
			if (noId == raiz) {
				No<?>[] noE = maiorValorSubEsq(raiz);
				No<?>[] noD = menorValorSubDir(raiz);

				No<?>[] noSubst; // nó substituto
				if (Math.abs(raiz.getId() - noE[1].getId()) > Math.abs(raiz.getId() - noD[1].getId())) {
					noSubst = noE;
				} else {
					noSubst = noD;
				}

				remover(noSubst[1].getId());

				noSubst[1].setEsq(raiz.getEsq());
				noSubst[1].setDir(raiz.getDir());
				raiz.setEsq(null);
				raiz.setDir(null);
				No<?> ret = raiz;
				raiz = noSubst[1];
				qtd--;
				return ret;
			}

			/*
			 * Estabelecemos as seguintes correspondencias: noId = E noPai = pE maiorEsq[1],
			 * menorDir[1] = S maiorEsq[0], menorDir[0] = pS
			 *
			 */
			if (noId.getId() > noPai.getId()) { // o nó removido é filho a direita

				// 2
				No<?>[] maiorEsq = maiorValorSubEsq(noId);

				// 3
				noPai.setDir(maiorEsq[1]);

				// 4
				maiorEsq[1].setDir(noId.getDir());

				// 5
				if (maiorEsq[0] != noId) {
					maiorEsq[1].setEsq(noId.getEsq());
					maiorEsq[0].setDir(null);
				}

			} else { // o nó removido é filho a esquerda

				// 2
				No<?>[] menorDir = menorValorSubDir(noId);

				// 3
				noPai.setEsq(menorDir[1]);

				// 4
				menorDir[1].setEsq(noId.getEsq());

				// 5
				if (menorDir[0] != noId) {
					menorDir[1].setDir(noId.getDir());
					menorDir[0].setEsq(null);
				}

			}

		}

		qtd--;
		return noId;

	}

	/**
	 * Localiza um nó dado o seu ID
	 *
	 * @param id
	 * @return Um vetor de 2 posições com o nó pai (indice 0) e o nó correspondente
	 *         ao ID fornecido ou null se não for encontrado na arvore (indice 1).
	 *         Se na posição pai (indice 0) constar null significa que o nó é raiz.
	 *         Se na posição do nó (indice 1) constar null, significa que o nó
	 *         procurado não consta na árvore.
	 */
	public No<?>[] localizarNo(int id) {

		No<?>[] no = new No<?>[2];

		no[1] = raiz;

		while (true) {

			if (no[1] == null || no[1].getId() == id) {
				return no;
			}

			no[0] = no[1]; // nó analisado passa a condição de pai

			if (id < no[0].getId()) {
				no[1] = no[0].getEsq();
			} else {
				no[1] = no[0].getDir();
			}
		}

	}

	/**
	 * Obtém o maior id da subarvore esquerda.
	 *
	 * @param no
	 * @return
	 */
	private No<?>[] maiorValorSubEsq(No<?> no) {

		No<?>[] noa = new No<?>[2];

		noa[0] = no;
		noa[1] = no.getEsq(); // primeiro nó da subarvore esquerda

		// o nó maior está no ramo mais a direita da subarvore
		while (noa[1].getDir() != null) {
			noa[0] = noa[1];
			noa[1] = noa[1].getDir();
		}

		return noa;

	}

	/**
	 * Obtém o menor ID da subarvore direita.
	 *
	 * @param no
	 * @return
	 */
	private No<?>[] menorValorSubDir(No<?> no) {

		No<?>[] noa = new No<?>[2];

		noa[0] = no;
		noa[1] = no.getDir(); // primeiro nó da subarvore direita

		// o nó menor estó no ramo mais a esquerda da subarvore
		while (noa[1].getEsq() != null) {
			noa[0] = noa[1];
			noa[1] = noa[1].getEsq();
		}

		return noa;

	}

	// impresões iterativas (em teste)
	public void imprimirEmOrdem_ITE() {

		if (qtd == 0) {
			System.err.println("Arvore vazia!");
		} else {
			Stack<No<?>> pilha = new Stack<>();
			No<?> cursor = this.raiz;
			cursor.lado = 'X';
			pilha.push(cursor);
			boolean verLadoDir = true;

			boolean doWhile1 = true;
			while1: while (doWhile1) {
				// caminhar tudo à esquerda e imprimir o último
				// (se nao houver filho esquerdo, imprime o proóprio)
				if (cursor.getEsq() != null) {
					while (cursor.getEsq() != null) {
						cursor = cursor.getEsq();
						cursor.lado = 'E';
						pilha.push(cursor);
					}
				}
				System.out.println(cursor.getId() + " - ");

				while (true) {

					// verif se tem ramo a direita
					if (verLadoDir && cursor.getDir() != null) {
						cursor = cursor.getDir();
						cursor.lado = 'D';
						pilha.push(cursor);
						verLadoDir = true;
						// volta ao inicio do laco para processar todo o lado esquerdo desse novo nó
						// continue;
						continue while1;
					}

					// voltar - se volta de filho esquerdo, imprime o pai
					// retirar a si mesmo da pilha
					char filhoLado = pilha.pop().lado;
					if (pilha.empty()) {
						doWhile1 = false;
						break while1;
					}

					// pai
					cursor = pilha.peek();
					if (filhoLado == 'E') { // voltou do filho esquerdo, imprime o pai
						System.out.println(cursor.getId() + " - ");
						verLadoDir = true;
					} else {
						verLadoDir = false;
					}
				}

			}

			System.out.println("Fim");

		}

	}

	public void imprimirPreOrdem_ITE() {

		if (qtd == 0) {
			System.err.println("Arvore vazia");
		} else {

			Stack<No<?>> pilha = new Stack<>(); // pilha auxiliar
			No<?> cursor = this.raiz;
			cursor.lado = 'X';
			pilha.push(cursor);
			System.out.println(cursor.getId() + " - ");

			while (true) {

				// caminhar tudo a esquerda imprimindo
				while (cursor.getEsq() != null) {
					cursor = cursor.getEsq();
					cursor.lado = 'E';
					pilha.push(cursor);
					System.out.println(cursor.getId() + " - ");
				}

				// * verifica se o último tem ramo a direita
				if (cursor.getDir() != null) {
					cursor = cursor.getDir();
					cursor.lado = 'D';
					pilha.push(cursor);
					System.out.println(cursor.getId() + " - ");
					// volta ao inicio do laco para imprimir todo o lado esquerdo desse novo nó
					continue;
				}

				// voltar acima e, se voltou de filho esquerdo, verificar se tem filho direito
				while (!pilha.empty()) {
					// retirar a si mesmo da pilha
					char filhoLado = pilha.pop().lado;
					if (pilha.empty()) {
						break;
					}

					// pai
					cursor = pilha.peek();

					// se volta do filho direito, já cumpriu o codigo seguinte
					if (filhoLado == 'D') {
						continue;
					}

					// *er se o pai tem filho direito
					if (cursor.getDir() != null) {
						cursor = cursor.getDir();
						cursor.lado = 'D';
						pilha.push(cursor);
						System.out.println(cursor.getId() + " - ");
						// voltar a imprimir todo o ramo esquerdo desta nova raiz
						break;
					} else {
						continue;
					}
				}

				if (pilha.empty()) {
					break;
				}
			}
			System.out.println("FIM");

		}

	}

	public void imprimirPosOrdem_ITE() {

		if (qtd == 0) {
			System.out.println("*** Arvore vazia ***");
		} else {
			Stack<No<?>> pilha = new Stack<>();
			No<?> cursor = this.raiz;
			cursor.lado = 'X';
			pilha.push(cursor);
			boolean verLadoDir = true;

			while1: while (true) {

				// caminhar tudo à esquerda
				if (cursor.getEsq() != null) {
					while (cursor.getEsq() != null) {
						cursor = cursor.getEsq();
						cursor.lado = 'E';
						pilha.push(cursor);
					}
				}

				while (true) {
					// verif se tem ramo a direita
					if (verLadoDir) {
						if (cursor.getDir() != null) {
							cursor = cursor.getDir();
							cursor.lado = 'D';
							pilha.push(cursor);
							verLadoDir = true;
							// volta ao inicio do laco para processar todo o lado esquerdo desse novo nó
							continue while1;
						} else {
							System.out.println(cursor.getId() + " - ");
						}
					}

					// voltar - se volta de filho direito.imprime o pai
					// retirar a si mesmo da pilha
					char filhoLado = pilha.pop().lado;
					if (pilha.empty()) {
						break while1;
					}

					// pai
					cursor = pilha.peek();
					if (filhoLado == 'D') { // * voltou do filho direito, imprime o pai
						System.out.println(cursor.getId() + " - ");
						verLadoDir = false;
					} else {
						verLadoDir = true;
					}
				}

			}
			System.out.println("FIM");

		}

	}

	// impressões recursiva
	public void imprimeEmOrdem_REC(No<?> no) {
		if (no != null) {
			imprimeEmOrdem_REC(no.getEsq());
			System.out.println(no.getId() + " ");
			imprimeEmOrdem_REC(no.getDir());
		}
	}

	public void imprimePreOrdem_REC(No<?> no) {
		if (no != null) {
			System.out.println(no.getId() + " ");
			imprimePreOrdem_REC(no.getEsq());
			imprimePreOrdem_REC(no.getDir());
		}
	}

	public void imprimePosOrdem_REC(No<?> no) {
		if (no != null) {
			imprimePosOrdem_REC(no.getEsq());
			imprimePosOrdem_REC(no.getDir());
			System.out.println(no.getId() + " ");
		}
	}

	public No<?> maiorValor() {
		if (qtd == 0) {
			return null;
		}
		No<?> cursor = raiz;
		while (cursor.getDir() != null) {
			cursor = cursor.getDir();
		}
		return cursor;
	}

	public No<?> menorValor() {
		if (qtd == 0) {
			return null;
		}
		No<?> cursor = raiz;
		while (cursor.getEsq() != null) {
			cursor = cursor.getEsq();
		}
		return cursor;
	}

	/**
	 * Inserção recursiva
	 *
	 * Baseado em: https://www.scaler.com/topics/binary-tree-implementation-in-java/
	 *
	 * @param raizRamo No raiz de um determinado (sub)ramo da arvore. Para o caso da
	 *                 insercao na arvore, este metodo deve ser invocado com o nó
	 *                 raiz principal.
	 * @param noId     No candidato a ser inserido na arvore.
	 * @return <i>true</i> no caso de sucesso ou <i>false</i> em caso de já existir
	 *         o ID presente na arvore.
	 */
	public boolean insert(No<?> raizRamo, No<?> noId) {
		if (raizRamo == null) {
			raiz = noId;
			qtd++;
			return true;
		}
		if (noId.getId() < raizRamo.getId()) {
			if (raizRamo.getEsq() != null) {
				insert(raizRamo.getEsq(), noId);
			} else {
				raizRamo.setEsq(noId);
				qtd++;
				return true;
			}
		} else if (noId.getId() > raizRamo.getId()) {
			if (raizRamo.getDir() != null) {
				insert(raizRamo.getDir(), noId);
			} else {
				raizRamo.setDir(noId);
				qtd++;
				return true;
			}
		}
		return false;
	}

}
