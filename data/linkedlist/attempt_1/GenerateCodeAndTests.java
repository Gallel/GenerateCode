
public class LinkedList<E> extends Object implements List<E> {

    //Empty list
    public LinkedList() {}

    //Delete received position
    public E delete(Position<E> node) {
        //code for deleting node at given position goes here
    }

    //Delete the first position in the list
    public E deleteFirst() {
        //code for deleting first node goes here
    }

    //Delete the next position
    public E deleteNext(Position<E> node) {
        //code for deleting node after given position goes here
    }

    //Add an item after the received position
    public Position<E> insertAfter(Position<E> node, E elem) {
        //code for inserting element after given position goes here
    }

    //Add an item before the received position
    public Position<E> insertBefore(Position<E> node, E elem) {
        //code for inserting element before given position goes here
    }

    //Add an item to the top of the list
    public Position<E> insertBeginning(E elem) {
        //code for inserting element at the beginning of the list goes here
    }

    //Add an item to the bottom of the list
    public Position<E> insertEnd(E elem) {
        //code for inserting element at the end of the list goes here
    }

    //Method to check if the container is empty
    public boolean isEmpty() {
        //code for checking if list is empty goes here
    }

    //Creates a chained node, places it next to the receipt as parameterize and increase the number of items.
    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        //code for creating new linked node goes here
    }

    //Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other.
    public Traversal<E> positions() {
        //code for traversing positions in list goes here
    }

    //Returns the node before the received one as a parameter.
    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        //code for getting previous node goes here
    }

    //Retrieves the number of items in the container.
    public int size() {
        //code for getting size of list goes here
    }

    //Exchange items contained in received positions.
    public void swap(Position<E> node1, Position<E> node2) {
        //code for swapping position elements goes here
    }

    //Method overwriting Object.toString().
    public String toString() {
        //code for converting list to string goes here
    }


    //Replaces the item contained in the received position.
    public E update(Position<E> node, E elem) {
        //code for updating element at given position goes here
    }

    /**
     * Retrieves the items in the container.
     */
    public Iterator<E> values() {
        //code for returning iterator over list elements goes here
    }
}
