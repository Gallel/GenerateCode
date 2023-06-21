
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private E[] elements;
    private int size;
    private int head = 0; //index of the first element
    private int tail = -1; //index of the last element

    public QueueArrayImpl() {
        this(100);
    }

    public QueueArrayImpl(int max) {
        elements = (E[]) new Object[max];
    }

    @Override
    public void add(E elem) {
        if(isFull()) throw new IllegalStateException("Container is full.");
        tail = (tail + 1) % elements.length;
        elements[tail] = elem;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == elements.length;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return elements[head];
    }

    @Override
    public E poll() {
        if(isEmpty()) return null;
        E elem = elements[head];
        elements[head] = null; //orphan the previous element
        head = (head + 1) % elements.length;
        size--;
        return elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < size; i++) {
            sb.append(elements[(head + i) % elements.length]).append(", ");
        }
        if(!isEmpty()) sb.setLength(sb.length() - 2); //remove the last comma and space
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> values() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if(!hasNext()) throw new NoSuchElementException();
                E elem = elements[(head + index) % elements.length];
                index++;
                return elem;
            }
        };
    }
}
