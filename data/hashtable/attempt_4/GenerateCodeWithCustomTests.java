
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {

    private int size;
    private LinkedList<KeyValue<K, V>>[] table;

    // Constructor without parameters (default table size).
    public HashTable() {
        this(17);
    }

    // Constructor with a parameter.
    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    // Method to check if the container is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    // Retrieves the number of items in the container.
    public int size() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            count += table[i].size();
        }
        return count;
    }

    @Override
    // Add an item with an associated key, if possible.
    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (containsKey(key)) {
            Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
            KeyValue<K, V> kv = pos.getElement();
            kv.setValue(value);
        } else {
            synonyms.addFirst(new KeyValue<>(key, value));
        }
    }

    protected int calculateIndex(K key) {
        if (key == null) {
            return 0;
        }
        int position = Math.abs(key.hashCode()) % size;
        return position;
    }

    @Override
    // Retrieves the value associated with a key.
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (containsKey(key)) {
            Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
            KeyValue<K, V> kv = pos.getElement();
            return kv.getValue();
        } else {
            return null;
        }
    }

    @Override
    // Checks for an item with a certain key.
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms, key) != null;
    }

    @Override
    // Delete the first matching key and associated item, if possible.
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (containsKey(key)) {
            Position<KeyValue<K, V>> pos = seekKeyInSynonyms(synonyms, key);
            KeyValue<K, V> kv = pos.getElement();
            synonyms.remove(pos);
            return kv.getValue();
        } else {
            return null;
        }
    }

    // Deletes all synonyms of a key.
    protected void deleteSynonymsList(int index) {
        table[index] = new LinkedList<>();
    }

    // Creates the list of synonyms of a certain cell.
    protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
        deleteSynonymsList(index);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        synonyms.addLast(kv);
        return synonyms;
    }

    // Finds the position of a key within a list of synonyms.
    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
        for (Position<KeyValue<K, V>> pos : synonyms.positions()) {
            if (key.equals(pos.getElement().getKey())) {
                return pos;
            }
        }
        return null;
    }
    
    @Override
    // Retrieves the items in the container.
    public Iterator<K> keys() {
        LinkedList<K> keys = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (KeyValue<K, V> kv : table[i]) {
                keys.addLast(kv.getKey());
            }
        }
        return keys.iterator();
    }

    @Override
    // Retrieves the items in the container.
    public Iterator<V> values() {
        LinkedList<V> values = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (KeyValue<K, V> kv : table[i]) {
                values.addLast(kv.getValue());
            }
        }
        return values.iterator();
    }

    @Override
    // Method overwriting Object.toString().
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            for (KeyValue<K, V> kv : table[i]) {
                s.append(kv);
                s.append(", ");
            }
        }
        return s.toString().substring(0, s.length() - 2) + "]";
    }
}
