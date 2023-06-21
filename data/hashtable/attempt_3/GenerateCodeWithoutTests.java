
import java.util.LinkedList;
import java.util.Iterator;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
  
  // Attributes
  private int size;
  private LinkedList<KeyValue<K,V>>[] table;
  
  // Constructors
  public HashTable() {
    this.size = 100;
    this.table = new LinkedList[size];
    for (int i=0; i<size; i++)
      table[i] = new LinkedList<KeyValue<K,V>>();
  }

  public HashTable(int size) {
    this.size = size;
    this.table = new LinkedList[size];
    for (int i=0; i<size; i++)
      table[i] = new LinkedList<KeyValue<K,V>>();
  }
  
  // Methods
  protected int calculateIndex(K key) {
    return Math.abs(key.hashCode()) % size;
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
    return synonyms;
  }
  
  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null)
      return null;
    else {
      KeyValue<K,V> kv = synonyms.remove(pos);
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
    if (pos == null)
      return null;
    else {
      KeyValue<K,V> kv = synonyms.get(pos);
      return kv.getValue();
    }
  }

  public boolean isEmpty() {
    for (int i=0; i<size; i++) {
      if (table[i].size() > 0)
        return false;
    }
    return true;
  }

  public Iterator<K> keys() {
    LinkedList<K> list = new LinkedList<K>();
    for (int i=0; i<size; i++) {
      for (KeyValue<K,V> kv : table[i]) {
        K key = kv.getKey();
        list.add(key);
      }
    }
    return list.iterator();
  }

  public void put(K key, V value) {
    KeyValue<K,V> kv = new KeyValue<K,V>(key, value);
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = createSynonymsList(index, kv);
    synonyms.addLast(kv);
  }

  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    Iterator<Position<KeyValue<K,V>>> it = synonyms.positions().iterator();
    while (it.hasNext()) {
      Position<KeyValue<K,V>> pos = it.next();
      KeyValue<K,V> kv = pos.getElement();
      if (key.equals(kv.getKey()))
        return pos;
    }
    return null;
  }

  public int size() {
    int count = 0;
    for (int i=0; i<size; i++) {
      count += table[i].size();
    }
    return count;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i=0; i<size; i++) {
      for (KeyValue<K,V> kv : table[i]) {
        sb.append(kv.getKey());
        sb.append("=");
        sb.append(kv.getValue());
        sb.append(", ");
      }
    }
    if (!isEmpty()) {
      sb.delete(sb.length()-2, sb.length());
    }
    sb.append("}");
    return sb.toString();
  }

  public Iterator<V> values() {
    LinkedList<V> list = new LinkedList<V>();
    for (int i=0; i<size; i++) {
      for (KeyValue<K,V> kv : table[i]) {
        V value = kv.getValue();
        list.add(value);
      }
    }
    return list.iterator();
  }
}
