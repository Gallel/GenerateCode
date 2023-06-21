
public class LinkedList<E> extends Object implements List<E> {
    
    protected static class LinkedNode<E> implements Position<E> {
        private E element;
        private LinkedNode<E> next;
        
        public LinkedNode(E elem, LinkedNode<E> next) {
            this.element = elem;
            this.next = next;
        }
        
        public E getElement() throws IllegalStateException {
            if (next == null)
                throw new IllegalStateException("Position no longer valid");
            return element;
        }
        
        public void setElement(E e) {
            element = e;
        }
        
        public LinkedNode<E> getNext() {
            return next;
        }
        
        public void setNext(LinkedNode<E> node) {
            next = node;
        }
    }
    
    private LinkedNode<E> head = null;
    private LinkedNode<E> tail = null;
    private int size = 0;
    
    public LinkedList() {
        
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public Position<E> first() {
        if (isEmpty()) return null;
        return head;
    }
    
    public Position<E> last() {
        if (isEmpty()) return null;
        return tail;
    }
    
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node == head) return null;
        LinkedNode<E> current = head;
        while (current.getNext() != node) {
            current = current.getNext();
        }
        return current;
    }
    
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node == tail) return null;
        return node.getNext();
    }
    
    public E replace(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = (LinkedNode<E>) p;
        E replaced = node.getElement();
        node.setElement(e);
        return replaced;
    }
    
    public Position<E> insertFirst(E e) {
        size++;
        LinkedNode<E> newest = new LinkedNode<>(e, head);
        if (isEmpty())
            tail = newest;
        head = newest;
        return newest;
    }
    
    public Position<E> insertLast(E e) {
        size++;
        LinkedNode<E> newest = new LinkedNode<>(e, null);
        if (isEmpty())
            head = newest;
        else
            tail.setNext(newest);
        tail = newest;
        return newest;
    }
    
    public Position<E> insertBefore(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node == head) {
            return insertFirst(e);
        }
        size++;
        LinkedNode<E> newest = new LinkedNode<>(e, node);
        LinkedNode<E> before = (LinkedNode<E>) before(node);
        before.setNext(newest);
        return newest;
    }
    
    public Position<E> insertAfter(Position<E> p, E e) throws IllegalArgumentException {
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node == tail) {
            return insertLast(e);
        }
        size++;
        LinkedNode<E> newest = new LinkedNode<>(e, node.getNext());
        node.setNext(newest);
        return newest;
    }
    
    public E remove(Position<E> p) throws IllegalArgumentException {
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node == head) {
            head = node.getNext();
        } else {
            LinkedNode<E> before = (LinkedNode<E>) before(node);
            before.setNext(node.getNext());
        }
        if (node == tail) {
            tail = (LinkedNode<E>) before(node);
        }
        size--;
        E removed = node.getElement();
        node.setElement(null);
        node.setNext(null);
        return removed;
    }
    
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        LinkedNode<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current != tail) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append(")");
        return sb.toString();
    }
    
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> positionIterator = new PositionIterator();
        
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
    
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();
        private Position<E> recent = null;
        
        public boolean hasNext() {
            return cursor != null;
        }
        
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("Nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }
        
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("Nothing to remove");
            LinkedList.this.remove(recent);
            recent = null;
        }
    }
    
    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
}
