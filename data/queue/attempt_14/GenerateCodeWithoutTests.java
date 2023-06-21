
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private E[] elements;
    private int headIndex = 0;
    private int tailIndex = 0;
    private int size = 0;

    public QueueArrayImpl() {
        this.elements = (E[]) new Object[10];
    }

    public QueueArrayImpl(int max) {
        this.elements = (E[]) new Object[max];
    }

    public void add(E elem) {
        if(size == elements.length) {
            throw new IllegalStateException();
        }
        elements[tailIndex] = elem;
        tailIndex = (tailIndex + 1) % elements.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return elements[headIndex];
    }

    public E poll() {
        if(isEmpty()) {
            return null;
        }
        E elem = elements[headIndex];
        elements[headIndex] = null;
        headIndex = (headIndex + 1) % elements.length;
        size--;
        return elem;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String result = "[";
        for(E elem : this) {
            result += elem + ", ";
        }
        if(result.length() > 1) {
            result = result.substring(0, result.length() - 2);
        }
        result += "]";
        return result;
    }

    public Iterator<E> values() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private int currentIndex = headIndex;
        private int count = size;

        public boolean hasNext() {
            return count > 0;
        }

        public E next() {
            E elem = elements[currentIndex];
            currentIndex = (currentIndex + 1) % elements.length;
            count--;
            return elem;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
