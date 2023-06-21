
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    // Declarations
    private int size;
    private LinkedList<KeyValue<K,V>>[] table;

    // Constructor without parameters (default table size)
    public HashTable() {
        this(1000);
    }

    // Constructor with a parameter
    public HashTable(int size) {
        this.size = size;
        table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[size];
        for(int i=0; i<size; i++)
            table[i] = new LinkedList<KeyValue<K,V>>();
    }

    // Method overwriting Object.toString()
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int i=0; i<size; i++)
            if(!table[i].isEmpty())
                s.append("["+i+"]"+table[i]+"\n");
        return s.toString();
    }

    // Method to check if the container is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Method to retrieves the number of items in the container
    public int size() {
        int count = 0;
        for(int i=0; i<size; i++)
            count += table[i].size();
        return count;
    }

    // Method to check if an item with a certain key exists
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        return seekKeyInSynonyms(table[index], key) != null;
    }

    // Method to retrieve the value associated with a key
    public V get(K key) {
        int index = calculateIndex(key);
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(table[index], key);
        return (position == null ? null : position.element().getValue());
    }

    // Method to retrieve the items in the container
    public Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<K>();
        for(int i=0; i<size; i++)
            for(KeyValue<K,V> kv : table[i])
                list.add(kv.getKey());
        return list.iterator();
    }

    // Method to retrieve the items values in the container
    public Iterator<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        for(int i=0; i<size; i++)
            for(KeyValue<K,V> kv : table[i])
                list.add(kv.getValue());
        return list.iterator();
    }

    // Method to delete an item with matching key, if possible
    public V delete(K key) {
        int index = calculateIndex(key);
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(table[index], key);
        if(position == null) return null;
        V result = position.element().getValue();
        table[index].remove(position);
        return result;
    }

    // Method to add an item with an associated key
    public void put(K key, V value) {
        int index = calculateIndex(key);
        table[index].addLast(new KeyValue<K,V>(key,value));
    }

    // Method to calculate the index for a given key
    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    // Method to delete all synonyms for a given index
    protected void deleteSynonymsList(int index) {
        table[index].clear();
    }

    // Method to seek a key within a list of synonyms
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(
        LinkedList<KeyValue<K,V>> synonyms, K key) {
        for(Position<KeyValue<K,V>> position : synonyms.positions())
            if(position.element().getKey().equals(key))
                return position;
        return null;
    }

    // Method to create a list of synonyms for a given index
    protected LinkedList<KeyValue<K,V>> createSynonymsList(
        int index, KeyValue<K,V> kv) {
        LinkedList<KeyValue<K,V>> list = new LinkedList<KeyValue<K,V>>();
        list.addFirst(kv);
        table[index] = list;
        return list;
    }

}
