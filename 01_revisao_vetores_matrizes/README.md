# Lista de Exercícios - Revisão de Vetores e Matrizes


### Exercício 1

Para cada uma das seguintes funções, escreva uma frase que descreva o
que ela faz. Tente identificar o papel de cada variável na função.

``` java
public static int banana (int[] a) {
    int uva = 0;
    int i = 0;
    while (i < a.length) {
        uva = uva + a[i];
        i++;
    }
    return uva;
}

public static int abacate (int[] a, int p) {
    int i = 0;
    int pera = 0;
    while (i < a.length) {
        if (a[i] == p) pera++;
        i++;
    }
    return pera;
}

public static int morango (int[] a, int p) {
    for (int i = 0; i < a.length; i++)
        if (a[i] == p)
            return i;
    return -1;
}
```

---

### Exercício 2

Faça a análise completa do programa abaixo e indique o que será exibido
na tela na linha sinalizada em amarelo.

``` java
public class Exe2 {
    public static void main (String[] args) {
        int[] bob = make(5);
        dub(bob);
        System.out.println(mus(bob));
    }

    public static int[] make (int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i+1;
        return a;
    }

    public static void dub (int[] jub) {
        for (int i = 0; i < jub.length; i++)
            jub[i] *= 2;
    }

    public static int mus (int[] zoo) {
        int fus = 0;
        for (int i = 0; i < zoo.length; i++)
            fus = fus + zoo[i];
        return fus;
    }
}
```

---

### Exercício 3 - Vetor: Inicialização e Impressão

Escreva um programa em Java que cria um vetor de inteiros de tamanho 10
e o inicialize com os valores de 1 a 10. Exiba os valores do vetor, um
por linha.

---

### Exercício 4 - Vetor: Soma dos Elementos

Escreva um programa em Java que cria um vetor de inteiros de tamanho 5 e
o inicialize com valores fornecidos pelo usuário. Calcule e exiba a soma
dos valores do vetor.

---

### Exercício 5 - Vetor: Maior e Menor Valor

Escreva um programa em Java que cria um vetor de inteiros de tamanho 10
e o inicialize com valores aleatórios entre 1 e 100. Encontre e exiba o
maior e o menor valor presente no vetor.

---

### Exercício 6 - Vetor: Contagem de Pares e Ímpares

Escreva um programa em Java que cria um vetor de inteiros de tamanho 10
e o inicialize com valores aleatórios entre 1 e 20. Conte e exiba
quantos números são pares e quantos são ímpares.

---

### Exercício 7 - Matriz: Diagonal Principal

Faça um **método** que receba como parâmetro uma matriz quadrada de
números inteiros `M(5,5)` e retorne a soma dos elementos da diagonal
principal desta matriz.

---

### Exercício 8 - Matriz: Edifício Mack Vista

O edifício **MACK VISTA** tem 10 andares e em cada andar existem 8
apartamentos. O síndico deseja saber:

-   Quantidade de apartamentos vazios (nº moradores = 0)
-   Qual é o andar que tem o maior número de moradores
-   Quantidade total de moradores do prédio

Faça um programa em Java que simule esta situação:

-   Construa uma matriz `M(10x8)` representando o prédio.
-   Linha 0 ➔ 1º andar, linha 1 ➔ 2º andar e assim por diante.
-   Preencha a matriz com números aleatórios entre 0 e 6 (quantidade de
    moradores).
-   Exiba a matriz na tela.
-   Calcule e exiba os levantamentos solicitados pelo síndico.
