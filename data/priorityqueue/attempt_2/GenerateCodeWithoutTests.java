
public class PriorityQueue<E> extends Object implements Queue<E> {

  // Constructor without parameters (maximum capacity, default) i elements of a class that implements java.lang.Comparable.
  PriorityQueue() {
    // code here
  }

  // Constructor with a parameter (given capacity) and elements of a class that implements java.lang.Comparable.
  PriorityQueue(int max) {
    // code here
  }

  // Constructor with two parameters (given capacity) and elements of one class comparable to the given comparator.
  PriorityQueue(int max, Comparator<E> comparator) {
    // code here
  }

  // Constructor with a parameter (maximum capacity, default) i items of a class comparable to the given comparator.
  PriorityQueue(Comparator<E> comparator) {
    // code here
  }

  // Add an item to the appropriate position, if any.
  void add(E elem) {
    // code here
  }

  PriorityQueue<E> clone() {
    // code here
  }

  // Protected method that compares the items contained in the positions received.
  protected int compare(Position<E> pos1, Position<E> pos2) {
    // code here
  }

  protected Position<E> deleteLastPosition() {
    // code here
  }

  // Method to check if the container is empty.
  boolean isEmpty() {
    // code here
  }

  // Method to check if the container is full.
  boolean isFull() {
    // code here
  }

  protected Position<E> nextLastPosition(E elem) {
    // code here
  }

  // Retrieves the lowest priority item, if any.
  E peek() {
    // code here
  }

  // Delete the lowest priority item, if any.
  E poll() {
    // code here
  }

  // Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other.
  Traversal positions() {
    // code here
  }

  protected void siftDown(Position<E> posicioAOrdenar) {
    // code here
  }

  protected void siftUp(Position<E> newPosition) {
    // code here
  }

  // Retrieves the number of items in the container.
  int size() {
    // code here
  }

  // Method overwriting Object.toString ().
  String toString() {
    // code here
  }

  // Retrieves the items in the container.
  Iterator<E> values() {
    // code here
  }
}
