
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private E[] elements;
    private int first;
    private int size;
    private int maxCapacity;

    public QueueArrayImpl() {
        this.maxCapacity = 100;
        this.first = 0;
        this.size = 0;
        this.elements = (E[]) new Object[maxCapacity];
    }

    public QueueArrayImpl(int max) {
        this.maxCapacity = max;
        this.first = 0;
        this.size = 0;
        this.elements = (E[]) new Object[maxCapacity];
    }

    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        int index = (first + size) % maxCapacity;
        elements[index] = elem;
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
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[first];
    }

    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E element = elements[first];
        elements[first] = null;
        size--;
        first = (first + 1) % maxCapacity;
        return element;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int index = first;
        for (int i = 0; i < size; i++) {
            sb.append(elements[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
            index = (index + 1) % maxCapacity;
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            public boolean hasNext() {
                return currentIndex < size && elements[currentIndex] != null;
            }

            public E next() {
                return elements[currentIndex++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}