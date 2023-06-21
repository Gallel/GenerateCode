
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  // Attributes
  private static final int DEFAULT_TABLE_SIZE = 10;
  private LinkedList<KeyValue<K,V>>[] table;
  private int size;

  // Constructors
  public HashTable() {
    table = new LinkedList[DEFAULT_TABLE_SIZE];
    size = 0;
  }

  public HashTable(int size) {
    table = new LinkedList[size];
    this.size = 0;
  }

  // Private methods
  protected int calculateIndex(K key) {
    int index = key.hashCode() % table.length;
    return index < 0 ? -index : index;
  }

  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
    LinkedList<KeyValue<K,V>> synonyms = new LinkedList<>();
    synonyms.add(kv);
    table[index] = synonyms;
    size++;
    return synonyms;
  }

  protected void deleteSynonymsList(int index) {
    table[index] = null;
    size--;
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    for (Position<KeyValue<K,V>> pos : synonyms.positions()) {
      if (pos.getElement().getKey().equals(key)) {
        return pos;
      }
    }
    return null;
  }

  // Public methods
  public boolean containsKey(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if (synonyms != null) {
      Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
      return pos != null;
    }
    return false;
  }

  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if (synonyms != null) {
      Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
      if (pos != null) {
        V value = pos.getElement().getValue();
        synonyms.remove(pos);
        if (synonyms.isEmpty()) {
          deleteSynonymsList(index);
        }
        return value;
      }
    }
    return null;
  }

  public V get(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if (synonyms != null) {
      Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
      if (pos != null) {
        return pos.getElement().getValue();
      }
    }
    return null;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Iterator<K> keys() {
    List<K> keys = new ArrayList<>();
    for (LinkedList<KeyValue<K,V>> synonyms : table) {
      if (synonyms != null) {
        for (KeyValue<K,V> kv : synonyms) {
          keys.add(kv.getKey());
        }
      }
    }
    return keys.iterator();
  }

  public void put(K key, V value) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    if (synonyms == null) {
      createSynonymsList(index, new KeyValue<>(key, value));
    } else {
      Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
      if (pos != null) {
        pos.getElement().setValue(value);
      } else {
        synonyms.add(new KeyValue<>(key, value));
        size++;
      }
    }
  }

  public int size() {
    return size;
  }

  public String toString() {
    String str = "[";
    for (LinkedList<KeyValue<K,V>> synonyms : table) {
      if (synonyms != null) {
        for (KeyValue<K,V> kv : synonyms) {
          str += kv.toString() + ", ";
        }
      }
    }
    if (size > 0) {
      str = str.substring(0, str.length()-2);
    }
    str += "]";
    return str;
  }

  public Iterator<V> values() {
    List<V> values = new ArrayList<>();
    for (LinkedList<KeyValue<K,V>> synonyms : table) {
      if (synonyms != null) {
        for (KeyValue<K,V> kv : synonyms) {
          values.add(kv.getValue());
        }
      }
    }
    return values.iterator();
  }
}
