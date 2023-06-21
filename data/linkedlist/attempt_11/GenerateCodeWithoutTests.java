
public class LinkedList<E> extends Object implements List<E> {

    // Empty list.
    public LinkedList() {}

    // Delete received position.
    public E delete(Position<E> node) {
        //(write the code here)
    }

    // Delete the first position in the list.
    public E deleteFirst() {
        //(write the code here)
    }

    // Delete the next position.
    public E deleteNext(Position<E> node) {
        //(write the code here)
    }

    // Add an item after the received position.
    public Position<E> insertAfter(Position<E> node, E elem) {
        //(write the code here)
    }

    // Add an item before the received position.
    public Position<E> insertBefore(Position<E> node, E elem) {
        //(write the code here)
    }

    // Add an item to the top of the list.
    public Position<E> insertBeginning(E elem) {
        //(write the code here)
    }

    // Add an item to the bottom of the list.
    public Position<E> insertEnd(E elem) {
        //(write the code here)
    }

    // Method to check if the container is empty.
    public boolean isEmpty() {
        //(write the code here)
    }

    // Creates a chained node, places it next to the receipt as
    // parameterize and increase the number of items.
    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        //(write the code here)
    }

    // Method that supports multiple paths, from the positions of the
    // container, simultaneous and independent of each other.
    public Traversal<E> positions() {
        //(write the code here)
    }

    // Returns the node before the received one as a parameter.
    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        //(write the code here)
    }

    // Retrieves the number of items in the container.
    public int size() {
        //(write the code here)
    }

    // Exchange items contained in received positions.
    public void swap(Position<E> node1, Position<E> node2) {
        //(write the code here)
    }

    // Method overwriting Object.toString ().
    public String toString() {
        //(write the code here)
    }

    // Replaces the item contained in the received position.
    public E update(Position<E> node, E elem) {
        //(write the code here)
    }

    // Retrieves the items in the container.
    public Iterator<E> values() {
        //(write the code here)
    }
}
