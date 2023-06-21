
public class QueueArrayImpl<E> implements Queue<E>, FiniteContainer<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int frontIndex;
    private int backIndex;
    private int size;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.frontIndex = 0;
        this.backIndex = -1;
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.elements = (E[]) new Object[max];
        this.frontIndex = 0;
        this.backIndex = -1;
        this.size = 0;
    }

    @Override
    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        backIndex = (backIndex + 1) % elements.length;
        elements[backIndex] = elem;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == elements.length;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[frontIndex];
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E element = elements[frontIndex];
        elements[frontIndex] = null;
        frontIndex = (frontIndex + 1) % elements.length;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> values() {
        return new QueueArrayIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int index = (frontIndex + i) % elements.length;
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(elements[index]);
        }
        sb.append("]");
        return sb.toString();
    }

    private class QueueArrayIterator implements Iterator<E> {
        private int current;

        public QueueArrayIterator() {
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index = (frontIndex + current) % elements.length;
            current++;
            return elements[index];
        }
    }
}
