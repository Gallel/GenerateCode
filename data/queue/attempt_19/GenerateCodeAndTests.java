
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> { 

    private int maxSize;
    private int size;
    private E[] queueArray;
    private int front;
    private int rear;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        this.maxSize = 1000;
        this.queueArray = (E[]) new Object[this.maxSize];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.maxSize = max;
        this.queueArray = (E[]) new Object[this.maxSize];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % maxSize;
        queueArray[rear] = elem;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == maxSize);
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queueArray[front];
    }

    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E elemRemoved = queueArray[front];
        queueArray[front] = null;
        front = (front + 1) % maxSize;
        size--;
        return elemRemoved;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                s += queueArray[(front + i) % maxSize];
            } else {
                s += queueArray[(front + i) % maxSize] + ", ";
            }
        }
        s += "]";
        return s;
    }

    public Iterator<E> values() {
        return new QueueArrayIterator();
    }

    private class QueueArrayIterator implements Iterator<E> {
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < size && queueArray[(front + currentIndex) % maxSize] != null;
        }

        public E next() {
            E elem = queueArray[(front + currentIndex) % maxSize];
            currentIndex++;
            return elem;
        }
    }
}
