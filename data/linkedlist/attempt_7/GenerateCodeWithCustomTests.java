
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        private E elem;
        private LinkedNode<E> next;
        private LinkedNode<E> prev;

        public LinkedNode(E elem, LinkedNode<E> prev, LinkedNode<E> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
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

    private LinkedNode<E> head;
    private LinkedNode<E> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public Position<E> insertBeginning(E elem) {
        LinkedNode<E> newnode = newPosition(head, elem);
        if (tail == null)
            tail = newnode;
        if (head != null)
            head.setPrev(newnode);
        head = newnode;
        ++size;
        return newnode;
    }

    public Position<E> insertEnd(E elem) {
        LinkedNode<E> newnode = newPosition(tail, elem);
        if (head == null)
            head = newnode;
        if (tail != null)
            tail.setNext(newnode);
        tail = newnode;
        ++size;
        return newnode;
    }

    public E delete(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> v = checkPosition(node);
        LinkedNode<E> vprev = v.getPrev();
        LinkedNode<E> vnext = v.getNext();
        if (vprev != null)
            vprev.setNext(vnext);
        else
            head = vnext;
        if (vnext != null)
            vnext.setPrev(vprev);
        else
            tail = vprev;
        --size;
        E velem = v.getElem();
        // unlink the position from the list
        v.setNext(null);
        v.setPrev(null);
        return velem;
    }

    public E deleteFirst() throws EmptyContainerException {
        if (isEmpty())
            throw new EmptyContainerException("list is empty");
        return delete(head);
    }

    public E deleteNext(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> v = checkPosition(node);
        if (v == tail)
            throw new InvalidPositionException("cannot delete after tail");

        return delete(v.getNext());
    }

    public Position<E> insertBefore(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> p = checkPosition(node);
        if (p == head)
            return insertBeginning(elem);

        LinkedNode<E> newnode = newPosition(p.getPrev(), elem);
        LinkedNode<E> pp = p.getPrev();
        newnode.setPrev(pp);
        newnode.setNext(p);
        pp.setNext(newnode);
        p.setPrev(newnode);
        ++size;
        return newnode;
    }

    public Position<E> insertAfter(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> p = checkPosition(node);
        if (p == tail)
            return insertEnd(elem);

        LinkedNode<E> newnode = newPosition(p, elem);
        LinkedNode<E> np = p.getNext();
        newnode.setPrev(p);
        newnode.setNext(np);
        np.setPrev(newnode);
        p.setNext(newnode);
        ++size;
        return newnode;
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> pos, E elem) {
        return new LinkedNode<>(elem, pos, pos.getNext());
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Traversal<E> positions() {
        return new LinkedPositionListTraversal(head, tail);
    }

    private LinkedNode<E> checkPosition(Position<E> node) throws InvalidPositionException {
        if (node == null || !(node instanceof LinkedNode))
            throw new InvalidPositionException("The position is invalid");
        LinkedNode<E> n = (LinkedNode<E>) node;
        if (n.getNext() == null)
            throw new InvalidPositionException("Position no longer valid");
        return n;
    }

    protected LinkedNode<E> previous(LinkedNode<E> node) {
        return node.getPrev();
    }

    public void swap(Position<E> node1, Position<E> node2) throws InvalidPositionException {
        E temp = node1.getElem();
        update(node1, node2.getElem());
        update(node2, temp);
    }

    public E update(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> p = checkPosition(node);
        E oldElem = p.getElem();
        p.setElem(elem);
        return oldElem;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        LinkedNode<E> p = head;
        while (p != null) {
            s.append(p.getElem());
            if (p != tail)
                s.append(", ");
            p = p.getNext();
        }
        s.append("]");
        return s.toString();
    }

    public Iterator<E> values() {
        return new LinkedPositionListIterator(head, tail);
    }
}
