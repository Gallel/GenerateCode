
import java.util.Comparator;
import edu.uoc.ds.adt.nonlinear.Position;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class PriorityQueue<E> extends Object implements Queue<E> {

    private Comparator<E> comparator;
    private int max;
    private E[] queue;
    private int size;

    public PriorityQueue() {
        this(10, null);
    }

    public PriorityQueue(int max) {
        this(max, null);
    }

    public PriorityQueue(int max, Comparator<E> comparator) {
        this.max = max;
        this.queue = (E[]) new Object[max+1];
        this.comparator = comparator;
        this.size = 0;
    }

    public PriorityQueue(Comparator<E> comparator) {
        this(10, comparator);
    }

    public void add(E elem) {
        if(isFull()) {
            throw new FullContainerException();
        }
        queue[++size] = elem;
        siftUp(size);
    }

    public PriorityQueue<E> clone() {
        PriorityQueue<E> copy = new PriorityQueue<>(max, comparator);
        copy.size = size;
        copy.queue = queue.clone();
        return copy; 
    }

    protected int compare(Position<E> pos1, Position<E> pos2) {
        E elem1 = pos1.getElement();
        E elem2 = pos2.getElement();
        if (comparator == null) {
            return ((Comparable<E>) elem1).compareTo(elem2);
        } else {
            return comparator.compare(elem1, elem2);
        }
    }

    protected Position<E> deleteLastPosition() {
        Position<E> lastPos = new Position<>(queue[size]);
        queue[size--] = null;
        return lastPos;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == max;
    }

    protected Position<E> nextLastPosition(E elem) {
        Position<E> pos = new Position<>(elem);
        queue[++size] = elem;
        return pos;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        } else {
            return queue[1];
        }
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        } else {
            E root = queue[1];
            queue[1] = queue[size];
            siftDown(new Position<>(queue[1]), 1);
            queue[size--] = null;
            return root;
        }
    }

    public Traversal<Position<E>> positions() {
        return null; //not implemented
    }

    protected void siftDown(Position<E> posAOrdenar, int position) {
        int leftIndex = 2 * position;
        int rightIndex = 2 * position + 1;
        int selectedIndex;
        if (rightIndex > size) {
            if (leftIndex > size) {
                return;
            } else {
                selectedIndex = leftIndex;
            }
        } else {
            if (compare(new Position<>(queue[leftIndex]), new Position<>(queue[rightIndex])) <= 0) {
                selectedIndex = leftIndex;
            } else {
                selectedIndex = rightIndex;
            }
        }
        if (compare(posAOrdenar, new Position<>(queue[selectedIndex])) > 0) {
            E temp = queue[position];
            queue[position] = queue[selectedIndex];
            queue[selectedIndex] = temp;
            siftDown(posAOrdenar, selectedIndex);
        } else {
            return;
        }
    }

    protected void siftUp(int newPosition) {
        if (newPosition <= 1) {
            return;
        }
        int parentIndex = newPosition / 2;
        if (compare(new Position<>(queue[newPosition]), new Position<>(queue[parentIndex])) < 0) {
            E temp = queue[parentIndex];
            queue[parentIndex] = queue[newPosition];
            queue[newPosition] = temp;
            siftUp(parentIndex);
        } else {
            return;
        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (!isEmpty()) {
            sb.append(queue[1]);
            for (int i = 2; i <= size; i++) {
                sb.append(", ");
                sb.append(queue[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new PriorityQueueIterator();
    }

    private class PriorityQueueIterator implements Iterator<E> {

        private int currentPosition;

        public PriorityQueueIterator() {
            this.currentPosition = 1;
        }

        public boolean hasNext() {
            return currentPosition <= size;
        }

        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            return queue[currentPosition++];
        }
    }
}
