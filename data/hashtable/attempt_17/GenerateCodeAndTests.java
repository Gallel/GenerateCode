
import java.util.*;
public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  private int tableSize;
  private LinkedList<KeyValue<K,V>>[] table;

  public HashTable() {
    tableSize = 100;
    table = new LinkedList[tableSize];
    for (int i = 0; i < tableSize; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  public HashTable(int size) {
    tableSize = size;
    table = new LinkedList[tableSize];
    for (int i = 0; i < tableSize; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  protected int calculateIndex(K key) {
    return Math.abs(key.hashCode() % tableSize);
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
    return synonyms;
  }

  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null) {
      return null;
    }
    V value = pos.element().getValue();
    synonyms.remove(pos);
    return value;
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
    return pos.element().getValue();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public Iterator<K> keys() {
    List<K> list = new LinkedList<K>();
    for (int i = 0; i < tableSize; i++) {
      LinkedList<KeyValue<K,V>> synonyms = table[i];
      for (KeyValue<K,V> kv : synonyms) {
        list.add(kv.getKey());
      }
    }
    return list.iterator();
  }

  public void put(K key, V value) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    KeyValue<K,V> kv = new KeyValue<K,V>(key, value);
    if (synonyms == null) {
      createSynonymsList(index, kv);
    } else {
      Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
      if (pos == null) {
        synonyms.add(kv);
      } else {
        pos.element().setValue(value);
      }
    }
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    if (synonyms == null) {
      return null;
    }
    Iterator<Position<KeyValue<K,V>>> it = synonyms.positions().iterator();
    while (it.hasNext()) {
      Position<KeyValue<K,V>> pos = it.next();
      if (key.equals(pos.element().getKey())) {
        return pos;
      }
    }
    return null;
  }

  public int size() {
    int count = 0;
    for (int i = 0; i < tableSize; i++) {
      LinkedList<KeyValue<K,V>> synonyms = table[i];
      count += synonyms.size();
    }
    return count;
  }

  public String toString() {
    String s = "";
    for (int i = 0; i < tableSize; i++) {
      LinkedList<KeyValue<K,V>> synonyms = table[i];
      if (synonyms == null) {
        s += "[]";
      } else {
        s += synonyms.toString();
      }
      s += "\n";
    }
    return s;
  }

  public Iterator<V> values() {
    List<V> list = new LinkedList<V>();
    for (int i = 0; i < tableSize; i++) {
      LinkedList<KeyValue<K,V>> synonyms = table[i];
      for (KeyValue<K,V> kv : synonyms) {
        list.add(kv.getValue());
      }
    }
    return list.iterator();
  }
}
