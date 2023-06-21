
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    private final LinkedList<KeyValue<K,V>>[] table;
    private static final int DEFAULT_TABLE_SIZE = 97;

    public HashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        table = new LinkedList[size];
    }

    protected int calculateIndex(K key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        return index;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    @SuppressWarnings("unchecked")
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> list = new LinkedList<>();
        list.add(kv);
        table[index] = list;
        return list;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null)
            return false;
        for (KeyValue<K,V> kv : synonyms)
            if (kv.getKey().equals(key))
                return true;
        return false;
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null)
            return null;
        for (KeyValue<K,V> kv : synonyms)
            if (kv.getKey().equals(key))
                return kv.getValue();
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<>();
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            if (synonyms !=  null)
                for (KeyValue<K,V> kv : synonyms)
                    list.add(kv.getKey());
        }
        return list.iterator();
    }

    @Override
    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null)
            createSynonymsList(index, new KeyValue<K,V>(key,value));
        else {
            KeyValue<K,V> kv = seekKeyInSynonyms(synonyms, key);
            if (kv == null)
                synonyms.add(new KeyValue<K,V>(key,value));
            else
                kv.setValue(value);
        }
    }


    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        for (KeyValue<K,V> kv : synonyms)
            if (kv.getKey().equals(key))
                return kv;
        return null;
    }

    @Override
    public int size() {
        int count = 0;
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            if (synonyms !=  null)
                count += synonyms.size();
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            if (synonyms !=  null)
                for (KeyValue<K,V> kv: synonyms)
                    s.append(kv.toString()).append("\n");
        }
        return s.toString();
    }

    @Override
    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<>();
        for (LinkedList<KeyValue<K,V>> synonyms : table) {
            if (synonyms !=  null)
                for (KeyValue<K,V> kv : synonyms)
                    list.add(kv.getValue());
        }
        return list.iterator();
    }

    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null)
            return null;
        for (KeyValue<K,V> kv : synonyms) {
            if (kv.getKey().equals(key)) {
                synonyms.remove(kv);
                if (synonyms.isEmpty())
                    deleteSynonymsList(index);
                return kv.getValue();
            }
        }
        return null;
    }
}
