
public class PriorityQueue<E> extends Object implements Queue<E> {

  // Constructors
  public PriorityQueue() {
    // constructor without parameters
  }

  public PriorityQueue(int max) {
    // constructor with parameter for maximum capacity
  }

  public PriorityQueue(int max, Comparator<E> comparator) {
    // constructor with two parameters
  }

  public PriorityQueue(Comparator<E> comparator) {
    // constructor with one parameter
  }

  // Methods
  public void add(E elem) {
    // method to add item to appropriate position
  }

  public PriorityQueue<E> clone() {
    // method to clone the priority queue
  }

  protected int compare(Position<E> pos1, Position<E> pos2) {
    // method to compare items in positions received
  }

  protected Position<E> deleteLastPosition() {
    // method to delete last position
  }

  public boolean isEmpty() {
    // method to check if container is empty
  }

  public boolean isFull() {
    // method to check if container is full
  }

  protected Position<E> nextLastPosition(E elem) {
    // method to retrieve next last position
  }

  public E peek() {
    // method to retrieve lowest priority item
  }

  public E poll() {
    // method to delete lowest priority item
  }

  public Traversal positions() {
    // method to support multiple paths from container positions
  }

  protected void siftDown(Position<E> posicioAOrdenar) {
    // method to sift down position
  }

  protected void siftUp(Position<E> newPosition) {
    // method to sift up position
  }

  public int size() {
    // method to retrieve number of items in container
  }

  public String toString() {
    // method to override Object.toString()
  }

  public Iterator<E> values() {
    // method to retrieve items in container
  }
}
