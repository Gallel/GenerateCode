
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private int maxCapacity;
    private E[] elements;
    private int topIndex;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        this.maxCapacity = 10;
        this.elements = (E[]) new Object[this.maxCapacity];
        this.topIndex = -1;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.maxCapacity = max;
        this.elements = (E[]) new Object[this.maxCapacity];
        this.topIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return (topIndex == -1);
    }

    @Override
    public boolean isFull() {
        return (topIndex == maxCapacity - 1);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[topIndex];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E elem = elements[topIndex];
        elements[topIndex] = null;
        topIndex--;
        return elem;
    }

    @Override
    public void push(E elem) {
        if (!isFull()) {
            topIndex++;
            elements[topIndex] = elem;
        }
    }

    @Override
    public int size() {
        return topIndex + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= topIndex; i++) {
            sb.append(elements[i]);
            if (i < topIndex) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> values() {
        return Arrays.stream(elements).filter(Objects::nonNull).iterator();
    }
}
