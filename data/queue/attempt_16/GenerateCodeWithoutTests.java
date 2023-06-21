
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private E[] elements;
    private int maxCapacity;
    private int size;

    public QueueArrayImpl() {
        this.maxCapacity = 10;
        elements = (E[]) new Object[maxCapacity];
    }

    public QueueArrayImpl(int max) {
        this.maxCapacity = max;
        elements = (E[]) new Object[maxCapacity];
    }

    public void add(E elem) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        elements[size++] = elem;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxCapacity;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[0];
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E elem = elements[0];
        size--;
        for (int i = 0; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size] = null;
        return elem;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return Arrays.asList(Arrays.copyOf(elements, size)).iterator();
    }
}
