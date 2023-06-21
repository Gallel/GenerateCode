
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private int max;
    private int size;
    private E[] elements;

    public QueueArrayImpl() {
        this.max = Integer.MAX_VALUE-1;
        this.size = 0;
        this.elements = (E[]) new Object[this.max];
    }

    public QueueArrayImpl(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Maximum capacity must be greater than zero.");
        }
        this.max = max;
        this.size = 0;
        this.elements = (E[]) new Object[this.max];
    }

    public void add(E elem) {
        if (this.isFull()) {
            throw new IllegalStateException("Queue is full.");
        }
        this.elements[size] = elem;
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.max;
    }

    public E peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        return this.elements[0];
    }

    public E poll() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        E elem = this.elements[0];
        for (int i = 1; i < this.size; i++) {
            this.elements[i-1] = this.elements[i];
        }
        this.elements[this.size-1] = null;
        this.size--;
        return elem;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.elements[i]);
            if (i != this.size-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new QueueArrayIterator();
    }

    private class QueueArrayIterator implements Iterator<E> {

        private int current;

        public QueueArrayIterator() {
            this.current = 0;
        }

        public boolean hasNext() {
            return this.current < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E elem = elements[current];
            this.current++;
            return elem;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
