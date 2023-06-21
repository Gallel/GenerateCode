
public class HashTable<K,V> extends Object implements Dictionary<K,V> {
  private LinkedList<KeyValue<K,V>>[] table;
  private int size;
  
  public HashTable() {
    this(997);
  }
  
  @SuppressWarnings("unchecked")
  public HashTable(int size) {
    this.size = size;
    table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[size];
    for (int i = 0; i < size; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }
  
  protected int calculateIndex(K key) {
    return (key.hashCode() & 0x7fffffff) % size;
  }
  
  public boolean containsKey(K key) {
    int index = calculateIndex(key);
    return seekKeyInSynonyms(table[index], key) != null;
  }
  
  protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
    LinkedList<KeyValue<K,V>> synonyms = new LinkedList<KeyValue<K,V>>();
    synonyms.add(kv);
    table[index] = synonyms;
    return synonyms;
  }
  
  public V delete(K key) {
    int index = calculateIndex(key);
    Position<KeyValue<K,V>> position = seekKeyInSynonyms(table[index], key);
    if (position == null) {
      return null;
    } else {
      KeyValue<K,V> kv = position.element();
      deleteFromSynonymsList(table[index], position);
      return kv.getValue();
    }
  }
  
  protected void deleteFromSynonymsList(LinkedList<KeyValue<K,V>> synonyms, Position<KeyValue<K,V>> position) {
    synonyms.remove(position);
  }
  
  protected void deleteSynonymsList(int index) {
    table[index] = new LinkedList<KeyValue<K,V>>();
  }
  
  public V get(K key) {
    int index = calculateIndex(key);
    Position<KeyValue<K,V>> position = seekKeyInSynonyms(table[index], key);
    if (position == null) {
      return null;
    } else {
      KeyValue<K,V> kv = position.element();
      return kv.getValue();
    }
  }
  
  public boolean isEmpty() {
    return size() == 0;
  }
  
  public Iterator<K> keys() {
    List<K> list = new LinkedList<K>();
    for (int i = 0; i < size; i++) {
      for (KeyValue<K,V> kv : table[i]) {
        list.add(kv.getKey());
      }
    }
    return list.iterator();
  }
  
  public void put(K key, V value) {
    int index = calculateIndex(key);
    Position<KeyValue<K,V>> position = seekKeyInSynonyms(table[index], key);
    if (position == null) {
      createSynonymsList(index, new KeyValue<K,V>(key, value));
    } else {
      position.element().setValue(value);
    }
  }
  
  protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    for (Position<KeyValue<K,V>> position : synonyms.positions()) {
      if (position.element().getKey().equals(key)) {
        return position;
      }
    }
    return null;
  }
  
  public int size() {
    int count = 0;
    for (int i = 0; i < size; i++) {
      count += table[i].size();
    }
    return count;
  }
  
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("{");
    for (int i = 0; i < size; i++) {
      for (KeyValue<K,V> kv : table[i]) {
        s.append(kv.getKey() + ":" + kv.getValue() + ", ");
      }
    }
    if (size() > 0) {
      s.setLength(s.length() - 2);
    }
    s.append("}");
    return s.toString();
  }
  
  public Iterator<V> values() {
    List<V> list = new LinkedList<V>();
    for (int i = 0; i < size; i++) {
      for (KeyValue<K,V> kv : table[i]) {
        list.add(kv.getValue());
      }
    }
    return list.iterator();
  }
}
