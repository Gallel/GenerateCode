
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class LinkedList<E> implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        protected E element;
        protected LinkedNode<E> next;
        public LinkedNode(E elem, LinkedNode<E> n) {
            element = elem;
            next = n;
        }
        public E getElem() {
            return element;
        }
        public E setElem(E elem) {
            E temp = element;
            element = elem;
            return temp;
        }
        public LinkedNode<E> getNext() {
            return next;
        }
        public LinkedNode<E> setNext(LinkedNode<E> node) {
            LinkedNode<E> temp = next;
            next = node;
            return temp;
        }
    }

    protected LinkedNode<E> head;
    protected LinkedNode<E> tail;
    protected int size;

    public LinkedList() {
        head = new LinkedNode<E>(null, null);
        tail = head;
        size = 0;
    }

    @Override
    public Position<E> insertBeginning(E elem) {
        return insertAfter(head, elem);
    }

    @Override
    public Position<E> insertEnd(E elem) {
        return insertAfter(tail, elem);
    }

    @Override
    public Position<E> insertAfter(Position<E> node, E elem)
            throws InvalidPositionException {
        LinkedNode<E> origin = checkPosition(node);
        LinkedNode<E> newNode = newPosition(origin, elem);
        size++;
        if (tail == origin)
            tail = newNode;
        return newNode;
    }

    @Override
    public Position<E> insertBefore(Position<E> node, E elem)
            throws InvalidPositionException {
        LinkedNode<E> origin = checkPosition(node);
        LinkedNode<E> newNode = newPosition(previous(origin), elem);
        size++;
        if (origin == head)
            head = newNode;
        return newNode;
    }

    @Override
    public E delete(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> n = checkPosition(node);
        E temp = n.getElem();
        LinkedNode<E> prev = previous(n);
        prev.setNext(n.getNext());
        if (n.getNext() == null)
            tail = prev;
        n.setElem(null);
        n.setNext(null);
        size--;
        return temp;
    }

    @Override
    public E deleteFirst() throws EmptyContainerException {
        if (isEmpty())
            throw new EmptyContainerException("Empty List.");
        return delete(head.getNext());
    }

    @Override
    public E deleteNext(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> origin = checkPosition(node);
        if (origin.getNext() == null)
            throw new InvalidPositionException("Position does not have a next element.");
        return delete(origin.getNext());
    }

    @Override
    public E update(Position<E> node, E elem)
            throws InvalidPositionException {
        LinkedNode<E> n = checkPosition(node);
        E temp = n.getElem();
        n.setElem(elem);
        return temp;
    }

    @Override
    public Iterator<E> values() {
        return new ListIterator(head.getNext());
    }

    protected LinkedList.LinkedNode<E> checkPosition(Position<E> node) throws InvalidPositionException {
        if (node == null || !(node instanceof LinkedList.LinkedNode))
            throw new InvalidPositionException("Invalid position");
        LinkedNode<E> n = (LinkedNode<E>) node;
        if (n.getNext() == null)
            throw new InvalidPositionException("Position no longer in the list.");
        return n;
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        return new LinkedNode<E>(elem, node.getNext());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void swap(Position<E> node1, Position<E> node2)
            throws InvalidPositionException {
        LinkedNode<E> n1 = checkPosition(node1);
        LinkedNode<E> n2 = checkPosition(node2);
        E temp = n1.getElem();
        n1.setElem(n2.getElem());
        n2.setElem(temp);
    }

    protected LinkedList.LinkedNode<E> previous(LinkedNode<E> node) {
        LinkedNode<E> prev = head;
        while (prev.getNext() != node && prev.getNext() != null)
            prev = prev.getNext();
        return prev;
    }

    @Override
    public Traversal<E> positions() {
        return new PositionIterator(head);
    }

    protected class ListIterator implements Iterator<E> {
        LinkedNode<E> i;

        public ListIterator(LinkedNode<E> node) {
            i = node;
        }

        @Override
        public boolean hasNext() {
            return i != null;
        }

        @Override
        public E next() {
            if (!hasNext())
                return null;
            E temp = i.getElem();
            i = i.getNext();
            return temp;
        }
    }

    protected class PositionIterator implements Traversal<E> {
        LinkedNode<E> i;

        public PositionIterator(LinkedNode<E> node) {
            i = node;
        }

        @Override
        public boolean hasNext() {
            return i != null;
        }

        @Override
        public Position<E> next() {
            if (!hasNext())
                return null;
            LinkedNode<E> temp = i;
            i = i.getNext();
            return temp;
        }
    }

    @Override
    public String toString() {
        String ret = "";
        Iterator<E> it = values();
        while (it.hasNext())
            ret += "[" + it.next().toString() + "]";
        return ret;
    }
}
