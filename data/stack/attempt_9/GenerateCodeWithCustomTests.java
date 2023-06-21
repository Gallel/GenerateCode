
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] elements;
    private int size;
    private final int MAX_CAPACITY;

    public StackArrayImpl() {
        this(MAX_SIZE);
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        if (max <= 0) throw new IllegalArgumentException("Maximum capacity must be greater than zero");
        MAX_CAPACITY = max;
        elements = (E[]) new Object[MAX_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAX_CAPACITY;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return elements[size - 1];
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E elem = elements[size - 1];
        elements[--size] = null;
        return elem;
    }

    @Override
    public void push(E elem) {
        if (isFull()) throw new FullContainerException();
        elements[size++] = elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i].toString());
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> values() {
        return new Iterator<E>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException("No more elements in iteration");
                return elements[i++];
            }

        };
    }
}
