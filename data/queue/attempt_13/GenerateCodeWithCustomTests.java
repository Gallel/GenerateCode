
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    
    private E[] container; 
    private int head; 
    private int tail; 
    private int max;
    private int size;
    
    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        this.max = DEFAULT_SIZE;
        this.container = (E[]) new Object[DEFAULT_SIZE];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    
    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.max = max;
        this.container = (E[]) new Object[max];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    
    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        container[tail] = elem;
        tail = (tail + 1) % max;
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
            return null;
        }
        return container[head];
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E elem = container[head];
        head = (head + 1) % max;
        size--;
        return elem;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(container[(head + i) % max]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return Arrays.asList(Arrays.copyOfRange(container, head, max)).iterator();
    }
}
