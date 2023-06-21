
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private int max;
    private E[] data;
    private int head;
    private int tail;
    private int count;

    public QueueArrayImpl() {
        this(100);
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.max = max;
        data = (E[]) new Object[max];
        head = 0;
        tail = -1;
        count = 0;
    }

    public void add(E elem) {
        if (isFull())
            throw new FullContainerException();

        tail = (tail + 1) % max;
        data[tail] = elem;
        count++;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == max;
    }

    public E peek() {
        if (isEmpty())
            throw new EmptyContainerException();

        return data[head];
    }

    public E poll() {
        if (isEmpty())
            throw new EmptyContainerException();

        E elem = data[head];
        head = (head + 1) % max;
        count--;

        return elem;
    }

    public int size() {
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            if (i > 0)
                sb.append(", ");
            sb.append(data[(head + i) % max]);
        }
        sb.append("]");

        return sb.toString();
    }

    public Iterator<E> values() {
        return new QueueArrayValueIterator();
    }

    private class QueueArrayValueIterator implements Iterator<E> {
        private int current;

        public QueueArrayValueIterator() {
            current = 0;
        }

        public boolean hasNext() {
            return current < count;
        }

        public E next() {
            E elem = data[(head + current) % max];
            current++;

            return elem;
        }
    }
}
