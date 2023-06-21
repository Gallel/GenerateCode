
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private E[] elements;
    private int capacity;
    private int front;
    private int rear;
    private int count;

    public QueueArrayImpl() {
        this(10);
    }

    public QueueArrayImpl(int max) {
        capacity = max;
        elements = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        elements[rear] = elem;
        count++;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
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
        count--;
        return elem;
    }

    public int size() {
        return count;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            int idx = (front + i) % capacity;
            sb.append(elements[idx]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int idx = front;

            public boolean hasNext() {
                return idx <= rear;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E elem = elements[idx % capacity];
                idx++;
                return elem;
            }
        };
    }
}
