
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private E[] data;
    private int front, back, size, max;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        max = 1000;
        front = 0;
        back = -1;
        size = 0;
        data = (E[]) new Object[max];
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.max = max;
        front = 0;
        back = -1;
        size = 0;
        data = (E[]) new Object[max];
    }

    public void add(E elem) {
        if (isFull()) throw new RuntimeException("Queue is full");
        back++;
        data[back % max] = elem;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == max;
    }

    public E peek() {
        if (isEmpty()) return null;
        return data[front];
    }

    public E poll() {
        if (isEmpty()) return null;
        E elem = data[front];
        front++;
        if (front == max) front = 0;
        size--;
        return elem;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[(front + i) % max]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private int i = front;

        public boolean hasNext() {
            return i <= back;
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return data[i++ % max];
        }
    }
}
