
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private int maxCapacity;
    private int currentSize;
    private E[] stackArray;

    // Constructor without parameters (maximum capacity, default).
    public StackArrayImpl() {
        maxCapacity = 10;
        currentSize = 0;
        stackArray = (E[]) new Object[maxCapacity];
    }

    // Constructor with a parameter.
    public StackArrayImpl(int max) {
        maxCapacity = max;
        currentSize = 0;
        stackArray = (E[]) new Object[maxCapacity];
    }

    // Method to check if the container is empty.
    public boolean isEmpty() {
        return currentSize == 0;
    }

    // Method to check if the container is full.
    public boolean isFull() {
        return currentSize == maxCapacity;
    }

    // Getter for the last item added to the stack, if any.
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[currentSize-1];
    }

    // Delete the top of the stack item, if any.
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E element = stackArray[--currentSize];
        stackArray[currentSize] = null;
        return element;
    }

    // Add an item to the stack, if any.
    public void push(E elem) {
        if (isFull()) {
            return;
        }
        stackArray[currentSize++] = elem;
    }

    // Getter for the number of items in the container.
    public int size() {
        return currentSize;
    }

    // Method overwriting Object.toString().
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < currentSize; i++) {
            sb.append(stackArray[i].toString());
            if (i != currentSize-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Retrieves the items in the container.
    public Iterator<E> values() {
        List<E> list = new ArrayList<E>();
        for (int i = 0; i < currentSize; i++) {
            list.add(stackArray[i]);
        }
        return list.iterator();
    }
}
