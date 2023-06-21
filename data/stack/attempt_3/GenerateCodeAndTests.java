
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] stackArray;
    private int topIndex;
    private int maxSize;

    // Constructor without parameters (maximum capacity, default).
    public StackArrayImpl() {
        this(100); // Default maximum capacity
    }

    // Constructor with a parameter.
    public StackArrayImpl(int max) {
        maxSize = max;
        stackArray = (E[]) new Object[maxSize];
        topIndex = -1;
    }

    // Method to check if the container is empty.
    public boolean isEmpty() {
        return topIndex == -1;
    }

    // Method to check if the container is full.
    public boolean isFull() {
        return topIndex == maxSize - 1;
    }

    // Getter for the last item added to the stack, if any.
    public E peek() {
        if (!isEmpty()) {
            return stackArray[topIndex];
        } else {
            return null;
        }
    }

    // Delete the top of the stack item, if any.
    public E pop() {
        if (!isEmpty()) {
            E elem = stackArray[topIndex];
            topIndex--;
            return elem;
        } else {
            return null;
        }
    }

    // Add an item to the stack if it's not full.
    public void push(E elem) {
        if (!isFull()) {
            topIndex++;
            stackArray[topIndex] = elem;
        }
    }

    // Getter for the number of items in the container.
    public int size() {
        return topIndex + 1;
    }

    // Method overwriting Object.toString ().
    public String toString() {
        String s = "";
        for (int i = topIndex; i >= 0; i--) {
            s += stackArray[i].toString() + " ";
        }
        return s;
    }

    // Retrieves the items in the container.
    public Iterator<E> values() {
        List<E> list = new ArrayList<E>();
        for (int i = topIndex; i >= 0; i--) {
            list.add(stackArray[i]);
        }
        return list.iterator();
    }
}
