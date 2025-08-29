# Projeto de Jogo: Labirinto LIFO – Chaves & Portas

Jogo single-player em terminal no qual o(a) aluno(a) navega por um labirinto (matriz) coletando chaves e abrindo portas com regras de pilha (LIFO). O jogo exige uso de vetores, matriz, lista linear, busca e ordenação e pilhas, com implementações próprias (sem `java.util.Stack`, `ArrayList`, `LinkedList` etc.).  

Vídeo de demosntração do esperado:

https://www.youtube.com/embed/zu9aQzv0C2g?si=kLT6LDZhFD7QwQeb

[![Watch the video](https://img.youtube.com/vi/zu9aQzv0C2g/maxresdefault.jpg)](https://youtu.be/zu9aQzv0C2g)


---

## 1) Objetivos de Aprendizagem
- Implementar estruturas de dados básicas do zero: pilha (LIFO) e lista linear (encadeada simples).  
- Manipular matriz como tabuleiro e vetores para ranking, inventário visual e placar de tesouros.  
- Aplicar busca (linear e binária) e ordenação (Insertion e Quick/Merge) no ranking.  
- Trabalhar I/O simples (arquivos .txt) e parâmetros de execução (seed, caminho do mapa).  
- Medir e argumentar sobre complexidade das operações.  

---

## 2) Visão Geral do Jogo
- O tabuleiro é lido de um arquivo texto como matriz de caracteres.
- **Símbolos**:
  - `#` parede | `.` piso | `S` início | `E` saída  
  - `a..z` chaves minúsculas (ex.: `a`)  
  - `A..Z` portas maiúsculas correspondentes (ex.: `A`)  
  - `$` tesouro (valor gerado por seed)  
  - `T` armadilha (aplica penalidade)  

- **Regras principais**:  
  - LIFO: só abre a porta `X` se o topo da pilha for a chave `x`. Ao abrir, faz `pop`.  
  - Inventário de chaves é uma pilha com capacidade limitada (definida no mapa).  
  - Cada passo gasta 1 ponto de energia.  
  - Abrir porta dá pontos; pegar tesouro dá pontos; cair em armadilha tira pontos.  
  - Objetivo: sair pelo `E` com a maior pontuação possível.  

---

## 3) Entradas & Execução

**Linha de comando:**

```bash
java -jar labirinto-lifo.jar --map=./mapas/map1.txt --seed=3 --player="Thiago"
```

- `--map` (obrigatório): caminho do arquivo de mapa.  
- `--seed` (opcional): controla distribuição/valores de tesouros e armadilhas.  
- `--player` (opcional): nome do jogador para ranking.  

---

## 4) Formato do Mapa (.txt)

- Primeira linha: `L C CAP` (linhas, colunas, capacidade da pilha).  
- Demais linhas: L linhas com C caracteres.  
- Linhas iniciadas por `;` são comentários.  

**Exemplo (map1.txt):**

```plaintext
; Labirinto de exemplo
7 12 5
############
#S..a...A..#
#..##..$...#
#..T...##..#
#...b..B...#
#..e....E..#
############
```

---

## 5) Mecânicas Detalhadas
- **Movimentos**: `W` (cima), `A` (esq.), `S` (baixo), `D` (dir.).  
- **Interações**:  
  - Chave `a`: `push('a')` se houver espaço.  
  - Porta `A`: abre se topo for `'a'`. Faz `pop()`.  
  - Tesouro `$`: soma valor pseudoaleatório (seed + posição).  
  - Armadilha `T`: penaliza pontos + registra no log (lista encadeada).  
  - Parede `#`: bloqueia.  
- **Undo (opcional)**: pilha de ações, limite 3.  

---

## 6) Estruturas de Dados (exigidas)
1. Matriz `char[][]` para o tabuleiro.  
2. Pilha própria `Stack<T>` (array-based).  
3. Lista encadeada simples (`SinglyLinkedList`) para log de eventos.  
4. Vetores para ranking (`ScoreEntry[]`) e tesouros.  
5. Ordenação: Insertion Sort + Quick/Merge.  
6. Busca binária por nome no ranking.  

**Proibido**: `Stack`, `ArrayList`, `LinkedList`, `Vector`, `Collections.sort`, `Stream.sorted`.  
**Permitido**: `Scanner`, `Random`, `Files`, `String`, `Arrays` (exceto sort), `System`.  

---

## 7) Arquitetura Sugerida (pacotes)

```
br.mack.labirinto
├─ app/
│  └─ Game.java
├─ core/
│  ├─ Board.java
│  ├─ Position.java
│  └─ RNG.java
├─ ds/
│  ├─ Stack.java
│  ├─ Node.java
│  └─ SinglyLinkedList.java
├─ model/
│  ├─ Inventory.java
│  ├─ ScoreEntry.java
│  └─ Scoreboard.java
├─ io/
│  ├─ MapLoader.java
│  └─ CLI.java
└─ util/
   └─ Sorts.java
```

---

## 8) Fluxo Principal (pseudo)

1. Ler `--map`, `--seed`, `--player`.  
2. Carregar matriz e posição inicial.  
3. Criar inventário (pilha).  
4. Loop de jogo: renderizar, ler comandos, aplicar regras, atualizar score.  
5. Persistir score no `ranking.csv`.  
6. Ordenar e exibir top 10.  
7. Permitir busca binária por nome.  

---

## 9) Esqueleto de Códigos

### 9.1 Pilha (array-based)
```java
public class Stack<T> {
    private final T[] data;
    private int top = -1;
    ...
}
```

### 9.2 Lista Encadeada
```java
public class SinglyLinkedList {
    private static class Node { ... }
    ...
}
```

### 9.3 Ordenação & Busca
```java
public class Sorts {
    public static void insertionSort(...) { ... }
    public static void quickSort(...) { ... }
    public static int binarySearchByName(...) { ... }
}
```

### 9.4 Carregador de Mapa
```java
public class MapLoader {
    public static MapData load(String path) throws Exception { ... }
}
```

---

## 10) Pontuação
- Passo: −1  
- Abrir porta: +15  
- Tesouro: +10..50  
- Armadilha: −20 + log  
- Concluir (`E`): +100  
- Bônus: +5 por chave não usada  

---


## 11) Entregáveis
1. Código-fonte organizado com Javadoc. Pode ser entregue o link de um repositório.
2. Relatório (≤4 págs.) com decisões, pseudocódigos, Big-O.  
3. Vídeo demo (≤5 min).  
4. Arquivos de mapas usados.  
5. `README.md` com instruções.  

---

## 12) Critérios de Avaliação
- Mecânica e pontuação: **40%**  
- Estruturas de dados próprias: **25%**  
- Ordenação e busca: **15%**  
- Qualidade de código: **10%**  
- Relatório + demo: **10%**  
- Bônus: Undo, visão centrada, novos símbolos, testes unitários.  

---

## 13) Sugestões de Testes
- Portas fora de ordem (força LIFO).  
- Pilha pequena (força escolhas).  
- Tesouros próximos de armadilhas.  
- Ranking ≥30 entradas → Quick/Merge.  
- Conferir token final.  

---

## 14) Perguntas Comuns (FAQ)
- Pode usar List/Stack prontos? **Não**.  
- Interface gráfica? **Opcional**.  
- Salvar/Carregar jogo? **Opcional**.  
- Tamanho mínimo mapa: **7×12**. 
