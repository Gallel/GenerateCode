
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private int maxCapacity;
    private E[] elements;
    private int frontIndex;
    private int rearIndex;
    private int size;

    //Constructor without parameters (Maximum capacity of the container by default)
    public QueueArrayImpl() {
        this.maxCapacity = 100;
        this.elements = (E[]) new Object[maxCapacity];
        this.frontIndex = 0;
        this.rearIndex = 0;
        this.size = 0;
    }

    //Constructor with a parameter
    public QueueArrayImpl(int max) {
        this.maxCapacity = max;
        this.elements = (E[]) new Object[maxCapacity];
        this.frontIndex = 0;
        this.rearIndex = 0;
        this.size = 0;
    }

    //Add an item to the queue
    public void add(E elem) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        size++;
        elements[rearIndex] = elem;
        rearIndex = (rearIndex + 1) % maxCapacity;
    }

    //Method to check if the container is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //Method to check if the container is full
    public boolean isFull() {
        return size == maxCapacity;
    }

    //Retrieves the first item added to the queue
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return elements[frontIndex];
    }

    //Delete the first item in the queue
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        size--;
        E element = elements[frontIndex];
        elements[frontIndex] = null;
        frontIndex = (frontIndex + 1) % maxCapacity;
        return element;
    }

    //Retrieves the number of items in the container
    public int size() {
        return size;
    }

    //Method overwriting Object.toString ()
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(elements[(frontIndex + i) % maxCapacity]).append(", ");
        }
        return sb.append(elements[(frontIndex + size - 1) % maxCapacity]).append("]").toString();
    }

    //Retrieves the items in the container
    public Iterator<E> values() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(elements[(frontIndex + i) % maxCapacity]);
        }
        return list.iterator();
    }
}
