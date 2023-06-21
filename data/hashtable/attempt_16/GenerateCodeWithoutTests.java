
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    
    //default constructor
    public HashTable() {
        
    }
    
    //constructor with parameter
    public HashTable(int size) {
        
    }
    
    protected int calculateIndex(K key) {
        
    }
    
    public boolean containsKey(K key) {
        
    }
    
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        
    }
    
    public V delete(K key) {
        
    }
    
    protected void deleteSynonymsList(int index) {
        
    }
    
    public V get(K key) {
        
    }
    
    public boolean isEmpty() {
        
    }
    
    public Iterator<K> keys() {
        
    }
    
    public void put(K key, V value) {
        
    }
    
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        
    }
    
    public int size() {
        
    }
    
    @Override
    public String toString() {
        
    }
    
    public Iterator<V> values() {
        
    }
    
}

class KeyValue<K,V> {
    //attributes
    private K key;
    private V value;

    //Constructor
    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    //Getters and setters
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
 
interface Dictionary<K,V> {

    boolean containsKey(K key);

    V get(K key);

    boolean isEmpty();

    Iterator<K> keys();

    void put(K key, V value);

    V delete(K key);

    int size();

    Iterator<V> values();
    
}
