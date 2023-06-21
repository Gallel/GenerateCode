
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V>{
  
  protected LinkedList<KeyValue<K,V>>[] table;
  protected int currentSize;

  public HashTable(){
    this(101); //default size
  }

  public HashTable(int size){
    table = new LinkedList[nextPrime(size)];
    for(int i = 0; i < table.length; i++){
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  protected int calculateIndex(K key){
    int hash = key.hashCode();
    if(hash < 0) hash = -hash;
    return hash % table.length;
  }

  protected int nextPrime(int n){
    if(n % 2 == 0) n++;
    for(; !isPrime(n); n += 2);
    return n;
  }

  private boolean isPrime(int n){
    if(n == 2 || n == 3) return true;
    if(n == 1 || n % 2 == 0) return false;
    for(int i = 3; i * i <= n; i += 2){
      if(n % i == 0) return false;
    }
    return true;
  }

  public boolean containsKey(K key){
    LinkedList<KeyValue<K,V>> synonyms = table[calculateIndex(key)];
    return seekKeyInSynonyms(synonyms, key) != null;
  }

  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv){
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if(synonyms == null){
      synonyms = new LinkedList<KeyValue<K,V>>();
      table[index] = synonyms;
    }
    return synonyms;
  }

  public V delete(K key){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
    if(pos == null) return null;
    else{
      synonyms.remove(pos);
      currentSize--;
      if(synonyms.isEmpty()) table[index] = null;
      return pos.element().getValue();
    }
  }

  protected void deleteSynonymsList(int index){
    table[index] = null;
  }

  public V get(K key){
    LinkedList<KeyValue<K,V>> synonyms = table[calculateIndex(key)];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
    if(pos == null) return null;
    else return pos.element().getValue();
  }

  public boolean isEmpty(){
    return size() == 0;
  }

  public Iterator<K> keys(){
    LinkedList<K> keys = new LinkedList<K>();
    for(int i = 0; i < table.length; i++){
        if(table[i] != null){
            for(KeyValue<K,V> kv : table[i]){
              keys.add(kv.getKey());
            }
        }
    }
    return keys.iterator();
  }

  public void put(K key, V value){
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if(seekKeyInSynonyms(synonyms, key) == null){
      synonyms.add(new KeyValue<K,V>(key, value));
      currentSize++;
      double loadFactor = (double) currentSize / table.length;
      if(loadFactor > 0.75) rehash();
    }
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key){
    if(synonyms == null) return null;
    for(Position<KeyValue<K,V>> pos = synonyms.first(); pos != null; pos = synonyms.next(pos)){
      if(pos.element().getKey().equals(key)){
        return pos;
      }
    }
    return null;
  }

  protected void rehash(){
    LinkedList<KeyValue<K,V>>[] oldTable = table;
    table = new LinkedList[nextPrime(2*oldTable.length)];
    currentSize = 0;
    for(int i = 0; i < oldTable.length; i++){
      if(oldTable[i] != null){
        for(KeyValue<K,V> kv : oldTable[i]){
          put(kv.getKey(), kv.getValue());
        }
      }
    }
  }

  public int size(){
    return currentSize;
  }

  public String toString(){
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    for(int i = 0; i < table.length; i++){
      if(table[i] != null){
        for(KeyValue<K,V> kv : table[i]){
          sb.append(kv.getKey() + ":" + kv.getValue() + ", ");
        }
      }
    }
    String result = sb.toString();
    if(result.length() > 1) result = result.substring(0, result.length() - 2);
    result += "]";
    return result;
  }

  public Iterator<V> values(){
    LinkedList<V> values = new LinkedList<V>();
    for(int i = 0; i < table.length; i++){
        if(table[i] != null){
            for(KeyValue<K,V> kv : table[i]){
              values.add(kv.getValue());
            }
        }
    }
    return values.iterator();
  }

}
