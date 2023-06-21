
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {

        private LinkedNode<E> prev, next;
        private E elem;

        public LinkedNode(LinkedNode<E> p, LinkedNode<E> n, E e) {
            prev = p;
            next = n;
            elem = e;
        }

        public LinkedNode<E> getNext() {
            return next;
        }

        public LinkedNode<E> getPrev() {
            return prev;
        }

        public void setNext(LinkedNode<E> newNext) {
            next = newNext;
        }

        public void setPrev(LinkedNode<E> newPrev) {
            prev = newPrev;
        }

        public void setElement(E e) {
            elem = e;
        }

        public E getElement() {
            return elem;
        }
    }

    private LinkedNode<E> header;
    private LinkedNode<E> trailer;
    private int size = 0;

    public LinkedList() {
        header = new LinkedNode<>(null, null, null);
        trailer = new LinkedNode<>(header, null, null);
        header.setNext(trailer);
    }

    protected LinkedNode<E> checkPosition(Position<E> p)
            throws IllegalArgumentException {
        if (p == null || !(p instanceof LinkedNode)) {
            throw new IllegalArgumentException("Invalid position");
        }
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node.getNext() == null) {
            throw new IllegalArgumentException(
            "Position is not in the list anymore");
        }
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> first() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        return header.getNext();
    }

    public Position<E> prev(Position<E> p) throws IllegalArgumentException,
            BoundaryViolationException {
        LinkedNode<E> node = checkPosition(p);
        LinkedNode<E> prev = node.getPrev();
        if (prev == header) {
            throw new BoundaryViolationException("Cannot move back");
        }
        return prev;
    }

    public Position<E> next(Position<E> p) throws IllegalArgumentException,
            BoundaryViolationException {
        LinkedNode<E> node = checkPosition(p);
        LinkedNode<E> next = node.getNext();
        if (next == trailer) {
            throw new BoundaryViolationException("Cannot move forward");
        }
        return next;
    }

    public Position<E> last() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        return trailer.getPrev();
    }

    public void addFirst(E e) {
        header.setNext(new LinkedNode<>(header, header.getNext(), e));
        size++;
    }

    public void addLast(E e) {
        trailer.setPrev(new LinkedNode<>(trailer.getPrev(), trailer, e));
        size++;
    }

    public void addAfter(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = checkPosition(p);
        LinkedNode<E> newNode = new LinkedNode<>(node, node.getNext(), e);
        node.getNext().setPrev(newNode);
        node.setNext(newNode);
        size++;
    }

    public void addBefore(Position<E> p, E e)
            throws IllegalArgumentException {
        LinkedNode<E> node = checkPosition(p);
        LinkedNode<E> newNode = new LinkedNode<>(node.getPrev(), node, e);
        node.getPrev().setNext(newNode);
        node.setPrev(newNode);
        size++;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = checkPosition(p);
        LinkedNode<E> prev = node.getPrev();
        LinkedNode<E> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return node.getElement();
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = checkPosition(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    public Iterator<E> iterator() {
        return new ElementIterator<>(this);
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable<>(this);
    }

    private static class ElementIterator<E> implements Iterator<E> {

        private Iterator<Position<E>> it;

        public ElementIterator(LinkedList<E> list) {
            it = list.positions().iterator();
        }

        public boolean hasNext() {
            return it.hasNext();
        }

        public E next() {
            return it.next().getElement();
        }

        public void remove() {
            it.remove();
        }
    }

    private static class PositionIterable<E> implements Iterable<Position<E>> {

        private LinkedList<E> list;

        public PositionIterable(LinkedList<E> list) {
            this.list = list;
        }

        public Iterator<Position<E>> iterator() {
            return new PositionIterator<>(list);
        }
    }

    private static class PositionIterator<E> implements Iterator<Position<E>> {

        private Position<E> cursor;
        private LinkedList<E> list;

        public PositionIterator(LinkedList<E> list) {
            this.list = list;
            cursor = (list.isEmpty() ? null : list.first());
        }

        public boolean hasNext() {
            return cursor != null;
        }

        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) {
                throw new NoSuchElementException("No next position");
            }
            Position<E> current = cursor;
            cursor = (cursor == list.last() ? null : list.next(cursor));
            return current;
        }

        public void remove() throws IllegalStateException {
            throw new IllegalStateException("Cannot remove");
        }
    }
}
