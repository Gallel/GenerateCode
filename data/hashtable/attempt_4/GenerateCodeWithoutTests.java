
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  //Attributes
  private int size;
  private LinkedList<KeyValue<K,V>>[] table;

  //Constructors
  public HashTable(){
    this.size = 997;
    this.table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[this.size];
    for(int i=0; i<this.size; i++){
      this.table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  public HashTable(int size){
    this.size = size;
    this.table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[this.size];
    for(int i=0; i<this.size; i++){
      this.table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  //Methods
  protected int calculateIndex(K key){
    return (key.hashCode() & 0x7fffffff) % this.size;
  }

  public boolean containsKey(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = this.table[index];

    for(KeyValue<K,V> kv : synonyms){
      if(kv.getKey().equals(key)){
        return true;
      }
    }

    return false;
  }

  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index,
 KeyValue<K,V> kv){
    LinkedList<KeyValue<K,V>> synonyms = this.table[index];
    if(synonyms == null){
      synonyms = new LinkedList<KeyValue<K,V>>();
      this.table[index] = synonyms;
    }
    synonyms.add(kv);
    return synonyms;
  }

  public V delete(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = this.table[index];

    for(KeyValue<K,V> kv : synonyms){
      if(kv.getKey().equals(key)){
        synonyms.remove(kv);
        return kv.getValue();
      }
    }

    return null;
  }

  protected void deleteSynonymsList(int index){
    this.table[index] = null;
  }

  public V get(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = this.table[index];

    for(KeyValue<K,V> kv : synonyms){
      if(kv.getKey().equals(key)){
        return kv.getValue();
      }
    }

    return null;
  }

  public boolean isEmpty(){
    int count = 0;
    for(int i=0; i<this.size; i++){
      if(this.table[i].size() > 0){
        count++;
      }
    }
    return (count == 0);
  }

  public Iterator<K> keys(){
    LinkedList<K> keys = new LinkedList<K>();
    for(int i=0; i<this.size; i++){
      for(KeyValue<K,V> kv : this.table[i]){
        keys.add(kv.getKey());
      }
    }
    return keys.iterator();
  }

  public void put(K key, V value){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = this.table[index];

    for(KeyValue<K,V> kv : synonyms){
      if(kv.getKey().equals(key)){
        kv.setValue(value);
        return;
      }
    }

    createSynonymsList(index, new KeyValue<K,V>(key, value));
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key){
  Iterator<KeyValue<K,V>> i = synonyms.iterator();
  while (i.hasNext()){
    KeyValue<K,V> kv = i.next();
    if (kv.getKey().equals(key)){
        return (Position<KeyValue<K,V>>) kv;
    }
  }
    return (Position<KeyValue<K,V>>) NULL;
  }

  public int size(){
    int count = 0;
    for(int i=0; i<this.size; i++){
      count += this.table[i].size();
    }
    return count;
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("{");

    for(int i=0; i<this.size; i++){
      for(KeyValue<K,V> kv : this.table[i]){
        sb.append(kv.getKey() + "=" + kv.getValue() + ",");
      }
    }

    if(sb.length() > 1){
      sb.setLength(sb.length() - 1);
    }

    sb.append("}");
    return sb.toString();
  }

  public Iterator<V> values(){
    LinkedList<V> values = new LinkedList<V>();
    for(int i=0; i<this.size; i++){
      for(KeyValue<K,V> kv : this.table[i]){
        values.add(kv.getValue());
      }
    }
    return values.iterator();
  }

}
