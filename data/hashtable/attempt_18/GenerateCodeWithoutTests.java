
public class HashTable<K,V> extends Object implements Dictionary<K,V> {
   //attributes
   private LinkedList<KeyValue<K,V>>[] table;
   private int tableSize;
   private int itemCount;
   
   //constructor
   public HashTable() {
      this.tableSize = 10;
      this.itemCount = 0;
      this.table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[tableSize];
      for(int i = 0; i < tableSize; i++) {
         table[i] = new LinkedList<KeyValue<K,V>>();
      }
   }
   
   public HashTable(int size) {
      this.tableSize = size;
      this.itemCount = 0;
      this.table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[tableSize];
      for(int i = 0; i < tableSize; i++) {
         table[i] = new LinkedList<KeyValue<K,V>>();
      }
   }
   
   //methods
   protected int calculateIndex(K key) {
      return Math.abs(key.hashCode()) % tableSize;
   }
   
   public boolean containsKey(K key) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonyms = table[index];
      for(KeyValue<K,V> kv : synonyms) {
         if(key.equals(kv.getKey())) {
            return true;
         }
      }
      return false;
   }
   
   protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
      LinkedList<KeyValue<K,V>> syn = new LinkedList<KeyValue<K,V>>();
      syn.add(kv);
      table[index] = syn;
      itemCount++;
      return syn;
   }
   
   public V delete(K key) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonyms = table[index];
      Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
      if(position == null) {
         return null;
      } else {
         itemCount--;
         return synonyms.remove(position).getValue();
      }
   }
   
   protected void deleteSynonymsList(int index) {
      table[index] = new LinkedList<KeyValue<K,V>>();
   }
   
   public V get(K key) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonyms = table[index];
      Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
      if(position == null) {
         return null;
      } else {
         return position.getElement().getValue();
      }
   }
   
   public boolean isEmpty() {
      return itemCount == 0;
   }
   
   public Iterator<K> keys() {
      LinkedList<K> keys = new LinkedList<K>();
      for(int i = 0; i < tableSize; i++) {
         LinkedList<KeyValue<K,V>> synonyms = table[i];
         for(KeyValue<K,V> kv : synonyms) {
            keys.add(kv.getKey());
         }
      }
      return keys.iterator();
   }
   
   public void put(K key, V value) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonyms = table[index];
      Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
      if(position == null) {
         createSynonymsList(index, new KeyValue<K,V>(key, value));
      } else {
         position.getElement().setValue(value);
      }
   }
   
   protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
      for(Position<KeyValue<K,V>> p = synonyms.first(); p != null; p = synonyms.next(p)) {
         if(key.equals(p.getElement().getKey())) {
            return p;
         }
      }
      return null;
   }
   
   public int size() {
      return itemCount;
   }
   
   public String toString() {
      String s = "[";
      for(int i = 0; i < tableSize; i++) {
         LinkedList<KeyValue<K,V>> synonyms = table[i];
         for(KeyValue<K,V> kv : synonyms) {
            s += kv.toString() + ",";
         }
      }
      if(s.length() > 1) {
         s = s.substring(0, s.length()-1);
      }
      s += "]";
      return s;
   }
   
   public Iterator<V> values() {
      LinkedList<V> values = new LinkedList<V>();
      for(int i = 0; i < tableSize; i++) {
         LinkedList<KeyValue<K,V>> syn = table[i];
         for(KeyValue<K,V> kv : syn) {
            values.add(kv.getValue());
         }
      }
      return values.iterator();
   }
}
