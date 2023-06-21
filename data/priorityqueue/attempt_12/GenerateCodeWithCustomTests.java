
import java.util.Comparator;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.adt.nonlinear.Position;
import edu.uoc.ds.adt.nonlinear.PriorityQueue;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;

public class PriorityQueue<E> extends Object implements Queue<E> {

    private E[] items;
    private int size;
    private Comparator<E> cmp;

    public PriorityQueue() {
        this(100, null);
    }

    public PriorityQueue(int max) {
        this(max, null);
    }

    public PriorityQueue(Comparator<E> comparator) {
        this(100, comparator);
    }

    public PriorityQueue(int max, Comparator<E> comparator) {
        items = (E[]) new Comparable[max+1];
        size = 0;
        cmp = (comparator == null) ? new DefaultComparator<E>() : comparator;
    }

    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        Position<E> newPosition = nextLastPosition(elem);
        siftUp(newPosition);
        size++;
    }

    public PriorityQueue<E> clone() {
        PriorityQueue<E> copy = new PriorityQueue<>(items.length - 1, cmp);
        copy.size = size;
        for (int i = 1; i < items.length; i++) {
            copy.items[i] = items[i];
        }
        return copy;
    }

    protected int compare(Position<E> pos1, Position<E> pos2) {
        return cmp.compare(pos1.getElement(), pos2.getElement());
    }

    protected Position<E> deleteLastPosition() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        Position<E> last = new InnerPosition<>(items[size], size);
        items[size] = null;
        size--;
        return last;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == items.length - 1;
    }

    protected Position<E> nextLastPosition(E elem) {
        Position<E> pos = new InnerPosition<>(elem, size + 1);
        items[size + 1] = elem;
        return pos;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return items[1];
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E min = items[1];
        Position<E> lastPosition = deleteLastPosition();
        items[1] = lastPosition.getElement();
        siftDown(new InnerPosition<>(items[1], 1));
        size--;
        return min;
    }

    public Traversal<Position<E>> positions() {
        return new PriorityQueueTraversal<>(this);
    }

    protected void siftDown(Position<E> pos) {
        int j;
        boolean swap = true;
        while (swap) {
            swap = false;
            if (2 * pos.getIndex() <= size) {
                j = 2 * pos.getIndex();
                if (j < size && compare(new InnerPosition<>(items[j], j), new InnerPosition<>(items[j + 1], j + 1)) > 0) {
                    j++;
                }
                if (compare(pos, new InnerPosition<>(items[j], j)) > 0) {
                    swap = true;
                    swap(pos.getIndex(), j);
                    pos = new InnerPosition<>(items[j], j);
                }
            }
        }
    }

    protected void siftUp(Position<E> newPosition) {
        int i = newPosition.getIndex();
        boolean swap = true;
        while (swap) {
            swap = false;
            if (i > 1 && compare(newPosition, new InnerPosition<>(items[i / 2], i / 2)) < 0) {
                swap(i / 2, i);
                i /= 2;
                swap = true;
            }
        }
    }

    public int size() {
        return size;
    }

    public Iterator<E> values() {
        return new PriorityQueueIterator<>(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Traversal<Position<E>> pos = positions();
        while (pos.hasNext()) {
            result.append(pos.next().getElement());
            if (pos.hasNext()) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    private void swap(int i, int j) {
        E temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private static class InnerPosition<E> implements Position<E> {

        private final E element;
        private final int index;

        public InnerPosition(E element, int index) {
            this.element = element;
            this.index = index;
        }

        public E getElement() {
            return element;
        }

        public int getIndex() {
            return index;
        }
    }

    private static class PriorityQueueIterator<E> implements Iterator<E> {

        private final PriorityQueue<E> pq;
        private final Traversal<Position<E>> pos;

        public PriorityQueueIterator(PriorityQueue<E> pq) {
            this.pq = pq;
            pos = pq.positions();
        }

        public boolean hasNext() {
            return pos.hasNext();
        }

        public E next() {
            return pos.next().getElement();
        }
    }

    private static class PriorityQueueTraversal<E> implements Traversal<Position<E>> {

        private final PriorityQueue<E> pq;
        private int i;

        public PriorityQueueTraversal(PriorityQueue<E> pq) {
            this.pq = pq;
            this.i = 1;
        }

        public boolean hasNext() {
            return i <= pq.size();
        }

        public Position<E> next() {
            return new InnerPosition<>(pq.items[i], i++);
        }
    }

    private static final class DefaultComparator<E extends Comparable<? super E>> implements Comparator<E> {

        public int compare(E o1, E o2) {
            return o1.compareTo(o2);
        }
    }

}
