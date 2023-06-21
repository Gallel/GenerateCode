
public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    
    // Constructors
    
    public HashTable() {
        // default constructor for HashTable
    }
    
    public HashTable(int size) {
        // constructor for HashTable with parameter size
    }
    
    // Methods
    
    @Override
    public int size() {
        // retrieves the number of items in the container
    }
    
    @Override
    public boolean isEmpty() {
        // method to check if the container is empty
    }
    
    @Override
    public V get(K key) {
        // retrieves the value associated with a key
    }
    
    @Override
    public void put(K key, V value) {
        // add an item with an associated key, if possible
    }
    
    @Override
    public V delete(K key) {
        // delete the first matching key and associated item, if possible
    }
    
    @Override
    public Iterator<K> keys() {
        // retrieves the items in the container
    }
    
    @Override
    public Iterator<V> values() {
        // retrieves the items in the container
    }
    
    @Override
    public boolean containsKey(K key) {
        // checks for an item with a certain key
    }
    
    @Override
    public String toString() {
        // method overwriting Object.toString()
    }
    
    // Protected Methods
    
    protected int calculateIndex(K key) {
        // returns the calculated index of the key
    }
    
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        // returns a new linked list with the given index and key-value pair
    }
    
    protected void deleteSynonymsList(int index) {
        // deletes the list of synonyms with the given index
    }
    
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        // searches for the key in the list of synonyms and returns its position
    }
}
