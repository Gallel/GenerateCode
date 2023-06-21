
public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    private final int DEFAULT_TABLE_SIZE = 101;
    private LinkedList<KeyValue<K, V>>[] table;
    private int size;

    public HashTable() {
        table = new LinkedList[DEFAULT_TABLE_SIZE];
        size = 0;
    }

    public HashTable(int size) {
        table = new LinkedList[size];
        this.size = 0;
    }

    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
        LinkedList<KeyValue<K, V>> list = new LinkedList<>();
        list.add(kv);
        table[index] = list;
        size++;
        return list;
    }

    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        if (table[index] == null) {
            return null;
        }
        for (KeyValue<K, V> kv : table[index]) {
            if (kv.key().equals(key)) {
                table[index].remove(kv);
                size--;
                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                return kv.value();
            }
        }
        return null;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        if (table[index] == null) {
            return null;
        }
        for (KeyValue<K, V> kv : table[index]) {
            if (kv.key().equals(key)) {
                return kv.value();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<K> keys() {
        List<K> keys = new ArrayList<>();
        for (LinkedList<KeyValue<K, V>> list : table) {
            if (list != null) {
                for (KeyValue<K, V> kv : list) {
                    keys.add(kv.key());
                }
            }
        }
        return new SimpleIterator<>(keys);
    }

    @Override
    public void put(K key, V value) {
        KeyValue<K, V> kv = new KeyValue<>(key, value);
        int index = calculateIndex(key);
        if (table[index] == null) {
            createSynonymsList(index, kv);
        } else {
            Position<KeyValue<K, V>> pos = seekKeyInSynonyms(table[index], key);
            if (pos == null) {
                table[index].add(kv);
                size++;
            } else {
                pos.getElement().setValue(value);
            }
        }
    }

    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
        for (Position<KeyValue<K, V>> p : synonyms.positions()) {
            if (p.getElement().getKey().equals(key)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (LinkedList<KeyValue<K, V>> list : table) {
            if (list != null) {
                for (KeyValue<K, V> kv : list) {
                    sb.append(kv.toString()).append(", ");
                }
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<V> values() {
        List<V> values = new ArrayList<>();
        for (LinkedList<KeyValue<K, V>> list : table) {
            if (list != null) {
                for (KeyValue<K, V> kv : list) {
                    values.add(kv.value());
                }
            }
        }
        return new SimpleIterator<>(values);
    }
}
