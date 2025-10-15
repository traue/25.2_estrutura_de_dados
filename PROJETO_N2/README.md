# PixelDex, sua coleção de criaturas 8-bit

Gerencie uma coleção de **Pixels** (criaturas 8-bit) usando **lista encadeada genérica** para a coleção do usuário e **árvore binária de busca (BST)** como índice global. Opcionalmente, adicione uma **tabela hash** para buscas O(1) por `id`.

---

## Sumário
- [Objetivos de aprendizagem](#objetivos-de-aprendizagem)
- [Requisitos do projeto](#requisitos-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Estruturas de dados](#estruturas-de-dados)
- [Arquitetura sugerida](#arquitetura-sugerida)
- [Como executar](#como-executar)
- [Comandos da CLI](#comandos-da-cli)
- [Exemplos de uso](#exemplos-de-uso)
- [Importação/Exportação (JSON)](#importaçãoexportação-json)
- [Testes automatizados](#testes-automatizados)
- [Complexidade das operações](#complexidade-das-operações)
- [Extras (bônus)](#extras-bônus)
- [Critérios de avaliação](#critérios-de-avaliação)
- [Roadmap sugerido](#roadmap-sugerido)
- [Licença](#licença)

---

## Objetivos de aprendizagem
- Implementar **do zero** uma **lista encadeada genérica** (inserção/remoção/iteração e utilitárias).
- Implementar **BST** com inserção, busca, **remoção nos 3 casos**, travessias e métricas.
- Discutir e medir **complexidade** e **desbalanceamento**.
- (Opcional) Integrar **tabela hash** por `id` com política de rehash.

---

## Requisitos do projeto
- **Linguagem**: Java 17+ (recomendado).
- **Proibido**: usar coleções prontas do Java para as estruturas centrais (ex.: `LinkedList`, `TreeMap`, `HashMap`). Permite-se uso para **tarefas periféricas** (parsing, I/O, testes auxiliares).
- **Generics**: a lista encadeada e a BST devem ser genéricas.
- **Tratamento de erros**: entradas inválidas não devem encerrar o programa abruptamente.
- **Testes**: JUnit com casos-base e de borda, incluindo remoção na BST (folha, 1 filho, 2 filhos).

---

## Funcionalidades
**Entidade `Pixel` (mínimo):**
- `id` (int, único)  
- `nome` (String)  
- `raridade` (`COMUM`, `INCOMUM`, `RARO`, `ÉPICO`, `LENDÁRIO`)  
- `poder` (int ≥ 0)

**Coleção do Usuário (lista encadeada):**
- `addFirst`, `addLast`, `addAt(index)`
- `removeFirst`, `removeLast`, `removeAt(index)`
- `size`, `isEmpty`, `iterator`
- Utilitárias: `reverse()`, `unique()`, `move(from,to)`, `slice(from,to)`

**Índice Global (BST):**
- `insert`, `search(key)`, `remove(key)`
- Travessias: `inOrder`, `preOrder`, `postOrder`
- Métricas: `height`, `countNodes`, `countLeaves`, `isBST`
- Consultas: `range(a,b)`; (opcional) `kSmallest(k)`, `kLargest(k)`

**Chave da BST (escolha e documente):**
- Por `nome` (lexicográfica, desempate por `id`) **ou**
- Por `poder` (crescente, desempate por `id`)

---

## Estruturas de dados
- **Lista Encadeada Genérica**: nós com `<T>` (referência para `next`, e opcionalmente `prev` se quiser duplamente encadeada — não é obrigatório).
- **BST Genérica**: nós com `<K,V>` ou `<T>` com `Comparator` para chave.
- **Tabela Hash (extra)**: por `id`, com **encadeamento separado** ou **endereçamento aberto**; documentar função de hash e política de rehash (ex.: fator de carga > 0.75).

---

## Arquitetura sugerida
```
pixeldex/
├─ README.md
├─ pom.xml                    # ou build.gradle
├─ src/
│  ├─ main/java/
│  │  ├─ app/cli/Main.java                # Ponto de entrada (CLI)
│  │  ├─ domain/Pixel.java                # Entidade
│  │  ├─ ds/list/LinkedList.java          # Lista encadeada genérica
│  │  ├─ ds/list/LinkedNode.java
│  │  ├─ ds/tree/BST.java                 # Árvore binária de busca genérica
│  │  ├─ ds/tree/BSTNode.java
│  │  ├─ ds/hash/IntHashTable.java        # (Opcional) Tabela hash por id
│  │  ├─ core/PixelIndex.java             # Orquestra BST + (hash)
│  │  ├─ core/PixelCollection.java        # Encapsula a lista do usuário
│  │  ├─ io/JsonIO.java                   # (Opcional) Import/Export
│  │  └─ util/Comparators.java            # Comparadores e utilidades
│  └─ test/java/
│     ├─ ds/list/LinkedListTest.java
│     ├─ ds/tree/BSTTest.java
│     └─ integration/FlowTest.java
```

---

## Como executar
### Maven
```bash
# compilar
mvn -q clean package

# rodar testes
mvn -q test

# executar (ajuste o nome do artefato se necessário)
java -jar target/pixeldex-1.0.0.jar
```

### Gradle
```bash
# compilar
./gradlew build

# rodar testes
./gradlew test

# executar (via aplicação Java)
java -jar build/libs/pixeldex-1.0.0.jar
```

---

## Comandos da CLI
```
ADD <id> <nome> <raridade> <poder>
FIND <nome|poder|id>
REMOVE-COLLECTION <index>
REMOVE-INDEX <key>
REVERSE
UNIQUE
MOVE <fromIndex> <toIndex>
SLICE <from> <to>
LIST
LIST-INDEX <INORDER|PREORDER|POSTORDER>
RANGE <a> <b>
IMPORT <caminho-json>           # opcional
EXPORT <caminho-json>           # opcional
HELP
EXIT
```

**Observações**
- `FIND` usa a **BST** (por nome ou por poder, conforme sua escolha de chave).  
- `REMOVE-INDEX` remove do índice global; defina e documente como a coleção reage (ex.: marcar item como removido ou limpar visualização).  
- Índices fora do intervalo devem produzir mensagens claras (sem `NullPointerException`).

---

## Exemplos de uso
```
> ADD 101 picozinho COMUM 12
OK: Pixel(101, picozinho, COMUM, 12) adicionado

> ADD 202 drakonauta RARO 87
OK

> LIST
[0] Pixel(101, picozinho, COMUM, 12)
[1] Pixel(202, drakonauta, RARO, 87)

> LIST-INDEX INORDER
Pixel(101, picozinho, COMUM, 12)
Pixel(202, drakonauta, RARO, 87)

> FIND picozinho
Encontrado: Pixel(101, picozinho, COMUM, 12)

> RANGE a m
Pixel(101, picozinho, COMUM, 12)

> REVERSE
OK

> MOVE 1 0
OK

> UNIQUE
OK (duplicatas removidas)

> REMOVE-INDEX picozinho
OK (removido do índice). Dica: atualize exibição da coleção.

> EXPORT pixels.json
Salvo em pixels.json

> EXIT
```

---

## Importação/Exportação (JSON)
Formato simples (opcional):
```json
[
  {"id":101, "nome":"picozinho", "raridade":"COMUM", "poder":12},
  {"id":202, "nome":"drakonauta", "raridade":"RARO", "poder":87}
]
```
- `IMPORT` popula **índice** e **coleção**.
- `EXPORT` salva o estado atual da coleção.

---

## Testes automatizados
Cobrir, no mínimo:
- **Lista**: inserções diversas; `reverse` com 0/1/N; `unique` com duplicatas intercaladas; `move` início/fim; `slice` limites inválidos.
- **BST**: inserção crescente (pior caso), aleatória e inversa; `search` existente/ausente; `remove` (folha, 1 filho, 2 filhos); `range` vazia e não vazia; `height` esperado em árvores pequenas.
- **Integração**: `ADD` → presente na coleção e no índice; `REMOVE-INDEX` → efeito coerente na coleção; `LIST-INDEX` reflete as travessias.

Executar:
```bash
mvn test
# ou
./gradlew test
```

---

## Complexidade das operações
| Estrutura | Operação                     | Complexidade (médio) | Pior caso |
|-----------|------------------------------|----------------------|-----------|
| Lista     | `addFirst`, `removeFirst`    | O(1)                 | O(1)      |
| Lista     | `addLast` (sem tail)         | O(n)                 | O(n)      |
| Lista     | `addAt`, `removeAt`, `move`  | O(n)                 | O(n)      |
| Lista     | `reverse` (in-place)         | O(n)                 | O(n)      |
| Lista     | `unique`                      | O(n²) \*           | O(n²)     |
| BST       | `insert`, `search`, `remove` | O(log n)             | O(n)      |
| BST       | `inOrder`/`pre`/`post`       | O(n)                 | O(n)      |
| Hash (extra) | `put`, `get`, `remove`    | O(1) amort.          | O(n)      |

\* Pode ser reduzido a O(n) usando uma **hash** auxiliar (permitido como extra).

---

## Extras (bônus)
1. **Tabela Hash por `id`**  
   - Encadeamento separado ou endereçamento aberto.  
   - Documentar função de hash, fator de carga e rehash.

2. **Balanceamento**  
   - Converter a BST para **AVL** (inserção e remoção).

3. **Seed & Benchmark**  
   - Geração de 50–200 Pixels.  
   - Comparar tempos médios de `search` em BST vs Hash.

4. **Filtros combinados**  
   - Consultas por `raridade` + intervalo de chave.

---

## Critérios de avaliação
- **Funcionalidade e corretude (40%)**: operações funcionando, principalmente remoção na BST (3 casos).
- **Qualidade do código (25%)**: generics, coesão, legibilidade, tratamento de erros, comentários objetivos.
- **Testes (25%)**: cobertura de casos críticos e clareza dos cenários.
- **Relatório (10%)**: arquitetura, análise de complexidade e limitações.

---

## Roadmap sugerido
1. Definir a **chave da BST** e o `Comparator`.
2. Implementar a **lista encadeada genérica** e testar.
3. Implementar a **BST** (inserção → busca → remoção).
4. Conectar **lista** + **BST** na **CLI**.
5. Escrever **testes de integração**.
6. Implementar **extras** (hash/AVL) e **benchmark**.

---

## Licença
Distribuído sob a licença de sua preferência (ex.: MIT). Atualize este trecho conforme o repositório.
