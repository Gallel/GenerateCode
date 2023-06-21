
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  private int size;
  private LinkedList<KeyValue<K,V>>[] table;

  public HashTable() {
    this(997);
  }

  @SuppressWarnings("unchecked")
  public HashTable(int size) {
    this.size = size;
    this.table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[size];
    for(int i = 0; i < size; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  protected int calculateIndex(K key) {
    return (key.hashCode() & 0x7fffffff) % size;
  }

  public boolean containsKey(K key) {
    return get(key) != null;
  }

  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if(synonyms == null) {
      synonyms = new LinkedList<KeyValue<K,V>>();
      table[index] = synonyms;
    }
    return synonyms;
  }

  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if(synonyms == null) {
      return null;
    }
    Iterator<KeyValue<K,V>> iterator = synonyms.iterator();
    while(iterator.hasNext()) {
      KeyValue<K,V> kv = iterator.next();
      if(kv.getKey().equals(key)) {
        iterator.remove();
        return kv.getValue();
      }
    }
    return null;
  }

  protected void deleteSynonymsList(int index) {
    table[index] = null;
  }

  public V get(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if(synonyms == null) {
      return null;
    }
    for(KeyValue<K,V> kv : synonyms) {
      if(kv.getKey().equals(key)) {
        return kv.getValue();
      }
    }
    return null;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public Iterator<K> keys() {
    LinkedList<K> list = new LinkedList<K>();
    for(int i = 0; i < size; i++) {
      if(table[i] == null) {
        continue;
      }
      for(KeyValue<K,V> kv : table[i]) {
        list.add(kv.getKey());
      }
    }
    return list.iterator();
  }

  public void put(K key, V value) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = createSynonymsList(index, new KeyValue<K,V>(key, value));
    for(KeyValue<K,V> kv : synonyms) {
      if(kv.getKey().equals(key)) {
        kv.setValue(value);
        return;
      }
    }
    synonyms.add(new KeyValue<K,V>(key, value));
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    if(synonyms == null) {
      return null;
    }
    Position<KeyValue<K,V>> position = new LinkedListPosition<KeyValue<K,V>>(synonyms);
    while(position != null) {
      if(position.element().getKey().equals(key)) {
        return position;
      }
      position = synonyms.next(position);
    }
    return null;
  }

  public int size() {
    int count = 0;
    for(int i = 0; i < size; i++) {
      if(table[i] == null) {
        continue;
      }
      count += table[i].size();
    }
    return count;
  }

  public String toString() {
    String s = "[";
    for(int i = 0; i < size; i++) {
      if(table[i] == null) {
        continue;
      }
      for(KeyValue<K,V> kv : table[i]) {
        s += kv.getKey() + ": " + kv.getValue() + ", ";
      }
    }
    if(s.length() > 1) {
      s = s.substring(0, s.length() - 2);
    }
    return s + "]";
  }

  public Iterator<V> values() {
    LinkedList<V> list = new LinkedList<V>();
    for(int i = 0; i < size; i++) {
      if(table[i] == null) {
        continue;
      }
      for(KeyValue<K,V> kv : table[i]) {
        list.add(kv.getValue());
      }
    }
    return list.iterator();
  }
}