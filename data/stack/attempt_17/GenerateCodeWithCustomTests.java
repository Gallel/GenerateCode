
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    
    // Attribute declaration
    private int capacity;
    private int size;
    private E[] elements;
    private int top;

    // Constructor without parameters (maximum capacity, default)
    public StackArrayImpl() {
        this(DEFAULT_SIZE);
    }

    // Constructor with a parameter
    public StackArrayImpl(int max) {
        if (max <= 0) throw new IllegalArgumentException();
        capacity = max;
        size = 0;
        elements = (E[]) new Object[capacity];
        top = -1;
    }

    // Method to check if the container is empty
    public boolean isEmpty() {
        return (size == 0);
    }

    // Method to check if the container is full
    public boolean isFull() {
        return (size == capacity);
    }

    // Getter for the last item added to the stack, if any
    public E peek() {
        if (isEmpty()) throw new EmptyContainerException();
        return elements[top];
    }

    // Delete the top of the stack item, if any
    public E pop() {
        if (isEmpty()) throw new EmptyContainerException();
        E element = elements[top];
        elements[top] = null;
        top = top - 1;
        size = size - 1;
        return element;
    }

    // Add an item to the stack, if any
    public void push(E elem) {
        if (isFull()) throw new FullContainerException();
        top = top + 1;
        elements[top] = elem;
        size = size + 1;
    }

    // Getter for the number of items in the container
    public int size() {
        return size;
    }

    // Method overwriting Object.toString ()
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = top; i >= 0; i--) {
            sb.append(elements[i].toString());
            if (i != 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Retrieves the items in the container
    public Iterator<E> values() {
        return new Iterator<E>() {
            private int currentIndex = top;

            public boolean hasNext() {
                return (currentIndex >= 0);
            }

            public E next() {
                E elem = elements[currentIndex];
                currentIndex = currentIndex - 1;
                return elem;
            }
        };
    }
}
