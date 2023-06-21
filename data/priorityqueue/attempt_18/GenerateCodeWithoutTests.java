
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Constructors:
    PriorityQueue() {
        // Constructor without parameters (maximum capacity, default) 
        // and elements of a class that implements java.lang.Comparable.
    }
    
    PriorityQueue(int max) {
        // Constructor with a parameter (given capacity) and elements of a
        // class that implements java.lang.Comparable.
    }
    
    PriorityQueue(int max, Comparator<E> comparator) {
        // Constructor with two parameters (given capacity) and elements of one
        // class comparable to the given comparator.
    }
    
    PriorityQueue(Comparator<E> comparator) {
        // Constructor with a parameter (maximum capacity, default) and
        // items of a class comparable to the given comparator.
    }
    
    // Methods:
    void add(E elem) {
        // Add an item to the appropriate position, if any.
    }
    
    PriorityQueue<E> clone() {
        // Clones the PriorityQueue object.
    }
    
    protected int compare(Position<E> pos1, Position<E> pos2) {
        // Protected method that compares the items contained in the positions
        // received.
    }
    
    protected Position<E> deleteLastPosition() {
        // Deletes the last position.
    }

    boolean isEmpty(){
        // Method to check if the container is empty.
    }
    
    boolean isFull() {
        // Method to check if the container is full.
    }
    
    protected Position<E> nextLastPosition(E elem) {
        // Returns the next last position.
    }
    
    E peek() {
        // Retrieves the lowest priority item, if any.
    }
    
    E poll() {
        // Delete the lowest priority item, if any.
    }
    
    Traversal positions() {
        // Method that supports multiple paths, from the positions of the
        // container, simultaneous and independent of each other.
    }
    
    protected void siftDown(Position<E> posicioAOrdenar) {
        // siftDowns the position.
    }
    
    protected void siftUp(Position<E> newPosition) {
        // siftUps the position.
    }
    
    int size(){
        // Retrieves the number of items in the container.
    }
    
    String toString() {
        // Method overwriting Object.toString().
    }
    
    Iterator<E> values() {
        // Retrieves the items in the container.
    }

}
