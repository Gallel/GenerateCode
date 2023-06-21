
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private int maxCapacity;
    private int currentSize;
    private int firstIdx;
    private int lastIdx; 
    private E[] elements;

    public QueueArrayImpl() {
        this.maxCapacity = 100;
        this.currentSize = 0;
        this.firstIdx = 0;
        this.lastIdx = -1;
        this.elements = (E[]) new Object[maxCapacity];
    }

    public QueueArrayImpl(int max) {
        this.maxCapacity = max;
        this.currentSize = 0;
        this.firstIdx = 0;
        this.lastIdx = -1;
        this.elements = (E[]) new Object[max];
    }

    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        lastIdx = (lastIdx + 1) % maxCapacity;
        elements[lastIdx] = elem;
        currentSize++;
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public boolean isFull() {
        return (currentSize == maxCapacity);
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return elements[firstIdx];
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elem = elements[firstIdx];
        elements[firstIdx] = null;
        firstIdx = (firstIdx + 1) % maxCapacity;
        currentSize--;
        return elem;
    }

    public int size() {
        return currentSize;
    }

    public String toString() {
        String msg = "[";
        int idx = firstIdx;
        for (int i=0; i<currentSize; i++) {
            msg += elements[idx];
            if (i < currentSize-1) {
                msg += ", ";
            }
            idx = (idx + 1) % maxCapacity;
        }
        msg += "]";
        return msg;
    }

    public Iterator<E> values() {
        return new QueueArrayIterator<E>(this, firstIdx);
    }

}

class QueueArrayIterator<E> implements Iterator<E> {

    private QueueArrayImpl<E> queue;
    private int currentIdx;
    private int visited;

    public QueueArrayIterator(QueueArrayImpl<E> q, int start) {
        this.queue = q;
        this.currentIdx = start;
        this.visited = 0;
    }

    public boolean hasNext() {
        return (visited < queue.size());
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E elem = queue.elements[currentIdx];
        currentIdx = (currentIdx + 1) % queue.maxCapacity;
        visited++;
        return elem;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}

