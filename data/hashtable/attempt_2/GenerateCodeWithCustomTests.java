
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    protected LinkedList<KeyValue<K,V>>[] table;

    public HashTable() {
        this(100);
    }
    
    public HashTable(int size) {
        table = new LinkedList[size];
    }
    
    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }
    
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = new LinkedList<KeyValue<K,V>>();
            table[index] = synonyms;
        }
        synonyms.add(kv);
        return synonyms;
    }
    
    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }
    
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        if (synonyms == null) {
            return null;
        }
        for (KeyValue<K,V> kv: synonyms) {
            if (kv.getKey().equals(key)) {
                return new SynonymsPosition(kv, synonyms);
            }
        }
        return null;
    }
    
    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms, key) != null;
    }
    
    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null) {
            return null;
        }
        KeyValue<K,V> kv = position.getElement();
        synonyms.remove(kv);
        if (synonyms.isEmpty()) {
            deleteSynonymsList(index);
        }
        return kv.getValue();
    }
    
    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null) {
            return null;
        }
        return position.getElement().getValue();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public Iterator<K> keys() {
        return new HashTableKeyIterator<K,V>(table);
    }
    
    @Override
    public void put(K key, V value) {
        KeyValue<K,V> kv = new KeyValue<K,V>(key, value);
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            createSynonymsList(index, kv);
        } else {
            Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
            if (position == null) {
                createSynonymsList(index, kv);
            } else {
                position.getElement().setValue(value);
            }
        }
    }
    
    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            if (synonyms != null) {
                size += synonyms.size();
            }
        }
        return size;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<K> it = keys();
        while (it.hasNext()) {
            K key = it.next();
            V value = get(key);
            sb.append("(").append(key).append(",").append(value).append(")");
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    @Override
    public Iterator<V> values() {
        return new HashTableValueIterator<K,V>(table);
    }
}