
public class HashTable<K,V> extends Object implements Dictionary<K,V> {

    private int size;
    private LinkedList<KeyValue<K,V>>[] table;

    public HashTable() {
        this(10);
    }

    public HashTable(int size) {
        this.size = size;
        table = (LinkedList<KeyValue<K,V>>[])new LinkedList<?>[size];
        
        for(int i=0;i<size;i++) {
            table[i] = new LinkedList<KeyValue<K,V>>();
        }
    }

    protected int calculateIndex(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    boolean containsKey(K key) {
        int index = calculateIndex(key);

        if (table[index] != null) {
            for (KeyValue<K,V> pair : table[index]) {
                if (pair.getKey().equals(key)) {
                    return true;
                }
            }
        }

        return false;
    }

    protected LinkedList<KeyValue<K,V>> createSynonymsList(int index, KeyValue<K,V> kv){
      table[index].add(kv);
      return table[index];
    }

    V delete(K key) {
        int index = calculateIndex(key);
        LinkedList<KeyValue<K,V>> synonymsIndex = table[index];

        if (synonymsIndex == null) {
            return null;
        }

        Iterator<KeyValue<K,V>> it = synonymsIndex.iterator();

        while (it.hasNext()) {
            KeyValue<K,V> pair = it.next();

            if (pair.getKey().equals(key)) {
                it.remove();
                return pair.getValue();
            }
        }

        return null;
    }

    protected void deleteSynonymsList(int index){
        table[index] = null;
    }

    V get(K key) {
        int index = calculateIndex(key);

        if (table[index] != null) {
            for (KeyValue<K,V> pair : table[index]) {
                if (pair.getKey().equals(key)) {
                    return pair.getValue();
                }
            }
        }

        return null;
    }

    boolean isEmpty(){
        return size() == 0;
    }

    Iterator<K> keys() {
        LinkedList<K> list = new LinkedList<K>();

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {

                for (KeyValue<K,V> pair : table[i]) {
                    list.add(pair.getKey());
                }
            }
        }

        return list.iterator();
    }

    void put(K key, V value) {
        int index = calculateIndex(key);

        if (table[index] == null) {
            table[index] = new LinkedList<KeyValue<K,V>>();
        }

        for (KeyValue<K,V> pair : table[index]) {
            if (pair.getKey().equals(key)) {
                pair.setValue(value);
                return;
            }
        }

        table[index].add(new KeyValue<K,V>(key, value));
    }

    protected Position<KeyValue<K,V>> seekKeyInSynonyms(LinkedList<KeyValue<K,V>> synonyms, K key){
        for(int i=0;i<synonyms.size();i++){
            if(synonyms.get(i).getKey().equals(key)){
                return new DoublyLinkedListNode<KeyValue<K, V>>(synonyms.get(i));
            }
        }
        return null;
    }

    int size() {
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                count += table[i].size();
            }
        }

        return count;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {

                for (KeyValue<K,V> pair : table[i]) {
                    result.append(pair + "\n");
                }
            }
        }

        return result.toString();
    }

    Iterator<V> values() {
        LinkedList<V> list = new LinkedList<V>();

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {

                for (KeyValue<K,V> pair : table[i]) {
                    list.add(pair.getValue());
                }
            }
        }

        return list.iterator();
    }

}
