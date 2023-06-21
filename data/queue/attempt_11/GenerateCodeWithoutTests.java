
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private int front;
    private int rear;
    private int size;
    private E[] queue;
    private int capacity;

    QueueArrayImpl() {
        this(100);
    }

    QueueArrayImpl(int max) {
        capacity = max;
        front = 0;
        rear = -1;
        size = 0;
        queue = (E[]) new Object[capacity];
    }

    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        rear = (rear + 1) % capacity;
        queue[rear] = elem;
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
            throw new IllegalStateException();
        }
        return queue[front];
    }

    public E poll() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E elem = queue[front];
        front = (front + 1) % capacity;
        size--;
        return elem;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size; i++) {
            s.append(queue[(front + i) % capacity].toString());
            if (i < size - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int count = size;
            private int index = front;

            public boolean hasNext() {
                return count > 0;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E elem = queue[index];
                index = (index + 1) % capacity;
                count--;
                return elem;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
