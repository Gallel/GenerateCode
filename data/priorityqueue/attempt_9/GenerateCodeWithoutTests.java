
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Constructor without parameters (maximum capacity, default) i
    // elements of a class that implements java.lang.Comparable.
    PriorityQueue() {
        // constructor code here
    }

    // Constructor with a parameter (given capacity) and elements of a
    // class that implements java.lang.Comparable.
    PriorityQueue(int max) {
        // constructor code here
    }

    // Constructor with two parameters (given capacity) and elements of one
    // class comparable to the given comparator.
    PriorityQueue(int max, Comparator<E> comparator) {
        // constructor code here
    }

    // Constructor with a parameter (maximum capacity, default) i
    // items of a class comparable to the given comparator.
    PriorityQueue(Comparator<E> comparator) {
        // constructor code here
    }

    // Add an item to the appropriate position, if any.
    void add(E elem) {
        // method code here
    }

    PriorityQueue<E> clone() {
        // method code here
    }

    // Protected method that compares the items contained in the positions
    // received.
    protected int compare(Position<E> pos1, Position<E> pos2) {
        // method code here
    }

    protected Position<E> deleteLastPosition() {
        // method code here
    }

    // Method to check if the container is empty.
    boolean isEmpty() {
        // method code here
    }

    // Method to check if the container is full.
    boolean isFull() {
        // method code here
    }

    protected Position<E> nextLastPosition(E elem) {
        // method code here
    }

    // Retrieves the lowest priority item, if any.
    E peek() {
        // method code here
    }

    // Delete the lowest priority item, if any.
    E poll() {
        // method code here
    }

    // Method that supports multiple paths, from the positions of the
    // container, simultaneous and independent of each other.
    Traversal positions() {
        // method code here
    }

    protected void siftDown(Position<E> posicioAOrdenar) {
        // method code here
    }

    protected void siftUp(Position<E> newPosition) {
        // method code here
    }

    // Retrieves the number of items in the container.
    int size() {
        // method code here
    }

    // Method overwriting Object.toString ().
    String toString() {
        // method code here
    }

    // Retrieves the items in the container.
    Iterator<E> values() {
        // method code here
    }
}
