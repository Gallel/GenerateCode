
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K,V> extends Object implements Dictionary<K,V>{
    private static final int DEFAULT_TABLE_SIZE = 11;
    private int currentSize;
    private LinkedList<KeyValue<K,V>>[] theLists;
    
    //Constructor without parameters (default table size).
    public HashTable(){
        this(DEFAULT_TABLE_SIZE);
    }
    
    //Constructor with a parameter.
    public HashTable(int size){
        currentSize = 0;
        theLists = new LinkedList[ size ];
        for( int i = 0; i < theLists.length; i++ )
            theLists[ i ] = new LinkedList<KeyValue<K,V>>();
    }
    
    protected int calculateIndex(K key){
        int hashVal = key.hashCode( );
        hashVal %= theLists.length;
        if( hashVal < 0 )
            hashVal += theLists.length;
        return hashVal;
    }

    //Checks for an item with a certain key.
    public boolean containsKey(K key){
        LinkedList<KeyValue<K,V>> whichList = theLists[ calculateIndex( key ) ];
        return seekKeyInSynonyms( whichList, key ) != null;
    }
    
    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv){
        LinkedList<KeyValue<K,V>> synonyms = theLists[index];
        if(synonyms == null){
            synonyms = new LinkedList<>();
            theLists[index] = synonyms;
        }
        synonyms.add(kv);
        currentSize++;
        return synonyms;
    }
    
    //Delete the first matching key and associated item, if possible.
    public V delete(K key){
        LinkedList<KeyValue<K,V>> synonyms = theLists[calculateIndex(key)];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms(synonyms, key);
        if(pos == null) return null;
        synonyms.remove(pos);
        currentSize--;
        if(synonyms.isEmpty())
            theLists[calculateIndex(key)] = null;
        return pos.element().getValue();
    }
    
    protected void deleteSynonymsList(int index){
        theLists[index] = null;
    }
    
    //Retrieves the value associated with a key.
    public V get(K key){
        LinkedList<KeyValue<K,V>> whichList = theLists[ calculateIndex( key ) ];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms( whichList, key );
        return pos == null ? null : pos.element().getValue();
    }
    
    //Method to check if the container is empty.
    public boolean isEmpty(){
        return size() == 0;
    }
    
    //Retrieves the items in the container.
    public Iterator<K> keys(){
        LinkedList<K> keys = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> theList : theLists) {
            if (theList != null) {
                for (KeyValue<K, V> kv : theList) {
                    keys.add(kv.getKey());
                }
            }
        }
        return keys.iterator();
    }
    
    //Add an item with an associated key, if possible.
    public void put(K key, V value){
        LinkedList<KeyValue<K,V>> whichList = theLists[ calculateIndex( key ) ];
        Position<KeyValue<K,V>> pos = seekKeyInSynonyms( whichList, key );
        if( pos == null ){
            createSynonymsList( calculateIndex( key ), new KeyValue<>( key, value ) );
            return;
        }
        pos.element().setValue( value );
    }
    
    protected Position<KeyValue<K,V>> seekKeyInSynonyms( LinkedList<KeyValue<K,V>> synonyms, K key ){
        if( synonyms == null ) return null;
        Iterator<KeyValue<K,V>> it = synonyms.iterator();
        while( it.hasNext( ) ){
            Position<KeyValue<K,V>> p = new NodePositionList.Position<>( it.next( ) );
            if( p.element( ).getKey( ).equals( key ) )
                return p;
        }
        return null;
    }
    
    //Retrieves the number of items in the container.
    public int size(){
        return currentSize;
    }
    
    //Method overwriting Object.toString ().
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for (LinkedList<KeyValue<K, V>> theList : theLists) {
            if (theList != null) {
                for (KeyValue<K, V> kv : theList) {
                    sb.append(kv.getKey()).append("=").append(kv.getValue()).append(", ");
                }
            }
        }
        return sb.length() == 1 ? "[]" : sb.substring(0, sb.length() - 2) + "]";
    }
    
    //Retrieves the items in the container.
    public Iterator<V> values(){
        LinkedList<V> values = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> theList : theLists) {
            if (theList != null) {
                for (KeyValue<K, V> kv : theList) {
                    values.add(kv.getValue());
                }
            }
        }
        return values.iterator();
    }
}
