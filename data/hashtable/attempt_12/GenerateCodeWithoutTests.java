
public class HashTable<K,V> extends Object implements Dictionary<K,V> {

  private int tableSize;
  private int numItems;
  private LinkedList<KeyValue<K,V>>[] table;

  public HashTable() {
    this.tableSize = 10;
    this.numItems = 0;
    this.table = new LinkedList[tableSize];
    for(int i = 0; i < tableSize; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  public HashTable(int size) {
    this.tableSize = size;
    this.numItems = 0;
    this.table = new LinkedList[tableSize];
    for(int i = 0; i < tableSize; i++) {
      table[i] = new LinkedList<KeyValue<K,V>>();
    }
  }

  @Override
  public boolean containsKey(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
    return (position != null);
  }

  @Override
  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms,key);
    if(position == null) return null;
    KeyValue<K,V> kv = synonyms.remove(position);
    numItems--;
    return kv.getValue();
  }

  @Override
  public V get(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms,key);
    return (position == null) ? null : position.getElement().getValue();
  }

  @Override
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

  @Override
  public void put(K key, V value) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K,V>> synonyms = table[index];
    Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms,key);
    if(position != null) {
      position.getElement().setValue(value);
    } else {
      KeyValue<K,V> kv = new KeyValue<K,V>(key,value);
      synonyms.addLast(kv);
      numItems++;
    }
    if(numItems > tableSize) rehash();
  }

  @Override
  public int size() {
    return numItems;
  }

  @Override
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

  private int calculateIndex(K key) {
    int index = key.hashCode() % tableSize;
    return (index >= 0) ? index : index + tableSize;
  }

  private void rehash() {
    HashTable<K,V> newTable = new HashTable<K,V>(tableSize*2);
    for(int i = 0; i < tableSize; i++) {
      LinkedList<KeyValue<K,V>> synonyms = table[i];
      for(KeyValue<K,V> kv : synonyms) {
        newTable.put(kv.getKey(),kv.getValue());
      }
    }
    this.table = newTable.table;
    this.tableSize = newTable.tableSize;
  }

  private Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
    for(Position<KeyValue<K,V>> p : synonyms.positions()) {
      if(key.equals(p.getElement().getKey())) {
        return p;
      }
    }
    return null;
  }

}
