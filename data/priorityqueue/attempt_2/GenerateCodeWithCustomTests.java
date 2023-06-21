
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Constructor without parameters (maximum capacity, default)
    // elements of a class that implements java.lang.Comparable.
    public PriorityQueue() {}

    // Constructor with a parameter (given capacity) and elements of a
    // class that implements java.lang.Comparable.
    public PriorityQueue(int max) {}

    // Constructor with two parameters (given capacity) and elements of one
    // class comparable to the given comparator.
    public PriorityQueue(int max, Comparator<E> comparator) {}

    // Constructor with a parameter (maximum capacity, default) i
    // items of a class comparable to the given comparator.
    public PriorityQueue(Comparator<E> comparator) {}

    // Add an item to the appropriate position, if any.
    public void add(E elem) {}

    // PriorityQueue clone method
    public PriorityQueue<E> clone() {}

    // Protected method that compares the items contained in the positions
    // received
    protected int compare(Position<E> pos1, Position<E> pos2) {}

    // Method to check if the container is empty.
    public boolean isEmpty() {}

    // Method to check if the container is full.
    public boolean isFull() {}

    // Protected method to get the next available position
    protected Position<E> nextLastPosition(E elem) {}

    // Retrieves the lowest priority item, if any.
    public E peek() {}

    // Delete the lowest priority item, if any.
    public E poll() {}

    // Method that supports multiple paths, from the positions of the
    // container, simultaneous and independent of each other.
    public Traversal positions() {}

    // Protected method to sift down a position in the heap
    protected void siftDown(Position<E> posicioAOrdenar) {}

    // Protected method to sift up a new position in the heap
    protected void siftUp(Position<E> newPosition) {}

    // Retrieves the number of items in the container.
    public int size() {}

    // Overriding Object toString method.
    public String toString() {}

    // Retrieves the items in the container.
    public Iterator<E> values() {}

}
