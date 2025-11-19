import java.util.LinkedList;
import java.util.Objects;

/**
 * HashTable simples em Java usando Separate Chaining (lista ligada) para tratar colisões.
 * - Genérica: K = tipo da chave, V = tipo do valor.
 * - Usa LinkedList<Entry<K,V>> em cada bucket.
 * - Faz resize (dobrar a capacidade) quando load factor excede o limite.
 */
public class HashTable<K, V> {

    /** Entrada (par chave-valor) armazenada nas listas dos buckets */
    private static class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // -------------------- Configurações --------------------
    private static final int DEFAULT_INITIAL_CAPACITY = 16; // número inicial de buckets (potência de 2 é comum)
    private static final float DEFAULT_LOAD_FACTOR = 0.75f; // quando ultrapassar, redimensiona

    // -------------------- Estado --------------------
    private LinkedList<Entry<K, V>>[] buckets; // array de listas ligadas (separate chaining)
    private int size; // número de pares chave-valor armazenados
    private final float loadFactor;

    // -------------------- Construtores --------------------
    public HashTable() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0)
            throw new IllegalArgumentException("initialCapacity deve ser > 0");
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("loadFactor inválido");

        // força capacidade para potência de 2 (opcional, mas facilita a distribuição)
        int capacity = 1;
        while (capacity < initialCapacity) capacity <<= 1;

        this.buckets = new LinkedList[capacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    // -------------------- Utilitários --------------------

    /** Retorna a capacidade atual (número de buckets) */
    public int capacity() {
        return buckets.length;
    }

    /** Calcula índice do bucket a partir do hash da chave */
    private int indexFor(Object key) {
        int h = (key == null) ? 0 : key.hashCode();
        // spread bits para reduzir padrões ruins de hash (similar a HashMap)
        h ^= (h >>> 16);
        return (h & (buckets.length - 1)); // buckets.length é potência de 2
    }

    /** Retorna o tamanho (número de pares armazenados) */
    public int size() {
        return size;
    }

    /** Verifica se está vazio */
    public boolean isEmpty() {
        return size == 0;
    }

    // -------------------- Operações principais --------------------

    /**
     * Insere ou atualiza um par (key, value).
     * Se a chave já existir, retorna o valor antigo; caso contrário, retorna null.
     */
    public V put(K key, V value) {
        // redimensiona se necessário
        if ((float) (size + 1) / buckets.length > loadFactor) {
            resize(buckets.length * 2);
        }

        int idx = indexFor(key);
        if (buckets[idx] == null) {
            buckets[idx] = new LinkedList<>();
        }

        LinkedList<Entry<K, V>> bucket = buckets[idx];

        // procura chave existente para atualizar
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                V old = entry.value;
                entry.value = value;
                return old; // valor anterior retornado
            }
        }

        // não encontrou: adiciona nova entrada
        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    /**
     * Recupera o valor associado à chave, ou null se não existir.
     */
    public V get(K key) {
        int idx = indexFor(key);
        LinkedList<Entry<K, V>> bucket = buckets[idx];
        if (bucket == null) return null;

        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Remove a entrada associada à chave e retorna o valor removido, ou null se não existir.
     */
    public V remove(K key) {
        int idx = indexFor(key);
        LinkedList<Entry<K, V>> bucket = buckets[idx];
        if (bucket == null) return null;

        var it = bucket.iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            if (Objects.equals(entry.key, key)) {
                V old = entry.value;
                it.remove();
                size--;
                return old;
            }
        }
        return null;
    }

    /**
     * Verifica se a chave existe na tabela.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /** Remove todas as entradas */
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new LinkedList[buckets.length];
        size = 0;
    }

    // -------------------- Redimensionamento --------------------

    /**
     * Redimensiona a tabela para uma nova capacidade (deve ser potência de 2 preferencialmente).
     * Re-hash de todas as entradas.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        // segurança: não permitir capacidade menor que 1
        if (newCapacity <= 0) return;

        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        LinkedList<Entry<K, V>>[] newBuckets = new LinkedList[newCapacity];

        // re-hash de todas as entradas
        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
            if (bucket == null) continue;
            for (Entry<K, V> entry : bucket) {
                int idx;
                int h = (entry.key == null) ? 0 : entry.key.hashCode();
                h ^= (h >>> 16);
                idx = (h & (newCapacity - 1));
                if (newBuckets[idx] == null) newBuckets[idx] = new LinkedList<>();
                newBuckets[idx].add(new Entry<>(entry.key, entry.value));
            }
        }

        buckets = newBuckets;
        // size permanece o mesmo
    }

    // -------------------- Representação simples --------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (Entry<K, V> e : bucket) {
                if (!first) sb.append(", ");
                sb.append(String.valueOf(e.key)).append("=").append(String.valueOf(e.value));
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // -------------------- Exemplo de uso --------------------
    public static void main(String[] args) {
        HashTable<String, Integer> map = new HashTable<>(4); // capacidade inicial pequena para testar resize
        map.put("um", 1);
        map.put("dois", 2);
        map.put("tres", 3);
        System.out.println("map = " + map);

        System.out.println("get(\"dois\") = " + map.get("dois"));
        System.out.println("containsKey(\"quatro\") = " + map.containsKey("quatro"));

        map.put("dois", 22); // atualiza
        System.out.println("após atualizar 'dois' = " + map.get("dois"));

        map.remove("um");
        System.out.println("após remover 'um' => map = " + map);

        // adiciona muitos para forçar resize
        for (int i = 0; i < 20; i++) {
            map.put("k" + i, i);
        }
        System.out.println("size = " + map.size() + ", capacity = " + map.capacity());
    }
}