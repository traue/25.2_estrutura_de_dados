package ed.tad;

public class No {

	private String nome;
	private int numero;
	private No proximo;

	public No(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
		this.proximo = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public No getProximo() {
		return proximo;
	}

	public void setProximo(No proximo) {
		this.proximo = proximo;
	}

}
