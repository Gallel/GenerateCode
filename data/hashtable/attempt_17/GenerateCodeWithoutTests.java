
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V>{

    /* Attributes */
    protected LinkedList<KeyValue<K,V>>[] table;
    protected int size;

    /* Constructors */
    public HashTable(){
        this(997); //default table size
    }

    @SuppressWarnings("unchecked")
    public HashTable(int size){
        table = (LinkedList<KeyValue<K,V>>[]) new LinkedList[size];
        for(int i=0; i<size; i++) {
            table[i] = new LinkedList<KeyValue<K,V>>();
        }
        this.size = 0;
    }
    
    /* Methods */
    protected int calculateIndex(K key){
        return (key.hashCode() & 0x7FFFFFFF) % table.length;
    }
    
    public boolean containsKey(K key){
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        return seekKeyInSynonyms(synonyms, key) != null;
    }
    
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv){
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        if(synonyms == null) {
            synonyms = new LinkedList<KeyValue<K,V>>();
            table[index] = synonyms;
        }
        synonyms.add(kv);   
        return synonyms;
    }
    
    public V delete(K key){
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if(position == null) {
            return null;
        }
        synonyms.remove(position);
        size--;
        return position.element().getValue();    
    }
    
    protected void deleteSynonymsList(int index){
        table[index] = null;
    }
    
    public V get(K key){
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if(position == null) {
            return null;
        }
        return position.element().getValue();
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public Iterator<K> keys(){
        LinkedList<K> list = new LinkedList<K>();
        for(int i=0; i<table.length; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            for(KeyValue<K,V> kv : synonyms) {
                list.add(kv.getKey());
            }
        }
        return list.iterator();
    }
    
    public void put(K key, V value){
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonyms = table[index];
        Position<KeyValue<K,V>> position = seekKeyInSynonyms(synonyms, key);
        if(position != null) {
            position.element().setValue(value);
        } else {
            createSynonymsList(index, new KeyValue<K,V>(key, value));
            size++;
        }
    }
    
    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key){
        if(synonyms == null) {
            return null;
        }
        for(Position<KeyValue<K,V>> pos : synonyms.positions()) {
            if(pos.element().getKey().equals(key)) {
                return pos;
            }
        }
        return null;
    }
    
    public int size(){
        return size;
    }
    
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("{");
        for(int i=0; i<table.length; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            for(KeyValue<K,V> kv : synonyms) {
                s.append(kv.getKey() + ":" + kv.getValue() + ", ");
            }
        }
        if(size > 0) {
            s.setLength(s.length()-2);
        }
        s.append("}");
        return s.toString();
    }
    
    public Iterator<V> values(){
        LinkedList<V> list = new LinkedList<V>();
        for(int i=0; i<table.length; i++) {
            LinkedList<KeyValue<K,V>> synonyms = table[i];
            for(KeyValue<K,V> kv : synonyms) {
                list.add(kv.getValue());
            }
        }
        return list.iterator();
    }

}
