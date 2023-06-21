
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  // Constructor without parameters (default table size).
  public HashTable() {
    (write the code here)
  }

  // Constructor with a parameter.
  public HashTable(int size) {
    (write the code here)
  }
  
  protected int calculateIndex(K key) {
    (write the code here)
  }

  // Checks for an item with a certain key.
  public boolean containsKey(K key) {
    (write the code here)
  }

  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index,
  KeyValue<K,V> kv) {
    (write the code here)
  }

  // Delete the first matching key and associated item, if possible.
  public V delete(K key) {
    (write the code here)
  }

  protected void deleteSynonymsList(int index) {
    (write the code here)
  }

  // Retrieves the value associated with a key.
  public V get(K key) {
    (write the code here)
  }

  // Method to check if the container is empty.
  public boolean isEmpty() {
    (write the code here)
  }

  // Retrieves the items in the container.
  public Iterator<K> keys() {
    (write the code here)
  }

  // Add an item with an associated key, if possible.
  public void put(K key, V value) {
    (write the code here)
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms,
  K key) {
    (write the code here)
  }

  // Retrieves the number of items in the container.
  public int size() {
    (write the code here)
  }

  // Method overwriting Object.toString ().
  public String toString() {
    (write the code here)
  }

  // Retrieves the items in the container.
  public Iterator<V> values() {
    (write the code here)
  }
}
