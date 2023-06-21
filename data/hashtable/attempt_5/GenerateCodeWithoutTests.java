
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {
  
  private LinkedList<KeyValue<K, V>>[] table;
  private int count;
  
  @SuppressWarnings("unchecked")
  public HashTable() {
    table = new LinkedList[31];
  }
  
  @SuppressWarnings("unchecked")
  public HashTable(int size) {
    table = new LinkedList[size];
  }
  
  protected int calculateIndex(K key) {
    int hash = key.hashCode();
    int index = Math.abs(hash) % table.length;
    return index;
  }
  
  public boolean containsKey(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = table[index];
    if (synonyms == null) {
      return false;
    }
    for (KeyValue<K, V> kv : synonyms) {
      if (kv.getKey().equals(key)) {
        return true;
      }
    }
    return false;
  }
  
  protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
    LinkedList<KeyValue<K, V>> synonyms = new LinkedList<KeyValue<K, V>>();
    synonyms.add(kv);
    table[index] = synonyms;
    count++;
    return synonyms;
  }
  
  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = table[index];
    if (synonyms == null) {
      return null;
    }
    for (KeyValue<K, V> kv : synonyms) {
      if (kv.getKey().equals(key)) {
        V value = kv.getValue();
        synonyms.remove(kv);
        if (synonyms.isEmpty()) {
          table[index] = null;
        }
        count--;
        return value;
      }
    }
    return null;
  }
  
  protected void deleteSynonymsList(int index) {
    table[index] = null;
  }
  
  public V get(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = table[index];
    if (synonyms == null) {
      return null;
    }
    for (KeyValue<K, V> kv : synonyms) {
      if (kv.getKey().equals(key)) {
        return kv.getValue();
      }
    }
    return null;
  }
  
  public boolean isEmpty() {
    return count == 0;
  }
  
  public Iterator<K> keys() {
    LinkedList<K> keys = new LinkedList<K>();
    for (LinkedList<KeyValue<K, V>> synonyms : table) {
      if (synonyms != null) {
        for (KeyValue<K, V> kv : synonyms) {
          keys.add(kv.getKey());
        }
      }
    }
    return keys.iterator();
  }
  
  public void put(K key, V value) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = table[index];
    if (synonyms == null) {
      createSynonymsList(index, new KeyValue<K, V>(key, value));
    } else {
      Position<KeyValue<K, V>> position = seekKeyInSynonyms(synonyms, key);
      if (position == null) {
        synonyms.add(new KeyValue<K, V>(key, value));
        count++;
      } else {
        position.getElement().setValue(value);
      }
    }
  }
  
  protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
    Position<KeyValue<K, V>> position = synonyms.first();
    while (position != null) {
      if (position.getElement().getKey().equals(key)) {
        return position;
      }
      position = synonyms.next(position);
    }
    return null;
  }
  
  public int size() {
    return count;
  }
  
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("{");
    Iterator<K> keys = keys();
    while (keys.hasNext()) {
      K key = keys.next();
      V value = get(key);
      s.append(key + ":" + value);
      if (keys.hasNext()) {
        s.append(", ");
      }
    }
    s.append("}");
    return s.toString();
  }
  
  public Iterator<V> values() {
    LinkedList<V> values = new LinkedList<V>();
    for (LinkedList<KeyValue<K, V>> synonyms : table) {
      if (synonyms != null) {
        for (KeyValue<K, V> kv : synonyms) {
          values.add(kv.getValue());
        }
      }
    }
    return values.iterator();
  }
}
