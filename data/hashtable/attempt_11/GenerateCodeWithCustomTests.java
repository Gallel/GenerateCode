
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {

    private static final int DEFAULT_TABLE_SIZE = 101;

    private LinkedList<KeyValue<K, V>>[] table;
    private int size;

    public HashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public HashTable(int size) {
        table = new LinkedList[size];
        this.size = 0;
    }

    protected int calculateIndex(K key) {
        int hash = key.hashCode();
        int index = hash % table.length;
        if (index < 0) {
            index += table.length;
        }
        return index;
    }

    protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = new LinkedList<>();
            table[index] = synonyms;
        }
        return synonyms;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
        Position<KeyValue<K, V>> result = null;
        Iterator<KeyValue<K, V>> it = synonyms.iterator();
        boolean found = false;
        while (!found && it.hasNext()) {
            Position<KeyValue<K, V>> pos = (Position<KeyValue<K, V>>) it.next();
            KeyValue<K, V> current = pos.element();
            if (key.equals(current.getKey())) {
                found = true;
                result = pos;
            }
        }
        return result;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        return synonyms != null && seekKeyInSynonyms(synonyms, key) != null;
    }

    @Override
    public V delete(K key) {
        V result = null;
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms != null) {
            Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
            if (pos != null) {
                result = pos.element().getValue();
                synonyms.remove(pos);
                size--;
                if (synonyms.isEmpty()) {
                    deleteSynonymsList(index);
                }
            }
        }
        return result;
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
        return pos == null ? null : pos.element().getValue();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<K> keys() {
        LinkedList<K> result = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K, V>> synonyms = table[i];
            if (synonyms != null) {
                for (KeyValue<K, V> kv : synonyms) {
                    result.add(kv.getKey());
                }
            }
        }
        return result.iterator();
    }

    @Override
    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = createSynonymsList(index, new KeyValue<>(key, value));
        Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
        if (pos == null) {
            synonyms.addLast(new KeyValue<>(key, value));
            size++;
        } else {
            pos.element().setValue(value);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        boolean first = true;
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K, V>> synonyms = table[i];
            if (synonyms != null) {
                for (KeyValue<K, V> kv : synonyms) {
                    if (!first) {
                        result.append(", ");
                    } else {
                        first = false;
                    }
                    result.append(kv.getKey()).append("=").append(kv.getValue());
                }
            }
        }
        result.append("}");
        return result.toString();
    }

    @Override
    public Iterator<V> values() {
        LinkedList<V> result = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K, V>> synonyms = table[i];
            if (synonyms != null) {
                for (KeyValue<K, V> kv : synonyms) {
                    result.add(kv.getValue());
                }
            }
        }
        return result.iterator();
    }

}
