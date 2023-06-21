
public class LinkedList<E> extends Object implements List<E> {

    LinkedList() {}

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {}

    boolean isEmpty() {}

    int size() {}

    E update(Position<E> node, E elem) {}

    void swap(Position<E> node1, Position<E> node2) {}

    E delete(Position<E> node) {}

    E deleteFirst() {}

    E deleteNext(Position<E> node) {}

    Position<E> insertBeginning(E elem) {}

    Position<E> insertEnd(E elem) {}

    Position<E> insertAfter(Position<E> node, E elem) {}

    Position<E> insertBefore(Position<E> node, E elem) {}

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {}

    Traversal<E> positions() {}

    Iterator<E> values() {}

    String toString() {}

}
