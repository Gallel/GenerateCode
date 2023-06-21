
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        /* attributes */
    }

    public LinkedList() {
        /* constructor body */
    }

    public E delete(Position<E> node) {
        /* method body */
    }

    public E deleteFirst() {
        /* method body */
    }

    public E deleteNext(Position<E> node) {
        /* method body */
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        /* method body */
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        /* method body */
    }

    public Position<E> insertBeginning(E elem) {
        /* method body */
    }

    public Position<E> insertEnd(E elem) {
        /* method body */
    }

    public boolean isEmpty() {
        /* method body */
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        /* method body */
    }

    public Traversal<E> positions() {
        /* method body */
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        /* method body */
    }

    public int size() {
        /* method body */
    }

    public void swap(Position<E> node1, Position<E> node2) {
        /* method body */
    }

    public String toString() {
        /* method body */
    }

    public E update(Position<E> node, E elem) {
        /* method body */
    }

    public Iterator<E> values() {
        /* method body */
    }
}
