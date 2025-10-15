package ed.tad;

public class ListaLigada {

	private No inicio;

	public boolean push(No e) {
		try {
			e.setProximo(inicio);
			inicio = e;
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	public No pop() {

		if (inicio == null) {
			return null;
		}

		No e = inicio;
		inicio = e.getProximo();
		e.setProximo(null);
		return e;

	}

}
