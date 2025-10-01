public class Fila<T> {

	private int inicio;
	private int fim;
	private int operacao;
	private final int capacidade;
	private final T[] fila;

	@SuppressWarnings("unchecked")
	public Fila(int tamanho) {
		this.inicio = 0;
		this.fim = 0;
		this.operacao = 0;
		this.capacidade = tamanho;
		// Criação de array genérico requer cast
		this.fila = (T[]) new Object[tamanho];
	}

	public boolean isEmpty() {
		return this.inicio == this.fim && this.operacao <= 0;
	}

	public boolean isFull() {
		return this.inicio == this.fim && this.operacao == 1;
	}

	public T peek() {
		if (this.isEmpty()) {
			return null;
		}
		return this.fila[this.inicio];
	}

	public String enqueue(T elem) {
		if (this.isFull()) {
			return "OPS...: a fila já está cheia";
		}

		this.fila[this.fim] = elem;
		this.fim = (this.fim + 1) % this.capacidade;
		this.operacao = 1;
		return "Elemento inserido!";
	}

	public T dequeue() {
		if (this.isEmpty()) {
			return null;
		}

		T elem = this.peek();
		this.inicio = (this.inicio + 1) % this.capacidade;
		this.operacao = -1;
		return elem;
	}

	public int size() {
		int s = 0;

		// caso inicio venha antes de fim
		if (this.inicio < this.fim) {
			s = this.fim - this.inicio;
		} else if (this.inicio == this.fim) { // caso inicio == fim
			if (this.isEmpty()) {
				s = 0;
			} else {
				s = this.capacidade;
			}
		} else if (this.inicio > this.fim) { // caso inicio venha depois de fim
			s = this.capacidade - this.inicio + this.fim;
		}

		return s;
	}

	public String print() {
		String ret = "";

		// verifica se está vazia
		if (this.isEmpty()) {
			ret = "A fila está vazia";
		} else if (this.inicio < this.fim) { // caso inicio venha antes do fim
			for (int i = this.inicio; i < this.fim; i++) {
				ret += this.fila[i] + " ";
			}
		} else if (this.isFull() || this.inicio > this.fim) { // fila está cheia OU inicio vem depois do fim
			for (int i = this.inicio; i < this.capacidade; i++) {
				ret += this.fila[i] + " ";
			}
			if (this.inicio > 0) {
				for (int i = 0; i < this.fim; i++) {
					ret += this.fila[i] + " ";
				}
			}
		}
		return ret.trim();
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	@Override
	public String toString() {
		return print();
	}
}
