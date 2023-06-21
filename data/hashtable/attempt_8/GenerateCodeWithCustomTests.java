
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    
    private LinkedList<KeyValue<K,V>>[] table;
    private int size;

    public HashTable() {
        this(997);
    }

    public HashTable(int size) {
        this.table = new LinkedList[size];
        this.size = 0;
    }

    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv){
        LinkedList<KeyValue<K,V>> syn = new LinkedList<KeyValue<K,V>>();
        syn.add(kv);
        table[index] = syn;
        return syn;
    }

    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        return seekKeyInSynonyms(table[index], key) != null;
    }

    protected void deleteSynonymsList(int index) {
        table[index] = null;
    }

    public V delete(K key) {
        V value = null;
        int index = calculateIndex(key);
        KeyValue<K,V> kv = seekKeyInSynonyms(table[index], key);
        if (kv != null) {
            value = kv.getValue();
            table[index].remove(kv);
            size--;
        }
        return value;
    }

    public V get(K key) {
        int index = calculateIndex(key);
        KeyValue<K,V> kv = seekKeyInSynonyms(table[index], key);
        return (kv != null) ? kv.getValue() : null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<K> keys() {
        return new KeyIterator();
    }

    public void put(K key, V value) {
        int index = calculateIndex(key);
        KeyValue<K,V> kv = seekKeyInSynonyms(table[index], key);
        if (kv == null) {
            createSynonymsList(index, new KeyValue<K,V>(key, value));
            size++;
        }
        else
            kv.setValue(value);
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        Position<KeyValue<K,V>> toReturn = null;
        if (synonyms != null) {
            boolean found = false;
            Iterator<KeyValue<K,V>> it = synonyms.iterator();
            while(it.hasNext() && !found) {
                KeyValue<K,V> kv = it.next();
                if (key.equals(kv.getKey())) {
                    toReturn = new SynonymIterator<KeyValue<K,V>>(synonyms, kv);
                    found = true;
                }
            }
        }
        return toReturn;
    }

    public int size() {
        return size;
    }

    public String toString() {
        Iterator<K> it = keys();
        StringBuilder sb = new StringBuilder("[");
        while(it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<V> values() {
        return new ValueIterator();
    }


    private class KeyIterator implements Iterator<K> {
        private Iterator<K> keysIterator;

        public KeyIterator() {
            LinkedList<K> keysList = new LinkedList<K>();
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    Iterator<KeyValue<K,V>> it = table[i].iterator();
                    while(it.hasNext())
                        keysList.add(it.next().getKey());
                }
            }
            this.keysIterator = keysList.iterator();
        }

        public boolean hasNext() {
            return keysIterator.hasNext();
        }

        public K next() {
            return keysIterator.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    private class SynonymIterator<K> implements Position<K> {
        private LinkedList<K> list;
        private K obj;

        public SynonymIterator(LinkedList<K> list, K obj) {
            this.list = list;
            this.obj = obj;
        }

        public K getElement() {
            return obj;
        }

        public LinkedList<K> getList() {
            return list;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void setList(LinkedList<K> l) {
            this.list = l;
        }
    }


    private class ValueIterator implements Iterator<V> {
        private Iterator<K> keysIterator;

        public ValueIterator() {
            LinkedList<V> valuesList = new LinkedList<V>();
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    Iterator<KeyValue<K,V>> it = table[i].iterator();
                    while(it.hasNext())
                        valuesList.add(it.next().getValue());
                }
            }
            this.keysIterator = (Iterator<K>) valuesList.iterator();
        }

        public boolean hasNext() {
            return keysIterator.hasNext();
        }

        public V next() {
            K key = keysIterator.next();
            return get(key);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class KeyValue<K,V> implements Position<K> {
        private K key;
        private V value;

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

        public K getElement() {
            return key;
        }
    }
}
