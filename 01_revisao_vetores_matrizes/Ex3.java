public class Ex3 {

    public static void main(String[] args) {
        
        //declaracao do vetor de tamanho 10
        int[] vetor = new int[10];

        //popular o vetor
        for(int i = 0; i < vetor.length; i++) { 
            vetor[i] = i + 1;
        }

        //imprimir o vetor
        System.out.println("Imprimindo os valores do vetor...: ");
        for(int i = 0; i < vetor.length; i++) {
            System.out.println(vetor[i]);
        }
        
    }

}
