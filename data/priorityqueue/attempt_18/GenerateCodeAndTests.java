
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Default constructor 
    PriorityQueue() {
        // TODO: Implementation
    }

    // Constructor with given capacity 
    PriorityQueue(int max) {
        // TODO: Implementation
    }

    // Constructor with given capacity and given comparator
    PriorityQueue(int max, Comparator<E> comparator) {
        // TODO: Implementation
    }

    // Constructor with given comparator
    PriorityQueue(Comparator<E> comparator) {
        // TODO: Implementation
    }

    // Add an item to the appropriate position, if any
    void add(E elem) {
        // TODO: Implementation
    }

    // Clone the priority queue
    PriorityQueue<E> clone() {
        // TODO: Implementation
    }

    // Compare two positions
    protected int compare(Position<E> pos1, Position<E> pos2) {
        // TODO: Implementation
    }

    // Delete the last position
    protected Position<E> deleteLastPosition() {
        // TODO: Implementation
    }

    // Check if the priority queue is empty
    boolean isEmpty() {
        // TODO: Implementation
    }

    // Check if the priority queue is full
    boolean isFull() {
        // TODO: Implementation
    }

    // Find the next last position
    protected Position<E> nextLastPosition(E elem) {
        // TODO: Implementation
    }

    // Retrieve the lowest priority item
    E peek() {
        // TODO: Implementation
    }

    // Delete the lowest priority item
    E poll() {
        // TODO: Implementation
    }

    // Traverse the priority queue
    Traversal positions() {
        // TODO: Implementation
    }

    // Sift down a position
    protected void siftDown(Position<E> pos) {
        // TODO: Implementation
    }

    // Sift up a position  
    protected void siftUp(Position<E> newPos) {
        // TODO: Implementation
    }

    // Retrieve the number of items in the priority queue
    int size() {
        // TODO: Implementation
    }

    // Override Object.toString()
    String toString() {
        // TODO: Implementation
    }

    // Retrieve the items in the priority queue
    Iterator<E> values() {
        // TODO: Implementation
    }
}
