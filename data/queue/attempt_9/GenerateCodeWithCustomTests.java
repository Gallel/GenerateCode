
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E>{
    private E[] container;
    private int size = 0;
    private int front = 0;
    private int rear = -1;
    
    // Constructor without parameters
    public QueueArrayImpl(){
        this.container = (E[])new Object[DEFAULT_SIZE];
    }
    
    // Constructor with a parameter 'max'
    public QueueArrayImpl(int max){
        this.container = (E[])new Object[max];
    }
    
    // Add an item to the queue
    public void add(E elem){
        if(!isFull()){
            rear = (rear + 1) % container.length;
            container[rear] = elem;
            size++;
        }
        else {
            throw new FullContainerException();
        }
    }
    
    // Method to check if the container is empty
    public boolean isEmpty(){
        return (size == 0);
    }
    
    // Method to check if the container is full
    public boolean isFull(){
        return (size == container.length);
    }
    
    // Retrieves the first item added to the queue
    public E peek(){
        if(!isEmpty()){
            return container[front];
        }
        else {
            throw new EmptyContainerException();
        }
    }
    
    // Delete the first item in the queue
    public E poll(){
        if(!isEmpty()){
            E temp = container[front];
            container[front] = null;
            front = (front + 1) % container.length;
            size--;
            return temp;
        }
        else {
            throw new EmptyContainerException();
        }
    }
    
    // Retrieves the number of items in the container
    public int size(){
        return size;
    }
    
    // Method overwriting Object.toString ()
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName()).append("[");
       
        if(!isEmpty()){
            for (int i = 0; i < size-1; i++){
                sb.append(container[(front + i) % container.length]).append(",");
            }
            sb.append(container[(front+size-1) % container.length]);
        }
        
        sb.append("]");
        return sb.toString();   
    }
    
    // Retrieves the items in the container
    public Iterator<E> values(){
        return new QueueArrayIterator();
    }
    
    private class QueueArrayIterator implements Iterator<E>{
        private int current = 0;
        private int count = 0;
        
        public boolean hasNext(){
            return count < size;
        }
        
        public E next(){
            if(hasNext()){
                E temp = container[(front + current) % container.length];
                current++;
                count++;
                return temp;
            }
            else {
                throw new NoSuchElementException();
            }
        }   
    }
}
