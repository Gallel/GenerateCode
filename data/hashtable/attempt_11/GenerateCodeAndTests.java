
public class HashTable<K,V> extends Object implements Dictionary<K,V> {
  // Constructor without parameters (default table size).
  public HashTable() {}

  // Constructor with a parameter.
  public HashTable(int size) {}

  // Checks for an item with a certain key.
  public boolean containsKey(K key) {}

  // Add an item with an associated key, if possible.
  public void put(K key, V value) {}

  // Deletes the first matching key and associated item, if possible.
  public V delete(K key) {}

  // Retrieves the value associated with a key.
  public V get(K key) {}

  // Method to check if the container is empty.
  public boolean isEmpty() {}

  // Retrieves the items in the container.
  public Iterator<K> keys() {}

  // Retrieves the items in the container.
  public Iterator<V> values() {}

  // Method overwriting Object.toString().
  public String toString() {}

  // Retrieves the number of items in the container.
  public int size() {}

  // Protected method to calculate an index for a certain key.
  protected int calculateIndex(K key) {}

  // Protected method to create synonyms list.
  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {}

  // Protected method to delete the synonyms list.
  protected void deleteSynonymsList(int index) {}

  // Protected method to seek for a key in synonyms list.
  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {}
}
