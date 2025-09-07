#  Listas

---

## 1) Objetivos

Ao final desta aula, você será capaz de:
- Explicar o conceito de **lista** e comparar **vetor dinâmico** (ArrayList) vs **lista ligada** (singly/doubly).
- Implementar, do zero, uma **lista ligada genérica** em Java (nó, encadeamento, operações básicas).
- Analisar **complexidade** (tempo e espaço) das principais operações.
- Utilizar **iteradores** e percursos, lidar com **casos de borda** (lista vazia, 1 elemento, índices inválidos).
- Resolver problemas típicos com listas (reversão, remoção, busca, detecção de ciclo, etc.).



## 2) Listas

Uma **lista** é uma coleção ordenada de elementos que permite inserções, remoções e acessos. Há diferentes implementações com *trade‑offs* distintos:

- **ArrayList (baseado em array dinâmico)**: acesso por índice é **O(1)**; inserções/remoções no **fim** costumam ser **amortizadas O(1)**; no **início/meio** podem custar **O(n)** (shift).
- **LinkedList (baseado em nós encadeados)**: inserções/remoções em posições conhecidas via ponteiros (ex.: início) podem ser **O(1)**; porém acesso por índice é **O(n)**.


### Quando escolher qual?
- Muitas leituras randômicas por índice → **ArrayList**.
- Muitas inserções/remoções nas extremidades (sobretudo início) → **LinkedList**.
- Memória: linked lists usam ponteiros e objetos por nó (overhead maior que array contíguo).


## 3) Modelos de Lista Ligada

### 3.1) Singly Linked List (encadeamento simples)
Cada nó conhece **apenas o próximo**.
```
[head] → [A] → [B] → [C] → null
```
- Inserir no início: O(1)
- Remover no início: O(1)
- Inserir/remover no fim: O(n) (sem ponteiro tail)

### 3.2) Doubly Linked List (duplamente encadeada)
Cada nó conhece **próximo** e **anterior**.
```
null ← [A] ⇄ [B] ⇄ [C] → null
```
- Facilita remoções no meio quando já temos o nó.
- Custa mais memória e cuidado ao manter dois ponteiros.

### 3.3) Variante Circular
O **último** aponta para o **primeiro** (útil para aplicações específicas, p.ex. Josephus).



## 4) Complexidade (Big‑O)

| Estrutura / Operação | Acesso por índice | Inserir fim | Inserir início | Inserir meio | Remover fim | Remover início | Remover meio | Busca (por valor) |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| **ArrayList**        | O(1) | Amort. O(1) | O(n) | O(n) | O(1) | O(n) | O(n) | O(n) |
| **Singly Linked**    | O(n) | O(n) * | O(1) | O(n) | O(n) * | O(1) | O(n) | O(n) |
| **Doubly Linked**    | O(n) | O(1) ** | O(1) | O(n) | O(1) ** | O(1) | O(n) | O(n) |

* Se **sem** ponteiro `tail`. Com `tail`, inserção no fim pode ser O(1).  
** Se mantivermos ponteiros `head` e `tail` e tamanho.

---

## 5) Interface do TAD Lista (mínima)
Vamos definir uma **interface** para guiar nossa implementação:

```java
public interface ListADT<T> {
    int size();
    boolean isEmpty();
    void addFirst(T value);
    void addLast(T value);
    void add(int index, T value);         // 0..size
    T get(int index);                      // 0..size-1
    T set(int index, T value);
    T removeFirst();
    T removeLast();
    T remove(int index);
    boolean contains(T value);
    int indexOf(T value);                  // -1 se não encontrado
    void clear();
}
```

> Observação: Poderíamos também estender `Iterable<T>` para usar `for-each`.

---

## 6) Implementação: Lista Ligada Simples (Genérica)
### 6.1) Classe `Node<T>`
```java
final class Node<T> {
    T value;
    Node<T> next;
    Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}
```

### 6.2) Implementação `SinglyLinkedList<T>`
```java
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> implements ListADT<T>, Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void addFirst(T value) {
        Node<T> n = new Node<>(value, head);
        head = n;
        if (tail == null) tail = n;
        size++;
    }

    @Override
    public void addLast(T value) {
        Node<T> n = new Node<>(value, null);
        if (tail == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    @Override
    public void add(int index, T value) {
        checkPositionIndex(index); // permite index == size
        if (index == 0) { addFirst(value); return; }
        if (index == size) { addLast(value); return; }
        Node<T> prev = nodeAt(index - 1);
        Node<T> n = new Node<>(value, prev.next);
        prev.next = n;
        size++;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return nodeAt(index).value;
    }

    @Override
    public T set(int index, T value) {
        checkElementIndex(index);
        Node<T> n = nodeAt(index);
        T old = n.value;
        n.value = value;
        return old;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Lista vazia.");
        T val = head.value;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Lista vazia.");
        if (size == 1) return removeFirst();
        Node<T> prev = nodeAt(size - 2);
        T val = Objects.requireNonNull(prev.next).value;
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();
        Node<T> prev = nodeAt(index - 1);
        Node<T> target = prev.next;
        prev.next = target.next;
        size--;
        return target.value;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        int i = 0;
        for (Node<T> cur = head; cur != null; cur = cur.next, i++) {
            if (Objects.equals(cur.value, value)) return i;
        }
        return -1;
    }

    @Override
    public void clear() {
        // Opcional: varrer para ajudar GC em ambientes críticos
        Node<T> cur = head;
        while (cur != null) {
            Node<T> nxt = cur.next;
            cur.next = null;
            cur.value = null;
            cur = nxt;
        }
        head = tail = null;
        size = 0;
    }

    // ==== Utilidades Internas ====
    private Node<T> nodeAt(int index) {
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur;
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
    }
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
    }

    // ==== Iterador (for-each) ====
    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            Node<T> cur = head;
            @Override public boolean hasNext() { return cur != null; }
            @Override public T next() {
                if (cur == null) throw new NoSuchElementException();
                T v = cur.value;
                cur = cur.next;
                return v;
            }
        };
    }

    // ==== (Exercício) toString ====
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> cur = head;
        while (cur != null) {
            sb.append(cur.value);
            cur = cur.next;
            if (cur != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
```

### 6.3) Pequeno `main` de teste manual
```java
public class TEsteLinkedList {
    public static void main(String[] args) {
        ListADT<String> list = new SinglyLinkedList<>();
        list.addFirst("C");
        list.addFirst("B");
        list.addFirst("A"); // A, B, C
        list.addLast("D");  // A, B, C, D
        list.add(2, "X");   // A, B, X, C, D

        System.out.println("size=" + list.size()); // 5
        System.out.println("indexOf(X)=" + list.indexOf("X")); // 2
        System.out.println("get(3)=" + list.get(3)); // C
        System.out.println(list); // [A, B, X, C, D]

        for (String s : (SinglyLinkedList<String>) list) {
            System.out.print(s + " "); // A B X C D
        }
        System.out.println();

        list.remove(2);     // remove X
        list.removeFirst(); // remove A
        list.removeLast();  // remove D

        for (String s : (SinglyLinkedList<String>) list) {
            System.out.print(s + " "); // B C
        }
        System.out.println();
    }
}
```

> Dica: teste com tipos **imutáveis** (`String`, `Integer`) e **mutáveis** (classes suas). Verifique `equals` corretamente.


## 7) Lista Duplamente Ligada
Esboço de nó:
```java
final class DNode<T> {
    T value;
    DNode<T> next, prev;
    DNode(T value) { this.value = value; }
}
```
- Manutenção dupla (`next`/`prev`) em inserções e remoções.
- Com ponteiros `head`/`tail`, `addFirst`/`addLast` e `removeFirst`/`removeLast` ficam O(1).



## 8) Boas Práticas & Armadilhas
- **Casos de borda**: lista vazia; 1 elemento; índices 0 e `size-1`.
- **Atualizar `tail`** em inserções/remoções no fim.
- **Fugas de memória**: em Java o GC ajuda, mas **desconectar** referências acelera liberação em estruturas grandes (como feito no `clear()`).
- **Iteradores inválidos**: se a estrutura muda durante iteração, seu iterador básico não é *fail‑fast* (diferente do `ArrayList`/`LinkedList` da JDK).
- **Complexidade**: evite percorrer mais de uma vez por operação (reutilize ponteiros locais).


## 9) Quando usar essas TADs (cenários práticos)

### 9.1) `ArrayList` (vetor dinâmico)
- Preferir quando: precisa de **acesso randômico O(1)** por índice; faz muitas **leituras/iterações**; o tamanho é **previsível** ou cresce por “rajadas”; fará **ordenação** e **busca binária**; quer melhor **localidade de cache**.
- Evitar quando: há **muitas inserções/remoções no início ou meio** de listas grandes (custo de deslocamento O(n)).

### 9.2) Lista Ligada Simples (Singly)
- Preferir quando: faz **muitas inserções/remoções no início**; constrói/consome como **pilha/stream**; precisa **concatenar** listas sem copiar (ligando `tail.next`); remove **nó conhecido** tendo o **predecessor**; tamanho é desconhecido e cresce **incrementalmente**.
- Evitar quando: há **acesso frequente por índice** ou saltos aleatórios (custo O(n)).

### 11.3) Lista Duplamente Ligada (Doubly)
- Preferir quando: precisa de **navegação bidirecional**; remoções/insert no **meio em O(1)** com o nó em mãos (sem precisar do predecessor); implementar **Deque**; **LRU Cache** (clássico: `HashMap` + doubly linked list); **editor com cursor**.
- Custo: **mais memória** (dois ponteiros por nó) e **maior complexidade** de manutenção.

### 9.4) Lista Circular
- Preferir quando: precisa de **varredura contínua/round‑robin** (agendadores, jogos tipo **Josephus**); deseja um **nó sentinela** para simplificar casos de borda.
- Observação: para **buffers circulares**, um **array circular** costuma ser mais eficiente em cache do que lista ligada.

### 9.5) Dicas práticas (JDK e escolha rápida)
- Para a maioria dos usos de lista em aplicações, **`ArrayList`** é a escolha padrão. Use **`LinkedList`** apenas se o perfil de uso acima justificar.
- Para **pilhas/filas**, prefira **`ArrayDeque`** a `LinkedList` (melhor desempenho e menor overhead).
- Se a necessidade real é “buscar por chave”, considere **`Map`**/`Set` em vez de listas.
- Para cenários com **muita leitura e pouca escrita** e necessidade de thread‑safety, considere **`CopyOnWriteArrayList`**.
- Regra de bolso: se você faz muitas operações no meio/início e quase **não acessa por índice**, avalie uma **linked list**; se você acessa por índice com frequência e altera pouco no meio, vá de **ArrayList**.

> Sempre **meça** com dados reais do seu caso (tamanho, padrão de acesso, GC) antes de decidir definitivamente.
