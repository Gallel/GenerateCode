
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        private E element;
        private LinkedNode<E> next;
        private LinkedNode<E> prev;

        public LinkedNode(E e, LinkedNode<E> n, LinkedNode<E> p) {
            element = e;
            next = n;
            prev = p;
        }

        public E getElement() throws IllegalStateException {
            if (next == null)
                throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public LinkedNode<E> getNext() {
            return next;
        }

        public LinkedNode<E> getPrev() {
            return prev;
        }
        public void setElement(E e) {
            element = e;
        }

        public void setNext(LinkedNode<E> n) {
            next = n;
        }

        public void setPrev(LinkedNode<E> p) {
            prev = p;
        }
    }

    private LinkedNode<E> header;
    private LinkedNode<E> trailer;
    private int size = 0;

    public LinkedList() {
        header = new LinkedNode<>(null, null, null);
        trailer = new LinkedNode<>(null, header, null);
        header.setNext(trailer);
    }

    private LinkedNode<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof LinkedNode))
            throw new IllegalArgumentException("Invalid position type");
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node.getNext() == null)
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> first() {
        if (isEmpty()) return null;
        return header.getNext();
    }

    public Position<E> last() {
        if (isEmpty()) return null;
        return trailer.getPrev();
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        return node.getPrev();
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        return node.getNext();
    }

    private Position<E> addBetween(E e, LinkedNode<E> pred, LinkedNode<E> succ) {
        LinkedNode<E> newest = new LinkedNode<>(e, succ, pred);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = validate(p);
        LinkedNode<E> predecessor = node.getPrev();
        LinkedNode<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext() { return posIterator.hasNext(); }
        public E next() { return posIterator.next().getElement(); }
        public void remove() { posIterator.remove(); }
    }

    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first(); 
        private Position<E> recent = null;  
        public boolean hasNext() { return (cursor != null); }
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            LinkedList.this.remove(recent);         // remove from outer list
            recent = null;                      // do not allow remove again until next is called
        }
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() { return new PositionIterator(); }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (Position<E> p : positions())
            sb.append(p.getElement()).append(", ");
        sb.append(")");
        return sb.toString();
    }
}
