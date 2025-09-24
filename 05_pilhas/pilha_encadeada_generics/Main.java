public class Main {
    public static void main(String[] args) {
        // Pilha de inteiros
        Pilha<Integer> pilhaInt = new Pilha<>();
        pilhaInt.empilhar(10);
        pilhaInt.empilhar(20);
        System.out.println(pilhaInt.desempilhar()); // 20

        // Pilha de strings
        Pilha<String> pilhaStr = new Pilha<>();
        pilhaStr.empilhar("A");
        pilhaStr.empilhar("B");
        System.out.println(pilhaStr.topo()); // B
    }
}