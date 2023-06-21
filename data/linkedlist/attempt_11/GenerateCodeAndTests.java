
public class LinkedList<E> extends Object implements List<E> {

    public LinkedList() {}

    public E delete(Position<E> node){}

    public E deleteFirst(){}

    public E deleteNext(Position<E> node){}

    public Position<E> insertAfter(Position<E> node, E elem){}

    public Position<E> insertBefore(Position<E> node, E elem){}

    public Position<E> insertBeginning(E elem){}

    public Position<E> insertEnd(E elem){}

    public boolean isEmpty(){}

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem){}

    public Traversal<E> positions(){}

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node){}

    public int size(){}

    public void swap(Position<E> node1, Position<E> node2){}

    public String toString(){}

    public E update(Position<E> node, E elem){}

    public Iterator<E> values(){}
}
