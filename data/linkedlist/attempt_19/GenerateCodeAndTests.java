
public class LinkedList<E> extends Object implements List<E> {

    /**
     * Creates an empty list.
     */
    public LinkedList() {}

    /**
     * Deletes the received position.
     * @param node position to be deleted
     * @return the item removed from the container
     */
    public E delete(Position<E> node) {}

    /**
     * Deletes the first position in the list.
     * @return the item removed from the container
     */
    public E deleteFirst() {}

    /**
     * Deletes the position after the received node.
     * @param node node that precedes the position to be deleted
     * @return the item removed from the container
     */
    public E deleteNext(Position<E> node) {}

    /**
     * Adds an item after the received position.
     * @param node position after which the new item will be added
     * @param elem item to be added
     * @return the new position where the item was added
     */
    public Position<E> insertAfter(Position<E> node, E elem) {}

    /**
     * Adds an item before the received position.
     * @param node position before which the new item will be added
     * @param elem item to be added
     * @return the new position where the item was added
     */
    public Position<E> insertBefore(Position<E> node, E elem) {}

    /**
     * Adds an item to the top of the list.
     * @param elem item to be added
     * @return the new position where the item was added
     */
    public Position<E> insertBeginning(E elem) {}

    /**
     * Adds an item to the bottom of the list.
     * @param elem item to be added
     * @return the new position where the item was added
     */
    public Position<E> insertEnd(E elem) {}

    /**
     * Checks if the container is empty.
     * @return true if the container is empty, false otherwise
     */
    public boolean isEmpty() {}

    /**
     * Creates a chained node, places it next to the received node, and increases the number of items.
     * @param node node used as reference to add the new node
     * @param elem item to be added
     * @return the new node created
     */
    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {}

    /**
     * Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other.
     * @return a Traversal object representing the positions of the container
     */
    public Traversal<E> positions() {}

    /**
     * Retrieves the node before the received one.
     * @param node node to have its predecessor retrieved
     * @return the node before the received one
     */
    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {}

    /**
     * Retrieves the number of items in the container.
     * @return the number of items in the container
     */
    public int size() {}

    /**
     * Exchange items contained in received positions.
     * @param node1 first position for the exchange
     * @param node2 second position for the exchange
     */
    public void swap(Position<E> node1, Position<E> node2) {}

    /**
     * Method overwriting Object.toString().
     * @return a String representation of the container
     */
    public String toString() {}

    /**
     * Replaces the item contained in the received position.
     * @param node position to have its item updated
     * @param elem new item to replace the old one
     * @return the old item removed from the container
     */
    public E update(Position<E> node, E elem) {}

    /**
     * Retrieves the items in the container as an iterator.
     * @return an Iterator object over the items in the container
     */
    public Iterator<E> values() {}
}

/**
 * Represents a linked node to be used in a linked list.
 * @param <E> type of element to be stored in the node
 */
protected static class LinkedNode<E> implements Position<E> {

    /**
     * Item stored in the node.
     */
    public E element;

    /**
     * Node after the current one.
     */
    public LinkedList.LinkedNode<E> next;

    /**
     * Creates a node that stores the received element and sets the next node as null.
     * @param element item to be stored in the node
     */
    public LinkedNode(E element) {}

    /**
     * Retrieves the item stored in the node.
     * @return the item stored in the node
     */
    public E getElement() {}

    /**
     * Sets the item stored in the node.
     * @param element item to be stored in the node
     */
    public void setElement(E element) {}
}

/**
 * Represents the positions of a linked list.
 * @param <E> type of element stored in the linked list
 */
protected class LinkedListPositions<E> implements Traversal<E> {

    /**
     * Retrieves the next position in the container.
     * @return the next position in the container
     */
    public Position<E> getNext() {}

    /**
     * Checks if there is a next position in the container.
     * @return true if there is a next position in the container, false otherwise
     */
    public boolean hasNext() {}

    /**
     * Resets the traversal to the first position in the container.
     */
    public void reset() {}

    /**
     * Retrieves the item stored in the current position.
     * @return the item stored in the current position
     */
    public E get() {}

    /**
     * Replaces the item stored in the current position.
     * @param elem new item to replace the old one
     */
    public void set(E elem) {}
}
