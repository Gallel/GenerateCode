
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    
    private int MAX;
    private E[] stack;
    private int top;
    
    // Constructor without parameters (maximum capacity, default).
    public StackArrayImpl() {
        MAX = 1000;
        stack = (E[]) new Object[MAX];
        top = -1;
    }
    
    // Constructor with a parameter.
    public StackArrayImpl(int max) {
        MAX = max;
        stack = (E[]) new Object[MAX];
        top = -1;
    }
    
    // Method to check if the container is empty.
    public boolean isEmpty() {
        return top == -1;
    }
    
    // Method to check if the container is full.
    public boolean isFull() {
        return top == MAX - 1;
    }
    
    // Getter for the last item added to the stack, if any.
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[top];
    }
    
    // Delete the top of the stack item, if any.
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E elem = stack[top];
        stack[top] = null;
        top--;
        return elem;
    }
    
    // Add an item to the stack, if any.
    public void push(E elem) {
        if (isFull()) {
            return;
        }
        top++;
        stack[top] = elem;
    }
    
    // Getter for the number of items in the container.
    public int size() {
        return top + 1;
    }
    
    // Method overwriting Object.toString ().
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = top; i >= 0; i--) {
            sb.append(stack[i]);
            if (i != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    // Retrieves the items in the container.
    public Iterator<E> values() {
        List<E> list = new ArrayList<>();
        for(int i = top; i >= 0; i--) {
            list.add(stack[i]);
        }
        return list.iterator();
    }
}
