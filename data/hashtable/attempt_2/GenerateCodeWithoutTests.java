
import java.util.*;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

   // Default constructor
   public HashTable() {
      
   }
   
   // Constructor with parameter
   public HashTable(int size) {
      
   }
   
   // Method to calculate index of key
   protected int calculateIndex(K key) {
      
   }
   
   // Method to check if key exists
   public boolean containsKey(K key) {
      
   }
   
   // Method to create synonyms list
   protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
      
   }
   
   // Method to delete key-value pair
   public V delete(K key) {
      
   }
   
   // Method to delete synonyms list
   protected void deleteSynonymsList(int index) {
      
   }
   
   // Method to get value associated with key
   public V get(K key) {
      
   }
   
   // Method to check if container is empty
   public boolean isEmpty() {
      
   }
   
   // Method to retrieve keys
   public Iterator<K> keys() {
      
   }
   
   // Method to put key-value pair
   public void put(K key, V value) {
      
   }
   
   // Method to seek key in synonyms list
   protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
      
   }
   
   // Method to retrieve size of container
   public int size() {
      
   }
   
   // Method to print container
   public String toString() {
      
   }
   
   // Method to retrieve values
   public Iterator<V> values() {
      
   }
   
}
