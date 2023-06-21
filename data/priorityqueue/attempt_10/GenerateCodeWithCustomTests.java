
import java.util.Comparator;
import java.util.Iterator;
import edu.uoc.ds.adt.nonlinear.Position;
import edu.uoc.ds.adt.nonlinear.Queue;

public class PriorityQueue<E> extends Object implements Queue<E> {

    private E[] heap;
    private int size;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public PriorityQueue() {
        this(DEFAULT_CAPACITY, null);
    }

    public PriorityQueue(int max) {
        this(max, null);
    }

    public PriorityQueue(int max, Comparator<E> comparator) {
        heap = (E[]) new Object[max + 1];
        size = 0;
        this.comparator = comparator;
    }

    public PriorityQueue(Comparator<E> comparator) {
        this(DEFAULT_CAPACITY, comparator);
    }

    @Override
    public void add(E elem) {
        if (isFull())
            throw new FullContainerException();

        size++;
        heap[size] = elem;
        siftUp(nextLastPosition(elem));
    }

    private Position<E> newLastPosition() {
        return new Position<E>(heap, size + 1);
    }

    // Returns the father position of given position
    private Position<E> parent(Position<E> pos) {
        return new Position<E>(heap, pos.getIntIndex() / 2);
    }

    // Returns the left child position of given position
    private Position<E> leftChild(Position<E> pos) {
        return new Position<E>(heap, pos.getIntIndex() * 2);
    }

    // Returns the right child position of given position
    private Position<E> rightChild(Position<E> pos) {
        return new Position<E>(heap, pos.getIntIndex() * 2 + 1);
    }

    // Returns the minimum element, i.e. the root
    @Override
    public E peek() {
        if (isEmpty())
            throw new EmptyContainerException();

        return heap[1];
    }

    // Removes and returns minimum element
    @Override
    public E poll() {
        if (isEmpty())
            throw new EmptyContainerException();

        E minElem = heap[1];
        heap[1] = heap[size];
        size--;
        siftDown(new Position<E> (heap, 1));
        return minElem;
    }

    // Returns true if the queue is full
    public boolean isFull() {
        return size == heap.length - 1;
    }

    // Returns true if the queue is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the size of the queue
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> values() {
        return new PriorityQueueIterator<E>(heap, 1, size);
    }

    @Override
    public PriorityQueue<E> clone() {
        PriorityQueue<E> cloned = new PriorityQueue<>(heap.length - 1, comparator);
        for (int i = 1; i <= size; i++) {
            cloned.heap[i] = heap[i];
        }

        cloned.size = size;
        return cloned;
    }

    // Swaps the values of two positions
    private void swap(Position<E> p1, Position<E> p2) {
        E temp = p1.getValue();
        p1.setValue(p2.getValue());
        p2.setValue(temp);
    }

    // Sifts the element up until it is at the correct position.
    private void siftUp(Position<E> newPosition) {
        Position<E> father = parent(newPosition);

        while (newPosition.getIntIndex() > 1 && compare(newPosition, father) < 0) {
            swap(newPosition, father);
            newPosition = father;
            father = parent(newPosition);
        }
    }

    // Sifts the element down until it is at the correct position.
    private void siftDown(Position<E> currentPosition) {
        Position<E> min = currentPosition;
        Position<E> leftChild = leftChild(currentPosition);
        Position<E> rightChild = rightChild(currentPosition);

        if (leftChild.getIntIndex() <= size && compare(leftChild, min) < 0) {
            min = leftChild;
        }

        if (rightChild.getIntIndex() <= size && compare(rightChild, min) < 0) {
            min = rightChild;
        }

        if (min != currentPosition) {
            swap(currentPosition, min);
            siftDown(min);
        }
    }

    // Compares the elements of two given positions and returns the result of that comparison
    protected int compare(Position<E> p1, Position<E> p2) {
        if (comparator != null) {
            return comparator.compare(p1.getValue(), p2.getValue());
        } else {
            return ((Comparable<E>) p1.getValue()).compareTo(p2.getValue());
        }
    }

    // Returns the next position to use
    protected Position<E> nextLastPosition(E elem) {
        Position<E> newPosition = newLastPosition();
        while (newPosition.getIntIndex() > 1 && compare(newPosition, parent(newPosition)) < 0) {
            newPosition.setValue(parent(newPosition).getValue());
            newPosition = parent(newPosition);
        }
        newPosition.setValue(elem);
        return newPosition;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            stringBuilder.append(heap[i]).append(" ");
        }
        return stringBuilder.toString();
    }

    // Iterator over the heap
    private static class PriorityQueueIterator<T> implements Iterator<T> {
        private T[] heap;
        private int currentIndex;
        private int lastItemIndex;

        PriorityQueueIterator(T[] heap, int currentIndex, int lastItemIndex) {
            this.heap = heap;
            this.currentIndex = currentIndex;
            this.lastItemIndex = lastItemIndex;
        }

        @Override
        public boolean hasNext() {
            return currentIndex <= lastItemIndex;
        }

        @Override
        public T next() {
            return heap[currentIndex++];
        }
    }

}
