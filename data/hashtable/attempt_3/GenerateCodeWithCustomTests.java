
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {

    private LinkedList<KeyValue<K, V>>[] table;
    private int currentSize;

    public HashTable() {
        table = new LinkedList[997];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        currentSize = 0;
    }

    public HashTable(int size) {
        table = new LinkedList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        currentSize = 0;
    }

    protected int calculateIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % table.length;
        if (index < 0) {
            index += table.length;
        }
        return index;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        Iterator<KeyValue<K, V>> it = synonyms.iterator();
        while (it.hasNext()) {
            KeyValue<K, V> kv = it.next();
            if (kv.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            synonyms = new LinkedList<>();
            table[index] = synonyms;
        }
        return synonyms;
    }

    @Override
    public V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            return null;
        }
        Iterator<KeyValue<K, V>> it = synonyms.iterator();
        while (it.hasNext()) {
            KeyValue<K, V> kv = it.next();
            if (kv.getKey().equals(key)) {
                it.remove();
                currentSize--;
                return kv.getValue();
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
        LinkedList<KeyValue<K, V>> synonyms = table[index];
        if (synonyms == null) {
            return null;
        }
        Iterator<KeyValue<K, V>> it = synonyms.iterator();
        while (it.hasNext()) {
            KeyValue<K, V> kv = it.next();
            if (kv.getKey().equals(key)) {
                return kv.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> synonyms : table) {
            if (synonyms != null) {
                for (KeyValue<K, V> kv : synonyms) {
                    list.add(kv.getKey());
                }
            }
        }
        return list.iterator();
    }

    @Override
    public void put(K key, V value) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K, V>> synonyms = createSynonymsList(index, new KeyValue<K, V>(key, value));
        if (containsKey(key)) {
            Position<KeyValue<K, V>> position = seekKeyInSynonyms(synonyms, key);
            position.getContent().setValue(value);
        } else {
            synonyms.add(new KeyValue<K, V>(key, value));
            currentSize++;
        }
    }

    protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
        Iterator<KeyValue<K, V>> it = synonyms.iterator();
        while (it.hasNext()) {
            KeyValue<K, V> kv = it.next();
            if (kv.getKey().equals(key)) {
                return new SynonymPosition(it);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator<K> it = keys();
        while (it.hasNext()) {
            K key = it.next();
            sb.append(key).append("=").append(get(key));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> synonyms : table) {
            if (synonyms != null) {
                for (KeyValue<K, V> kv : synonyms) {
                    list.add(kv.getValue());
                }
            }
        }
        return list.iterator();
    }

    protected static class SynonymPosition<K, V> implements Position<KeyValue<K, V>> {

        private Iterator<KeyValue<K, V>> it;

        public SynonymPosition(Iterator<KeyValue<K, V>> it) {
            this.it = it;
        }

        @Override
        public KeyValue<K, V> getContent() {
            return it.next();
        }

        @Override
        public void setContent(KeyValue<K, V> content) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
