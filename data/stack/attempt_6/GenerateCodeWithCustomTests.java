
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] stackArray;
    private int top;
    private int max;

    //Constructor without parameters (maximum capacity, default).
    public StackArrayImpl() {
        this.max = 1000;
        this.top = -1;
        this.stackArray = (E[]) new Object[max];
    }

    //Constructor with a parameter.
    public StackArrayImpl(int max) {
        this.max = max;
        this.top = -1;
        this.stackArray = (E[]) new Object[max];
    }

    //Method to check if the container is empty.
    public boolean isEmpty() {
        return (top == -1);
    }

    //Method to check if the container is full.
    public boolean isFull() {
        return (top == max - 1);
    }

    //Getter for the last item added to the stack, if any.
    public E peek() {
        if (top == -1)
            return null;
        return stackArray[top];
    }

    //Delete the top of the stack item, if any.
    public E pop() {
        if (top == -1)
            return null;
        return stackArray[top--];
    }

    //Add an item to the stack, if any.
    public void push(E elem) {
        if (top < max - 1)
            stackArray[++top] = elem;
    }

    //Getter for the number of items in the container.
    public int size() {
        return top + 1;
    }

    //Method overwriting Object.toString ().
    public String toString() {
        if (top == -1)
            return "Stack is empty.";
        StringBuilder str = new StringBuilder();
        for (int i = top; i >= 0; i--) {
            str.append(stackArray[i].toString());
            str.append("\n");
        }
        return str.toString();
    }

    //Retrieves the items in the container.
    public Iterator<E> values() {
        return new Iterator<E>() {
            private int i = top;

            public boolean hasNext() {
                return i >= 0;
            }

            public E next() {
                return stackArray[i--];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
