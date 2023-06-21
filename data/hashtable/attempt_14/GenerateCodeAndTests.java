
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> extends Object implements Dictionary<K, V> {

  private LinkedList<KeyValue<K, V>>[] table;
  private int size;

  public HashTable() {
    this(997);
  }

  public HashTable(int size) {
    this.table = new LinkedList[size];
    for (int i = 0; i < size; i++) {
      this.table[i] = new LinkedList<KeyValue<K, V>>();
    }
    this.size = 0;
  }

	protected int calculateIndex(K key) {
		return (key.hashCode() & 0x7fffffff) % table.length;
	}

	public boolean containsKey(K key) {
		int index = calculateIndex(key);
		return seekKeyInSynonyms(table[index], key) != null;
	}

	protected LinkedList<KeyValue<K, V>> createSynonymsList(int index, KeyValue<K, V> kv) {
		LinkedList<KeyValue<K, V>> synonyms = table[index];
		if (synonyms == null) {
			table[index] = synonyms = new LinkedList<KeyValue<K, V>>();
		}
		synonyms.addFirst(kv);
		size++;
		return synonyms;
	}

	public V delete(K key) {
		int index = calculateIndex(key);
		LinkedList<KeyValue<K, V>> synonyms = table[index];
		if (synonyms == null) {
			return null;
		}
		Position<KeyValue<K, V>> position = seekKeyInSynonyms(synonyms, key);
		if (position == null) {
			return null;
		}
		else {
			KeyValue<K, V> kv = position.element();
			synonyms.remove(position);
			size--;
			if (synonyms.isEmpty()) {
				table[index] = null;
			}
			return kv.getValue();
		}
	}

	protected void deleteSynonymsList(int index) {
		table[index] = null;
	}

	public V get(K key) {
		int index = calculateIndex(key);
		Position<KeyValue<K, V>> position = seekKeyInSynonyms(table[index], key);
		if (position == null) {
			return null;
		}
		else {
			return position.element().getValue();
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<K> keys() {
		return new KeyIterator<K, V>(this);
	}

	public void put(K key, V value) {
		if (key == null) {
			return;
		}
		int index = calculateIndex(key);
		Position<KeyValue<K, V>> position = seekKeyInSynonyms(table[index], key);
		if (position == null) {
			createSynonymsList(index, new KeyValue<K, V>(key, value));
		}
		else {
			position.element().setValue(value);
		}
	}

	protected Position<KeyValue<K, V>> seekKeyInSynonyms(LinkedList<KeyValue<K, V>> synonyms, K key) {
		if (synonyms == null) {
			return null;
		}
		Iterator<KeyValue<K, V>> it = synonyms.iterator();
		while (it.hasNext()) {
			Position<KeyValue<K, V>> position = (Position<KeyValue<K, V>>) it.next();
			if (position.element().getKey().equals(key)) {
				return position;
			}
		}
		return null;
	}

	public int size() {
		return size;
	}

	public String toString() {
		String separator = "";
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < table.length; i++) {
			LinkedList<KeyValue<K,V>> synonyms = table[i];
			if (synonyms != null && !synonyms.isEmpty()) {
				Iterator<KeyValue<K,V>> it = synonyms.iterator();
				while (it.hasNext()) {
					KeyValue<K,V> kv = (KeyValue<K,V>) it.next();
					sb.append(separator);
					separator = ", ";
					sb.append(kv.getKey());
					sb.append(": ");
					sb.append(kv.getValue());
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public Iterator<V> values() {
		return new ValueIterator<K, V>(this);
	}
}
