
public class HashTable<K,V> extends Object implements Dictionary<K,V> {
    
    public HashTable() {}
    public HashTable(int size) {}
    
    protected int calculateIndex(K key) {}
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv) {}
    protected void deleteSynonymsList(int index) {}
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key) {}
    
    public boolean containsKey(K key) {}
    public V delete(K key) {}
    public V get(K key) {}
    public boolean isEmpty() {}
    public Iterator<K> keys() {}
    public void put(K key, V value) {}
    public int size() {}
    public String toString() {}
    public Iterator<V> values() {}
}
