# Revisão de ordenação e busca binária


## Revisão — Principais Algoritmos de Ordenação de Vetores

Antes de entendermos a **busca binária**, é importante revisar os principais algoritmos de **ordenação**, pois a busca binária só funciona em **coleções ordenadas**. 

---

### 1) Bubble Sort

- Compara pares adjacentes e os troca se estiverem fora de ordem.  
- Repetido até que nenhuma troca seja necessária.  
- Complexidade: **O(n²)** no pior caso.

#### Exemplo em Java

```java
public static void bubbleSort(int[] v) {
    for (int i = 0; i < v.length - 1; i++) {
        for (int j = 0; j < v.length - 1 - i; j++) {
            if (v[j] > v[j + 1]) {
                int temp = v[j];
                v[j] = v[j + 1];
                v[j + 1] = temp;
            }
        }
    }
}
```

---

### 2) Insertion Sort

- Constrói a lista ordenada **inserindo** cada elemento no lugar correto.  
- Eficiente para listas pequenas ou quase ordenadas.  
- Complexidade: **O(n²)** no pior caso.

#### Exemplo em Java
```java
public static void insertionSort(int[] v) {
    for (int i = 1; i < v.length; i++) {
        int chave = v[i];
        int j = i - 1;
        while (j >= 0 && v[j] > chave) {
            v[j + 1] = v[j];
            j--;
        }
        v[j + 1] = chave;
    }
}
```

---

### 3) Selection Sort

- Encontra o **menor elemento** e coloca na primeira posição.  
- Repete para cada posição seguinte.  
- Complexidade: **O(n²)** em todos os casos.

#### Exemplo em Java
```java
public static void selectionSort(int[] v) {
    for (int i = 0; i < v.length - 1; i++) {
        int min = i;
        for (int j = i + 1; j < v.length; j++) {
            if (v[j] < v[min]) min = j;
        }
        int temp = v[i];
        v[i] = v[min];
        v[min] = temp;
    }
}
```

---

### 4) Merge Sort

- Estratégia **Dividir para Conquistar**.  
- Divide o vetor em dois, ordena recursivamente, depois mescla.  
- Complexidade: **O(n log n)** no pior caso.

#### Exemplo em Java (resumido)
```java
public static void mergeSort(int[] v, int ini, int fim) {
    if (ini < fim) {
        int meio = (ini + fim) / 2;
        mergeSort(v, ini, meio);
        mergeSort(v, meio + 1, fim);
        merge(v, ini, meio, fim);
    }
}
```

---

### 5) Quick Sort
- Escolhe um **pivô**, divide em menores/maiores que o pivô e ordena recursivamente.  
- Complexidade: **O(n log n)** em média, **O(n²)** no pior caso (se pivô for mal escolhido).

#### Exemplo em Java (resumido)
```java
public static void quickSort(int[] v, int ini, int fim) {
    if (ini < fim) {
        int p = particiona(v, ini, fim);
        quickSort(v, ini, p - 1);
        quickSort(v, p + 1, fim);
    }
}
```

---

###  Comparação rápida

| Algoritmo     | Melhor caso | Pior caso | Estável | Observações |
|---------------|-------------|-----------|---------|-------------|
| Bubble Sort   | O(n)        | O(n²)     | Sim     | Didático, mas pouco usado |
| Insertion Sort| O(n)        | O(n²)     | Sim     | Bom p/ listas pequenas |
| Selection Sort| O(n²)       | O(n²)     | Não     | Simples, mas ineficiente |
| Merge Sort    | O(n log n)  | O(n log n)| Sim     | Uso de memória extra |
| Quick Sort    | O(n log n)  | O(n²)     | Não     | Muito eficiente na prática |

---

# Busca Binária

Essencialmente há duas formas de realizar buscas em vetores: Busca linerar (onde percorre-se cada elemento, posição a posição, comparando-a com o valor buscado) e a **busca binária**, que é mais eficiente, mas requer que o vetor esteja ordenado, conforme veremos a seguir.


## 1) O que é e quando usar

A **busca binária** é um algoritmo para **encontrar um elemento** em uma **coleção ORDENADA** (array, lista, etc.).
A ideia é **dividir para conquistar**: a cada passo, comparamos o alvo (`chave`) com o elemento do **meio** e descartamos **metade** do espaço de busca.

- Pré‑requisito fundamental: **a coleção deve estar ordenada** em ordem crescente (ou decrescente, adaptando as comparações).
- Vantagem: reduz o número de comparações de `O(n)` (busca linear) para `O(log n)` (isso é MUITA coisa!)

---

## 2) Intuição (visual)

Suponha o vetor ordenado `v = [3, 7, 10, 14, 21, 30, 42]` e queremos buscar `21`.

Passo a passo:

1. **Meio**: índice `3` (elemento `14`). Como `21 > 14`, descartamos a **metade esquerda**.
2. Novo intervalo: `[21, 30, 42]`. **Meio** agora é `30`. Como `21 < 30`, descartamos a **metade direita**.
3. Sobra `[21]`. Encontrado no índice `4`.

Cada comparação **remove metade** dos candidatos.

```
[ 3,  7, 10, 14, 21, 30, 42 ]
            ^
            14  -> 21 > 14 → procura à direita
                 [ 21, 30, 42 ]
                   ^
                   30  -> 21 < 30 → procura à esquerda
                 [ 21 ]
                   ^
                   21  -> encontrado (índice 4)
```

---

## 3) Pseudocódigo (iterativo)

```text
binária(vetor v, inteiro chave):
    ini ← 0
    fim ← tamanho(v) - 1

    enquanto ini ≤ fim:
        meio ← (ini + fim) // 2   // divisão inteira
        se v[meio] == chave:
            retorne meio
        senão se v[meio] < chave:
            ini ← meio + 1        // busca na metade direita
        senão:
            fim ← meio - 1        // busca na metade esquerda

    retorne -1  // não encontrado
```

---

## 4) Implementações em Java

### 4.1) Versão iterativa
```java
public class BuscaBinaria {

    public static int buscaBinaria(int[] v, int chave) {
        int ini = 0, fim = v.length - 1;

        while (ini <= fim) {
            int meio = ini + (fim - ini) / 2; // evita overflow
            if (v[meio] == chave) {
                return meio;
            } else if (v[meio] < chave) {
                ini = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {3, 7, 10, 14, 21, 30, 42};
        int pos = buscaBinaria(a, 21);
        System.out.println("Posição: " + pos); // esperado: 4
    }
}
```

### 4.2) Versão recursiva
```java
public class BuscaBinariaRecursiva {

    public static int busca(int[] v, int chave) {
        return buscaRec(v, chave, 0, v.length - 1);
    }

    private static int buscaRec(int[] v, int chave, int ini, int fim) {
        if (ini > fim) return -1;
        int meio = ini + (fim - ini) / 2;
        if (v[meio] == chave) return meio;
        if (v[meio] < chave)  return buscaRec(v, chave, meio + 1, fim);
        else                  return buscaRec(v, chave, ini, meio - 1);
    }

    public static void main(String[] args) {
        int[] a = {3, 7, 10, 14, 21, 30, 42};
        int pos = busca(a, 30);
        System.out.println("Posição: " + pos); // esperado: 5
    }
}
```

---

## 5) Propriedade-chave (Invariante) e Correção
**Invariante do laço:** em qualquer iteração, se a `chave` estiver no vetor, então ela está no **intervalo [ini, fim]**.
- Inicialmente, o intervalo cobre todo o vetor.
- A cada passo, atualizamos `ini` ou `fim` de modo que a `chave` permaneça (se existir) dentro do novo subintervalo.
- O algoritmo termina porque o intervalo **encolhe** a cada iteração; se `ini` ultrapassa `fim`, não há elemento.

---

## 6) Complexidade
- **Tempo:** `O(log n)` no pior caso (divide o espaço de busca ao meio a cada passo).
- **Espaço:** `O(1)` para a versão iterativa; `O(log n)` na recursiva (pela pilha de chamadas).

---

## 7) Casos de borda importantes
1. **Vetor vazio** (`n = 0`): deve retornar `-1`.
2. **Elemento no início ou no fim**: garante que as condições de parada funcionam corretamente.
3. **Elementos repetidos**: a busca encontra **uma** ocorrência; para primeira/última ocorrência, ajuste a lógica após o acerto.
4. **Overflow de meio**: use `meio = ini + (fim - ini) / 2` em inteiros grandes.
5. **Ordem decrescente**: inverta as comparações (`>`/`<`) ou normalize antes.

---

## 8) Erros comuns (e como evitar)
- **Esquecer que o vetor precisa estar ordenado.**
- **Condição do laço errada** (`ini < fim` em vez de `ini <= fim`) — pode deixar de checar o último elemento.
- **Atualizar índices de forma incorreta** (ex.: `ini = meio` em vez de `meio + 1`).
- **Calcular `meio` com `(ini + fim)/2` sem cuidado** em inteiros grandes (overflow).

---

## 9) Comparação com Busca Linear
| Critério            | Busca Linear | Busca Binária |
|---------------------|--------------|---------------|
| Pré‑requisito       | nenhum       | vetor ordenado |
| Pior caso (tempo)   | `O(n)`       | `O(log n)`     |
| Acesso aleatório    | opcional     | recomendado (arrays) |
| Simplicidade        | alta         | média          |

---

## 10) Mini‑exercícios 

1. **Vetor:** `[2, 4, 6, 8, 10, 12]`, **chave:** `8` → **índice esperado:** `3`  
2. **Vetor:** `[5, 9, 13, 21, 34, 55]`, **chave:** `1` → **não encontrado** (`-1`)  
3. **Vetor:** `[1, 2, 2, 2, 3]`, **chave:** `2` → **qualquer índice 1..3** está correto na versão simples.  
   - **Desafio:** adapte para retornar a **primeira ocorrência**. *Dica:* ao encontrar `2`, continue buscando à **esquerda**.

---

## 11) Variante: primeira e última ocorrência (opcional)
Para **primeira ocorrência** de `chave`:
- Guarde a posição quando encontrar (`ans = meio`) e continue a busca em `[ini, meio-1]`.
Para **última ocorrência**: semelhante, mas continue em `[meio+1, fim]`.

---

## 12) Quando NÃO usar busca binária
- Coleção **desordenada** sem custo/tempo para ordenar.
- Estruturas com **acesso sequencial** puro (ex.: listas ligadas), onde ir ao “meio” é caro.  
  - Alternativas: **salvamento em array**, **árvores balanceadas**, **tabelas de hash** (quando apropriado).

---

### Referência rápida (cheat‑sheet)
- Pré‑requisito: **ordenado**.
- Laço: `while (ini <= fim)`.
- Meio seguro: `ini + (fim - ini) / 2`.
- Move limites:  
  - `v[meio] < chave` → `ini = meio + 1`  
  - `v[meio] > chave` → `fim = meio - 1`
- Retorno: índice ou `-1`.

---

# Atividade: Caça ao tesouro


## Contexto

Imagine que você está desenvolvendo um sistema de gerenciamento de itens raros em um jogo digital.
O inventário de cada jogador é armazenado em ordem crescente de IDs dos itens (números inteiros únicos).

Um jogador precisa encontrar rapidamente um item específico (seu “Mapa do Tesouro”) dentro de seu inventário, que pode ter milhares de elementos.

Implementar uma busca sequencial seria ineficiente. Por isso, o sistema utiliza a busca binária para localizar o item de forma rápida.

---

## Desafio


Implemente um programa em Java que:
 1.	Possua um vetor de IDs de itens ordenados.
 2.	Permita que o usuário digite o ID do item que deseja buscar.
 3.	Use busca binária para verificar se o item está no inventário.
 4.	Informe:
    - A posição do item no vetor (se encontrado).
    - Uma mensagem adequada se o item não existir.



## Exemplo de execução


Valor dos itens

```
[101, 205, 356, 410, 478, 512, 630, 700, 815, 920]
```

Usuário busca pelo item: 478

Saída:
```
Item 478 encontrado na posição 4 (Mapa do Tesouro encontrado!)
```

Se o usuário buscasse por 999:

```
Item 999 não encontrado no inventário.
```

## Restrições

 - Entregar via Moodle (em pdf, com código-fonte e simulações)
 - Individual
 - Pazo: Vide moodle


