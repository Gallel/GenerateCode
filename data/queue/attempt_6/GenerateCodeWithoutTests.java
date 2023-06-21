
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private E[] container;
    private int front;
    private int rear;
    private int size;

    // Constructor without parameters
    public QueueArrayImpl() {
        container = (E[]) new Object[10];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Constructor with a parameter
    public QueueArrayImpl(int max) {
        container = (E[]) new Object[max];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Add an item to the queue
    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % container.length;
        container[rear] = elem;
        size++;
    }

    // Method to check if the container is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to check if the container is full
    public boolean isFull() {
        return size == container.length;
    }

    // Retrieves the first item added to the queue
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return container[front];
    }

    // Delete the first item in the queue
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E elem = container[front];
        container[front] = null;
        front = (front + 1) % container.length;
        size--;
        return elem;
    }

    // Retrieves the number of items in the container
    public int size() {
        return size;
    }

    // Method overwriting Object.toString()
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % container.length;
            sb.append(container[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Retrieves the items in the container
    public Iterator<E> values() {
        return new QueueArrayIterator();
    }

    private class QueueArrayIterator implements Iterator<E> {
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index = (front + currentIndex) % container.length;
            E elem = container[index];
            currentIndex++;
            return elem;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
