
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] stackArray;
    private int capacity;
    private int top;
    
    // Constructor without parameters (maximum capacity, default).
    public StackArrayImpl() {
        this.capacity = 100;
        this.stackArray = (E[]) new Object[capacity];
        this.top = -1;
    }
    
    // Constructor with a parameter.
    public StackArrayImpl(int max) {
        this.capacity = max;
        this.stackArray = (E[]) new Object[capacity];
        this.top = -1;
    }
    
    // Method to check if the container is empty.
    public boolean isEmpty() {
        return (top == -1);
    }
    
    // Method to check if the container is full.
    public boolean isFull() {
        return (top == capacity - 1);
    }
    
    // Getter for the last item added to the stack, if any.
    public E peek() {
        if(top == -1) {
            return null;
        }
        return stackArray[top];
    }
    
    // Delete the top of the stack item, if any.
    public E pop() {
        if(top == -1) {
            return null;
        }
        E item = stackArray[top--];
        return item;
    }
    
    // Add an item to the stack, if any.
    public void push(E elem) {
        if(top == capacity - 1) {
            return;
        }
        stackArray[++top] = elem;
    }
    
    // Getter for the number of items in the container.
    public int size() {
        return top + 1;
    }
    
    // Method overwriting Object.toString ().
    @Override
    public String toString() {
        String result = "";
        for(int i = top; i >= 0; i--) {
            result += stackArray[i] + " ";
        }
        return result.trim();
    }
    
    // Retrieves the items in the container.
    public Iterator<E> values() {
        List<E> valueList = new ArrayList<>();
        for(int i = top; i >= 0; i--) {
            valueList.add(stackArray[i]);
        }
        return valueList.iterator();
    }
}
