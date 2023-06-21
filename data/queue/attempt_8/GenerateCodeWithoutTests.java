
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    
    private E[] elements; 
    private int front; 
    private int rear; 
    private int maxSize;
    
    //Constructor without parameters (Maximum capacity of the container by default)
    public QueueArrayImpl() {
        this(10); //default capacity is 10
    }
 
    //Constructor with a parameter
    public QueueArrayImpl(int max) {
        maxSize = max;
        elements = (E[]) new Object[maxSize];
        front = 0;
        rear = -1;
    }
    
    //Add an item to the queue
    public void add(E elem) {
        if(isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear++;
        elements[rear] = elem;
    }
    
    //Method to check if the container is empty
    public boolean isEmpty() {
        return (size() == 0);
    }
    
    //Method to check if the container is full
    public boolean isFull() {
        return (size() == maxSize);
    }
    
    //Retrieves the first item added to the queue
    public E peek() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements[front];
    }
    
    //Delete the first item in the queue
    public E poll() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E removedElement = elements[front];
        front++;
        return removedElement;
    }
    
    //Retrieves the number of items in the container
    public int size() {
        return (rear - front + 1);
    }
    
    //Method overwriting Object.toString()
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = front; i <= rear; i++) {
            sb.append(elements[i]);
            if(i != rear) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    //Retrieves the items in the container
    public Iterator<E> values() {
        return Arrays.asList(elements).subList(front, rear+1).iterator();
    }
}
