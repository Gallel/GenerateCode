
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V>{
  
  // Attributes
  protected int size;
  protected int USED;
  protected float MAX_LOAD_FACTOR;
  protected LinkedList<KeyValue<K,V>>[] t;
  
  // Constructors  
  public HashTable(){
    this(23);
  }
  
  public HashTable(int size){
    this.size = size;
    this.USED = 0;
    this.MAX_LOAD_FACTOR = 1.3f;
    t = new LinkedList[size];
    for(int i=0;i<size;i++){
      t[i] = new LinkedList<KeyValue<K,V>>();
    }
  }
  
  // Operations
  protected int calculateIndex(K key){
    int index = key.hashCode() % size;
    if(index < 0) index += size;
    return index;
  }
  
  public boolean containsKey(K key){
    // Returns true if the key is in the dictionary, false otherwise.
    int i = calculateIndex(key);
    return seekKeyInSynonyms(t[i],key) != null;
  }
  
  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv){
    // Creates a new list of synonyms and initializes it with the given element.
    LinkedList<KeyValue<K,V>> synonyms = new LinkedList<KeyValue<K,V>>();
    synonyms.add(kv);
    t[index] = synonyms;
    USED++;
    return synonyms;
  }
  
  public V delete(K key){
    // Deletes the first occurrence of the key in the dictionary (if any).
    int i = calculateIndex(key);
    KeyValue<K,V> kv = seekKeyInSynonyms(t[i],key);
    if(kv != null){
      V v = kv.getValue();
      t[i].remove(kv);
      USED--;
      return v;
    }else{
      return null;
    }
  }
  
  protected void deleteSynonymsList(int index){
    // Deletes the list of synonyms at the given index, if it is not empty.
    LinkedList<KeyValue<K,V>> synonyms = t[index];
    if(!synonyms.isEmpty()){
      USED -= synonyms.size();
      t[index] = new LinkedList<KeyValue<K,V>>();
    }
  }

  public V get(K key){
    // Returns the value associated with the key (if any).
    int i = calculateIndex(key);
    KeyValue<K,V> kv = seekKeyInSynonyms(t[i],key);
    if(kv != null){
      return kv.getValue();
    }else{
      return null;
    }
  }
  
  public boolean isEmpty(){
    // Returns true if the dictionary is empty, false otherwise.
    return USED == 0;
  }
  
  public Iterator<K> keys(){
    // Returns an iterator containing all the keys of the dictionary (in an arbitrary order).
    LinkedList<K> keys = new LinkedList<K>();
    for(int i=0;i<size;i++){
      LinkedList<KeyValue<K,V>> synonyms = t[i];
      for(KeyValue<K,V> kv : synonyms){
        keys.add(kv.getKey());
      }
    }
    return keys.iterator();
  }
  
  public void put(K key, V value){
    // Inserts a new element into the dictionary, replacing the value associated with the key if there was one already present.
    KeyValue<K,V> kv = new KeyValue<K,V>(key,value);
    int i = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = t[i];
    if(synonyms.isEmpty()){
      createSynonymsList(i,kv);
    }else{
      KeyValue<K,V> oldkv = seekKeyInSynonyms(synonyms,key);
      if(oldkv != null){
        synonyms.remove(oldkv);
        USED--;
      }
      synonyms.add(kv);
      USED++;
    }
    if(USED > MAX_LOAD_FACTOR*size){
      rehash();
    }
  }
  
  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key){
    // Returns a reference to the location within the list of synonyms where the key is (if it is present in the list), or null otherwise.
    for(Position<KeyValue<K,V>> p = synonyms.first(); p != null; p = synonyms.next(p)){
      KeyValue<K,V> kv = synonyms.get(p);
      if(kv.getKey().equals(key)){
        return p;
      }
    }
    return null;
  }
  
  public int size(){
    // Returns the number of elements currently stored in the dictionary.
    return USED;
  }
  
  public String toString(){
    // Returns a string representation of the contents of the dictionary (for debugging purposes only).
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for(int i=0;i<size;i++){
      LinkedList<KeyValue<K,V>> synonyms = t[i];
      for(KeyValue<K,V> kv : synonyms){
        sb.append("(");
        sb.append(kv.getKey().toString());
        sb.append(",");
        sb.append(kv.getValue().toString());
        sb.append(")");
      }
    }
    sb.append("]");
    return sb.toString();
  }
  
  public Iterator<V> values(){
    // Returns an iterator containing all the values of the dictionary (in an arbitrary order).
    LinkedList<V> values = new LinkedList<V>();
    for(int i=0;i<size;i++){
      LinkedList<KeyValue<K,V>> synonyms = t[i];
      for(KeyValue<K,V> kv : synonyms){
        values.add(kv.getValue());
      }
    }
    return values.iterator();
  }
  
  protected void rehash(){
    // Doubles the size of the table and rehashes all the items that it contains.
    this.size *= 2;
    LinkedList<KeyValue<K,V>>[] newTable = new LinkedList[size];
    for(int i=0;i<size;i++){
      newTable[i] = new LinkedList<KeyValue<K,V>>();
    }
    for(int i=0;i<size/2;i++){
      LinkedList<KeyValue<K,V>> synonyms = t[i];
      for(KeyValue<K,V> kv : synonyms){
        int j = calculateIndex(kv.getKey());
        newTable[j].add(kv);
      }
    }
    t = newTable;
  }
}
