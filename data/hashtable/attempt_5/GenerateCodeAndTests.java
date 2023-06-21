
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class that implements the operations of a dictionary using an indirect scattering table,
 * known as a chained table open (separate chaining).
 */
public class HashTable<K, V> extends Object implements Dictionary<K, V> {

    /**
     * Constructor without parameters (default table size).
     */
    public HashTable() {
        //implementation
    }

    /**
     * Constructor with a parameter.
     *
     * @param size
     */
    public HashTable(int size) {
        //implementation
    }

    /**
     * Checks for an item with a certain key.
     *
     * @param key
     * @return boolean
     */
    public boolean containsKey(K key) {
        //implementation
    }

    /**
     * Deletes the first matching key and associated item, if possible.
     *
     * @param key
     * @return V
     */
    public V delete(K key) {
        //implementation
    }

    /**
     * Retrieves the value associated with a key.
     *
     * @param key
     * @return V
     */
    public V get(K key) {
        //implementation
    }

    /**
     * Method to check if the container is empty.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        //implementation
    }

    /**
     * Retrieves the items in the container.
     *
     * @return Iterator<K>
     */
    public Iterator<K> keys() {
        //implementation
    }

    /**
     * Add an item with an associated key, if possible.
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        //implementation
    }

    /**
     * Retrieves the number of items in the container.
     *
     * @return int
     */
    public int size() {
        //implementation
    }

    /**
     * Method overwriting Object.toString().
     *
     * @return String
     */
    public String toString() {
        //implementation
    }

    /**
     * Retrieves the items in the container.
     *
     * @return Iterator<V>
     */
    public Iterator<V> values() {
        //implementation
    }

    /**
     * Calculates the index for the key.
     *
     * @param key
     * @return int
     */
    protected int calculateIndex(K key) {
        //implementation
    }

    /**
     * Deletes all synonyms for a given index.
     *
     * @param index
     */
    protected void deleteSynonymsList(int index) {
        //implementation
    }

    /**
     * Creates the list of synonyms for a given index.
     *
     * @param index
     * @param kv
     * @return LinkedList<KeyValue<K, V>>
     */
    protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
        //implementation
    }

    /**
     * Search for a key in the list of synonyms for a given index.
     *
     * @param synonyms
     * @param key
     * @return Position<KeyValue<K, V>>
     */
    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
        //implementation
    }

}
