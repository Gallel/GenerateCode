
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    protected LinkedList<KeyValue<K,V>>[] table;
    protected int count;

    public HashTable() {
        this(997);
    }

    public HashTable(int size) {
        table = new LinkedList[size];
        for (int i = 0; i < size; i++)
            table[i] = new LinkedList<KeyValue<K,V>>();
    }

    protected int calculateIndex(K key) {
        int hashCode = key.hashCode() % table.length;
        if (hashCode < 0)
            hashCode += table.length;
        return hashCode;
    }

    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms, key) != null;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null)
            table[index] = synonyms = new LinkedList<KeyValue<K,V>>();
        synonyms.add(kv);
        count++;
        return synonyms;
    }

    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null)
            return null;
        else {
            KeyValue<K,V> kv = synonyms.get(position);
            synonyms.remove(position);
            count--;
            return kv.getValue();
        }
    }

    protected void deleteSynonymsList(int index) {
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms != null) {
            table[index] = null;
            count -= synonyms.size();
        }
    }

    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null)
            return null;
        else {
            KeyValue<K,V> kv = synonyms.get(position);
            return kv.getValue();
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<K>();
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            if (synonyms != null) {
                for (KeyValue<K,V> kv : synonyms)
                    list.add(kv.getKey());
            }
        }
        return list.iterator();
    }

    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null) {
            createSynonymsList(index, new KeyValue<K,V>(key, value));
        }
        else {
            KeyValue<K,V> kv = synonyms.get(position);
            kv.setValue(value);
        }
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        if (synonyms == null) return null;
        Iterator<Position<KeyValue<K,V>>> it = synonyms.positions().iterator();
        while (it.hasNext()) {
            Position<KeyValue<K,V>> position = it.next();
            KeyValue<K,V> kv = position.getElement();
            if (key.equals(kv.getKey()))
                return position;
        }
        return null;
    }

    public int size() {
        return count;
    }

    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            if (synonyms != null) {
                for (KeyValue<K,V> kv : synonyms)
                    list.add(kv.getValue());
            }
        }
        return list.iterator();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        Iterator<K> keys = keys();
        while (keys.hasNext()) {
            K key = keys.next();
            V value = get(key);
            buffer.append(key);
            buffer.append(":");
            buffer.append(value);
            if (keys.hasNext())
                buffer.append(", ");
        }
        buffer.append("]");
        return buffer.toString();
    }

}
