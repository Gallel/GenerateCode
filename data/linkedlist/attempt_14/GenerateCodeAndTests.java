
public class LinkedList<E> extends Object implements List<E> {

    /**
     * Empty list.
     */
    LinkedList(){}

    /**
     * Delete received position.
     */
    E delete(Position<E> node){}

    /**
     * Delete the first position in the list.
     */
    E deleteFirst(){}

    /**
     * Delete the next position.
     */
    E deleteNext(Position<E> node){}

    /**
     * Add an item after the received position.
     */
    Position<E> insertAfter(Position<E> node, E elem){}

    /**
     * Add an item before the received position.
     */
    Position<E> insertBefore(Position<E> node, E elem){}

    /**
     * Add an item to the top of the list.
     */
    Position<E> insertBeginning(E elem){}

    /**
     * Add an item to the bottom of the list.
     */
    Position<E> insertEnd(E elem){}

    /**
     * Method to check if the container is empty.
     */
    boolean isEmpty(){}

    /**
     * Creates a chained node, places it next to the receipt as parameterize and increase the number of items.
     */
    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem){}

    /**
     * Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other.
     */
    Traversal<E> positions(){}

    /**
     * Returns the node before the received one as a parameter.
     */
    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node){}

    /**
     * Retrieves the number of items in the container.
     */
    int size(){}

    /**
     * Exchange items contained in received positions.
     */
    void swap(Position<E> node1, Position<E> node2){}

    /**
     * Method overwriting Object.toString ().
     */
    String toString(){}

    /**
     * Replaces the item contained in the received position.
     */
    E update(Position<E> node, E elem){}

    /**
     * Retrieves the items in the container.
     */
    Iterator<E> values(){}
}
