
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private int maxCapacity;
    private int size;
    private E[] stackArray;

    public StackArrayImpl() {
        this.maxCapacity = 10;
        this.size = 0;
        this.stackArray = (E[]) new Object[this.maxCapacity];
    }

    public StackArrayImpl(int max) {
        this.maxCapacity = max;
        this.size = 0;
        this.stackArray = (E[]) new Object[this.maxCapacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxCapacity;
    }

    public E peek() {
        if (size == 0) {
            return null;
        }
        return stackArray[size-1];
    }

    public E pop() {
        if (size == 0) {
            return null;
        }
        E top = stackArray[size-1];
        stackArray[size-1] = null;
        size--;
        return top;
    }

    public void push(E elem) {
        if (isFull()) {
            return;
        }
        stackArray[size] = elem;
        size++;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size-1; i++) {
            sb.append(stackArray[i]).append(", ");
        }
        if (size > 0) {
            sb.append(stackArray[size-1]);
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return Arrays.asList(Arrays.copyOfRange(stackArray, 0, size)).iterator();
    }
}
