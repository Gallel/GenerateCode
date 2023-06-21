
public class LinkedList<E> extends Object implements List<E> {

    public LinkedList() {
        // empty list
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        // creates a chained node and increases the number of items
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        // returns the node before the received one as a parameter
    }

    @Override
    public boolean isEmpty() {
        // returns true if the container is empty, false otherwise
    }

    @Override
    public int size() {
        // retrieves the number of items in the container
    }

    @Override
    public Iterator<E> values() {
        // retrieves the items in the container
    }

    @Override
    public Position<E> insertBeginning(E elem) {
        // add an item to the top of the list
    }

    @Override
    public Position<E> insertEnd(E elem) {
        // add an item to the bottom of the list
    }

    @Override
    public Position<E> insertBefore(Position<E> node, E elem) {
        // add an item before the received position
    }

    @Override
    public Position<E> insertAfter(Position<E> node, E elem) {
        // add an item after the received position
    }

    @Override
    public E update(Position<E> node, E elem) {
        // replaces the item contained in the received position
    }

    @Override
    public E delete(Position<E> node) {
        // delete received position
    }

    @Override
    public E deleteFirst() {
        // delete the first position in the list
    }

    @Override
    public E deleteNext(Position<E> node) {
        // delete the next position
    }

    @Override
    public void swap(Position<E> node1, Position<E> node2) {
        // exchange items contained in received positions
    }

    @Override
    public Traversal<E> positions() {
        // method that supports multiple paths
    }

    @Override
    public String toString() {
        // method overwriting Object.toString()
    }
}
