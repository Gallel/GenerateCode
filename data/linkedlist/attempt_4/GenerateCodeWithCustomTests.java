
public class LinkedList<E> extends Object implements List<E> {

    private LinkedList.LinkedNode<E> bottom;
    private int numElements;

    public LinkedList() {
        bottom = new LinkedList.LinkedNode<>(null);
        numElements = 0;
    }

    public boolean isEmpty() {
        return numElements == 0;
    }

    public int size() {
        return numElements;
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        LinkedList.LinkedNode<E> newNode = new LinkedList.LinkedNode<>(elem);
        newNode.setNext(node.getNext());
        node.setNext(newNode);
        numElements++;
        return newNode;
    }

    public Position<E> insertBeginning(E elem) {
        return newPosition(bottom, elem);
    }

    public Position<E> insertEnd(E elem) {
        return newPosition(previous(bottom), elem);
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        LinkedList.LinkedNode<E> current = bottom.getNext();

        while (current.getNext() != node.getNext()) {
            current = current.getNext();
        }
        return current;
    }

    public E deleteFirst() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }

        LinkedList.LinkedNode<E> toDelete = bottom.getNext();
        bottom.setNext(toDelete.getNext());
        numElements--;
        return toDelete.getElem();
    }

    public E delete(Position<E> node) {
        LinkedList.LinkedNode<E> toDelete = (LinkedList.LinkedNode<E>) node;

        if (toDelete.getNext() == null) {
            throw new InvalidPositionException();
        }

        LinkedList.LinkedNode<E> previous = previous(toDelete);
        previous.setNext(toDelete.getNext());
        numElements--;
        return toDelete.getElem();
    }

    public E deleteNext(Position<E> node) {
        LinkedList.LinkedNode<E> toDelete = ((LinkedList.LinkedNode<E>) node).getNext();

        if (toDelete == null) {
            throw new InvalidPositionException();
        }

        ((LinkedList.LinkedNode<E>) node).setNext(toDelete.getNext());
        numElements--;
        return toDelete.getElem();
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        return newPosition((LinkedList.LinkedNode<E>) node, elem);
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> previous = previous((LinkedList.LinkedNode<E>) node);
        return newPosition(previous, elem);
    }

    public void swap(Position<E> node1, Position<E> node2) {
        E elem1 = node1.getElem();
        ((LinkedList.LinkedNode<E>) node1).setElem(node2.getElem());
        ((LinkedList.LinkedNode<E>) node2).setElem(elem1);
    }

    public Traversal<E> positions() {
        return new LinkedListPositionTraversal<>(this);
    }

    public E update(Position<E> node, E elem) {
        ((LinkedList.LinkedNode<E>) node).setElem(elem);
        return elem;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> it = values();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new LinkedListValueTraversal<>(this);
    }

    private static class LinkedNode<E> implements Position<E> {

        private E elem;
        private LinkedList.LinkedNode<E> next;

        public LinkedNode(E elem) {
            this.elem = elem;
            next = null;
        }

        public void setNext(LinkedList.LinkedNode<E> next) {
            this.next = next;
        }

        public LinkedList.LinkedNode<E> getNext() {
            return next;
        }

        public E getElem() {
            return elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }

    }

}
