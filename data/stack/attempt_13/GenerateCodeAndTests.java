
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] elements;
    private int size;
    private int maxCapacity;

    public StackArrayImpl() {
        this(100); // default capacity
    }

    public StackArrayImpl(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Max capacity must be greater than 0.");
        }
        this.maxCapacity = max;
        this.elements = (E[]) new Object[max];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxCapacity;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return elements[size - 1];
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        E elem = elements[--size];
        elements[size] = null;
        return elem;
    }

    public void push(E elem) {
        if (isFull()) {
            throw new RuntimeException("Stack is full.");
        }
        elements[size++] = elem;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int index = 0;

            public boolean hasNext() {
                return index < size;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[index++];
            }
        };
    }
}
