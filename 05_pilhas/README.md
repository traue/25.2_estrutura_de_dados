# 📘 TAD Pilha em Java

## 1) O que é uma TAD Pilha?
A **TAD Pilha (Tipo Abstrato de Dados Pilha)** é uma estrutura de dados linear baseada no princípio **LIFO** (*Last In, First Out*), ou seja, o último elemento inserido é o primeiro a ser removido.  

### Operações principais:
- **push(x):** insere o elemento `x` no topo da pilha.  
- **pop():** remove e retorna o elemento que está no topo.  
- **peek() / top():** retorna o elemento do topo sem removê-lo.  
- **isEmpty():** verifica se a pilha está vazia.  
- **isFull():** (apenas na implementação estática) verifica se a pilha atingiu sua capacidade máxima.  

Exemplo intuitivo:  
👉 Imagine uma pilha de pratos. Sempre colocamos ou retiramos o prato **do topo**.

---

## 2) Diferença entre implementação Estática e Encadeada

| Característica | Pilha Estática | Pilha Encadeada |
|----------------|---------------|----------------|
| **Estrutura interna** | Vetor fixo (array) | Nós ligados dinamicamente (ponteiros) |
| **Tamanho** | Limitado pela capacidade definida no array | Dinâmico, limitado apenas pela memória disponível |
| **Alocação de memória** | Contígua (um bloco único de memória) | Não contígua (cada nó é alocado separadamente) |
| **Verificação de cheio** | Necessário (`isFull()`) | Não existe, pois cresce dinamicamente |
| **Velocidade de acesso** | Mais rápida (índices de array) | Mais lenta (navegação por ponteiros) |
| **Uso de memória** | Pode desperdiçar memória (espaços não usados no array) | Usa memória conforme necessário |
| **Complexidade das operações** | `O(1)` para push/pop (na maioria dos casos) | `O(1)` para push/pop |

👉 **Resumo:**  
- A **pilha estática** é como uma caixa com espaço fixo para X pratos. Se encher, não cabe mais nada.  
- A **pilha encadeada** é como um suporte onde você vai encaixando pratos um sobre o outro sem limite fixo, até acabar o espaço da cozinha (memória).  

---

## 3) Atividade

📌 **Objetivo:** Fixar o entendimento sobre pilhas estáticas e encadeadas, e praticar a implementação em Java.

### Enunciado:
Implemente em Java uma aplicação que utilize a TAD Pilha (você pode escolher **estática** ou **encadeada**).  

A aplicação deve:  
1. Ler uma sequência de números inteiros do usuário.  
2. Armazenar esses números em uma pilha.  
3. Ao final, exibir os números na **ordem inversa** à que foram digitados.  
   - Exemplo: se a entrada for `10, 20, 30`, a saída deve ser `30, 20, 10`.  
4. (Desafio extra ⭐) Implemente uma função que verifique se uma expressão matemática contém **parênteses balanceados** usando a pilha.  
   - Entrada: `((2+3)*5)` → Saída: `Balanceada`  
   - Entrada: `(2+3]*5)` → Saída: `Não balanceada`

### Critérios de entrega:
- Código Java documentado.  
- README explicando qual tipo de pilha foi escolhido (estática ou encadeada) e por quê.  
- Teste mostrando a execução do programa.  
