
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E>{

    private Object[] array;
    private int size;
    private int capacity;

    public QueueArrayImpl(){
        this(100);
        array = new Object[capacity];
        size = 0;
    }

    public QueueArrayImpl(int max){
        capacity = max;
        array = new Object[capacity];
        size = 0;
    }

    public void add(E elem){
        if (isFull()){
            throw new FullContainerException();
        }
        array[size] = elem;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public E peek(){
        if (isEmpty()){
            throw new EmptyContainerException();
        }
        return (E) array[0];
    }

    public E poll(){
        if (isEmpty()){
            throw new EmptyContainerException();
        }
        E elem = (E) array[0];
        for (int i=0; i<size-1; i++){
            array[i] = array[i+1];
        }
        size--;
        return elem;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: ");
        for (int i=0; i<size; i++){
            sb.append(array[i].toString());
            if (i<size-1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size && array[currentIndex] != null;
            }
            @Override
            public E next() {
                return (E) array[currentIndex++];
            }
        };
    }
}
