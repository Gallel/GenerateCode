
import java.util.*;

public class PriorityQueue<E> extends Object implements Queue<E> {
    
    // Attributes
    private int MAX_CAPACITY;
    private E[] elements;
    private int size;
    private Comparator<E> comparator;
    
    // Constructors
    public PriorityQueue() {
        this.MAX_CAPACITY = 100;
        this.elements = (E[]) new Object[MAX_CAPACITY];
        this.size = 0;
        this.comparator = null;
    }
    
    public PriorityQueue(int max) {
        this.MAX_CAPACITY = max;
        this.elements = (E[]) new Object[MAX_CAPACITY];
        this.size = 0;
        this.comparator = null;
    }
    
    public PriorityQueue(int max, Comparator<E> comparator) {
        this.MAX_CAPACITY = max;
        this.elements = (E[]) new Object[MAX_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }
    
    public PriorityQueue(Comparator<E> comparator) {
        this.MAX_CAPACITY = 100;
        this.elements = (E[]) new Object[MAX_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }
    
    // Methods
    public void add(E elem) {
        if (isFull()) {
            throw new RuntimeException("Container is full.");
        }
        Position<E> newPos = nextLastPosition(elem);
        siftUp(newPos);
        size++;
    }
    
    public PriorityQueue<E> clone() {
        PriorityQueue<E> newPQ = new PriorityQueue<E>(this.MAX_CAPACITY, this.comparator);
        newPQ.size = this.size;
        System.arraycopy(this.elements, 0, newPQ.elements, 0, this.size);
        return newPQ;
    }
    
    protected int compare(Position<E> pos1, Position<E> pos2) {
        if (comparator != null) {
            return comparator.compare(pos1.getElement(), pos2.getElement());
        } else {
            return ((Comparable<E>) pos1.getElement()).compareTo(pos2.getElement());
        }
    }
    
    protected Position<E> deleteLastPosition() {
        if (isEmpty()) {
            throw new RuntimeException("Container is empty.");
        }
        Position<E> lastPos = new VectorPosition<E>(elements[size-1], size-1);
        elements[size-1] = null;
        size--;
        return lastPos;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == MAX_CAPACITY;
    }
    
    protected Position<E> nextLastPosition(E elem) {
        Position<E> newPos = new VectorPosition<E>(elem, size);
        elements[size] = elem;
        return newPos;
    }
    
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[0];
    }
    
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E elem = elements[0];
        Position<E> lastPos = deleteLastPosition();
        if (!isEmpty()) {
            elements[0] = lastPos.getElement();
            siftDown(new VectorPosition<E>(elements[0], 0));
        }
        return elem;
    }
    
    public Traversal positions() {
        return new PriorityQueueTraversal();
    }
    
    protected void siftDown(Position<E> pos) {
        int i = pos.getIndex();
        boolean done = false;
        while (!done && hasLeft(i)) {
            int leftIndex = left(i);
            int smallChildIndex = leftIndex;
            if (hasRight(i)) {
                int rightIndex = right(i);
                if (compare(heap.get(leftIndex), heap.get(rightIndex))) > 0 {
                    smallChildIndex = rightIndex;
                }
            }
            if (compare(elements[smallChildIndex], elements[i])) >= 0 {
                done = true;
            } else {
                swap(i, smallChildIndex);
                i = smallChildIndex;
            }
        }
    }
    
    protected void siftUp(Position<E> newPos) {
        Position<E> parent = parent(newPos.getIndex());
        while (newPos.getIndex() > 0 && compare(parent, newPos) > 0) {
            swap(newPos.getIndex(), parent.getIndex());
            newPos = parent;
            parent = parent(newPos.getIndex());
        }
    }
    
    public int size() {
        return size;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i=0; i<size; i++) {
            sb.append(elements[i]);
            if (i != size-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Iterator<E> values() {
        ArrayList<E> list = new ArrayList<E>();
        for (int i=0; i<size; i++) {
            list.add(elements[i]);
        }
        return list.iterator();
    }
    
    // Helper methods
    protected boolean hasLeft(int i) {
        return left(i) < size;
    }
    
    protected boolean hasRight(int i) {
        return right(i) < size;
    }
    
    protected int left(int i) {
        return 2*i+1;
    }
    
    protected Position<E> parent(int i) {
        return new VectorPosition<E>(elements[(i-1)/2], (i-1)/2);
    }
    
    protected int right(int i) {
        return 2*i+2;
    }
    
    protected void swap(int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
    
    // Classes
    private class PriorityQueueTraversal implements Traversal {
        private int i = 0;
        
        public boolean hasNext() {
            return i < size;
        }
        
        public Position next() {
            int j = i;
            i++;
            return new VectorPosition<E>(elements[j], j);
        }
    }
}
