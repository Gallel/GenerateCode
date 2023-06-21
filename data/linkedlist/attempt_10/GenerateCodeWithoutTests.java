
public class LinkedList<E> extends Object implements List<E> {

  protected int count; // number of elements in the list
  protected LinkedList.LinkedNode<E> head; // first node in the list

  // Empty list.
  public LinkedList() {
    count = 0;
    head = null;
  }

  // Delete received position.
  public E delete(Position<E> node) {
    // code here
  }

  // Delete the first position in the list.
  public E deleteFirst() {
    // code here
  }

  // Delete the next position.
  public E deleteNext(Position<E> node) {
    // code here
  }

  // Add an item after the received position.
  public Position<E> insertAfter(Position<E> node, E elem) {
    // code here
  }

  // Add an item before the received position.
  public Position<E> insertBefore(Position<E> node, E elem) {
    // code here
  }

  // Add an item to the top of the list.
  public Position<E> insertBeginning(E elem) {
    // code here
  }

  // Add an item to the bottom of the list.
  public Position<E> insertEnd(E elem) {
    // code here
  }

  // Method to check if the container is empty.
  public boolean isEmpty() {
    // code here
  }

  // Creates a chained node, places it next to the receipt as parameterize and increase the number of items.
  protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
    // code here
  }

  // Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other.
  public Traversal<E> positions() {
    // code here
  }

  // Returns the node before the received one as a parameter.
  protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
    // code here
  }

  // Retrieves the number of items in the container.
  public int size() {
    // code here
  }

  // Exchange items contained in received positions.
  public void swap(Position<E> node1, Position<E> node2) {
    // code here
  }

  // Method overwriting Object.toString().
  public String toString() {
    // code here
  }

  // Replaces the item contained in the received position.
  public E update(Position<E> node, E elem) {
    // code here
  }

  /**
   * Retrieves the items in the container.
   */
  public Iterator<E> values() {
    // code here
  }

}

class LinkedNode<E> implements Position<E> {
  // code here
}

interface Position<E> {
  // code here
}

interface Traversal<E> extends Iterator<Position<E>> {
  // code here
}

interface List<E> {
  // code here
}
