
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Default constructor
    public PriorityQueue() {}

    // Constructor with given capacity
    public PriorityQueue(int max) {}

    // Constructor with given capacity and comparator
    public PriorityQueue(int max, Comparator<E> comparator) {}

    // Constructor with given comparator
    public PriorityQueue(Comparator<E> comparator) {}

    // Add an item to the appropriate position
    public void add(E elem) {}

    // Returns a clone of the priority queue
    public PriorityQueue<E> clone() {}

    // Compare two positions in the priority queue
    protected int compare(Position<E> pos1, Position<E> pos2) {}

    // Delete the last position in the priority queue
    protected Position<E> deleteLastPosition() {}

    // Check if the priority queue is empty
    public boolean isEmpty() {}

    // Check if the priority queue is full
    public boolean isFull() {}

    // Returns the next last position for an element
    protected Position<E> nextLastPosition(E elem) {}

    // Retrieves the lowest priority item
    public E peek() {}

    // Deletes the lowest priority item
    public E poll() {}

    // Traversal positions in the container
    public Traversal positions() {}

    // Sift down a position in the priority queue
    protected void siftDown(Position<E> posicioAOrdenar) {}

    // Sift up a new position in the priority queue
    protected void siftUp(Position<E> newPosition) {}

    // Returns the number of items in the priority queue
    public int size() {}

    // Returns a string representation of the priority queue
    public String toString() {}

    // Returns an Iterator over the items in the priority queue
    public Iterator<E> values() {}
}
