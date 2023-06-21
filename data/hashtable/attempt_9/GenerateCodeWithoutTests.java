
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
  
  // attributes
  private final int DEFAULT_TABLE_SIZE = 101; // chosen by experience
  private LinkedList<KeyValue<K,V>>[] table; // the table arranges linked lists of KeyValue objects
  private int size; // the size is the amount of KeyValue objects stored in the table
  
  // constructors
  public HashTable() {
    table = new LinkedList[DEFAULT_TABLE_SIZE];
    for (int i = 0; i < DEFAULT_TABLE_SIZE; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
    size = 0; // the table starts empty
  }
  public HashTable(int size) {
    table = new LinkedList[size];
    for (int i = 0; i < size; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
    this.size = 0; // the table starts empty
  }
  
  // methods
  protected int calculateIndex(K key) {
    return Math.abs(key.hashCode()) % table.length;
  }
  public boolean containsKey(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    return seekKeyInSynonyms(synonyms, key) != null;
  }
  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if (synonyms == null) {
      synonyms = new LinkedList<KeyValue<K,V>>();
      table[index] = synonyms;
    }
    synonyms.add(kv);
    size++;
    return synonyms;
  }
  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null) {
      return null;
    }
    else {
      KeyValue<K,V> kv = synonyms.remove(pos);
      if (synonyms.isEmpty()) {
        table[index] = null;
      }
      size--;
      return kv.getValue();
    }
  }
  protected void deleteSynonymsList(int index) {
    table[index] = null;
  }
  public V get(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null) {
      return null;
    }
    else {
      KeyValue<K,V> kv = synonyms.get(pos);
      return kv.getValue();
    }
  }
  public boolean isEmpty() {
    return size == 0;
  }
  public Iterator<K> keys() {
    LinkedList<K> list = new LinkedList<K>();
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null) {
        for (int j = 0; j < table[i].size(); j++) {
          KeyValue<K,V> kv = table[i].get(j);
          list.add(kv.getKey());
        }
      }
    }
    return list.iterator();
  }
  public void put(K key, V value) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null) {
      createSynonymsList(index, new KeyValue<K,V>(key,value));
    }
    else {
      KeyValue<K,V> kv = synonyms.get(pos);
      kv.setValue(value);
    }
  }
  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    if (synonyms != null) {
      Iterator<Position<KeyValue<K,V>>> it = synonyms.positions();
      while (it.hasNext()) {
        Position<KeyValue<K,V>> pos = it.next();
        if (pos.getElement().getKey().equals(key)) {
          return pos;
        }
      }
    }
    return null;
  }
  public int size() {
    return size;
  }
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    boolean first = true;
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null) {
        for (int j = 0; j < table[i].size(); j++) {
          KeyValue<K,V> kv = table[i].get(j);
          if (!first) {
            sb.append(", ");
          }
          sb.append(kv.getKey());
          sb.append(": ");
          sb.append(kv.getValue());
          first = false;
        }
      }
    }
    sb.append("}");
    return sb.toString();
  }
  public Iterator<V> values() {
    LinkedList<V> list = new LinkedList<V>();
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null) {
        for (int j = 0; j < table[i].size(); j++) {
          KeyValue<K,V> kv = table[i].get(j);
          list.add(kv.getValue());
        }
      }
    }
    return list.iterator();
  }
}


public class KeyValue<K,V> {
  // attributes
  private K key;
  private V value;
  
  // constructor
  public KeyValue(K key, V value) {
    this.key = key;
    this.value = value;
  }
  
  // methods
  public K getKey() {
    return key;
  }
  public V getValue() {
    return value;
  }
  public void setValue(V value) {
    this.value = value;
  }
}
