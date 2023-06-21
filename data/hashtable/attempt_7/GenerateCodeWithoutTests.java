
public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    private static final int DEFAULT_TABLE_SIZE = 64;
    private int tableSize;
    private LinkedList<KeyValue<K,V>>[] table;

    public HashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public HashTable(int size) {
        tableSize = size;
        table = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < tableSize; i++) {
            count += table[i].size();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    protected int calculateIndex(K key) {
        int hashCode = key.hashCode();
        int index = (hashCode & 0x7fffffff) % tableSize;
        return index;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = new LinkedList<>();
            table[index] = synonyms;
        }
        return synonyms;
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        int n = synonyms.size();
        Position<KeyValue<K,V>> position = synonyms.first();
        for (int i = 0; i < n; i++) {
            if (position.element().getKey().equals(key)) {
                return position;
            }
            position = synonyms.next(position);
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            return false;
        }
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        return (position != null);
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            return null;
        }
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null) {
            return null;
        }
        return position.element().getValue();
    }

    @Override
    public V put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = createSynonymsList(index, new KeyValue<>(key, value));
        }
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null) {
            synonyms.addLast(new KeyValue<>(key, value));
            return null;
        } else {
            V oldValue = position.element().getValue();
            position.element().setValue(value);
            return oldValue;
        }
    }

    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) {
            return null;
        }
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if (position == null) {
            return null;
        }
        V value = position.element().getValue();
        synonyms.remove(position);
        if (synonyms.isEmpty()) {
            table[index] = null;
        }
        return value;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    @Override
    public Iterator<K> keys() {
        List<K> list = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            if (synonyms != null) {
                int n = synonyms.size();
                Position<KeyValue<K,V>> position = synonyms.first();
                for (int j = 0; j < n; j++) {
                    list.add(position.element().getKey());
                    position = synonyms.next(position);
                }
            }
        }
        return list.iterator();
    }

    @Override
    public Iterator<V> values() {
        List<V> list = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            if (synonyms != null) {
                int n = synonyms.size();
                Position<KeyValue<K,V>> position = synonyms.first();
                for (int j  = 0; j < n; j++) {
                    list.add(position.element().getValue());
                    position = synonyms.next(position);
                }
            }
        }
        return list.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<K> it = keys();
        while (it.hasNext()) {
            K key = it.next();
            sb.append(key);
            sb.append(":");
            sb.append(get(key));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
