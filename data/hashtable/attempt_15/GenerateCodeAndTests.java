
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  // Private class that implements the key-value pair
  private class KeyValue<K,V>{
    K key;
    V value;

    KeyValue(K key, V value){
      this.key = key;
      this.value = value;
    }

    public boolean equals(Object obj){
      if(obj instanceof KeyValue){
        KeyValue<K,V> kv = (KeyValue<K,V>) obj;
        return key.equals(kv.key);
      }
      return false;
    }

    public String toString(){
      return "{" + key.toString() + ", " + value.toString() + "}";
    }
  }

  // Array of synonym lists
  protected LinkedList<KeyValue<K,V>>[] table;
  // Number of elements in the table
  protected int count;

  // Constructors
  public HashTable(){
    this(997);
  }

  public HashTable(int size){
    table = new LinkedList[size];
    for(int i=0; i<size; i++){
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
    count = 0;
  }

  protected int calculateIndex(K key){
    return (key.hashCode() & 0x7FFFFFFF) % table.length;
  }

  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv){
    LinkedList<KeyValue<K,V>> list = table[index];
    if(list == null){
      list = new LinkedList<KeyValue<K,V>>();
      table[index] = list;
    }
    return list;
  }

  public boolean containsKey(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> list = table[index];
    return list != null && list.indexOf(new KeyValue<K,V>(key,null)) != -1;
  }

  public V delete(K key){
    V value = null;
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> list = table[index];
    if(list != null){
      int position = list.indexOf(new KeyValue<K,V>(key,null));
      if(position != -1){
        KeyValue<K,V> kv = list.remove(position);
        value = kv.value;
        count--;
        if(list.isEmpty()){
          table[index] = null;
        }
      }
    }
    return value;
  }

  protected void deleteSynonymsList(int index){
    table[index] = null;
  }

  public V get(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> list = table[index];
    if(list != null){
      int position = list.indexOf(new KeyValue<K,V>(key,null));
      if(position != -1){
        KeyValue<K,V> kv = list.get(position);
        return kv.value;
      }
    }
    return null;
  }

  public boolean isEmpty(){
    return count == 0;
  }

  public Iterator<K> keys(){
    LinkedList<K> keys = new LinkedList<K>();
    for(int i=0; i<table.length; i++){
      LinkedList<KeyValue<K,V>> list = table[i];
      if(list != null){
        for(KeyValue<K,V> kv : list){
          keys.add(kv.key);
        }
      }
    }
    return keys.iterator();
  }

  public void put(K key, V value){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> list = createSynonymsList(index, new KeyValue<K,V>(key,value));
    KeyValue<K,V> kv = new KeyValue<K,V>(key,value);
    int position = list.indexOf(kv);
    if(position == -1){
      list.add(kv);
      count++;
    }else{
      list.set(position, kv);
    }
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key){
    if(synonyms != null && !synonyms.isEmpty()){
      for(int i=0; i<synonyms.size(); i++){
        KeyValue<K,V> kv = synonyms.get(i);
        if(kv != null && kv.key.equals(key)){
          return new Position<KeyValue<K,V>>(synonyms, i);
        }
      }
    }
    return null;
  }

  public int size(){
    return count;
  }

  public String toString(){
    StringBuilder result = new StringBuilder();
    result.append("[");
    Iterator<K> keys = keys();
    while(keys.hasNext()){
      K key = keys.next();
      result.append(key.toString() + ": " + get(key).toString());
      if(keys.hasNext()){
        result.append(", ");
      }
    }
    result.append("]");
    return result.toString();
  }

  public Iterator<V> values(){
    LinkedList<V> values = new LinkedList<V>();
    for(int i=0; i<table.length; i++){
      LinkedList<KeyValue<K,V>> list = table[i];
      if(list != null){
        for(KeyValue<K,V> kv : list){
          values.add(kv.value);
        }
      }
    }
    return values.iterator();
  }

}
