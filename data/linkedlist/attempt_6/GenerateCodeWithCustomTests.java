
public class LinkedList<E> extends Object implements List<E> {
    
    protected static class LinkedNode<E> implements Position<E> {
        E element;
        LinkedNode<E> next;
        LinkedNode<E> prev;
        
        public LinkedNode(E element, LinkedNode<E> next, LinkedNode<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
        
        @Override
        public E getElem() {
            return element;
        }
        
        public void setElem(E e) {
            element = e;
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
    
    LinkedNode<E> header;
    LinkedNode<E> trailer;
    int size;
    
    public LinkedList() {
        header = new LinkedNode<>(null, null, null);
        trailer = new LinkedNode<>(null, null, header);
        header.setNext(trailer);
        size = 0;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public Position<E> insertBeginning(E elem) {
        return insertAfter(header, elem);
    }
    
    @Override
    public Position<E> insertEnd(E elem) {
        return insertBefore(trailer, elem);
    }
    
    @Override
    public Position<E> insertAfter(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> ln = checkPosition(node);
        LinkedNode<E> newNode = newPosition(ln, elem);
        newNode.setNext(ln.getNext());
        newNode.getNext().setPrev(newNode);
        newNode.setPrev(ln);
        ln.setNext(newNode);
        size++;
        return position(newNode);
    }
    
    @Override
    public Position<E> insertBefore(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> ln = checkPosition(node);
        return insertAfter(ln.getPrev(), elem);
    }
    
    @Override
    public E delete(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> ln = checkPosition(node);
        E elem = ln.getElem();
        ln.getNext().setPrev(ln.getPrev());
        ln.getPrev().setNext(ln.getNext());
        size--;
        return elem;
    }
    
    @Override
    public E deleteFirst() throws EmptyContainerException {
        if (isEmpty()) {
            throw new EmptyContainerException("The list is empty");
        }
        return delete(header.getNext());
    }
    
    @Override
    public E deleteNext(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> ln = checkPosition(node);
        if (ln.getNext() == trailer) {
            throw new InvalidPositionException("There is no next position");
        }
        return delete(ln.getNext());
    }
    
    @Override
    public E update(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> ln = checkPosition(node);
        E oldElem = ln.getElem();
        ln.setElem(elem);
        return oldElem;
    }
    
    @Override
    public Traversal<E> positions() {
        return new PositionIterator<>();
    }
    
    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        return new LinkedNode<>(elem, node.getNext(), node);
    }
    
    protected LinkedNode<E> checkPosition(Position<E> node) throws InvalidPositionException {
        if (node == null || !(node instanceof LinkedNode)) {
            throw new InvalidPositionException("Invalid position");
        }
        LinkedNode<E> ln = (LinkedNode<E>) node;
        if (ln.getNext() == null || ln.getPrev() == null) {
            throw new InvalidPositionException("Position is no longer available");
        }
        return ln;
    }
    
    @Override
    public Iterator<E> values() {
        return new ValueIterator<>();
    }
    
    protected Position<E> position(LinkedNode<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }
    
    protected LinkedNode<E> previous(LinkedNode<E> node) {
        if (node == header) {
            return null;
        }
        return node.getPrev();
    }
    
    @Override
    public void swap(Position<E> node1, Position<E> node2) throws InvalidPositionException {
        LinkedNode<E> ln1 = checkPosition(node1);
        LinkedNode<E> ln2 = checkPosition(node2);
        E auxElem = ln1.getElem();
        ln1.setElem(ln2.getElem());
        ln2.setElem(auxElem);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Position<E> p : positions()) {
            sb.append(p.getElem().toString());
            if (p != trailer) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    protected class PositionIterator<T> implements Traversal<T> {
        LinkedNode<T> current = (LinkedNode<T>) header.getNext();
        
        @Override
        public boolean hasNext() {
            return current != trailer;
        }
        
        @Override
        public Position<T> next() {
            if (current == trailer) {
                return null;
            }
            LinkedNode<T> ret = current;
            current = current.getNext();
            return ret;
        }
    }
    
    protected class ValueIterator<V> implements Iterator<V> {
        PositionIterator<E> it = new PositionIterator<>();
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public V next() {
            return (V) it.next().getElem();
        }
    }
}
