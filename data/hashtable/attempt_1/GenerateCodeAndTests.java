
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    // Constructor without parameters (default table size).
    public HashTable() {
        // constructor code here
    }

    // Constructor with a parameter.
    public HashTable(int size) {
        // constructor code here
    }

    protected int calculateIndex(K key) {
        // method code here
    }

    // Checks for an item with a certain key.
    public boolean containsKey(K key) {
        // method code here
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        // method code here
    }

    // Delete the first matching key and associated item, if possible.
    public V delete(K key) {
        // method code here
    }

    protected void deleteSynonymsList(int index) {
        // method code here
    }

    // Retrieves the value associated with a key.
    public V get(K key) {
        // method code here
    }

    // Method to check if the container is empty.
    public boolean isEmpty() {
        // method code here
    }

    // Retrieves the keys in the container.
    public Iterator<K> keys() {
        // method code here
    }

    // Add an item with an associated key, if possible.
    public void put(K key, V value) {
        // method code here
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        // method code here
    }

    // Retrieves the number of items in the container.
    public int size() {
        // method code here
    }

    // Method overwriting Object.toString ().
    public String toString() {
        // method code here
    }

    // Retrieves the values in the container.
    public Iterator<V> values() {
        // method code here
    }
}
