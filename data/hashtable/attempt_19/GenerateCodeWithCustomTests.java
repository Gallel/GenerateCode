
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {

    private int size;
    private float loadFactor;
    private LinkedList<KeyValue<K, V>>[] table;

    public HashTable() {
        this(11);
    }

    public HashTable(int size) {
        this.size = 0;
        this.loadFactor = 0.75f;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<KeyValue<K, V>>();
        }
    }

    protected int calculateIndex(K key) {
        int hash = key == null ? 0 : key.hashCode();
        hash = hash ^ (hash >>> 20) ^ (hash >>> 12);
        return Math.abs(hash ^ (hash >>> 7) ^ (hash >>> 4)) % table.length;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = new LinkedList<KeyValue<K, V>>();
            table[index] = synonyms;
        }
        return synonyms;
    }

    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms != null) {
            for (KeyValue<K, V> kv : synonyms) {
                if (key == null ? kv.getKey() == null : key.equals(kv.getKey())) {
                    synonyms.remove(kv);
                    size--;
                    return kv.getValue();
                }
            }
        }
        return null;
    }

    protected void deleteSynonymsList(int index) {
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms != null) {
            synonyms.clear();
            size--;
        }
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms != null) {
            for (KeyValue<K, V> kv : synonyms) {
                if (key == null ? kv.getKey() == null : key.equals(kv.getKey())) {
                    return kv.getValue();
                }
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
        return new KeyIterator();
    }

    @Override
    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = createSynonymsList(index, new KeyValue<K, V>(key, value));
        for (KeyValue<K, V> kv : synonyms) {
            if (key == null ? kv.getKey() == null : key.equals(kv.getKey())) {
                kv.setValue(value);
                return;
            }
        }
        synonyms.add(new KeyValue<K, V>(key, value));
        size++;
        if (size > loadFactor * table.length) {
            rehash();
        }
    }

    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
        Position<KeyValue<K, V>> result = null;
        if (synonyms != null) {
            for (Position<KeyValue<K, V>> p = synonyms.first(); p != null; p = synonyms.next(p)) {
                if (key == null ? p.getElement().getKey() == null : key.equals(p.getElement().getKey())) {
                    result = p;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        boolean first = true;
        for (K key : keys()) {
            if (first) {
                first = false;
            } else {
                s.append(", ");
            }
            s.append(key.toString());
            s.append("->");
            s.append(get(key).toString());
        }
        s.append("]");
        return s.toString();
    }

    @Override
    public Iterator<V> values() {
        return new ValueIterator();
    }

    void rehash() {
        LinkedList<KeyValue<K, V>>[] oldTable = table;
        table = new LinkedList[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<KeyValue<K, V>>();
        }
        size = 0;
        for (LinkedList<KeyValue<K, V>> synonyms : oldTable) {
            if (synonyms != null) {
                for (KeyValue<K, V> kv : synonyms) {
                    put(kv.getKey(), kv.getValue());
                }
            }
        }
    }

    private class KeyIterator implements Iterator<K> {

        private int i;
        private Position<K> p;

        KeyIterator() {
            i = -1;
            p = null;
            findNext();
        }

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public K next() {
            K result = p.getElement();
            findNext();
            return result;
        }

        private void findNext() {
            if (p != null) {
                p = null;
            }
            while (p == null && i < table.length - 1) {
                i++;
                LinkedList<KeyValue<K, V>> synonyms = table[i];
                if (synonyms != null && !synonyms.isEmpty()) {
                    p = (Position<K>) synonyms.first();
                }
            }
        }
    }

    private class ValueIterator implements Iterator<V> {

        private Iterator<K> i;

        ValueIterator() {
            i = keys();
        }

        @Override
        public boolean hasNext() {
            return i.hasNext();
        }

        @Override
        public V next() {
            return get(i.next());
        }
    }
}
