
public class QueueArrayImpl<E> implements Queue<E>, FiniteContainer<E>{
    private int maxCapacity;
    private int currentSize;
    private E[] elements;

    // Constructor without parameters
    public QueueArrayImpl(){
        this.maxCapacity = 100;
        this.currentSize = 0;
        this.elements = (E[]) new Object[maxCapacity];
    }

    // Constructor with a parameter
    public QueueArrayImpl(int max){
        this.maxCapacity = max;
        this.currentSize = 0;
        this.elements = (E[]) new Object[maxCapacity];
    }

    // Add an item to the queue
    public void add(E elem) throws FullContainerException{
        if (isFull()){
            throw new FullContainerException("The container is full");
        }
        elements[currentSize] = elem;
        currentSize++;
    }

    // Method to check if the container is empty
    public boolean isEmpty(){
        return (currentSize == 0);
    }

    // Method to check if the container is full
    public boolean isFull(){
        return (currentSize >= maxCapacity);
    }

    // Retrieves the first item added to the queue
    public E peek() throws EmptyContainerException{
        if (isEmpty()){
            throw new EmptyContainerException("The container is empty");
        }
        return elements[0];
    }

    // Delete the first item in the queue
    public E poll() throws EmptyContainerException{
        if (isEmpty()){
            throw new EmptyContainerException("The container is empty");
        }
        E elem = elements[0];
        currentSize--;
        for (int i = 0; i < currentSize; i++){
            elements[i] = elements[i+1];
        }
        elements[currentSize] = null;
        return elem;
    }

    // Retrieves the number of items in the container
    public int size(){
        return currentSize;
    }

    // Method overwriting Object.toString()
    public String toString(){
        String str = "[";
        for (int i = 0; i < currentSize-1; i++){
            str += elements[i].toString() + ", ";
        }
        if (currentSize > 0){
            str += elements[currentSize-1].toString();
        }
        str += "]";
        return str;
    }

    // Retrieves the items in the container
    public Iterator<E> values(){
        List<E> list = new ArrayList<E>();
        for (int i = 0; i < currentSize; i++){
            list.add(elements[i]);
        }
        return list.iterator();
    }
}
