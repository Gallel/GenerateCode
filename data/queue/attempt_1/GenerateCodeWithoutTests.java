
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private int maxCapacity;
    private E[] queueArray;
    private int frontIndex;
    private int rearIndex;

    // Constructor without parameters (Maximum capacity of the container by default)
    public QueueArrayImpl() {
        this(100);
    }

    // Constructor with a parameter
    public QueueArrayImpl(int max) {
        maxCapacity = max;
        queueArray = (E[]) new Object[maxCapacity];
        frontIndex = 0;
        rearIndex = -1;
    }

    // Add an item to the queue
    public void add(E elem) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        rearIndex++;
        queueArray[rearIndex] = elem;
    }

    // Method to check if the container is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Method to check if the container is full
    public boolean isFull() {
        return (rearIndex == maxCapacity - 1);
    }

    // Retrieves the first item added to the queue
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return queueArray[frontIndex];
    }

    // Delete the first item in the queue
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        E elem = queueArray[frontIndex];
        queueArray[frontIndex] = null;
        frontIndex++;
        return elem;
    }

    // Retrieves the number of items in the container
    public int size() {
        return rearIndex - frontIndex + 1;
    }

    // Method overwriting Object.toString()
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = frontIndex; i <= rearIndex; i++) {
            sb.append(queueArray[i].toString());
            if (i < rearIndex) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Retrieves the items in the container
    public Iterator<E> values() {
        List<E> list = new ArrayList<E>();
        for (int i = frontIndex; i <= rearIndex; i++) {
            list.add(queueArray[i]);
        }
        return list.iterator();
    }
}