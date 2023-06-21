
public class LinkedList<E> extends Object implements List<E> {

    //empty list
    public LinkedList() {}

    //delete received position
    public E delete(Position<E> node) {}

    //delete first position in the list
    public E deleteFirst() {}

    //delete the next position
    public E deleteNext(Position<E> node) {}

    //add an item after the received position
    public Position<E> insertAfter(Position<E> node, E elem) {}

    //add an item before the received position
    public Position<E> insertBefore(Position<E> node, E elem) {}

    //add an item to the top of the list
    public Position<E> insertBeginning(E elem) {}

    //add an item to the bottom of the list
    public Position<E> insertEnd(E elem) {}

    //check if the container is empty
    public boolean isEmpty() {}

    //Creates a chained node
    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {}

    //supports multiple paths from containers
    public Traversal<E> positions() {}

    //returns the node before the received one as a parameter
    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {}

    //retrieves the number of items in the container
    public int size() {}

    //Exchange items contained in received positions
    public void swap(Position<E> node1, Position<E> node2) {}

    //Method overwriting Object.toString()
    public String toString() {}

    //replaces the item contained in the received position
    public E update(Position<E> node, E elem) {}

    //retrieves the items in the container
    public Iterator<E> values() {}
}
