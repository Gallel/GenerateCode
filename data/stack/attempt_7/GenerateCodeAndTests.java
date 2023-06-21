
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] stackArray;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        this.stackArray = (E[]) new Object[10];
        this.currentSize = 0;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.stackArray = (E[]) new Object[max];
        this.currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == stackArray.length;
    }

    public E peek() {
        if (currentSize == 0) {
            return null;
        }
        return stackArray[currentSize - 1];
    }

    public E pop() {
        if (currentSize == 0) {
            return null;
        }
        E result = stackArray[currentSize - 1];
        stackArray[currentSize - 1] = null;
        currentSize--;
        return result;
    }

    public void push(E elem) {
        if (currentSize == stackArray.length) {
            return;
        }
        stackArray[currentSize] = elem;
        currentSize++;
    }

    public int size() {
        return currentSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < currentSize; i++) {
            sb.append(stackArray[i].toString());
            if (i < currentSize - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < currentSize; i++) {
            list.add(stackArray[i]);
        }
        return list.iterator();
    }
}
