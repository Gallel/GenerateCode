
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private int maxCapacity;
    private int size;
    private int frontIndex;
    private int rearIndex;
    private E[] queueArray;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        this.maxCapacity = 10;
        this.size = 0;
        this.frontIndex = 0;
        this.rearIndex = -1;
        this.queueArray = (E[]) new Object[maxCapacity];
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.maxCapacity = max;
        this.size = 0;
        this.frontIndex = 0;
        this.rearIndex = -1;
        this.queueArray = (E[]) new Object[maxCapacity];
    }

    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rearIndex = (rearIndex + 1) % maxCapacity;
        queueArray[rearIndex] = elem;
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
        return queueArray[frontIndex];
    }

    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E elem = queueArray[frontIndex];
        queueArray[frontIndex] = null;
        frontIndex = (frontIndex + 1) % maxCapacity;
        size--;
        return elem;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            sb.append(queueArray[(frontIndex + i) % maxCapacity]);
            if (i != size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            E elem = queueArray[(frontIndex + currentIndex) % maxCapacity];
            currentIndex++;
            return elem;
        }
    }

}
