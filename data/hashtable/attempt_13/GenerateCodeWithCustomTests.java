
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V>{
    private LinkedList<KeyValue<K,V>>[] table;
    private int count;
    private int size;

    public HashTable() {
        this(1000);
    }

    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i=0; i<size; i++) {
            table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }

    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = new LinkedList<KeyValue<K,V>>();
        synonyms.add(kv);
        table[index] = synonyms;
        count++;
        return synonyms;
    }

    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms, key) != null;
    }

    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> posFound = seekKeyInSynonyms(synonyms, key);
        if (posFound!=null) {
            KeyValue<K,V> kv = synonyms.remove(posFound);
            count--;
            return kv.getValue();
        }else {
            return null;
        }
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> posFound = seekKeyInSynonyms(synonyms, key);
        if (posFound!=null) {
            return posFound.getElement().getValue();
        }else {
            return null;
        }
    }

    public boolean isEmpty() {
        return count==0;
    }

    public Iterator<K> keys() {
        List<K> keys = new ArrayList<K>();
        for (int i=0; i<table.length; i++) {
            if (table[i]!=null) {
                for (KeyValue<K,V> keyValue : table[i]) {
                    keys.add(keyValue.getKey());
                }
            }
        }
        return keys.iterator();
    }

    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> posFound = seekKeyInSynonyms(synonyms, key); 
        if (posFound!=null) {
            posFound.getElement().setValue(value);
        }else {
            createSynonymsList(index,new KeyValue<K,V>(key,value));
        }
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        if (synonyms==null) {
            return null;
        }
        Iterator<Position<KeyValue<K,V>>> it = synonyms.positions().iterator();
        while (it.hasNext()) {
            Position<KeyValue<K,V>> pos = it.next();
            if (pos.getElement().getKey().equals(key)) {
                return pos;
            }
        }
        return null;
    }

    public int size() {
        return count;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i=0; i<table.length; i++) {
            if (table[i]!=null) {
                for (KeyValue<K,V> keyValue : table[i]) {
                    s.append(keyValue.toString()+"\n");
                }
            }
        }
        return s.toString();
    }

    public Iterator<V> values() {
        List<V> values = new ArrayList<V>();
        for (int i=0; i<table.length; i++) {
            if (table[i]!=null) {
                for (KeyValue<K,V> keyValue : table[i]) {
                    values.add(keyValue.getValue());
                }
            }
        }
        return values.iterator();
    }
}
