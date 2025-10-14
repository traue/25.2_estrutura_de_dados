# Aula: Lista Encadeada (Linked List) em Java — com Genéricos e Testes


---

## 1. Objetivos de aprendizagem
Ao final desta aula, o estudante será capaz de:
- Explicar o que é uma **lista encadeada** e quando usá-la em vez de um `ArrayList`.
- Implementar uma **lista encadeada simples** genérica em Java.
- Analisar a **complexidade** (tempo e espaço) das operações.
- Escrever **testes unitários** cobrindo casos típicos e de borda.

---

## 2. Motivação e Intuição
Um `ArrayList` usa um **vetor contínuo**, e inserir/remover no meio exige deslocar elementos (O(n)).  
Uma **lista encadeada** usa **nós** que apontam para o **próximo**:

```
[head] -> [A|•] -> [B|•] -> [C|•] -> null
```

**Vantagens:**
- Inserir/remover no início é O(1).
- Crescimento dinâmico sem realocação.

**Desvantagens:**
- Acesso por índice é O(n).
- Usa mais memória.

**Quando usar:** muitas inserções/remoções no início, e acesso por índice não é essencial.

---

## 3. Interface planejada
A classe `LinkedList<T>` implementará:
- `addFirst`, `addLast`, `add(index, value)`
- `removeFirst`, `removeLast`, `remove(index)`, `remove(Object)`
- `get`, `set`, `contains`, `indexOf`
- `size`, `isEmpty`, `clear`, `toArray`
- `iterator` (para for-each)

Campos: `head`, `tail`, `size`.

---

## 4. Complexidade

| Operação | Tempo | Observações |
|-----------|--------|--------------|
| addFirst/removeFirst | O(1) | Simples ponteiro |
| addLast | O(1) | Mantendo tail |
| removeLast | O(n) | Necessita percorrer até penúltimo |
| add/remove/get(index) | O(n) | Caminhada até o índice |
| contains/indexOf | O(n) | Busca linear |
| size | O(1) | Contador |

---

## 5. Implementação em Java

### 5.1 Estrutura de pastas
```
src/
 ├── main/java/edu/mack/datastructures/LinkedList.java
 └── test/java/edu/mack/datastructures/LinkedListTest.java
```

### 5.2 Código da lista
```java
// Vide repositório
```
---

### 5.3 Testes JUnit 5
```java
// Vide repositório
```
---

## 6. Execução dos testes
### Maven
```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.10.2</version>
  <scope>test</scope>
</dependency>
```
Rodar: `mvn test`

### Gradle
```kotlin
testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
```
Rodar: `./gradlew test`

---

## 7. Demonstração
```java
var list = new LinkedList<String>();
list.addFirst("c");
list.addFirst("b");
list.addLast("d");
list.add(0, "a");
System.out.println(list.get(2)); // c
list.remove("b");
for (String s : list) System.out.print(s + " "); // a c d
```

---

## 8. Desafios
1. Implemente `reverse()` para inverter a lista.
2. Faça `kthFromEnd(int k)` retornando o elemento k-ésimo a partir do fim.
3. Implemente `removeDuplicates()` usando `HashSet<T>`.

---

## 9. Roteiro sugerido (90–120 min)
1. Introdução e motivação (10 min)
2. Modelo mental e desenho dos nós (15 min)
3. Implementação guiada (50 min)
4. Execução de testes e discussão (20 min)
5. Extensões e encerramento (10 min)

---

## 10. Extensões sugeridas
- Lista duplamente encadeada.
- Implementar `List<T>` da Java Collections.
- Iterador fail-fast.