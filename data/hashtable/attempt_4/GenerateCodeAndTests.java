
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    protected static final int DEFAULT_TABLE_SIZE = 11;
    protected int size;
    protected LinkedList<KeyValue<K, V>>[] table;

    public HashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<KeyValue<K, V>>();
        }
    }

    protected int calculateIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % size;
        if (index < 0) {
            index += size;
        }
        return index;
    }

    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms, key) != null;
    }

    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        KeyValue<K, V> match = seekKeyInSynonyms(synonyms, key);

        if (match != null) {
            synonyms.remove(match);
            return match.getValue();
        }
        return null;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = new LinkedList<KeyValue<K,V>>();
    }

    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        KeyValue<K, V> match = seekKeyInSynonyms(synonyms, key);
        if (match != null) {
            return match.getValue();
        }
        return null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<K> keys() {
        LinkedList<K> keys = new LinkedList<K>();
        for (int i = 0; i < size; i++) {
            for (KeyValue<K,V> kv : table[i]) {
                keys.add(kv.getKey());
            }
        }
        return keys.iterator();
    }

    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        KeyValue<K, V> match = seekKeyInSynonyms(synonyms, key);
        if (match == null) {
            synonyms.add(new KeyValue<K,V>(key, value));
        } else {
            match.setValue(value);
        }
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms,
                                                               K key) {
        if (synonyms == null) {
            return null;
        }
        for (KeyValue<K,V> kv : synonyms) {
            if (kv.getKey().equals(key)) {
                return kv;
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
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < size; i++) {
            for (KeyValue<K,V> kv : table[i]) {
                sb.append(kv);
                sb.append(", ");
            }
        }
        if (size() > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    public Iterator<V> values() {
      LinkedList<V> values = new LinkedList<>();
      for (int i = 0; i < size; i++) {
          for (KeyValue<K,V> kv : table[i]) {
              values.add(kv.getValue());
          }
      }
      return values.iterator();
    }

    class KeyValue<K,V> {
        protected K key;
        protected V value;

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

        public String toString() {
            return "{" + key + ": " + value + "}";
        }
    }
}
