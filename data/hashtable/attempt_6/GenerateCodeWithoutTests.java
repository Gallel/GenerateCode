
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

   private LinkedList<KeyValue<K,V>>[] table;
   private int size;
   
   public HashTable() {
      this(997);
   }
   
   public HashTable(int size) {
      this.size = size;
      table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[size];
      for (int i = 0; i < size; i++) {
         table[i] = new LinkedList<KeyValue<K,V>>();
      }
   }
   
   protected int calculateIndex(K key) {
      return Math.abs(key.hashCode()) % size;
   }
   
   public boolean containsKey(K key) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonymsList = table[index];
      return seekKeyInSynonyms(synonymsList, key) != null;
   }
   
   protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
      LinkedList<KeyValue<K,V>> synonymsList = table[index];
      if (synonymsList == null) {
         synonymsList = new LinkedList<KeyValue<K,V>>();
         table[index] = synonymsList;
      }
      synonymsList.add(kv);
      return synonymsList;
   }
   
   public V delete(K key) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonymsList = table[index];
      Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonymsList, key);
      if (position == null) return null;
      synonymsList.remove(position);
      return position.getElement().getValue();
   }
   
   protected void deleteSynonymsList(int index) {
      table[index] = null;
   }
   
   public V get(K key) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonymsList = table[index];
      Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonymsList, key);
      if (position == null) return null;
      return position.getElement().getValue();
   }
   
   public boolean isEmpty() {
      return size() == 0;
   }
   
   public Iterator<K> keys() {
      LinkedList<K> keysList = new LinkedList<K>();
      for (int i = 0; i < size; i++) {
         LinkedList<KeyValue<K,V>> synonymsList = table[i];
         for (KeyValue<K,V> kv : synonymsList) {
            keysList.add(kv.getKey());
         }
      }
      return keysList.iterator();
   }
   
   public void put(K key, V value) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonymsList = table[index];
      Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonymsList, key);
      if (position == null) {
         createSynonymsList(index, new KeyValue<K,V>(key, value));
      }
      else {
         position.getElement().setValue(value);
      }
   }
   
   protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms,
                                                        K key) {
      if (synonyms == null) return null;
      Iterator<Position<KeyValue<K,V>>> it = synonyms.positions().iterator();
      while (it.hasNext()) {
         Position<KeyValue<K,V>> position = it.next();
         if (position.getElement().getKey().equals(key)) return position;
      }
      return null;
   }
   
   public int size() {
      int counter = 0;
      for (int i = 0; i < size; i++) {
         counter += table[i].size();
      }
      return counter;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      for (int i = 0; i < size; i++) {
         LinkedList<KeyValue<K,V>> synonymsList = table[i];
         for (KeyValue<K,V> kv : synonymsList) {
            sb.append(kv);
            sb.append(", ");
         }
      }
      sb.append("]");
      return sb.toString();
   }
   
   public Iterator<V> values() {
      LinkedList<V> valuesList = new LinkedList<V>();
      for (int i = 0; i < size; i++) {
         LinkedList<KeyValue<K,V>> synonymsList = table[i];
         for (KeyValue<K,V> kv : synonymsList) {
            valuesList.add(kv.getValue());
         }
      }
      return valuesList.iterator();
   }
}