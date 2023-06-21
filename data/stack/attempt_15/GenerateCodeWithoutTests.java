
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{

    // Maximum capacity of the stack
    private int maxCapacity;
    
    // Array that stores the elements of the stack
    private E[] stackArray;
    
    // Index of the top of the stack
    private int topIndex;

    // Constructor without parameters (maximum capacity = 100)
    public StackArrayImpl(){
        maxCapacity = 100;
        stackArray = (E[])new Object[maxCapacity];
        topIndex = -1;
    }

    // Constructor with a parameter
    public StackArrayImpl(int max){
        maxCapacity = max;
        stackArray = (E[])new Object[maxCapacity];
        topIndex = -1;
    }

    // Method to check if the container is empty
    public boolean isEmpty(){
        return topIndex == -1;
    }

    // Method to check if the container is full
    public boolean isFull(){
        return topIndex == maxCapacity-1;
    }

    // Getter for the last item added to the stack, if any
    public E peek(){
        if(isEmpty()) return null;
        return stackArray[topIndex];
    }

    // Delete the top of the stack item, if any
    public E pop(){
        if(isEmpty()) return null;
        E elem = stackArray[topIndex];
        stackArray[topIndex] = null;
        topIndex--;
        return elem;
    }

    // Add an item to the stack, if any
    public void push(E elem){
        if(isFull()) return;
        topIndex++;
        stackArray[topIndex] = elem;
    }

    // Getter for the number of items in the container
    public int size(){
        return topIndex+1;
    }

    // Method overwriting Object.toString()
    public String toString(){
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<=topIndex; i++){
            sb.append(stackArray[i].toString());
            if(i < topIndex) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Retrieves the items in the container
    public Iterator<E> values(){
        return new Iterator<E>(){
            private int currentIndex = topIndex;
            public boolean hasNext(){
                return currentIndex >= 0;
            }
            public E next(){
                return stackArray[currentIndex--];
            }
        };
    }
}
