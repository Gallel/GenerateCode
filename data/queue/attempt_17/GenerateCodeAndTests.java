
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private E[] elements; // array to store elements
    private int front; // index of first element
    private int rear; // index of last element
    private int capacity; // maximum capacity of the queue
    private int count; // current size of the queue

    // Constructor without parameters (Maximum capacity of the container by default).
    public QueueArrayImpl() {
        this(10);
    }

    // Constructor with a parameter.
    public QueueArrayImpl(int max) {
        this.capacity = max;
        this.elements = (E[]) new Object[max];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    // Add an item to the queue.
    public void add(E elem) {
        if(rear == capacity-1) {
            throw new IllegalStateException("Queue is full");
        }
        else {
            elements[++rear] = elem;
            count++;
        }
    }

    // Method to check if the container is empty.
    public boolean isEmpty() {
        return (count == 0);
    }

    // Method to check if the container is full.
    public boolean isFull() {
        return (count == capacity);
    }

    // Retrieves the first item added to the queue.
    public E peek() {
        if(isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[front];
    }

    // Delete the first item in the queue.
    public E poll() {
        if(isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        else {
            E item = elements[front];
            elements[front++] = null; // to avoid memory leak
            count--;
            return item;
        }
    }

    // Retrieves the number of items in the container.
    public int size() {
        return count;
    }

    // Method overwriting Object.toString ().
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = front; i <= rear; i++) {
            sb.append(elements[i]);
            if(i != rear) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Retrieves the items in the container.
    public Iterator<E> values() {
        List<E> list = new ArrayList<E>(count);
        for(int i = front; i <= rear; i++) {
            list.add(elements[i]);
        }
        return list.iterator();
    }
}
