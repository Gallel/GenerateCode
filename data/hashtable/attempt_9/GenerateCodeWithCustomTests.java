
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    private int size; 
    private LinkedList<KeyValue<K,V>>[] table; 

    @SuppressWarnings("unchecked") 
    public HashTable() { 
        size = 128; 
        table = new LinkedList[size]; 
        for (int i = 0; i < size; i++) { 
            table[i] = new LinkedList<>(); 
        } 
    } 

    @SuppressWarnings("unchecked") 
    public HashTable(int size) { 
        this.size = size;  
        table = new LinkedList[size]; 
        for (int i = 0; i < size; i++) { 
            table[i] = new LinkedList<>(); 
        } 
    } 

    @Override
    public void put(K key, V value) { 
        int index = calculateIndex(key); 
        LinkedList<KeyValue<K,V>> synonyms = table[index]; 
        if (containsKey(key)) {
            return; 
        } else { 
            synonyms.addFirst(new KeyValue<>(key, value)); 
        } 
    }  

    @Override
    public V get(K key) { 
        int index = calculateIndex(key); 
        LinkedList<KeyValue<K,V>> synonyms = table[index]; 
        if (!containsKey(key)) {
            return null; 
        } else { 
            Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key); 
            KeyValue<K,V> kv = position.element(); 
            return kv.value(); 
        } 
    } 

    @Override
    public V delete(K key) { 

        int index = calculateIndex(key); 
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (!containsKey(key)) { 
            return null; 
        } else { 
            Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key); 
            KeyValue<K,V> kv = position.element(); 
            synonyms.remove(position);
            return kv.value(); 
        } 
    } 

    @Override
    public Iterator<K> keys() { 
        LinkedList<K> list = new LinkedList<K>(); 
        for (int i = 0; i < size; i++) { 
            LinkedList<KeyValue<K,V>> synonyms = table[i]; 
            for (KeyValue<K,V> kv : synonyms) { 
                list.add(kv.key()); 
            } 
        } 
        return list.iterator(); 
    } 

    @Override
    public Iterator<V> values() { 
        LinkedList<V> list = new LinkedList<V>(); 
        for (int i = 0; i < size; i++) { 
            LinkedList<KeyValue<K,V>> synonyms = table[i]; 
            for (KeyValue<K,V> kv : synonyms) { 
                list.add(kv.value()); 
            } 
        } 
        return list.iterator(); 
    }

    @Override
    public boolean isEmpty() { 
        return size == 0; 
    } 

    @Override
    public int size() { 
        int count = 0; 
        for (int i = 0; i < size; i++) { 
            LinkedList<KeyValue<K,V>> synonyms = table[i]; 
            count += synonyms.size(); 
        } 
        return count; 
    } 

    private Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {
        Iterator<Position<KeyValue<K,V>>> i = synonyms.iterator();
        while (i.hasNext()) { 
            Position<KeyValue<K,V>> p = i.next(); 
            if (p.element().key().equals(key)) { 
                return p; 
            } 
        } 
        return null; 
    } 

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) { 
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if (synonyms == null) { 
            synonyms = new LinkedList<KeyValue<K,V>>(); 
            table[index] = synonyms; 
        }
        return synonyms; 
    } 

    protected void deleteSynonymsList(int index) { 
        table[index] = null;
    } 

    protected int calculateIndex(K key) { 
        return Math.abs(key.hashCode()) % size; 
    }

    @Override
    public boolean containsKey(K key) { 
        int index = calculateIndex(key); 
        LinkedList<KeyValue<K,V>> synonyms = table[index]; 
        if (synonyms.size() == 0) { 
            return false; 
        } else { 
            Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key); 
            if (position == null) { 
                return false; 
            } else { 
                return true; 
            } 
        } 
    }

    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer();
        Iterator<K> keysIter = keys();
        while (keysIter.hasNext()) { 
            K key = keysIter.next(); 
            V value = get(key); 
            sb.append("("); 
            sb.append(key.toString()); 
            sb.append(", "); 
            sb.append(value.toString()); 
            sb.append(")"); 
            if (keysIter.hasNext()) { 
                sb.append(", "); 
            } 
        } 
        return sb.toString(); 
    }
}
