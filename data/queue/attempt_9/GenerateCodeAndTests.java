
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private int capacity;
    private int size;
    private int front;
    private int rear;
    private E[] elements;

    public QueueArrayImpl() {
        this.capacity = 10;
        this.size = 0;
        this.front = 0;
        this.rear = -1;
        this.elements = (E[]) new Object[capacity];
    }

    public QueueArrayImpl(int max) {
        this.capacity = max;
        this.size = 0;
        this.front = 0;
        this.rear = -1;
        this.elements = (E[]) new Object[capacity];
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[(front + i) % capacity].toString());
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private int current = front;

        public boolean hasNext() {
            return current != rear + 1;
        }

        public E next() {
            E elem = elements[current];
            current = (current + 1) % capacity;
            return elem;
        }
    }
}
