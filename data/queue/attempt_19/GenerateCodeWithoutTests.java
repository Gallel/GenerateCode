
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    
    private E[] elements;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        capacity = 10;
        elements = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Invalid capacity: " + max);
        }
        capacity = max;
        elements = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        elements[rear] = elem;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[front];
    }

    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E elem = elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
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
            sb.append(elements[(front + i) % capacity]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int index = 0;

            public boolean hasNext() {
                return index < size;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[(front + index++) % capacity];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
