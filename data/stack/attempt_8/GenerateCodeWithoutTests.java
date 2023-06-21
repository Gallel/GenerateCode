
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] stackArray;
    private int top;
    private int capacity;

    // Constructor without parameters (maximum capacity, default).
    public StackArrayImpl() {
        this(10);
    }

    // Constructor with a parameter.
    public StackArrayImpl(int max) {
        stackArray = (E[])new Object[max];
        capacity = max;
        top = -1;
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
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[top];
    }

    // Delete the top of the stack item, if any.
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E topElement = stackArray[top];
        stackArray[top--] = null;
        return topElement;
    }

    // Add an item to the stack, if any.
    public void push(E elem) {
        if (isFull()) {
            throw new IndexOutOfBoundsException();
        }
        stackArray[++top] = elem;
    }

    // Getter for the number of items in the container.
    public int size() {
        return top + 1;
    }

    // Method overwriting Object.toString().
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            sb.append(stackArray[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Retrieves the items in the container.
    public Iterator<E> values() {
        return new StackArrayIterator();
    }

    private class StackArrayIterator implements Iterator<E> {
        private int index = top;

        public boolean hasNext() {
            return index >= 0;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return stackArray[index--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
