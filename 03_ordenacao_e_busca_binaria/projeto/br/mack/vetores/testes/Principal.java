package projeto.br.mack.vetores.testes;

import static projeto.br.mack.vetores.ordenacao.OrdenacaoVetor.bubbleSort;
import static projeto.br.mack.vetores.ordenacao.OrdenacaoVetor.insertionSort;
import static projeto.br.mack.vetores.ordenacao.OrdenacaoVetor.mergeSort;
import static projeto.br.mack.vetores.ordenacao.OrdenacaoVetor.selectionSort;
import static projeto.br.mack.vetores.util.VetorUtils.imprimirVetor;
import static projeto.br.mack.vetores.ordenacao.OrdenacaoVetor.quickSort;

public class Principal {
    public static void main(String[] args) {
        int v[] = { 15, -1, 5, 10, 8, 15, 7, 6, 0, -8, 67, 11, 13, 14, 17 };
        imprimirVetor(v);
        bubbleSort(v);
        imprimirVetor(v);

        int w[] = { 15, -1, 5, 10, 8, 15, 7, 6, 0, -8, 67, 11, 13, 14, 17 };
        insertionSort(w);
        imprimirVetor(w);

        int z[] = { 15, -1, 5, 10, 8, 15, 7, 6, 0, -8, 67, 11, 13, 14, 17 };
        selectionSort(z);
        imprimirVetor(z);

        int t[] = { 15, -1, 5, 10, 8, 15, 7, 6, 0, -8, 67, 11, 13, 14, 17 };
        int contador = 0;
        mergeSort(t, 15, contador);
        imprimirVetor(t);
        System.out.println("Qtd de trocas (merge sort): " + contador);

        int q[] = { 15, -1, 5, 10, 8, 15, 7, 6, 0, -8, 67, 11, 13, 14, 17 };
        quickSort(q, 0, 14);
        imprimirVetor(q);

        // Testes de busca
        int busca = 15;
        int posicao = projeto.br.mack.vetores.busca.BuscaLinear.buscar(v, busca);
        System.out.println("Busca Linear: Valor " + busca + " encontrado na posicao: " + posicao); 
        posicao = projeto.br.mack.vetores.busca.BuscaBinaria.buscar(v, busca);
        System.out.println("Busca Binaria Iterativa: Valor " + busca + " encontrado na posicao: " + posicao);
        posicao = projeto.br.mack.vetores.busca.BuscaBinaria.buscaBinariaRecursiva(v, busca, 0, v.length - 1);
        System.out.println("Busca Binaria Recursiva: Valor " + busca + " encontrado na posicao: " + posicao);

    }

}