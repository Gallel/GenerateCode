
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {

    /**
     * Constructor without parameters (default table size).
     */
    public HashTable() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor with a parameter.
     */
    public HashTable(int size) {
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<KeyValue<K, V>>();
        }
    }

    private static final int DEFAULT_SIZE = 101;

    @Override
    public void put(K k, V v) {
        int i = calculateIndex(k);
        LinkedList<KeyValue<K, V>> synonyms = table[i];
        if (synonyms.size() != 0 && seekKeyInSynonyms(synonyms, k) != null) {
            return;
        }
        synonyms.addFirst(new KeyValue<>(k, v));
    }

    @Override
    public V get(K k) {
        LinkedList<KeyValue<K, V>> synonyms = table[calculateIndex(k)];
        Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, k);
        return (pos != null) ? pos.getElement().getValue() : null;
    }

    @Override
    public V delete(K k) {
        int i = calculateIndex(k);
        LinkedList<KeyValue<K, V>> synonyms = table[i];
        Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, k);
        if (pos == null) {
            return null;
        }
        V old = pos.getElement().getValue();
        synonyms.remove(pos);
        return old;
    }

    @Override
    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<K>();
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K, V>> synonyms = table[i];
            for (KeyValue<K, V> kv : synonyms) {
                list.add(kv.getKey());
            }
        }
        return list.iterator();
    }

    @Override
    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K, V>> synonyms = table[i];
            for (KeyValue<K, V> kv : synonyms) {
                list.add(kv.getValue());
            }
        }
        return list.iterator();
    }

    @Override
    public boolean containsKey(K k) {
        return get(k) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            count += table[i].size();
        }
        return count;
    }

    protected int calculateIndex(K k) {
        return Math.abs(k.hashCode()) % table.length;
    }

    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K k) {
        for (Position<KeyValue<K, V>> p : synonyms.positions()) {
            if (k.equals(p.getElement().getKey())) {
                return p;
            }
        }
        return null;
    }

    protected LinkedList<KeyValue<K, V>> createSynonymsList(int i, KeyValue<K, V> kv) {
        LinkedList<KeyValue<K, V>> synonyms = new LinkedList<KeyValue<K, V>>();
        synonyms.addFirst(kv);
        table[i] = synonyms;
        return synonyms;
    }

    protected void deleteSynonymsList(int i) {
        table[i] = new LinkedList<KeyValue<K, V>>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K, V>> synonyms = table[i];
            if (synonyms.size() != 0) {
                s.append(i + ": ");
                for (KeyValue<K, V> kv : synonyms) {
                    s.append(kv.getKey() + "/" + kv.getValue() + " ");
                }
                s.append("\n");
            }
        }
        return s.toString();
    }

    private final LinkedList<KeyValue<K, V>>[] table;
}
