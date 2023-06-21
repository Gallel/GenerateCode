
public class LinkedList<E> extends Object implements List<E> {

    protected LinkedList.LinkedNode<E> header;
    protected int size;

    public LinkedList() {
        header = new LinkedList.LinkedNode<>(null, null, null);
        header.setNext(header);
        header.setPrevious(header);
        size = 0;
    }

    public E delete(Position<E> node) {
        LinkedList.LinkedNode<E> p = checkPosition(node);
        return delete(p);
    }

    private E delete(LinkedList.LinkedNode<E> p) {
        p.getPrevious().setNext(p.getNext());
        p.getNext().setPrevious(p.getPrevious());
        size--;
        return p.getElem();
    }

    public E deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        LinkedList.LinkedNode<E> p = header.getNext();
        return delete(p);
    }

    public E deleteNext(Position<E> node) {
        LinkedList.LinkedNode<E> p = checkPosition(node);
        if (p.getNext() == header) {
            throw new InvalidPositionException("Invalid position");
        }
        return delete(p.getNext());
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> p = checkPosition(node);
        return newPosition(p, elem);
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> p = checkPosition(node);
        return newPosition(p.getPrevious(), elem);
    }

    public Position<E> insertBeginning(E elem) {
        return newPosition(header, elem);
    }

    public Position<E> insertEnd(E elem) {
        return newPosition(header.getPrevious(), elem);
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        LinkedList.LinkedNode<E> newNode = new LinkedList.LinkedNode<>(node, node.getNext(), elem);
        node.getNext().setPrevious(newNode);
        node.setNext(newNode);
        size++;
        return newNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected LinkedList.LinkedNode<E> checkPosition(Position<E> node) throws InvalidPositionException {
        if (node == null || !(node instanceof LinkedList.LinkedNode)) {
            throw new InvalidPositionException("Invalid position");
        }
        LinkedList.LinkedNode<E> p = (LinkedList.LinkedNode<E>) node;
        if (p.isHeader()) {
            throw new InvalidPositionException("Invalid position");
        }
        return p;
    }

    public Traversal<E> positions() {
        return new LinkedListTraversal<>(this);
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        if (node == header || node.getPrevious() == header) {
            return null;
        }
        return node.getPrevious();
    }

    public int size() {
        return size;
    }

    public void swap(Position<E> node1, Position<E> node2) {
        LinkedList.LinkedNode<E> p = checkPosition(node1);
        LinkedList.LinkedNode<E> q = checkPosition(node2);

        E temp = p.getElem();
        p.setElem(q.getElem());
        q.setElem(temp);
    }

    public String toString() {
        String className = getClass().getName();
        return className.substring(className.lastIndexOf('.') + 1) + "@" + hashCode() + "(size=" + size + ")";
    }

    public E update(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> p = checkPosition(node);
        E temp = p.getElem();
        p.setElem(elem);
        return temp;
    }

    public Iterator<E> values() {
        return new LinkedListIterator<>(this);
    }

    class LinkedNode<T> implements Position<T> {
        private final T elem;
        private LinkedList.LinkedNode<T> previous;
        private LinkedList.LinkedNode<T> next;

        LinkedNode(LinkedList.LinkedNode<T> previous, LinkedList.LinkedNode<T> next, T elem) {
            this.elem = elem;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public T getElem() {
            return elem;
        }

        public LinkedList.LinkedNode<T> getPrevious() {
            return previous;
        }

        public void setPrevious(LinkedList.LinkedNode<T> previous) {
            this.previous = previous;
        }

        public LinkedList.LinkedNode<T> getNext() {
            return next;
        }

        public void setNext(LinkedList.LinkedNode<T> next) {
            this.next = next;
        }

        boolean isHeader() {
            return elem == null;
        }
    }

    private static class LinkedListIterator<E> implements Iterator<E> {
        LinkedList<E> list;
        Position<E> current;

        LinkedListIterator(LinkedList<E> list) {
            this.list = list;
            current = list.header;
        }

        public boolean hasNext() {
            return current.getNext() != list.header;
        }

        public E next() {
            current = current.getNext();
            return current.getElem();
        }
    }

    private static class LinkedListTraversal<E> implements Traversal<E> {
        LinkedList.LinkedNode<E> current;

        LinkedListTraversal(LinkedList<E> list) {
            current = list.header;
        }

        public boolean hasNext() {
            return current.getNext() != null && !current.getNext().isHeader();
        }

        public Position<E> next() {
            current = current.getNext();
            return current;
        }
    }

}
