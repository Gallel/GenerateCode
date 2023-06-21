
public class LinkedList<E> extends Object implements List<E> {

    private int size;
    private LinkedList.LinkedNode<E> first;
    private LinkedList.LinkedNode<E> last;

    public LinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E delete(Position<E> node) throws InvalidPositionException {
        LinkedList.LinkedNode<E> n = checkPosition(node);
        return removeNode(n);
    }

    @Override
    public E deleteFirst() throws EmptyContainerException {
        if (isEmpty()) throw new EmptyContainerException();
        return removeNode(first);
    }

    @Override
    public E deleteNext(Position<E> node) throws InvalidPositionException {
        LinkedList.LinkedNode<E> n = checkPosition(node);
        if (n.getNext() == null) throw new InvalidPositionException();
        return removeNode(n.getNext());
    }

    @Override
    public Position<E> insertAfter(Position<E> node, E elem) throws InvalidPositionException {
        LinkedList.LinkedNode<E> n = checkPosition(node);
        return addAfter(n, elem);
    }

    @Override
    public Position<E> insertBefore(Position<E> node, E elem) throws InvalidPositionException {
        LinkedList.LinkedNode<E> n = checkPosition(node);
        return addBefore(n, elem);
    }

    @Override
    public Position<E> insertBeginning(E elem) {
        return addFirst(elem);
    }

    @Override
    public Position<E> insertEnd(E elem) {
        return addLast(elem);
    }

    @Override
    public Traversal<E> positions() {
        return new PositionListTraversal<>(this);
    }

    @Override
    public void swap(Position<E> node1, Position<E> node2) throws InvalidPositionException {
        LinkedList.LinkedNode<E> n1 = checkPosition(node1);
        LinkedList.LinkedNode<E> n2 = checkPosition(node2);
        swapNodes(n1, n2);
    }

    @Override
    public E update(Position<E> node, E elem) throws InvalidPositionException {
        LinkedList.LinkedNode<E> n = checkPosition(node);
        E oldElem = n.getElem();
        n.setElem(elem);
        return oldElem;
    }

    private LinkedList.LinkedNode<E> newNode(E elem) {
        LinkedList.LinkedNode<E> n = new LinkedList.LinkedNode<>();
        n.setElem(elem);
        n.setNext(null);
        n.setPrevious(null);
        return n;
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        if (node == first) return null;
        return node.getPrevious();
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        LinkedList.LinkedNode<E> n = newNode(elem);
        n.setNext(node);
        if (node == null) {
            n.setPrevious(null);
            last = n;
        } else {
            n.setPrevious(node.getPrevious());
            node.setPrevious(n);
        }
        if (n.getPrevious() != null) n.getPrevious().setNext(n);
        if (first == null) first = n;
        size++;
        return n;
    }

    private Position<E> addLast(E elem) {
        return newPosition(last, elem);
    }

    private Position<E> addFirst(E elem) {
        return newPosition(first, elem);
    }

    private Position<E> addBefore(LinkedList.LinkedNode<E> node, E elem) {
        LinkedList.LinkedNode<E> newNode = newPosition(node, elem);
        return newNode;
    }

    private Position<E> addAfter(LinkedList.LinkedNode<E> node, E elem) {
        LinkedList.LinkedNode<E> newNode = newPosition(node.getNext(), elem);
        return newNode;
    }

    private E removeNode(LinkedList.LinkedNode<E> node) {
        E elem = node.getElem();
        if (node == first) {
            first = node.getNext();
        } else {
            node.getPrevious().setNext(node.getNext());
        }
        if (node == last) {
            last = node.getPrevious();
        } else {
            node.getNext().setPrevious(node.getPrevious());
        }
        size--;
        return elem;
    }

    private void swapNodes(LinkedList.LinkedNode<E> n1, LinkedList.LinkedNode<E> n2) {
        if (n1 == n2) return;
        E elem1 = n1.getElem();
        n1.setElem(n2.getElem());
        n2.setElem(elem1);
    }

    private LinkedList.LinkedNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
        if (p == null || !(p instanceof LinkedList.LinkedNode)) {
            throw new InvalidPositionException();
        }
        LinkedList.LinkedNode<E> n = (LinkedList.LinkedNode<E>) p;
        if (n.getContainer() != this) {
            throw new InvalidPositionException();
        }
        return n;
    }

    @Override
    public Iterator<E> values() {
        return new LinkedListIterator<>(first);
    }

    private static class LinkedNode<E> extends PositionListLinkedNode<E> {

        private LinkedList.LinkedNode<E> previous;
        private LinkedList.LinkedNode<E> next;

        LinkedList.LinkedNode<E> getPrevious() {
            return previous;
        }

        void setPrevious(LinkedList.LinkedNode<E> p) {
            previous = p;
        }

        LinkedList.LinkedNode<E> getNext() {
            return next;
        }

        void setNext(LinkedList.LinkedNode<E> n) {
            next = n;
        }

    }

}
