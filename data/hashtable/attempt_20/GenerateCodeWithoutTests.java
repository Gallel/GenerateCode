
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  private LinkedList<KeyValue<K,V>>[] table;
  private int tableSize;
  private int size;

  public HashTable() {
    this.tableSize = 997;
    this.table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[tableSize];
    for(int i = 0; i < tableSize; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  public HashTable(int size) {
    this.tableSize = size;
    this.table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[tableSize];
    for(int i = 0; i < tableSize; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  protected int calculateIndex(K key) {
    return (key.hashCode() & 0x7fffffff) % tableSize;
  }

  public boolean containsKey(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    return seekKeyInSynonyms(synonyms, key) != null;
  }

  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if(synonyms == null) {
      synonyms = new LinkedList<KeyValue<K,V>>();
      table[index] = synonyms;
    }
    synonyms.addFirst(kv);

    return synonyms;
  }

  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];

    Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
    if(position == null) {
      return null;
    } else {
      size--;
      return synonyms.remove(position).getValue();
    }
  }

  protected void deleteSynonymsList(int index) {
    table[index] = null;
  }

  public V get(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];

    Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
    if(position == null) {
      return null;
    } else {
      return position.getValue().getValue();
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Iterator<K> keys() {
    List<K> keys = new ArrayList<K>();
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
      size++;
      KeyValue<K,V> kv = new KeyValue<K,V>(key, value);
      createSynonymsList(index, kv);
    } else {
      position.getValue().setValue(value);
    }
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    if(synonyms == null) {
      return null;
    }
    Iterator<KeyValue<K,V>> iterator = synonyms.iterator();
    int i = 0;
    while(iterator.hasNext()){
      if(iterator.next().getKey().equals(key)) {
        return new NodePositionList<KeyValue<K,V>>(synonyms, i);
      }
      i++;
    }
    return null;
  }

  public int size() {
    return size;
  }

  public String toString() {
    String s = "[";
    Iterator<K> keys = keys();
    while(keys.hasNext()) {
      s += keys.next();
      if(keys.hasNext()) {
        s += ", ";
      }
    }
    s += "]";
    return s;
  }

  public Iterator<V> values() {
    List<V> values = new ArrayList<V>();
    for(int i = 0; i < tableSize; i++) {
      LinkedList<KeyValue<K,V>> synonyms = table[i];
      for(KeyValue<K,V> kv : synonyms) {
        values.add(kv.getValue());
      }
    }
    return values.iterator();
  }
}

public class KeyValue<K, V> {
  private K key;
  private V value;

  public KeyValue(K key, V value) {
    this.key = key;
    this.value = value;
  }

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

public interface Dictionary<K,V> {

  // Checks for an item with a certain key.
  boolean containsKey(K key);

  // Delete the first matching key and associated item, if possible.
  V delete(K key);

  // Get the value associated with a key.
  V get(K key);

  // Method to check if the container is empty.
  boolean isEmpty();

  // Retrieves the items in the container.
  Iterator<K> keys();

  // Add an item with an associated key, if possible.
  void put(K key, V value);

  // Retrieves the number of items in the container.
  int size();

  // Method overwriting Object.toString().
  String toString();

  // Retrieves the items in the container.
  Iterator<V> values();
}
