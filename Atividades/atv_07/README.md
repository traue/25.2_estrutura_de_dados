# Atividade Teórica: Árvores Binárias de Busca (BST)

Nesta atividade, você vai analisar e refletir sobre o funcionamento das **Árvores Binárias de Busca (Binary Search Trees — BST)**, estruturas essenciais para armazenar e buscar informações de forma eficiente.

Uma BST segue a seguinte propriedade fundamental:

> **Valores menores que o nó atual ficam à esquerda; valores maiores, à direita.**

---

## Parte 1: Construção Conceitual

Dada a sequência de inserções:

```
50, 30, 70, 20, 40, 60, 80
```

1. Desenhe a árvore binária de busca formada após todas as inserções.
2. Identifique:
   - **Nó raiz**
   - **Nós folha**
   - **Altura da árvore** (considere raiz como nível 0)
3. Escreva as saídas dos percursos:
   - **In-order**
   - **Pre-order**
   - **Post-order**

> Explique brevemente, com suas palavras, o que cada tipo de percurso representa em termos de lógica do processo.

---

## Parte 2: Raciocínio Algorítmico

Considere o método Java abaixo, que realiza busca em uma BST:

```java
public Node search(Node root, int value) {
    if (root == null || root.value == value) {
        return root;
    }

    if (value < root.value) {
        return search(root.left, value);
    } else {
        return search(root.right, value);
    }
}
```

Responda:

a) Explique em suas palavras como o algoritmo decide qual nó visitar em seguida.  
b) Qual é a complexidade de tempo da busca em:
   - Uma árvore **balanceada**
   - Uma árvore **desbalanceada/degenerada**

> Justifique suas respostas de forma clara.

---

## Parte 3: Reflexão

1. Por que uma BST construída com inserções já ordenadas (ex.: `10, 11, 12, 13, 14...`) não é eficiente?
2. Cite um exemplo de situação real em que uma BST seria útil e explique o porquê.

---

### Entrega

Atividade individual.

Entregue essa atividade em PDF no Moodle da disciplina. 