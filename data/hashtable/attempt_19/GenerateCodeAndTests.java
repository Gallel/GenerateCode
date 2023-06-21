
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
   public HashTable() {}
   public HashTable(int size) {}
   protected int calculateIndex(K key) {}
   public boolean containsKey(K key) {}
   protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {}
   public V delete(K key) {}
   protected void deleteSynonymsList(int index) {}
   public V get(K key) {}
   public boolean isEmpty() {}
   public Iterator<K> keys() {}
   public void put(K key, V value) {}
   protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {}
   public int size() {}
   public String toString() {}
   public Iterator<V> values() {}
}

class KeyValue<K,V> {
  K key;
  V value;
  
  public KeyValue(K key, V value) {
    this.key = key;
    this.value = value;
  }
  
  public K getKey() {
    return key;
  }
  
  public V getValue() {
    return value;
  }
  
  public void setValue(V value) {
    this.value = value;
  }
  
  public String toString() {
    return "(" + key.toString() + ", " + value.toString() + ")";
  }
} 
