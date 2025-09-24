public class Pilha<T> {
    private No<T> topo;

    public Pilha() {
        this.topo = null;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void empilhar(T dado) {
        No<T> novo = new No<>(dado);
        novo.setProx(topo);
        topo = novo;
    }

    public T desempilhar() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha está vazia!");
        }
        T dado = topo.getDado();
        topo = topo.getProx();
        return dado;
    }

    public T topo() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha está vazia!");
        }
        return topo.getDado();
    }
}