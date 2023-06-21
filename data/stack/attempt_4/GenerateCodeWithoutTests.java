
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] data;
    private int top;
    private int max;

    // Constructor without parameters (maximum capacity, default).
    StackArrayImpl() {
        this.max = 100;
        data = (E[])new Object[max];
        top = -1;
    }

    // Constructor with a parameter.
    StackArrayImpl(int max) {
        this.max = max;
        data = (E[])new Object[max];
        top = -1;
    }

    // Method to check if the container is empty.
    public boolean isEmpty() {
        return (top == -1);
    }

    // Method to check if the container is full.
    public boolean isFull() {
        return (top == max-1);
    }

    // Getter for the last item added to the stack, if any.
    public E peek() {
        if(isEmpty()){
            // Stack is empty
            throw new RuntimeException("Stack Empty");
        }
        return data[top];
    }

    // Delete the top of the stack item, if any.
    public E pop() {
        if(isEmpty()){
            // Stack is empty
            throw new RuntimeException("Stack Empty");
        }
        E val = data[top];
        data[top--] = null;
        return val;
    }

    // Add an item to the stack, if any.
    public void push(E elem) {
        if(isFull()){
            // Stack is full
            throw new RuntimeException("Stack Full");
        }
        data[++top] = elem;
    }

    // Getter for the number of items in the container.
    public int size() {
        return top+1;
    }

    // Method overwriting Object.toString ().
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i=0;i<=top;i++){
            result.append(data[i]).append(" ");
        }
        return result.toString().trim();
    }

    // Retrieving the items in the container.
    public Iterator<E> values() {
        return new Iterator<E>(){

            private int current = 0;

            public boolean hasNext(){
                return current <= top;
            }

            public E next(){
                return data[current++];
            }
        };
    }
}
