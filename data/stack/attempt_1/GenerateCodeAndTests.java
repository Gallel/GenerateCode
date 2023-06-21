
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] stack;
    private int top;
    private int max;

    // Constructor without parameters (maximum capacity, default).
    public StackArrayImpl() {
        this.max = 1000;
        this.top = -1;
        this.stack = (E[]) new Object[max];
    }
    
    // Constructor with a parameter.
    public StackArrayImpl(int max) {
        this.max = max;
        this.top = -1;
        this.stack = (E[]) new Object[max];
    }
    
    // Method to check if the container is empty.
    public boolean isEmpty() {
        return top == -1;
    }
    
    // Method to check if the container is full.
    public boolean isFull() {
        return top == max - 1;
    }
    
    // Getter for the last item added to the stack, if any.
    public E peek() {
        if (top == -1){
            return null;
        } else {
            return stack[top];
        }
    }
    
    // Delete the top of the stack item, if any.
    public E pop() {
        if (top == -1) {
            return null;
        } else {
            E elem = stack[top];
            stack[top] = null;
            top--;
            return elem;
        }
    }
    
    // Add an item to the stack, if any.
    public void push(E elem) {
        if (top == max - 1) {
            return;
        } else {
            top++;
            stack[top] = elem;
        }
    }
    
    // Getter for the number of items in the container.
    public int size() {
        return top + 1;
    }
    
    // Method overwriting Object.toString ().
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for(int i = 0; i <= top; i++) {
            buffer.append(stack[i]);
            if(i != top) {
                buffer.append(", ");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
    
    // Retrieves the items in the container.
    public Iterator<E> values() {
        ArrayList<E> list = new ArrayList<E>();
        for(int i = top; i >= 0; i--) {
            list.add(stack[i]);
        }
        return list.iterator();
    }
}
