
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        private E element;
        private LinkedNode<E> next;
  
        public LinkedNode(E e, LinkedNode<E> n) {
            element = e;
            next = n;
        }
  
        public E getElement() throws IllegalStateException {
            if (next == null) // convention for defunct node
                throw new IllegalStateException("Position no longer valid");
            return element;
        }
  
        public LinkedNode<E> getNext() {
            return next;
        }
  
        public void setNext(LinkedNode<E> n) {
            next = n;
        }
  
        public void setElement(E e) {
            element = e;
        }
    }

    private int size;
    private LinkedNode<E> header;
  
    public LinkedList() {
        size = 0;
        header = new LinkedNode<>(null, null);
    }
  
    private LinkedNode<E> getNode(int index) {
        LinkedNode<E> current = header.getNext();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }
  
    public E delete(Position<E> node) {
        LinkedNode<E> n = (LinkedNode<E>)node;
        LinkedNode<E> p = previous(n);
        p.setNext(n.getNext());
        size--;
        return n.getElement();
    }
  
    public E deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        LinkedNode<E> first = header.getNext();
        header.setNext(first.getNext());
        size--;
        return first.getElement();
    }
  
    public E deleteNext(Position<E> node) {
        LinkedNode<E> n = (LinkedNode<E>)node;
        LinkedNode<E> next = n.getNext();
        if (next == null) {
            throw new NoSuchElementException("No next element");
        }
        n.setNext(next.getNext());
        size--;
        return next.getElement();
    }
  
    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedNode<E> n = (LinkedNode<E>)node;
        LinkedNode<E> newNode = new LinkedNode<>(elem, n.getNext());
        n.setNext(newNode);
        size++;
        return newNode;
    }
  
    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedNode<E> n = (LinkedNode<E>)node;
        LinkedNode<E> newNode = new LinkedNode<>(elem, n);
        LinkedNode<E> p = previous(n);
        p.setNext(newNode);
        size++;
        return newNode;
    }
  
    public Position<E> insertBeginning(E elem) {
        LinkedNode<E> newNode = new LinkedNode<>(elem, header.getNext());
        header.setNext(newNode);
        size++;
        return newNode;
    }
  
    public Position<E> insertEnd(E elem) {
        LinkedNode<E> newNode = new LinkedNode<>(elem, null);
        LinkedNode<E> last = getNode(size - 1);
        last.setNext(newNode);
        size++;
        return newNode;
    }
  
    public boolean isEmpty() {
        return size == 0;
    }
  
    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        LinkedNode<E> newNode = new LinkedNode<>(elem, node.getNext());
        node.setNext(newNode);
        size++;
        return newNode;
    }
  
    public Traversal<E> positions() {
        return new PositionIterator();
    }
  
    protected LinkedNode<E> previous(LinkedNode<E> node) {
        LinkedNode<E> current = header.getNext();
        while (current.getNext() != node) {
            current = current.getNext();
        }
        return current;
    }
  
    public int size() {
        return size;
    }
  
    public void swap(Position<E> node1, Position<E> node2) {
        LinkedNode<E> n1 = (LinkedNode<E>)node1;
        LinkedNode<E> n2 = (LinkedNode<E>)node2;
        LinkedNode<E> p1 = previous(n1);
        LinkedNode<E> p2 = previous(n2);
        LinkedNode<E> t1 = n1.getNext();
        LinkedNode<E> t2 = n2.getNext();
        if (n2 == t1) {
            p1.setNext(n2);
            n2.setNext(n1);
            n1.setNext(t2);
        } else if (n1 == t2) {
            p2.setNext(n1);
            n1.setNext(n2);
            n2.setNext(t1);
        } else {
            p1.setNext(n2);
            n2.setNext(t1);
            p2.setNext(n1);
            n1.setNext(t2);
        }
    }
  
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        Traversal<E> positions = positions();
        while (positions.hasNext()) {
            s.append(positions.next().getElement());
            if (positions.hasNext()) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
  
    public E update(Position<E> node, E elem) {
        LinkedNode<E> n = (LinkedNode<E>)node;
        E old = n.getElement();
        n.setElement(elem);
        return old;
    }
  
    private class ElementIterator implements Iterator<E> {
        private Iterator<Position<E>> posIterator = positions().iterator();
  
        public boolean hasNext() {
            return posIterator.hasNext();
        }
  
        public E next() {
            return posIterator.next().getElement();
        }
  
        public void remove() {
            posIterator.remove();
        }
    }
  
    private class PositionIterator implements Traversal<E> {
        private LinkedNode<E> cursor = header.getNext();
        private LinkedNode<E> recent = null;
  
        public boolean hasNext() {
            return cursor != null;
        }
  
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) {
                throw new NoSuchElementException("No next element");
            }
            recent = cursor;
            cursor = cursor.getNext();
            return recent;
        }
  
        public void remove() throws IllegalStateException {
            if (recent == null) {
                throw new IllegalStateException("Nothing to remove");
            }
            LinkedNode<E> prev = previous(recent);
            prev.setNext(recent.getNext());
            size--;
            recent = null;
        }
    }
  
    public Iterator<E> values() {
        return new ElementIterator();
    }
}
