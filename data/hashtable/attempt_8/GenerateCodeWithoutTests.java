
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    
    //attributes
    private int tableSize;
    private LinkedList<KeyValue<K,V>>[] table;

    //constructors
    public HashTable() {
        this.tableSize = 100;
        this.table = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }
    
    public HashTable(int size) {
        this.tableSize = size;
        this.table = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }
    
    //methods
    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode() % tableSize);
    }

    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms, key) != null;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = new LinkedList<KeyValue<K,V>>();
        synonyms.add(kv);
        table[index] = synonyms;
        return synonyms;
    }

    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null) {
            return null;
        }
        synonyms.remove(position);
        return position.element().getValue();
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null) {
            return null;
        }
        return position.element().getValue();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<K>();
        for (int i = 0; i < tableSize; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            for (KeyValue<K,V> kv : synonyms) {
                list.add(kv.getKey());
            }
        }
        return list.iterator();
    }

    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        for (KeyValue<K,V> kv : synonyms) {
            if (kv.getKey().equals(key)) {
                kv.setValue(value);
                return;
            }
        }
        KeyValue<K,V> kv = new KeyValue<K,V>(key, value);
        synonyms.add(kv);
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        if (synonyms == null) {
            return null;
        }
        for (Position<KeyValue<K,V>> p = synonyms.first(); p != null; p = synonyms.next(p)) {
            KeyValue<K,V> kv = p.element();
            if (kv.getKey().equals(key)) {
                return p;
            }
        }
        return null;
    }

    public int size() {
        int count = 0;
        for (int i = 0; i < tableSize; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            count += synonyms.size();
        }
        return count;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < tableSize; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            if (synonyms.size() > 0) {
                result += "("+i+"): ";
                for (KeyValue<K,V> kv : synonyms) {
                    result += kv.toString() + " ";
                }
                result += "\n";
            }
        }
        return result;
    }

    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        for (int i = 0; i < tableSize; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            for (KeyValue<K,V> kv : synonyms) {
                list.add(kv.getValue());
            }
        }
        return list.iterator();
    }
}
