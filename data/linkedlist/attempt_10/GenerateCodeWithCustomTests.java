
public class LinkedList<E> extends Object implements List<E> {
    
    protected static class LinkedNode<E> implements Position<E> {
        private E elem;
        private LinkedNode<E> next;
        private LinkedNode<E> prev;

        public LinkedNode(E elem, LinkedNode<E> prev, LinkedNode<E> next) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }

        public E getElem() {
            return elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }

        public LinkedNode<E> getNext() {
            return next;
        }

        public void setNext(LinkedNode<E> next) {
            this.next = next;
        }

        public LinkedNode<E> getPrev() {
            return prev;
        }

        public void setPrev(LinkedNode<E> prev) {
            this.prev = prev;
        }
    }

    private int size;
    private LinkedNode<E> header;
    private LinkedNode<E> trailer;

    public LinkedList() {
        header = new LinkedNode<>(null, null, null);
        trailer = new LinkedNode<>(null, header, null);
        header.setNext(trailer);
        size = 0;
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
    public Iterator<E> values() {
        return new LinkedListIterator();
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        LinkedNode<E> newNode = new LinkedNode<>(elem, node, node.getNext());
        node.getNext().setPrev(newNode);
        node.setNext(newNode);
        size++;
        return newNode;
    }

    @Override
    public Position<E> insertAfter(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> n = checkPosition(node);
        return newPosition(n, elem);
    }

    @Override
    public Position<E> insertBeginning(E elem) {
        return newPosition(header, elem);
    }

    @Override
    public Position<E> insertEnd(E elem) {
        return newPosition(trailer.getPrev(), elem);
    }

    @Override
    public E delete(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> n = checkPosition(node);
        LinkedNode<E> prev = n.getPrev();
        LinkedNode<E> next = n.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        E elem = n.getElem();
        n.setElem(null);
        n.setNext(null);
        n.setPrev(null);
        return elem;
    }

    @Override
    public E deleteFirst() throws EmptyContainerException {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return delete(header.getNext());
    }

    @Override
    public E deleteNext(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> n = checkPosition(node);
        if (n.getNext() == trailer) {
            throw new InvalidPositionException();
        }
        return delete(n.getNext());
    }

    @Override
    public E update(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> n = checkPosition(node);
        E oldElem = n.getElem();
        n.setElem(elem);
        return oldElem;
    }

    @Override
    public void swap(Position<E> node1, Position<E> node2) throws InvalidPositionException {
        LinkedNode<E> n1 = checkPosition(node1);
        LinkedNode<E> n2 = checkPosition(node2);
        E temp = n1.getElem();
        n1.setElem(n2.getElem());
        n2.setElem(temp);
    }

    @Override
    public Traversal<E> positions() {
        return new LinkedListTraversal();
    }

    protected LinkedNode<E> checkPosition(Position<E> node) throws InvalidPositionException {
        if (node == null || !(node instanceof LinkedNode)) {
            throw new InvalidPositionException();
        }
        LinkedNode<E> n = (LinkedNode<E>) node;
        if (n.getNext() == null && n.getPrev() == null) {
            throw new InvalidPositionException();
        }
        return n;
    }

    protected class LinkedListIterator implements Iterator<E> {
        private LinkedNode<E> current = header.getNext();

        @Override
        public boolean hasNext() {
            return current != trailer;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list");
            }
            E elem = current.getElem();
            current = current.getNext();
            return elem;
        }
    }

    protected class LinkedListTraversal implements Traversal<E> {
        private LinkedNode<E> current = header.getNext();

        @Override
        public boolean hasNext() {
            return current != trailer;
        }

        @Override
        public Position<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more positions in the list");
            }
            LinkedNode<E> n = current;
            current = current.getNext();
            return n;
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
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
}
