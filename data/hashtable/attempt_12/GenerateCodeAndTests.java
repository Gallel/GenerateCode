
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    private static final int DEFAULT_SIZE = 11;
    private int size;
    private LinkedList<KeyValue<K,V>>[] table;

    public HashTable() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++)
            table[i] = new LinkedList<KeyValue<K,V>>();
    }

    protected int calculateIndex(K key) {
        return key.hashCode() % size;
    }

    public boolean containsKey(K key) {
        LinkedList<KeyValue<K,V>> list = table[calculateIndex(key)];
        return seekKeyInSynonyms(list, key) != null;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> list = table[index];
        if (list == null)
            table[index] = list = new LinkedList<KeyValue<K,V>>();
        list.add(kv);
        return list;
    }

    public V delete(K key) {
        LinkedList<KeyValue<K,V>> list = table[calculateIndex(key)];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(list, key);
        if (position == null)
            return null;
        else {
            list.remove(position);
            return position.element().getValue();
        }
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    public V get(K key) {
        LinkedList<KeyValue<K,V>> list = table[calculateIndex(key)];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(list, key);
        return position == null ? null : position.element().getValue();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<K> keys() {
        LinkedList<K> keys = new LinkedList<K>();
        for (int i = 0; i < size; i++)
            if (table[i] != null) {
                LinkedList<KeyValue<K,V>> list = table[i];
                for (KeyValue<K,V> kv : list)
                    keys.add(kv.getKey());
            }
        return keys.iterator();
    }

    public void put(K key, V value) {
        LinkedList<KeyValue<K,V>> list = table[calculateIndex(key)];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(list, key);
        if (position == null)
            createSynonymsList(calculateIndex(key), new KeyValue<K,V>(key, value));
        else
            position.element().setValue(value);
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        if (synonyms == null)
            return null;
        for (Position<KeyValue<K,V>> position = synonyms.first(); position != null; position = synonyms.next(position))
            if (position.element().getKey().equals(key))
                return position;
        return null;
    }

    public int size() {
        int s = 0;
        for (int i = 0; i < size; i++)
            if (table[i] != null)
                s += table[i].size();
        return s;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++)
            if (table[i] != null) {
                LinkedList<KeyValue<K,V>> list = table[i];
                for (KeyValue<K,V> kv : list)
                    s.append(kv.getKey()).append("=").append(kv.getValue()).append(", ");
            }
        if (s.length() > 1)
            s.delete(s.length() - 2, s.length());
        s.append("]");
        return s.toString();
    }

    public Iterator<V> values() {
        LinkedList<V> values = new LinkedList<V>();
        for (int i = 0; i < size; i++)
            if (table[i] != null) {
                LinkedList<KeyValue<K,V>> list = table[i];
                for (KeyValue<K,V> kv : list)
                    values.add(kv.getValue());
            }
        return values.iterator();
    }
}
