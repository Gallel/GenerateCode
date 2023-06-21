
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    protected LinkedList<KeyValue<K,V>>[] table;
    protected int tableSize;

    public HashTable() {
        this(997);
    }

    public HashTable(int size) {
        tableSize = size;
        createTable();
    }

    private void createTable() {
        table = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++)
            table[i] = new LinkedList<KeyValue<K,V>>();
    }

    protected int calculateIndex(K key) {
        int code = key.hashCode();
        if (code < 0) code = -code;
        return code % tableSize;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms, key) != null;
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        for (Position<KeyValue<K,V>> p : synonyms.positions()) {
            if (p.getElement().getKey().equals(key)) return p;
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
        if (pos == null) return null;
        else return pos.getElement().getValue();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<K>();
        for (int i = 0; i < tableSize; i++)
            for (KeyValue<K,V> item : table[i])
                list.add(item.getKey());
        return list.iterator();
    }

    @Override
    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
        if (pos == null) {
            synonyms.addLast(new KeyValue<K,V>(key, value));
        }
        else {
            pos.getElement().setValue(value);
        }
    }

    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
        if (pos == null) return null;
        V value = pos.getElement().getValue();
        synonyms.remove(pos);
        return value;
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < tableSize; i++)
            count += table[i].size();
        return count;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < tableSize; i++)
            for (KeyValue<K,V> item : table[i])
                res.append(item.getKey()).append("=").append(item.getValue()).append(", ");
        if (res.length() > 1) res.setLength(res.length() - 2);
        res.append("]");
        return res.toString();
    }

    @Override
    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        for (int i = 0; i < tableSize; i++)
            for (KeyValue<K,V> item : table[i])
                list.add(item.getValue());
        return list.iterator();
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = new LinkedList<KeyValue<K,V>>();
            table[index] = synonyms;
        }
        return synonyms;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }
}
