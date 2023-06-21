
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {
    
    private LinkedList<KeyValue<K, V>>[] table;
    private int size;

    public HashTable() {
        this.table = new LinkedList[997];
        this.size = 0;
    }

    public HashTable(int size) {
        this.table = new LinkedList[size];
        this.size = 0;
    }

    protected int calculateIndex(K key) {
        return Math.abs(Objects.hashCode(key)) % table.length;
    }

    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            return false;
        }
        for (KeyValue<K, V> kv : synonyms) {
            if (kv.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = new LinkedList<>();
            table[index] = synonyms;
        }
        synonyms.add(kv);
        size++;
        return synonyms;
    }

    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            return null;
        }
        for (KeyValue<K, V> kv : synonyms) {
            if (kv.getKey().equals(key)) {
                synonyms.remove(kv);
                size--;
                if (synonyms.isEmpty()) {
                    table[index] = null;
                }
                return kv.getValue();
            }
        }
        return null;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            return null;
        }
        for (KeyValue<K, V> kv : synonyms) {
            if (kv.getKey().equals(key)) {
                return kv.getValue();
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<K> keys() {
        LinkedList<K> keys = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> synonyms : table) {
            if (synonyms == null) {
                continue;
            }
            for (KeyValue<K, V> kv : synonyms) {
                keys.add(kv.getKey());
            }
        }
        return keys.iterator();
    }

    public void put(K key, V value) {
        int index = calculateIndex(key);
        KeyValue<K, V> kv = new KeyValue<>(key, value);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            createSynonymsList(index, kv);
            return;
        }
        for (KeyValue<K, V> pair : synonyms) {
            if (pair.getKey().equals(key)) {
                pair.setValue(value);
                return;
            }
        }
        synonyms.add(kv);
        size++;
    }

    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
        if (synonyms == null) {
            return null;
        }
        Position<KeyValue<K, V>> position = synonyms.first();
        while (position != null) {
            if (position.getElement().getKey().equals(key)) {
                return position;
            }
            position = synonyms.next(position);
        }
        return null;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String output = "[";
        for (LinkedList<KeyValue<K, V>> synonyms : table) {
            if (synonyms == null) {
                continue;
            }
            for (KeyValue<K, V> kv : synonyms) {
                output += kv + ", ";
            }
        }
        if (output.length() > 1) {
            output = output.substring(0, output.length() - 2);
        }
        output += "]";
        return output;
    }

    public Iterator<V> values() {
        LinkedList<V> values = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> synonyms : table) {
            if (synonyms == null) {
                continue;
            }
            for (KeyValue<K, V> kv : synonyms) {
                values.add(kv.getValue());
            }
        }
        return values.iterator();
    }

}
