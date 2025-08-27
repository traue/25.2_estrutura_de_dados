public class Ex2 {
    public static void main (String[] args) {
        int[] bob = make(5); // bob = [1, 2, 3, 4, 5]
        dub(bob); // dobra cada um dos valores de bob (passagem por referência)
                  // [2, 4, 6, 8, 10]

        System.out.println(mus(bob)); // <<- LINHA AMARELA!!!! -> 30
        // 2 + 4 + 6 + 8 + 10 = 30
    }

    // cria um vetor de tamanho N e o popula de 1 (na posicao 0) até N (na posção n -1)
    public static int[] make (int n) {
        int[] a = new int[n]; //é criado um vetor de tamanho N aqui. Neste caso, 5
        for (int i = 0; i < n; i++)
            a[i] = i + 1; // popula o vetor a, conforme: [1, 2, 3, 4, 5]
        return a; //devolve para quem chamou (main) o vetor completo e populado 
    }

    // dobra o valor de cada uma das posicoes do vetor. Atenção: NADA É RETORNADO
    // mas por estarmos em um contexto estãtico, o vetor original é alterado
    public static void dub (int[] jub) {
        for (int i = 0; i < jub.length; i++) // percorre o vetor recebido inteiro
            jub[i] *= 2; //é o mesmo que jub[i] = jub[i] * 2 
    }

    // está somando todos os valores do vetor na variável fus
    public static int mus (int[] zoo) {
        int fus = 0;
        for (int i = 0; i < zoo.length; i++)
            fus = fus + zoo[i]; // fus recebe o valor da posicao mais seu valor original
        return fus;
    }
}