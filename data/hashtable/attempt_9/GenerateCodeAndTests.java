
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    
    private Object[] table;
    private int size;
   
    public HashTable() {
        this.size = 101;
        this.table = new Object[this.size];
        for (int i = 0; i < this.size ; i++) {
            this.table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }
    
    public HashTable(int size) {
        this.size = size;
        this.table = new Object[this.size];
        for (int i = 0; i < this.size ; i++) {
            this.table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }
    
    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = (LinkedList<KeyValue<K,V>>) this.table[index];
        KeyValue<K,V> kv = seekKeyInSynonyms(synonyms, key).element();
        return kv != null;
    }
    
    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = (LinkedList<KeyValue<K,V>>) this.table[index];
        Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonyms,key);
        if (p.element() == null) {
            return null;
        } else {
            return p.element().getValue();
        }
    }
    
    @Override
    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = (LinkedList<KeyValue<K,V>>) this.table[index];
        Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonyms,key);
        if (p.element() == null) {
            synonyms.addLast(new KeyValue<K,V>(key,value));
        } else {
            p.element().setValue(value);
        }
    }
    
    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = (LinkedList<KeyValue<K,V>>) this.table[index];
        Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonyms,key);
        if (p.element() == null) {
            return null;
        }
        V toReturn = p.element().getValue();
        synonyms.remove(p);
        return toReturn;
    }
    
    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            count += ((LinkedList<KeyValue<K,V>>) this.table[i]).size();
        }
        return count;
    }
    
    @Override
    public boolean isEmpty() {
        for (int i = 0; i < this.size; i++) {
            if (!((LinkedList<KeyValue<K,V>>) this.table[i]).isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<K>();
        for (int i = 0; i < this.size; i++) {
            Iterator<KeyValue<K,V>> it = ((LinkedList<KeyValue<K,V>>) this.table[i]).iterator();
            while (it.hasNext()) {
                list.addLast(it.next().getKey());
            }
        }
        return list.iterator();
    }
    
    @Override
    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        for (int i = 0; i < this.size ; i++) {
            Iterator<KeyValue<K,V>> it = ((LinkedList<KeyValue<K,V>>) this.table[i]).iterator();
            while (it.hasNext()) {
                list.addLast(it.next().getValue());
            }
        }
        return list.iterator();
    }
    
    
    protected int calculateIndex(K key) {
        int hashCode = key.hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode % this.size;
    }
    
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        PositionList<KeyValue<K,V>> list = (PositionList<KeyValue<K,V>>) synonyms;
        Position<KeyValue<K,V>> toReturn = null;
        for (Position<KeyValue<K,V>> p : list.positions()) {
            if (p.element().getKey().equals(key)) {
                toReturn = p;
            }
        }
        return toReturn;
    }
    
    @Override
    public String toString() {
        String s = "";
        for (int i=0; i < this.size; i++) {
            s += "["+i+"]";
            Iterator<KeyValue<K,V>> it = ((LinkedList<KeyValue<K,V>>) this.table[i]).iterator();
            while (it.hasNext()) {
                KeyValue<K,V> kv = it.next();
                s += " -> " + kv.toString();
            }
            s += "\n";
        }
        return s;
    }
    
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> list = new LinkedList<KeyValue<K,V>>();
        list.addFirst(kv);
        this.table[index] = list;
        return list;
    }
    
    protected void deleteSynonymsList(int index) {
        this.table[index] = new LinkedList<KeyValue<K,V>>();
    }
    
}
