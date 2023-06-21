
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    
    //Declare attributes
    private E[] elements;
    private int max;
    private int first;
    private int last;
    private int count;
    
    //Constructor without parameters
    public QueueArrayImpl() {
        this.max = 100;
        this.elements = (E[]) new Object[this.max];
        this.first = 0;
        this.last = -1;
        this.count = 0;
    }
    
    //Constructor with a parameter
    public QueueArrayImpl(int max) {
        this.max = max;
        this.elements = (E[]) new Object[this.max];
        this.first = 0;
        this.last = -1;
        this.count = 0;
    }
    
    //Add an item to the queue
    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        } else {
            last++;
            if (last == max) {
                last = 0;
            }
            elements[last] = elem;
            count++;
        }
    }
    
    //Method to check if the container is empty
    public boolean isEmpty() {
        return count == 0;
    }
    
    //Method to check if the container is full
    public boolean isFull() {
        return count == max;
    }
    
    //Retrieves the first item added to the queue
    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        } else {
            return elements[first];
        }
    }
    
    //Delete the first item in the queue
    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        } else {
            E element = elements[first];
            first++;
            if (first == max) {
                first = 0;
            }
            count--;
            return element;
        }
    }
    
    //Retrieves the number of items in the container
    public int size() {
        return count;
    }
    
    //Method overwriting Object.toString()
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < count; i++) {
            int index = (first + i) % max;
            result.append(elements[index].toString());
            if (i < count - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
    
    //Retrieves the items in the container.
    public Iterator<E> values() {
        ArrayList<E> list = new ArrayList<E>();
        for (int i = 0; i < count; i++) {
            int index = (first + i) % max;
            list.add(elements[index]);
        }
        return list.iterator();
    }
}