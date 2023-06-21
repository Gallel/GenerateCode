
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V>{
    
    private int size;
    private LinkedList<KeyValue<K,V>>[] table;
    
    //Constructor without parameters (default table size).
    public HashTable() {
        this.size = 997;
        table = new LinkedList[this.size];
        for (int i = 0; i < this.size; i++) {
            table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }
    
    //Constructor with a parameter.
    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[this.size];
        for (int i = 0; i < this.size; i++) {
            table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }
    
    protected int calculateIndex(K key) {
        int hash = key.hashCode();
        int index = Math.abs(hash) % this.size;
        return index;
    }
    
    //Checks for an item with a certain key.
    public boolean containsKey(K key) {
        int index = this.calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms,key) != null;
    }
    
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = new LinkedList<KeyValue<K,V>>();
            table[index] = synonyms;
        }
        synonyms.addFirst(kv);
        return synonyms;
    }
    
    //Delete the first matching key and associated item, if possible.
    public V delete(K key) {
        int index = this.calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
        if (pos == null) {
            return null; 
        }
        synonyms.remove(pos);
        return pos.getElement().getValue();
    }
    
    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }
    
    //Retrieves the value associated with a key.
    public V get(K key) {
        int index = this.calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
        if (pos == null) {
            return null; 
        }
        return pos.getElement().getValue();
    }
    
    //Method to check if the container is empty.
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    //Retrieves the items in the container.
    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<>();
        for (int i = 0; i < this.size; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            for (KeyValue<K,V> kv : synonyms) {
                list.addFirst(kv.getKey());
            }
        } 
        return list.iterator();
    }
    
    //Add an item with an associated key, if possible.
    public void put(K key, V value) {
        int index = this.calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
        if (pos != null) {
            pos.setElement(new KeyValue<K,V>(key, value));
            return;
        }
        createSynonymsList(index, new KeyValue<K,V>(key, value));
    }
    
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        Iterator<Position<KeyValue<K,V>>> it = synonyms.positions().iterator();
        while (it.hasNext()) {
            Position<KeyValue<K,V>> pos = it.next();
            if (pos.getElement().getKey().equals(key)) {
                return pos;
            }
        }
        return null;
    }
    
    //Retrieves the number of items in the container.
    public int size() {
        int total = 0;
        for (int i = 0; i < this.size; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            total += synonyms.size();
        }
        return total;
    }
    
    //Method overwriting Object.toString
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < this.size; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            for (KeyValue<K,V> kv : synonyms) {
                s.append(kv);
                s.append(", ");
            }
        } 
        s.append("]");
        return s.toString();
    }
    
    //Retrieves the items in the container.
    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<>();
        for (int i = 0; i < this.size; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            for (KeyValue<K,V> kv : synonyms) {
                list.addFirst(kv.getValue());
            }
        } 
        return list.iterator();
    }
}
