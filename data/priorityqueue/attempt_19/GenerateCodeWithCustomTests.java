
import java.util.Comparator;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class PriorityQueue<E> extends Object implements Queue<E> {

    private Object[] queue;
    private int size;
    private int max;
    private Comparator<E> cmp;

    public PriorityQueue() {
        this(Integer.MAX_VALUE, null);
    }

    public PriorityQueue(int max) {
        this(max, null);
    }

    public PriorityQueue(Comparator<E> cmp) {
        this(Integer.MAX_VALUE, cmp);
    }

    public PriorityQueue(int max, Comparator<E> cmp) {
        this.queue = new Object[max + 1];
        this.size = 0;
        this.max = max;
        this.cmp = cmp;
    }

    public void add(E elem) {
        if (size == max) {
            throw new FullContainerException();
        }
        int i = size;
        queue[++size] = elem;
        while (i > 1 && compare(queue[i], queue[i / 2]) > 0) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E result = (E) queue[1];
        queue[1] = queue[size--];
        siftDown(1);
        return result;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return (E) queue[1];
    }

    private void siftDown(int position) {
        int i = position;
        boolean done = false;
        while (2 * i <= size && !done) {
            int child = 2 * i;
            if (child < size && compare(queue[child + 1], queue[child]) > 0) {
                child++;
            }
            if (compare(queue[i], queue[child]) >= 0) {
                done = true;
            } else {
                swap(i, child);
                i = child;
            }
        }
    }

    private void swap(int i, int j) {
        E temp = (E) queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == max;
    }

    public PriorityQueue<E> clone() {
        try {
            PriorityQueue<E> newQueue = (PriorityQueue<E>) super.clone();
            newQueue.queue = queue.clone();
            return newQueue;
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }

    public Iterator<E> values() {
        return Traversal.inOrder(new Tree<E>() {
            public E value(Position<E> p) {
                return p.getElement();
            }
            public Position<E> left(Position<E> p) {
                return position(2 * (index(p)));
            }
            public Position<E> right(Position<E> p) {
                return position(2 * (index(p)) + 1);
            }
            public Position<E> root() {
                return position(1);
            }
        });
    }

    protected int compare(Object pos1, Object pos2) {
        if (cmp == null) {
            return ((Comparable<E>) pos1).compareTo((E) pos2);
        } else {
            return cmp.compare((E) pos1, (E) pos2);
        }
    }

    protected Position<E> nextLastPosition(E elem) {
        add(elem);
        return position(size);
    }

    protected Position<E> deleteLastPosition() {
        Position<E> p = position(size);
        queue[size--] = null;
        return p;
    }

    protected void siftUp(Position<E> newPosition) {
        int i = index(newPosition);
        while (i > 1 && compare(queue[i], queue[i / 2]) > 0) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    private int index(Position<E> p) {
        ArrayPosition<E> ap = (ArrayPosition<E>) p;
        return ((Integer) ap.getIndex()).intValue();
    }

    private Position<E> position(int i) {
        return new ArrayPosition<E>(queue, i);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('[');
        Iterator<E> it = values();
        while (it.hasNext()) {
            s.append(it.next());
            if (it.hasNext()) {
                s.append(", ");
            }
        }
        s.append(']');
        return s.toString();
    }

}

class ArrayPosition<T> implements Position<T> {

    Object[] array;
    int index;

    public ArrayPosition(Object[] array, int index) {
        this.array = array;
        this.index = index;
    }

    public T getElement() {
        return (T) array[index];
    }

    public void setElement(T o) {
        array[index] = o;
    }

    public int getIndex() {
        return index;
    }

    public String toString() {
        return "[" + index + "]";
    }

}
