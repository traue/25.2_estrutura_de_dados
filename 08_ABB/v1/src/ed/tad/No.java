package ed.tad;

public class No<T> {

	public No(int id, T dados) {
		this.id = id;
		this.dados = dados;
	}

	private No<?> esq;
	private No<?> dir;
	private final int id;
	private final T dados;
	public char lado;

	public No<?> getEsq() {
		return esq;
	}

	public void setEsq(No<?> esq) {
		this.esq = esq;
	}

	public No<?> getDir() {
		return dir;
	}

	public void setDir(No<?> dir) {
		this.dir = dir;
	}

	public int getId() {
		return id;
	}

	public T getDados() {
		return dados;
	}

}
