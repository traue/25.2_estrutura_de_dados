# 🌳 Atividade Prática: Utilizando uma Árvore Binária de Busca (BST)

Você já possui a implementação de uma **Árvore Binária de Busca (BST)**. Agora, vamos utilizá‑la para simular um pequeno sistema acadêmico que armazena números de matrícula (RA) de alunos.

O objetivo é explorar na prática operações fundamentais dessa estrutura.

---

## Tarefa

### 1) Inserir valores na BST

Insira os seguintes números de matrícula na sua BST:

```
1023, 985, 2001, 1500, 1200, 750, 3000, 2500
```

### 2) Exibir percursos da árvore

Mostre no console os valores da árvore utilizando:

- **In‑order**
- **Pre‑order**
- **Post‑order**

### 3) Buscar um RA informado pelo usuário

Via teclado (`Scanner`), peça ao usuário um RA e:

- Busque o valor na árvore
- Informe se o aluno existe ou não

Exemplo:

```java
Scanner input = new Scanner(System.in);
System.out.print("Digite um RA para buscar: ");
int ra = input.nextInt();

Node resultado = bst.search(bst.root, ra);

if (resultado != null) {
    System.out.println("Aluno encontrado: " + ra);
} else {
    System.out.println("Aluno NÃO encontrado.");
}
```

### 4) Remover valores da árvore

Remova **pelo menos dois RAs** da lista e, após cada remoção, exiba o percurso **in‑order**.

> Caso sua BST não tenha remoção implementada, simule: escreva o antes e depois no console e explique o que mudaria.

---

## Entrega

Envie (em PDF no Moodle) e individualmente:

- Código fonte
- Captura(s) de tela mostrando:
  - Percursos antes da remoção
  - Resultado da busca interativa
  - Percurso após cada remoção