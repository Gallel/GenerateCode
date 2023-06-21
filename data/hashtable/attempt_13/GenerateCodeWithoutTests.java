
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    protected LinkedList<KeyValue<K,V>>[] table;
    protected int size;
    
    public HashTable() {
        this(1000);
    }
    
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = 0;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }
    
    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        KeyValue<K,V> kv = new KeyValue<K,V>(key, null);
        return synonyms.contains(kv);
    }

    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> list = new LinkedList<KeyValue<K,V>>();
        list.add(kv);
        table[index] = list;
        size++;
        return list;
    }

    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        KeyValue<K,V> kv = new KeyValue<K,V>(key, null);
        boolean result = synonyms.remove(kv);
        if (result) {
            size--;
            return kv.getValue();
        } else {
            return null;
        }
    }

    protected void deleteSynonymsList(int index) {
        table[index].clear();
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> list = table[index];
        for (KeyValue<K,V> kv : list) {
            if (kv.getKey().equals(key)) {
                return kv.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<K> keys() {
        LinkedList<K> keys = new LinkedList<K>();
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            for (KeyValue<K,V> kv : synonyms) {
                keys.add(kv.getKey());
            }
        }
        return keys.iterator();
    }

    @Override
    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        KeyValue<K,V> kv = new KeyValue<K,V>(key, value);
        if (synonyms.contains(kv)) {
            synonyms.remove(kv);
        } else {
            size++;
        }
        synonyms.add(kv);
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        for (KeyValue<K,V> kv : synonyms) {
            if (kv.getKey().equals(key)) {
                return kv;
            }
        }
        return null; // key not found
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Iterator<K> itr = keys();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (itr.hasNext()) {
            K key = itr.next();
            sb.append(key);
            sb.append(": ");
            sb.append(get(key));
            if (itr.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Iterator<V> values() {
        LinkedList<V> values = new LinkedList<V>();
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            for (KeyValue<K,V> kv : synonyms) {
                values.add(kv.getValue());
            }
        }
        return values.iterator();
    }
}
