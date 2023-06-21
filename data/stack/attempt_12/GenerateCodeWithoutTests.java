
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] stackArray;
    private int top;
    private int maxSize;
    
    // Constructor without parameters (maximum capacity, default).
    public StackArrayImpl() {
        this(10);
    }
    
    // Constructor with a parameter.
    public StackArrayImpl(int max) {
        this.maxSize = max;
        this.stackArray = (E[]) new Object[maxSize];
        this.top = -1;
    }
    
    // Method to check if the container is empty.
    public boolean isEmpty() {
        return (top == -1);
    }
    
    // Method to check if the container is full.
    public boolean isFull() {
        return (top == maxSize - 1);
    }
    
    // Getter for the last item added to the stack, if any.
    public E peek() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return stackArray[top];
    }
    
    // Delete the top of the stack item, if any.
    public E pop() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        E item = stackArray[top];
        top--;
        return item;
    }
    
    // Add an item to the stack, if any.
    public void push(E elem) {
        if(isFull()) {
            throw new IllegalStateException();
        }
        top++;
        stackArray[top] = elem;
    }
    
    // Getter for the number of items in the container.
    public int size() {
        return top + 1;
    }
    
    // Method overwriting Object.toString ().
    public String toString() {
        StringBuilder sb = new StringBuilder("Stack: ");
        for(int i = top; i >= 0; i--) {
            sb.append(stackArray[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
    
    // Retrieves the items in the container.
    public Iterator<E> values() {
        List<E> list = new ArrayList<E>();
        for(int i = top; i >= 0; i--) {
            list.add(stackArray[i]);
        }
        return list.iterator();
    }
}
