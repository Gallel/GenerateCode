
public class PriorityQueue<E> extends Object implements Queue<E> {
    private E[] container;
    private int capacity;
    private Comparator<E> comparator;
    private int size;
    
    public PriorityQueue() {
        this.container = (E[]) new Object[10];
        this.capacity = 10;
        this.comparator = null;
        this.size = 0;
    }
    
    public PriorityQueue(int max) {
        this.container = (E[]) new Object[max];
        this.capacity = max;
        this.comparator = null;
        this.size = 0;
    }
    
    public PriorityQueue(int max, Comparator<E> comparator) {
        this.container = (E[]) new Object[max];
        this.capacity = max;
        this.comparator = comparator;
        this.size = 0;
    }
    
    public PriorityQueue(Comparator<E> comparator) {
        this.container = (E[]) new Object[10];
        this.capacity = 10;
        this.comparator = comparator;
        this.size = 0;
    }
    
    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        container[size] = elem;
        siftUp(nextLastPosition(elem));
        size ++;
    }
    
    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E result = peek();
        container[0] = container[size - 1];
        size --;
        siftDown(0);
        return result;
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return container[0];
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    
    protected void siftDown(int pos) {
        int child = pos * 2 + 1;
        int maxIndex = pos;
        if (child < size && compare(container[child], container[maxIndex]) > 0) {
            maxIndex = child;
        }
        if (child + 1 < size && compare(container[child + 1], container[maxIndex]) > 0) {
            maxIndex = child + 1;
        }
        if (maxIndex != pos) {
            swap(pos, maxIndex);
            siftDown(maxIndex);
        }
    }
    
    protected void siftUp(int newPosition) {
        if (newPosition > 0) {
            int parent = (newPosition - 1) / 2;
            if (compare(container[parent], container[newPosition]) < 0) {
                swap(parent, newPosition);
                siftUp(parent);
            }
        }
    }
    
    protected void swap(int pos1, int pos2) {
        E temp = container[pos1];
        container[pos1] = container[pos2];
        container[pos2] = temp;
    }
    
    protected int compare(E elem1, E elem2) {
        if (comparator != null) {
            return comparator.compare(elem1, elem2);
        }
        return ((Comparable<E>) elem1).compareTo(elem2);
    }
    
    protected int compare(Position<E> pos1, Position<E> pos2) {
        return compare(pos1.getElement(), pos2.getElement());
    }
    
    protected Position<E> deleteLastPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected Position<E> nextLastPosition(E elem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected Traversal<Position<E>> positions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public PriorityQueue<E> clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Iterator<E> values() {
        return new PriorityQueueIterator();
    }
    
    private class PriorityQueueIterator implements Iterator<E> {
        int currentIndex = 0;
        
        @Override
        public boolean hasNext() {
            return currentIndex < size && container[currentIndex] != null;
        }

        @Override
        public E next() {
            E item = container[currentIndex];
            currentIndex ++;
            return item;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i ++) {
            sb.append(container[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
