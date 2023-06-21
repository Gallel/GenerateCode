
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E>{

        private E element;
        private LinkedNode<E> next;

        public LinkedNode(E element, LinkedNode<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (next == null) throw new IllegalStateException("Position no longer valid");
            return element;
        }
        public void setElement(E e) {
            element = e;
        }

        public LinkedNode<E> getNext() {
            return next;
        }
        public void setNext(LinkedNode<E> n) {
            this.next = n;
        }
    }

    private LinkedNode<E> head, tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> insertBeginning(E e) {
        head = new LinkedNode<>(e, head);
        if (size == 0) {
            tail = head;
        }
        size++;
        return head;
    }

    public Position<E> insertEnd(E e) {
        LinkedNode<E> newest = new LinkedNode<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
        return tail;
    }

    public Position<E> insertAfter(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        node.setNext(new LinkedNode<>(e, node.getNext()));
        size++;
        return node.getNext();
    }

    public Position<E> insertBefore(Position<E> p, E e) {
        LinkedNode<E> before = (LinkedNode<E>) validate(p);
        LinkedNode<E> node = new LinkedNode<>(e, before.getNext());
        before.setNext(node);
        size++;
        return node;
    }

    public E deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return answer;
    }

    public E delete(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        LinkedNode<E> predecessor = head;
        if (predecessor == node) {
            return deleteFirst();
        } else {
            while (predecessor.getNext() != node) {
                predecessor = predecessor.getNext();
            }
            predecessor.setNext(node.getNext());
            size--;
        }
        return node.getElement();
    }

    public E deleteNext(Position<E> p) {
        LinkedNode<E> node = validate(p);
        if (node.getNext() == null) throw new IllegalArgumentException("Position has no next.");
        LinkedNode<E> nodeToRemove = node.getNext();
        node.setNext(nodeToRemove.getNext());
        E element = nodeToRemove.getElement();
        nodeToRemove.setNext(null);
        nodeToRemove.setElement(null);
        size--;
        return element;
    }

    public void swap(Position<E> p, Position<E> q) {
        LinkedNode<E> a = validate(p);
        LinkedNode<E> b = validate(q);
        E temp = a.getElement();
        a.setElement(b.getElement());
        b.setElement(temp);
    }

    public E update(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    private LinkedNode<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof LinkedNode)) throw new IllegalArgumentException("Invalid position type.");
        LinkedNode<E> node = (LinkedNode<E>) p; // safe cast
        if (node.getNext() == null) {
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    private class PositionIterator implements Iterator<Position<E>> {

        private Position<E> cursor = first();
        private Position<E> recent = null;

        public boolean hasNext() { return (cursor != null);  }

        public Position<E> next( ) throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        public void remove( ) throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            LinkedList.this.delete(recent);
            recent = null;
        }
    }

    private class ElementIterator implements Iterator<E> {

        Iterator<Position<E>> positionIterator = new PositionIterator();

        public boolean hasNext() { return positionIterator.hasNext(); }

        public E next() throws NoSuchElementException {
            return positionIterator.next().getElement();
        }

        public void remove() throws IllegalStateException {
            positionIterator.remove();
        }
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    public Position<E> first( ) {
        return head;
    }

    public Position<E> last( ) {
        return tail;
    }

    public Position<E> before(Position<E> p ) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        LinkedNode<E> current = head;
        if (current == node) {
            return null;
        }
        while (current.getNext() != node) {
            current = current.getNext();
        }
        return current;
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        return node.getNext();
    }

    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() { return new PositionIterator(); }
    }

    @Override
    public Iterator<E> values() {
        return new ElementIterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (E e : this) {
            s.append(e).append(", ");
        }
        int len = s.length();
        if (len > 1) {
            s.delete(len - 2, len);
        }
        s.append("]");
        return s.toString();
    }
}
