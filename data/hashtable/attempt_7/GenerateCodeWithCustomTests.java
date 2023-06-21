
public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    // Attributes
    private int size = 101;  // default table size
    private LinkedList<KeyValue<K,V>>[] table;

    // Constructors
    public HashTable() {
        table = (LinkedList<KeyValue<K, V>>[]) new LinkedList[size];
    }
    public HashTable(int size) {
        this.size = size;
        table = (LinkedList<KeyValue<K, V>>[]) new LinkedList[size];
    }

    // Overrides
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<K> it = keys();
        while (it.hasNext()) {
            K key = it.next();
            stringBuilder.append(String.format("%s=%s", key.toString(), get(key).toString()));
            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        return String.format("{%s}", stringBuilder.toString());
    }

    // Methods
    private int calculateIndex(K key) {
        return key.hashCode() % size;
    }

    private LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        LinkedList<KeyValue<K,V>> synonymsList = table[index];
        synonymsList.addLast(kv);
        return synonymsList;
    }

    private void deleteSynonymsList(int index) {
        table[index] = null;
    }

    private Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        if (synonyms == null) {
            return null;
        }
        Iterator<Position<KeyValue<K,V>>> it = synonyms.positions();
        while (it.hasNext()) {
            Position<KeyValue<K,V>> position = it.next();
            if (position.getElement().getKey().equals(key)) {
                return position;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> found = seekKeyInSynonyms(synonyms, key);
        return found != null;
    }

    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> found = seekKeyInSynonyms(synonyms, key);
        if (found != null) {
            KeyValue<K,V> element = synonyms.remove(found);
            if (synonyms.isEmpty()) {
                deleteSynonymsList(index);
            }
            return element.getValue();
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> found = seekKeyInSynonyms(synonyms, key);
        if (found != null) {
            return found.getElement().getValue();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            LinkedList<KeyValue<K, V>> synonyms = table[i];
            if (synonyms != null) {
                for (Position<KeyValue<K, V>> position : synonyms.positions()) {
                    list.addLast(position.getElement().getKey());
                }
            }
        }
        return list.iterator();
    }

    @Override
    public void put(K key, V value) {
        KeyValue<K, V> kv = new KeyValue<>(key, value);
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = createSynonymsList(index, kv);
        } else {
            Position<KeyValue<K,V>> found = seekKeyInSynonyms(synonyms, key);
            if (found == null) {
                synonyms = createSynonymsList(index, kv);
            } else {
                found.getElement().setValue(value);
            }
        }
    }

    @Override
    public int size() {
        int size = 0;
        for (LinkedList<KeyValue<K, V>> list : table) {
            if (list != null) {
                size += list.size();
            }
        }
        return size;
    }

    @Override
    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> synonyms : table) {
            if (synonyms != null) {
                for (Position<KeyValue<K, V>> position : synonyms.positions()) {
                    list.addLast(position.getElement().getValue());
                }
            }
        }
        return list.iterator();
    }
}
