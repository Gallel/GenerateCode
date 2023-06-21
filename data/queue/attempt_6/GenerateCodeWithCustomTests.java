
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private int capacity;
    private E[] elements;
    private int front, rear, count;

    // Constructor without parameters
    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        this.capacity = 1000;
        this.elements = (E[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    // Constructor with a parameter
    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.capacity = max;
        this.elements = (E[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    // Add an item to the queue
    @Override
    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        rear = (rear + 1) % capacity;
        elements[rear] = elem;
        count++;
    }

    // Method to check if the container is empty
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    // Method to check if the container is full
    @Override
    public boolean isFull() {
        return count == capacity;
    }

    // Retrieves the first item added to the queue
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return elements[front];
    }

    // Delete the first item in the queue
    @Override
    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elem = elements[front];
        front = (front + 1) % capacity;
        count--;
        return elem;
    }

    // Retrieves the number of items in the container
    @Override
    public int size() {
        return count;
    }

    // Method overwriting Object.toString().
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i = front;
        while (i != rear) {
            sb.append(elements[i]).append(", ");
            i = (i + 1) % capacity;
        }
        sb.append(elements[rear]).append("]");
        return sb.toString();
    }

    // Retrieves the items in the container.
    @Override
    public Iterator<E> values() {
        throw new UnsupportedOperationException();
    }
}
