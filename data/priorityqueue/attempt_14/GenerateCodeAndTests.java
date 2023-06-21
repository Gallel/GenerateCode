
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Constructors
    public PriorityQueue() {}
    public PriorityQueue(int max) {}
    public PriorityQueue(int max, Comparator<E> comparator) {}
    public PriorityQueue(Comparator<E> comparator) {}

    // Methods
    public void add(E elem) {}
    public PriorityQueue<E> clone() {}
    protected int compare(Position<E> pos1, Position<E> pos2) {}
    protected Position<E> deleteLastPosition() {}
    public boolean isEmpty() {}
    public boolean isFull() {}
    protected Position<E> nextLastPosition(E elem) {}
    public E peek() {}
    public E poll() {}
    Traversal positions() {}
    protected void siftDown(Position<E> posicioAOrdenar) {}
    protected void siftUp(Position<E> newPosition) {}
    public int size() {}
    public String toString() {}
    public Iterator<E> values() {}
}
