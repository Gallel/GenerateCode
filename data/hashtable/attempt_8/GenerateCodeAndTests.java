
public class HashTable<K,V> extends Object implements Dictionary<K,V> {
  
  // default constructor
  public HashTable() {}
  
  // constructor with size parameter
  public HashTable(int size) {}
  
  protected int calculateIndex(K key) {}
  
  // check for item with certain key
  public boolean containsKey(K key) {}
  
  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {}
  
  // delete an item with a certain key
  public V delete(K key) {}
  
  protected void deleteSynonymsList(int index) {}
  
  // retrieve the value associated with a key
  public V get(K key) {}
  
  // check if container is empty
  public boolean isEmpty() {}
  
  // retrieve the keys in the container
  public Iterator<K> keys() {}
  
  // add an item with an associated key
  public void put(K key, V value) {}
  
  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {}
  
  // retrieve the number of items in the container
  public int size() {}
  
  // method overwriting Object.toString()
  public String toString() {}
  
  // retrieve the items in the container
  public Iterator<V> values() {}
  
}
