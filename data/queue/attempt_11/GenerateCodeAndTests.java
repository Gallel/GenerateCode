
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private int maxCapacity; // maximum capacity of the queue
    private E[] queueArray; // array to store elements
    private int front; // index of the front element in the queue
    private int rear; // index of the rear element in the queue
    private int size; // number of elements in the queue

    // Constructor without parameters (Maximum capacity of the container by default).
    public QueueArrayImpl() {
        this(100); // default maximum capacity
    }

    // Constructor with a parameter.
    public QueueArrayImpl(int max) {
        this.maxCapacity = max;
        queueArray = (E[]) new Object[maxCapacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Add an item to the queue
    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % maxCapacity;
        queueArray[rear] = elem;
        size++;
    }

    // Method to check if the container is empty.
    public boolean isEmpty() {
        return (size == 0);
    }

    // Method to check if the container is full.
    public boolean isFull() {
        return (size == maxCapacity);
    }

    // Retrieves the first item added to the queue
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queueArray[front];
    }

    // Delete the first item in the queue
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E item = queueArray[front];
        queueArray[front] = null;
        front = (front + 1) % maxCapacity;
        size--;
        return item;
    }

    // Retrieves the number of items in the container.
    public int size() {
        return size;
    }

    // Method overwriting Object.toString ().
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = front; i != rear; i = (i + 1) % maxCapacity) {
            sb.append(queueArray[i]).append(", ");
        }
        sb.append(queueArray[rear]).append("]");
        return sb.toString();
    }

    // Retrieves the items in the container.
    public Iterator<E> values() {
        return new ArrayIterator<E>(queueArray, front, size);
    }
}
