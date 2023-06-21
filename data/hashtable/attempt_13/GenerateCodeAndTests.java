
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {
  
  //Attributes
  private LinkedList<KeyValue<K,V>>[] table;
  private int size;
  
  //Constructors
  public HashTable() {
    this(997);
  }
  
  public HashTable(int size) {
    this.table = new LinkedList[size];
    for (int i = 0; i < size; i++) {
      table[i] = new LinkedList<>();
    }
    this.size = 0;
  }
  
  //Methods
  protected int calculateIndex(K key) {
    int hash = key.hashCode();
    return (Math.abs(hash) % table.length);
  }
  
  public boolean containsKey(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = table[index];
    return seekKeyInSynonyms(synonyms, key) != null;
  }
  
  protected void deleteSynonymsList(int index) {
    table[index] = new LinkedList<>();
  }
  
  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = table[index];
    Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null) {
      return null;
    }
    V value = pos.getElement().getValue();
    synonyms.remove(pos);
    size--;
    return value;
  }
  
  public V get(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = table[index];
    Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null) {
      return null;
    }
    return pos.getElement().getValue();
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public Iterator<K> keys() {
    LinkedList<K> keys = new LinkedList<>();
    for (int i = 0; i < table.length; i++) {
      for (KeyValue<K, V> kv : table[i]) {
        keys.add(kv.getKey());
      }
    }
    return keys.iterator();
  }
  
  public void put(K key, V value) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = table[index];
    if (seekKeyInSynonyms(synonyms, key) == null) {
      synonyms.addLast(new KeyValue<>(key, value));
      size++;
    } else {
      Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
      pos.getElement().setValue(value);
    }
  }
  
  protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
    for (Position<KeyValue<K, V>> pos: synonyms.positions()) {
      if (key.equals(pos.getElement().getKey())) {
        return pos;
      }
    }
    return null;
  }
  
  public int size() {
    return size;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < table.length; i++) {
      for (KeyValue<K, V> kv : table[i]) {
        sb.append(kv);
        sb.append(", ");
      }
    }
    if (size > 0) {
      sb.setLength(sb.length() - 2);
    }
    sb.append("]");
    return sb.toString();
  }
  
  public Iterator<V> values() {
    LinkedList<V> values = new LinkedList<>();
    for (int i = 0; i < table.length; i++) {
      for (KeyValue<K, V> kv : table[i]) {
        values.add(kv.getValue());
      }
    }
    return values.iterator();
  }
}
