
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    LinkedList<KeyValue<K,V>>[] table;
    int size;

    HashTable() {
        this.table = new LinkedList[997];
        this.size = 0;
    }

    HashTable(int size) {
        this.table = new LinkedList[size];
        this.size = 0;
    }

    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    boolean containsKey(K key) {
        if(table[calculateIndex(key)] != null) {
            for(KeyValue<K,V> kv : table[calculateIndex(key)]) {
                if(kv.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = new LinkedList<KeyValue<K,V>>();
        synonyms.add(kv);
        table[index] = synonyms;
        size++;
        return synonyms;
    }

    V delete(K key) {
        int index = calculateIndex(key);
        if(table[index] != null) {
            for(KeyValue<K,V> kv : table[index]) {
                if(kv.key.equals(key)) {
                    table[index].remove(kv);
                    size--;
                    return kv.value;
                }
            }
        }
        return null;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    V get(K key) {
        int index = calculateIndex(key);
        if(table[index] != null) {
            for(KeyValue<K,V> kv : table[index]) {
                if(kv.key.equals(key)) {
                    return kv.value;
                }
            }
        }
        return null;
    }

    boolean isEmpty() {
        return size == 0;
    }

    Iterator<K> keys() {
        LinkedList<K> keys = new LinkedList<>();
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            if (synonyms != null) {
                for (KeyValue<K,V> kv : synonyms) {
                    keys.add(kv.key);
                }
            }
        }
        return keys.iterator();
    }

    void put(K key, V value) {
        int index = calculateIndex(key);
        if(table[index] == null) {
            createSynonymsList(index, new KeyValue<K,V>(key, value));
        } else {
            for(KeyValue<K,V> kv : table[index]) {
                if(kv.key.equals(key)) {
                    kv.value = value;
                    return;
                }
            }
            table[index].add(new KeyValue<K,V>(key, value));
            size++;
        }
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        if(synonyms != null) {
            for(KeyValue<K,V> kv : synonyms) {
                if(kv.key.equals(key)) {
                    return null;
                }
            }
        }
        return null;
    }

    int size() {
        return size;
    }

    public String toString() {
        if (size == 0) return "{}";
        StringBuilder buffer = new StringBuilder();
        buffer.append("{");
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            if (synonyms != null) {
                for (KeyValue<K,V> kv : synonyms)
                    buffer.append(kv.key + "=>" + kv.value +", ");
            }
        }
        buffer.delete(buffer.length()-2, buffer.length());
        buffer.append("}");
        return buffer.toString();
    }

    Iterator<V> values() {
        LinkedList<V> values = new LinkedList<>();
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            if (synonyms != null) {
                for (KeyValue<K,V> kv : synonyms) {
                    values.add(kv.value);
                }
            }
        }
        return values.iterator();
    }
}
