
import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class LinkedList<E> implements List<E> {

    protected static class LinkedNode<E> implements Position<E>{
        private E elem;
        private LinkedNode<E> prev, next;

        public LinkedNode(E elem, LinkedNode<E> prev, LinkedNode<E> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }

        public LinkedNode<E> getNext() {
            return next;
        }

        public LinkedNode<E> getPrev() {
            return prev;
        }

        @Override
        public E getElem() {
            return elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }

        public void setPrev(LinkedNode<E> prev) {
            this.prev = prev;
        }

        public void setNext(LinkedNode<E> next) {
            this.next = next;
        }
    }

    private LinkedNode<E> head, tail;
    private int size;

    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> newPosition(LinkedNode<E> node, E elem){
        LinkedNode<E> newNode = new LinkedNode<E>(elem, node, node.getNext());
        newNode.getNext().setPrev(newNode);
        node.setNext(newNode);
        size++;
        return newNode;
    }

    public Position<E> insertBeginning(E elem) {
        return newPosition(head, elem);
    }

    public Position<E> insertEnd(E elem) {
        return newPosition(tail.getPrev(), elem);
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedNode<E> linkedNode = checkPosition(node);
        return newPosition(linkedNode, elem);
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedNode<E> linkedNode = checkPosition(node);
        return newPosition(linkedNode.getPrev(), elem);
    }

    public E update(Position<E> node, E elem) {
        LinkedNode<E> linkedNode = checkPosition(node);
        E temp = linkedNode.getElem();
        linkedNode.setElem(elem);
        return temp;
    }

    public E delete(Position<E> node) {
        LinkedNode<E> p = checkPosition(node);
        LinkedNode<E> prev = p.getPrev(), next = p.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return p.getElem();
    }

    public E deleteFirst() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return delete(head.getNext());
    }

    public E deleteNext(Position<E> node) {
        LinkedNode<E> p = checkPosition(node);
        if (p == tail.getPrev()) {
            throw new InvalidPositionException();
        }
        return delete(p.getNext());
    }

    public Traversal<E> positions() {
        return new LinkedListIterator<E>(head, tail);
    }

    protected static class LinkedListIterator<E> implements Traversal<E>{
        private LinkedNode<E> head, tail, current;

        public LinkedListIterator(LinkedNode<E> head, LinkedNode<E> tail) {
            current = this.head = head;
            this.tail = tail;
        }

        public boolean hasNext() {
            return current != tail;
        }

        public E next() {
            if(!hasNext()) {
                throw new InvalidPositionException();
            }
            current = current.getNext();
            return current.getElem();
        }
    }

    public E get(Position<E> node) {
        LinkedNode<E> p = checkPosition(node);
        return p.getElem();
    }

    public void swap(Position<E> node1, Position<E> node2) {
        LinkedNode<E> n1 = checkPosition(node1), n2 = checkPosition(node2);
        E temp = n1.getElem();
        n1.setElem(n2.getElem());
        n2.setElem(temp);
    }

    protected LinkedNode<E> checkPosition(Position<E> node) {
        if (node == null || !(node instanceof LinkedNode)) {
            throw new InvalidPositionException();
        }
        LinkedNode<E> p = (LinkedNode<E>) node;
        if (p.getNext() == null) {
            throw new InvalidPositionException();
        }
        return p;
    }

    public Iterator<E> values() {
        return positions();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        LinkedNode<E> node = head.getNext();
        while (node != tail) {
            builder.append(node.getElem());
            node = node.getNext();
            if (node != tail) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.insertBeginning(1);
        linkedList.insertBeginning(2);
        linkedList.insertBeginning(3);
        linkedList.insertBeginning(4);
        linkedList.deleteFirst();

        System.out.println(linkedList);
    }
}
