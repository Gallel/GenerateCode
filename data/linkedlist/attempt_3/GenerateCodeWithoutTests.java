
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E>{
        private E element;
        private LinkedNode<E> next;
        private LinkedNode<E> prev;
        
        public LinkedNode(LinkedNode<E> prev,LinkedNode<E> next,E element){
            this.prev = prev;
            this.next = next;
            this.element = element;
        }

        public E getElement() throws IllegalStateException {
            return element;
        }

        public LinkedNode<E> getNext(){
            return next;
        }

        public LinkedNode<E> getPrev(){
            return prev;
        }

        public void setNext(LinkedNode<E> next){
            this.next = next;
        }

        public void setPrev(LinkedNode<E> prev){
            this.prev = prev;
        }

        public void setElement(E element){
            this.element = element;
        }

    }

    private LinkedNode<E> header;
    private LinkedNode<E> trailer;
    private int size;

    public LinkedList() {
        header = new LinkedNode<>(null, null, null);
        trailer = new LinkedNode<>(header, null, null);
        header.setNext(trailer);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> first() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }

        return header.getNext();
    }

    public Position<E> last() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }

        return trailer.getPrev();
    }

    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    private Position<E> addBetween(E e, LinkedNode<E> pred, LinkedNode<E> succ) {
        LinkedNode<E> newest = new LinkedNode<>(pred, succ, e);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    public E remove(Position<E> p) throws RuntimeException {
        LinkedNode<E> node = (LinkedNode<E>) p;
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

    public E set(Position<E> p, E e) throws RuntimeException {
        LinkedNode<E> node = (LinkedNode<E>) p;
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    private class LinkedPositionalListIterator implements Iterator<Position<E>> {

        private Position<E> cursor = first();
        private Position<E> recent = null;

        public boolean hasNext() {
            return (cursor != null);
        }

        public Position<E> next() {
            if (cursor == null) {
                throw new RuntimeException("Nothing left to iterate.");
            }
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        public void remove() throws RuntimeException {
            if (recent == null) {
                throw new RuntimeException("Nothing to remove.");
            }
            LinkedNode<E> predecessor = ((LinkedNode<E>) recent).getPrev();
            LinkedNode<E> successor = ((LinkedNode<E>) recent).getNext();
            predecessor.setNext(successor);
            successor.setPrev(predecessor);
            size--;
            recent = null;
        }
    }

    public Iterator<Position<E>> iterator() {
        return new LinkedPositionalListIterator();
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    private class PositionIterable implements Iterable<Position<E>> {

        public Iterator<Position<E>> iterator() {
            return new LinkedPositionalListIterator();
        }

        public Iterable<E> values() {
            return new ValueIterable();
        }
    }

    private class ValueIterator implements Iterator<E> {

        Iterator<Position<E>> positionIterator = new LinkedPositionalListIterator();

        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        public E next() {
            return positionIterator.next().getElement();
        }

        public void remove() {
            positionIterator.remove();
        }
    }

    private class ValueIterable implements Iterable<E> {

        public Iterator<E> iterator() {
            return new ValueIterator();
        }
    }

    private Position<E> position(LinkedNode<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }

    private LinkedNode<E> validate(Position<E> p) throws RuntimeException {
        if (!(p instanceof LinkedNode)) {
            throw new RuntimeException("Invalid position type.");
        }
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node.getNext() == null) {
            throw new RuntimeException("Position no longer in the list.");
        }
        return node;
    }

    private Position<E> addBefore(Position<E> p, E e) {
        LinkedNode<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    private Position<E> addAfter(Position<E> p, E e) {
        LinkedNode<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    private Position<E> position(int index) {
        LinkedNode<E> node;

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        if (index < size / 2) {
            node = header.getNext();
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        } else {
            node = trailer;
            for (int i = size; i > index; i--) {
                node = node.getPrev();
            }
        }

        return node;
    }

    public E get(int index) throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }

        return position(index).getElement();
    }

    public E remove(int index) throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }

        return remove(position(index));
    }

    public E set(int index, E e) throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }

        return set(position(index), e);
    }

    public void add(int index, E e) throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("List is empty.");
        }

        addBefore(position(index), e);
    }

}
