
public class LinkedList<E> extends Object implements List<E> {

    /**
     * Constructor for an empty list.
     */
    public LinkedList() {}

    /**
     * Method to check if the container is empty.
     */
    public boolean isEmpty() {}

    /**
     * Method to get the size of the list.
     */
    public int size() {}

    /**
     * Method to add an item to the top of the list.
     * Returns a Position object representing the newly added item.
     */
    public Position<E> insertBeginning(E elem) {}

    /**
     * Method to add an item to the bottom of the list.
     * Returns a Position object representing the newly added item.
     */
    public Position<E> insertEnd(E elem) {}

    /**
     * Method to add an item after the received position.
     * Returns a Position object representing the newly added item.
     */
    public Position<E> insertAfter(Position<E> node, E elem) {}

    /**
     * Method to add an item before the received position.
     * Returns a Position object representing the newly added item.
     */
    public Position<E> insertBefore(Position<E> node, E elem) {}

    /**
     * Method to delete the first position in the list.
     * Returns the element contained in the deleted position.
     * Throws an EmptyContainerException if the list is empty.
     */
    public E deleteFirst() throws EmptyContainerException {}

    /**
     * Delete the received position.
     * Returns the element contained in the deleted position.
     * Throws an InvalidPositionException if the position is not valid.
     */
    public E delete(Position<E> node) throws InvalidPositionException {}

    /**
     * Delete the next position.
     * Returns the element contained in the deleted position.
     * Throws an InvalidPositionException if the position is not valid.
     */
    public E deleteNext(Position<E> node) throws InvalidPositionException {}

    /**
     * Method to update the element contained in the received position.
     * Returns the old element.
     * Throws an InvalidPositionException if the position is not valid.
     */
    public E update(Position<E> node, E elem) throws InvalidPositionException {}

    /**
     * Method to exchange the items contained in two received positions.
     */
    public void swap(Position<E> node1, Position<E> node2) {}

    /**
     * Method to check if the container is empty.
     */
    public boolean isEmpty() {}

    /**
     * Method to create a chained node, place it next to the received position as a parameter, and increase the number of items.
     */
    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {}

    /**
     * Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other.
     * Returns a Traversal object representing the positions.
     */
    public Traversal<E> positions() {}

    /**
     * Method to get the node before the received one as a parameter.
     */
    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {}

    /**
     * Method that retrieves the items in the container.
     * Returns an Iterator object representing the items.
     */
    public Iterator<E> values() {}

    /**
     * Method that overwrites Object.toString().
     * Returns a String representing the list.
     */
    public String toString() {}

    /**
     * Linked node class.
     */
    protected static class LinkedNode<E> implements Position<E> {}

}
