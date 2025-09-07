package projeto.br.mack.vetores.ordenacao;

public class OrdenacaoVetor {

    public static void bubbleSort(int[] v) {
        int contador = 0;
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = 0; j < v.length - 1 - i; j++) {
                if (v[j] > v[j + 1]) {
                    int temp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = temp;
                    contador++;
                }
            }
        }
        System.out.println("Qtd de trocas (bouble sort): " + contador);
    }

    public static void insertionSort(int[] v) {
        int contador = 0;
        for (int i = 1; i < v.length; i++) {
            int chave = v[i];
            int j = i - 1;
            while (j >= 0 && v[j] > chave) {
                v[j + 1] = v[j];
                j--;
                contador++;
            }
            v[j + 1] = chave;
        }
        System.out.println("Qtd de trocas (insertion sort): " + contador);
    }

    public static void selectionSort(int[] v) {
        int contador = 0;
        for (int i = 0; i < v.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[min]) {
                    min = j;
                    contador++;
                }
            }
            int temp = v[i];
            v[i] = v[min];
            v[min] = temp;
            contador++;
        }
        System.out.println("Qtd de trocas (selection sort): " + contador);
    }

    public static void mergeSort(int[] a, int n, int contador) {
        if (n < 2) {
            contador++;
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }

        mergeSort(l, mid, contador++);
        mergeSort(r, n - mid, contador++);

        contador++;
        merge(a, l, r, mid, n - mid, contador);
    }

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right, int contador) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                contador++;
            } else {
                a[k++] = r[j++];
                contador++;
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    private static int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo)
                i++;
            else if (pivo < vetor[f])
                f--;
            else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }
}
