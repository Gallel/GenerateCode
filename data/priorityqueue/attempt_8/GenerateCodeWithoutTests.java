
public class PriorityQueue<E> extends Object implements Queue<E> {

  // Constructor without parameters (maximum capacity, default)
  // elements of a class that implements java.lang.Comparable.
  public PriorityQueue() {
    (code for constructor)
  }

  // Constructor with a parameter (given capacity) and elements of a
  // class that implements java.lang.Comparable.
  public PriorityQueue(int max) {
    (code for constructor)
  }

  // Constructor with two parameters (given capacity) and elements of one
  // class comparable to the given comparator.
  public PriorityQueue(int max, Comparator<E> comparator) {
    (code for constructor)
  }

  // Constructor with a parameter (maximum capacity, default) and items
  // of a class comparable to the given comparator.
  public PriorityQueue(Comparator<E> comparator) {
    (code for constructor)
  }

  // Add an item to the appropriate position, if any.
  public void add(E elem) {
    (code for method)
  }

  // Clone the priority queue.
  public PriorityQueue<E> clone() {
    (code for method)
  }

  // Protected method that compares the items contained in the positions received.
  protected int compare(Position<E> pos1, Position<E> pos2) {
    (code for method)
  }

  // Protected method that deletes the last position.
  protected Position<E> deleteLastPosition() {
    (code for method)
  }

  // Check if the container is empty.
  public boolean isEmpty() {
    (code for method)
  }

  // Check if the container is full.
  public boolean isFull() {
    (code for method)
  }

  // Protected method that retrieves the next last position.
  protected Position<E> nextLastPosition(E elem) {
    (code for method)
  }

  // Retrieves the lowest priority item, if any.
  public E peek() {
    (code for method)
  }

  // Delete the lowest priority item, if any.
  public E poll() {
    (code for method)
  }

  // Method that supports multiple paths, from the positions of the
  // container, simultaneous and independent of each other.
  public Traversal positions() {
    (code for method)
  }

  // Protected method that orders the item in the given position down.
  protected void siftDown(Position<E> posicioAOrdenar) {
    (code for method)
  }

  // Protected method that orders the item in the new position up.
  protected void siftUp(Position<E> newPosition) {
    (code for method)
  }

  // Retrieves the number of items in the container.
  public int size() {
    (code for method)
  }

  // Method overwriting Object.toString().
  public String toString() {
    (code for method)
  }

  // Retrieves the items in the container.
  public Iterator<E> values() {
    (code for method)
  }
}
