
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {
  
  private int tableSize;
  private float loadFactor;
  private int count;
  private LinkedList<KeyValue<K, V>>[] hashTable;

  public HashTable() {
    this(1000);
  }

  public HashTable(int size) {
    this.tableSize = size;
    loadFactor = 0.75f;
    count = 0;
    hashTable = new LinkedList[size];
  }

  public int size() {
    return count;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  private int calculateIndex(K key) {
    return Math.abs(key.hashCode()) % tableSize;
  }
  
  private LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
    LinkedList<KeyValue<K, V>> synonyms = hashTable[index];
    if (synonyms == null) {
      synonyms = new LinkedList<KeyValue<K, V>>();
      hashTable[index] = synonyms;
    }
    synonyms.add(kv);
    count++;
    return synonyms;
  }
  
  protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
    if (synonyms == null) {
      return null;
    }
    for (KeyValue<K, V> kv : synonyms) {
      if (kv.getKey().equals(key)) {
        return new SynonymsPosition(kv, synonyms);
      }
    }
    return null;
  }
 
  protected void deleteSynonymsList(int index) {
    hashTable[index] = null;
  }
  
  public boolean containsKey(K key) {
    int index = calculateIndex(key);
    Position<KeyValue<K, V>> pos = seekKeyInSynonyms(hashTable[index], key);
    return pos != null;
  }
  
  public void put(K key, V value) {
    KeyValue<K, V> kv = new KeyValue<K, V>(key, value);
    int index = calculateIndex(key);
    createSynonymsList(index, kv);
  }
  
  public V get(K key) {
    int index = calculateIndex(key);
    Position<KeyValue<K, V>> pos = seekKeyInSynonyms(hashTable[index], key);
    if (pos == null) {
      return null;
    }
    return pos.getElement().getValue();
  }

  public V delete(K key) {
    int index = calculateIndex(key);
    LinkedList<KeyValue<K, V>> synonyms = hashTable[index];
    Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
    if (pos == null) {
      return null;
    }
    KeyValue<K, V> kv = synonyms.remove(pos.getPosition());
    count--;
    if (synonyms.isEmpty()) {
      hashTable[index] = null;
    }
    return kv.getValue();
  }
  
  public Iterator<K> keys() {
    return new HashTableKeyIterator<>();
  }
  
  public Iterator<V> values() {
    return new HashTableValueIterator<>();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    Iterator<K> keys = keys();
    while (keys.hasNext()) {
      K k = keys.next();
      V v = get(k);
      sb.append(k.toString() + "=" + v.toString());
      if (keys.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append("}");
    return sb.toString();
  }
  
  private class HashTableKeyIterator<T> implements Iterator<T> {
    private int current = -1;
    private int seen = 0;
    private LinkedList<K> keys = new LinkedList<K>();

    public HashTableKeyIterator() {
      for (int i=0; i<hashTable.length; i++) {
        LinkedList<KeyValue<K, V>> synonyms = hashTable[i];
        if (synonyms != null) {
          for (KeyValue<K, V> kv : synonyms) {
            keys.addLast(kv.getKey());
          }
        }
      }
    }

    public boolean hasNext() {
      return seen < count;
    }

    public T next() {
      seen++;
      return (T) keys.removeFirst();
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private class HashTableValueIterator<T> implements Iterator<T> {
    private Iterator<K> keys = keys();

    public boolean hasNext() {
      return keys.hasNext();
    }

    public T next() {
      return (T) get(keys.next());
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  protected interface Position<T> {
    int getPosition();
    T getElement();
  }

  protected class SynonymsPosition implements Position<KeyValue<K, V>> {
    private int position;
    private KeyValue<K, V> element;
    private LinkedList<KeyValue<K, V>> synonyms;

    public SynonymsPosition(KeyValue<K, V> element, LinkedList<KeyValue<K, V>> synonyms) {
      int i = -1;
      for (KeyValue<K, V> kv : synonyms) {
        i++;
        if (kv.equals(element)) {
          this.element = kv;
          position = i;
          this.synonyms = synonyms;
          break;
        }
      }
    }

    public int getPosition() {
      return position;
    }

    public KeyValue<K, V> getElement() {
      return element;
    }

    protected LinkedList<KeyValue<K, V>> getSynonyms() {
      return synonyms;
    }
  }

  protected class KeyValue<K, V> {
    K key;
    V value;

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

    public boolean equals(Object o) {
      if (o == null) {
        return false;
      }
      if (o.getClass() != getClass()) {
        return false;
      }
      KeyValue<?, ?> kv = (KeyValue<?, ?>) o;
      return key.equals(kv.getKey());
    }

    public String toString() {
      return key.toString() + "=" + value.toString();
    }
  }

}
