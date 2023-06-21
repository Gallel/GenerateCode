
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E>{

    private int maxSize;
    private int currentSize;
    private E[] queue;

    //Constructor without parameters (Maximum capacity of the container by default)
    public QueueArrayImpl(){
        this.maxSize = 1000; //default maximum capacity
        this.currentSize = 0;
        this.queue = (E[]) new Object[maxSize]; //create empty array of size maxSize
    }

    //Constructor with a parameter
    public QueueArrayImpl(int max){
        this.maxSize = max;
        this.currentSize = 0;
        this.queue = (E[]) new Object[maxSize]; //create empty array of size max
    }

    //Add an item to the queue
    public void add(E elem){
        if(currentSize < maxSize){
            queue[currentSize] = elem;
            currentSize++;
        }
    }

    //Method to check if the container is empty
    public boolean isEmpty(){
        return currentSize == 0;
    }

    //Method to check if the container is full
    public boolean isFull(){
        return currentSize == maxSize;
    }

    //Retrieves the first item added to the queue
    public E peek(){
        if(!isEmpty()){
            return queue[0];
        }
        return null;
    }

    //Delete the first item in the queue
    public E poll(){
        if(!isEmpty()){
            E elem = queue[0];
            for(int i = 0; i < currentSize-1; i++){
                queue[i] = queue[i+1]; //shift all elements left by one
            }
            currentSize--;
            return elem;
        }
        return null;
    }

    //Retrieves the number of items in the container
    public int size(){
        return currentSize;
    }

    //Method overwriting Object.toString()
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < currentSize; i++){
            sb.append(queue[i].toString());
            if(i != currentSize-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //Retrieves the items in the container.
    public Iterator<E> values(){
        return new Iterator<E>(){

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && queue[currentIndex] != null;
            }

            @Override
            public E next() {
                return queue[currentIndex++];
            }
        };
    }
}
