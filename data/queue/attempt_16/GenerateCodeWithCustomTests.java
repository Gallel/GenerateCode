
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    
    // attributes    
    private int capacity;
    private int front;
    private int rear;
    private int size;
    private E[] queue;
    
    // constructors
    public QueueArrayImpl() {
        this(FiniteContainer.DEFAULT_MAX_SIZE);
    }

    public QueueArrayImpl(int max) {
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.capacity = max;
        this.queue = (E[]) new Object[max];
    }
    
    // methods
    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        rear = (rear + 1) % capacity;
        queue[rear] = elem;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == capacity);
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return queue[front];
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elem = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;
        return elem;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int i;
        int j = size();
        for (i = front; j > 1; j--) {
            sb.append(queue[i]).append(", ");
            i = (i + 1) % capacity;
        }
        if (j > 0) {
            sb.append(queue[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int idx = front;

            public boolean hasNext() {
                return idx != rear;
            }

            public E next() {
                E elem = queue[idx];
                idx = (idx + 1) % capacity;
                return elem;
            }
        };
    }
}
