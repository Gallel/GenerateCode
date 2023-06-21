
import java.util.LinkedList;
import edu.uoc.ds.adt.Dictionary;
import edu.uoc.ds.traversal.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    
    private LinkedList<KeyValue<K,V>>[] table;
  
    HashTable() {
        this(101);
    }
  
    HashTable(int size) {
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }

    protected int calculateIndex(K key) {
        int code = key.hashCode();
        if (code < 0) {
            code = -code;
        }
        return code % table.length;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = new LinkedList<KeyValue<K,V>>();
        table[index] = synonyms;
        return synonyms;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        for (Position<KeyValue<K,V>> p : synonyms.positions()) {
            if (p.getElement().getKey().equals(key)) {
                return p;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            return false;
        }
        return seekKeyInSynonyms(synonyms, key) != null;
    }

    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            return null;
        }
        Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonyms, key);
        if (p == null) {
            return null;
        }
        return p.getElement().getValue();
    }

    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Position<KeyValue<K,V>> p : table[i].positions()) {
                    list.add(p.getElement().getKey());
                }
            }
        }
        return list.iterator();
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Null key");
        }
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = createSynonymsList(index, new KeyValue<K,V>(key, value));
        }
        else {
            Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonyms, key);
            if (p == null) {
                synonyms.addLast(new KeyValue<K,V>(key, value));
            }
            else {
                p.getElement().setValue(value);
            }
        }
    }

    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            return null;
        }
        Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonyms, key);
        if (p == null) {
            return null;
        }
        V value = p.getElement().getValue();
        synonyms.remove(p);
        if (synonyms.isEmpty()) {
            deleteSynonymsList(index);
        }
        return value;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                count += table[i].size();
            }
        }
        return count;
    }

    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Position<KeyValue<K,V>> p : table[i].positions()) {
                    list.add(p.getElement().getValue());
                }
            }
        }
        return list.iterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator<K> it = keys();
        while (it.hasNext()) {
            K key = it.next();
            V value = get(key);
            sb.append(key);
            sb.append(":");
            sb.append(value);
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

}
