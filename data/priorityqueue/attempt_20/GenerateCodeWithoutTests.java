
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Constructors

    // Constructor without parameters (maximum capacity, default) i
    // elements of a class that implements java.lang.Comparable.
    PriorityQueue() {}

    // Constructor with a parameter (given capacity) and elements of a
    // class that implements java.lang.Comparable.
    PriorityQueue(int max) {}

    // Constructor with two parameters (given capacity) and elements of one
    // class comparable to the given comparator.
    PriorityQueue(int max, Comparator<E> comparator) {}

    // Constructor with a parameter (maximum capacity, default) i
    // items of a class comparable to the given comparator.
    PriorityQueue(Comparator<E> comparator) {}

    // Methods

    // Add an item to the appropriate position, if any.
    void add(E elem) {}

    // PriorityQueue clone
    PriorityQueue<E> clone() {}

    // Protected method that compares the items contained in the positions
    // received.
    protected int compare(Position<E> pos1, Position<E> pos2) {}

    protected Position<E> deleteLastPosition() {}

    // Method to check if the container is empty.
    boolean isEmpty() {}

    // Method to check if the container is full.
    boolean isFull() {}

    protected Position<E> nextLastPosition(E elem) {}

    // Retrieves the lowest priority item, if any.
    E peek() {}

    // Delete the lowest priority item, if any.
    E poll() {}

    // Method that supports multiple paths, from the positions of the
    // container, simultaneous and independent of each other.
    Traversal positions() {}

    protected void siftDown(Position<E> posicioAOrdenar) {}

    protected void siftUp(Position<E> newPosition) {}

    // Retrieves the number of items in the container.
    int size() {}

    // Method overwriting Object.toString().
    String toString() {}

    // Retrieves the items in the container.
    Iterator<E> values() {}
}
