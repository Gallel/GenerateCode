
import java.util.LinkedList; 

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  //Attributes
  protected int sizeTable; 
  protected LinkedList<KeyValue<K,V>>[] table;
  
  //Constructors
  public HashTable(){
    this(1000); //default table size
  }
  
  public HashTable(int size){
    this.sizeTable = size;
    this.table = new LinkedList[size];
    for(int i=0; i<size; i++){
      this.table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }
  
  //Methods
  protected int calculateIndex(K key){
    return Math.abs(key.hashCode()) % this.sizeTable;
  }
  
  public boolean containsKey(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonymsList = table[index];
    Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonymsList, key);
    if(p==null){
      return false;
    }else{
      return true;
    }
  }
  
  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index,
 KeyValue<K,V> kv){
    LinkedList<KeyValue<K,V>> synonymsList = new LinkedList<KeyValue<K,V>>();
    synonymsList.addFirst(kv);
    this.table[index] = synonymsList;
    return synonymsList;
  }
  
  public V delete(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonymsList = table[index];
    Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonymsList, key);
    if(p==null){
      return null;
    }else{
      KeyValue<K,V> kv = synonymsList.remove(p);
      if(synonymsList.isEmpty()){
        table[index] = null;
      }
      return kv.getValue();
    }
  }
  
  protected void deleteSynonymsList(int index){
    this.table[index] = null;
  }
  
  public V get(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonymsList = table[index];
    Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonymsList, key);
    if(p==null){
      return null;
    }else{
      KeyValue<K,V> kv = synonymsList.get(p);
      return kv.getValue();
    }
  }
  
  public boolean isEmpty(){
    return size() ==0;
  }
  
  public Iterator<K> keys(){
    LinkedList<K> keysList = new LinkedList<K>();
    for(int i=0; i<this.sizeTable; i++){
      LinkedList<KeyValue<K,V>> synonymsList = table[i];
      if(synonymsList != null){
        for(KeyValue<K,V> kv : synonymsList){
          keysList.add(kv.getKey());
        }
      }
    }
    return keysList.iterator();
  }
  
  public void put(K key, V value){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonymsList = table[index];
    Position<KeyValue<K,V>> p = seekKeyInSynonyms(synonymsList, key);
    KeyValue<K,V> kv = new KeyValue<K,V>(key, value);
    if(p==null){
      synonymsList.addFirst(kv);
    }else{
      synonymsList.set(p, kv);
    }
  }
  
  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms,
 K key){
    Position<KeyValue<K,V>> p = synonyms.first();
    while(p!=null){
      KeyValue<K,V> kv = p.element();
      if(key.equals(kv.getKey())){
        return p;
      }
      p = synonyms.next(p);
    }
    return null;
  }
  
  public int size(){
    int counter = 0;
    for(int i=0; i<this.sizeTable; i++){
      LinkedList<KeyValue<K,V>> synonymsList = table[i];
      if(synonymsList != null){
        counter += synonymsList.size();
      }
    }
    return counter;
  }
  
  public String toString(){
    if(isEmpty()){
      return "{}";
    }
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for(int i=0; i<this.sizeTable; i++){
      LinkedList<KeyValue<K,V>> synonymsList = table[i];
      if(synonymsList != null){
        for(KeyValue<K,V> kv : synonymsList){
          sb.append(kv.getKey() + "=" + kv.getValue() + ", ");
        }
      }
    }
    sb.delete(sb.length()-2, sb.length());
    sb.append("}");
    return sb.toString();
  }
  
  public Iterator<V> values(){
    LinkedList<V> valuesList = new LinkedList<V>();
    for(int i=0; i<this.sizeTable; i++){
      LinkedList<KeyValue<K,V>> synonymsList = table[i];
      if(synonymsList != null){
        for(KeyValue<K,V> kv : synonymsList){
          valuesList.add(kv.getValue());
        }
      }
    }
    return valuesList.iterator();
  }
}
