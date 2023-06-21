
public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    
    // default constructor
    public HashTable(){
    
    }
    
    // constructor with parameter
    public HashTable(int size){
    
    }
    
    // method to calculate index of vector
    protected int calculateIndex(K key){
    
    }
    
    // method to check if item with key is present
    public boolean containsKey(K key){
    
    }
    
    // method to create synonym list
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv){
    
    }
    
    // method to delete the first matching key and associated item
    public V delete(K key){
    
    }
    
    // method to delete synonyms list
    protected void deleteSynonymsList(int index){
    
    }
    
    // method to get value associated with key
    public V get(K key){
    
    }
    
    // method to check if hashtable is empty
    public boolean isEmpty(){
    
    }
    
    // method to retrieve keys in hashtable
    public Iterator<K> keys(){
    
    }
    
    // method to add an item with an associated key
    public void put(K key, V value){
    
    }
    
    // method to seek key in synonyms list
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key){
    
    }
    
    // method to retrieve size of hashtable
    public int size(){
    
    }
    
    // overwriting toString() method
    @Override
    public String toString(){
    
    }
    
    // method to retrieve values in hashtable
    public Iterator<V> values(){
    
    }
}
