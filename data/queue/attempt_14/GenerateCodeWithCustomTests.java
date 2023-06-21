
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    
    private Object[] array;
    private int size;
    private int max;
    
    public QueueArrayImpl() {
        this.max = 1000;
        this.array = new Object[max];
        this.size = 0;
    }
    
    public QueueArrayImpl(int max) {
        this.max = max;
        this.array = new Object[max];
        this.size = 0;
    }
    
    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        array[size] = elem;
        size++;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == max;
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return (E) array[0];
    }
    
    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elem = (E) array[0];
        for (int i = 0; i < size-1; i++) {
            array[i] = array[i+1];
        }
        size--;
        return elem;
    }
    
    public int size() {
        return size;
    }
    
    public String toString() {
        String s = "";
        s += "[";
        for (int i = 0; i < size; i++) {
            s += array[i].toString();
            if (i != size-1) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
    
    public Iterator<E> values() {
        return new Iterator<E>() {
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                return index < size;
            }
            
            @Override
            public E next() {
                E elem = (E) array[index];
                index++;
                return elem;
            }
        };
    }
}
