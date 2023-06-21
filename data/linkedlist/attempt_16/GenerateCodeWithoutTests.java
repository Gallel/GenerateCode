
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        // implementation of the Position interface
    }

    // constructors
    public LinkedList() {
        // implementation of empty list constructor
    }

    // methods
    public E delete(Position<E> node) {
        // implementation of delete received position method
    }

    public E deleteFirst() {
        // implementation of delete first position method
    }

    public E deleteNext(Position<E> node) {
        // implementation of delete next position method
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        // implementation of insert after received position method
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        // implementation of insert before received position method
    }

    public Position<E> insertBeginning(E elem) {
        // implementation of insert at beginning method
    }

    public Position<E> insertEnd(E elem) {
        // implementation of insert at end method
    }

    public boolean isEmpty() {
        // implementation of is empty method
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        // implementation of new position method
    }

    public Traversal<E> positions() {
        // implementation of positions method
    }

    protected LinkedNode<E> previous(LinkedNode<E> node) {
        // implementation of previous position method
    }

    public int size() {
        // implementation of size method
    }

    public void swap(Position<E> node1, Position<E> node2) {
        // implementation of swap method
    }

    @Override
    public String toString() {
        // implementation of toString method
    }

    public E update(Position<E> node, E elem) {
        // implementation of update method
    }

    @Override
    public Iterator<E> values() {
        // implementation of values method
    }
}
