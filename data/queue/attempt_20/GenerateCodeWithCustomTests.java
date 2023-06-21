
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private int maxCapacity;
    private int size;
    private E[] elements;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        maxCapacity = 100;
        size = 0;
        elements = (E[]) new Object[maxCapacity];
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        maxCapacity = max;
        size = 0;
        elements = (E[]) new Object[maxCapacity];
    }

    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        elements[size] = elem;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxCapacity;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return elements[0];
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elem = elements[0];
        for (int i = 1; i < size; i++) {
            elements[i-1] = elements[i];
        }
        elements[size-1] = null;
        size--;
        return elem;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size-1; i++) {
            sb.append(elements[i].toString());
            sb.append(", ");
        }
        sb.append(elements[size-1]);
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int current = 0;

            public boolean hasNext() {
                return current < size;
            }

            public E next() {
                E elem = elements[current];
                current++;
                return elem;
            }
        };
    }
}
