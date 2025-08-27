public class Ex1 {

    public static void main(String[] args) {
        
        //teste do método banana
        int[] v1 = {1, 2, 3, 4, 5};
        int resultadoBanana = banana(v1);
        System.out.println("Resultado da operaçao BANANA: " + resultadoBanana);
        
        //teste do método abacate
        int[] v2 = {1, 3, 5, 1, 8, 0, -1, 5, 9, 20, 1, 5};
        int p = 5;
        int resultadoAcabate = abacate(v2, p);
        System.out.println("REsultado da operação ABATATE: " + resultadoAcabate);

        //teste do método morango
        int v3[] = {4, 2, 3, 6, 7, 2, 4, 9, 0 , 1, 20, -3, -666, 333, 2, 0, 24};
        int p1, p2;
        p1 = 0;
        p2 = -6;

        System.out.println("Procurando por p1 (0): " + morango(v3, p1));
        System.out.println("Procurando por p2 (-6): " + morango(v3, p2));

    }


    //soma todos os alementos do vetor
    public static int banana(int[] a) { //a é um vetor de inteiros; possível nome: somaElementosVetor
        int uva = 0; //recebe a soma de cada elemento pelo laço
        int i = 0;
        while (i < a.length) {
            uva = uva + a[i];
            i++;
        }
        return uva;
    }

    //conta a quantidade de ocorrência de P em um vetor A
    public static int abacate(int[] a, int p) {
        int i = 0;
        int pera = 0; // veriavel aux. para contagem de ocorrência de p em a
        while (i < a.length) {
            if (a[i] == p) // verifica se a posicao i de a é igual a p
                pera++; //é o mesmo que pera = pera + 12
            i++;
        }
        return pera;
    }

    // mostra a posicao de um valor P em um vetor a, se existir (a primeira ocorrência)
    // caso não encontre, retorna -1
    public static int morango(int[] a, int p) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == p)
                return i;
        return -1;
    }
}