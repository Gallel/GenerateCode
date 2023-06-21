
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  private LinkedList<KeyValue<K,V>>[] table;
  private int size;

  public HashTable() {
    this(997);
  }

  public HashTable(int size) {
    this.table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[size];
    this.size = 0;
  }

  protected int calculateIndex(K key) {
    int hash = key.hashCode();
    if (hash < 0) {
      hash = -hash;
    }
    return hash % table.length;
  }

  public boolean containsKey(K key) {
    LinkedList<KeyValue<K,V>> list = table[calculateIndex(key)];
    return seekKeyInSynonyms(list, key) != null;
  }

  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if (synonyms == null) {
      synonyms = new LinkedList<KeyValue<K,V>>();
      table[index] = synonyms;
    }
    synonyms.addFirst(kv);
    size++;
    return synonyms;
  }

  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null) {
      return null;
    } else {
      KeyValue<K,V> kv = synonyms.get(pos);
      synonyms.remove(pos);
      size--;
      if (synonyms.isEmpty()) {
        table[index] = null;
      }
      return kv.getValue();
    }
  }

  protected void deleteSynonymsList(int index) {
    table[index] = null;
  }

  public V get(K key) {
    LinkedList<KeyValue<K,V>> list = table[calculateIndex(key)];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(list, key);
    if (pos == null) {
      return null;
    } else {
      return list.get(pos).getValue();
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Iterator<K> keys() {
    LinkedList<K> keys = new LinkedList<K>();
    for (int i = 0; i < table.length; i++) {
      LinkedList<KeyValue<K,V>> list = table[i];
      if (list != null) {
        for (KeyValue<K,V> kv : list) {
          keys.add(kv.getKey());
        }
      }
    }
    return keys.iterator();
  }

  public void put(K key, V value) {
    if (containsKey(key)) {
      int index = calculateIndex(key);
      LinkedList<KeyValue<K,V>> synonyms = table[index];
      Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
      synonyms.get(pos).setValue(value);
    } else {
      createSynonymsList(calculateIndex(key), new KeyValue<K,V>(key, value));
    }
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    if (synonyms == null) {
      return null;
    }
    Iterator<Position<KeyValue<K,V>>> it = synonyms.positions();
    while (it.hasNext()) {
      Position<KeyValue<K,V>> pos = it.next();
      if (pos.getElement().getKey().equals(key)) {
        return pos;
      }
    }
    return null;
  }

  public int size() {
    return size;
  }

  public String toString() {
    StringBuffer s = new StringBuffer();
    s.append("[");
    for (int i = 0; i < table.length; i++) {
      LinkedList<KeyValue<K,V>> list = table[i];
      if (list != null) {
        for (KeyValue<K,V> kv : list) {
          s.append(kv + ", ");
        }
      }
    }
    if (size > 0) {
      s.delete(s.length()-2, s.length());
    }
    s.append("]");
    return s.toString();
  }

  public Iterator<V> values() {
    LinkedList<V> values = new LinkedList<V>();
    for (int i = 0; i < table.length; i++) {
      LinkedList<KeyValue<K,V>> list = table[i];
      if (list != null) {
        for (KeyValue<K,V> kv : list) {
          values.add(kv.getValue());
        }
      }
    }
    return values.iterator();
  }
}