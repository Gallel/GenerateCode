
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        private E element;
        private LinkedNode<E> next;
        public E getElement(){
            return element;
        }
        public void setElement(E element){
            this.element = element;
        }
        public LinkedNode<E> getNext(){
            return next;
        }
        public void setNext(LinkedNode<E> next){
            this.next = next;
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

    public E delete(Position<E> node) {
        LinkedNode<E> current = (LinkedNode<E>) node;
        if (current == null) {
            throw new RuntimeException("Invalid node");
        }
        if (current == head) {
            return deleteFirst();
        }
        LinkedNode<E> previous = previous(current);
        previous.setNext(current.getNext());
        size--;
        return current.getElement();
    }

    public E deleteFirst() {
        if (isEmpty()){
            throw new RuntimeException("Empty list");
        }
        E element = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return element;
    }

    public E deleteNext(Position<E> node) {
        LinkedNode<E> current = (LinkedNode<E>) node;
        if (current == null || current.getNext() == null) {
            throw new RuntimeException("Invalid node");
        }
        LinkedNode<E> next = current.getNext();
        current.setNext(next.getNext());
        size--;
        if (next == tail) {
            tail = current;
        }
        return next.getElement();
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedNode<E> current = (LinkedNode<E>) node;
        LinkedNode<E> newNode = newPosition(current.getNext(), elem);
        current.setNext(newNode);
        if (tail == current) {
            tail = newNode;
        }
        size++;
        return newNode;
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedNode<E> current = (LinkedNode<E>) node;
        LinkedNode<E> newNode = newPosition(current, elem);
        if (current == head) {
            head = newNode;
        } else {
            LinkedNode<E> previous = previous(current);
            previous.setNext(newNode);
        }
        size++;
        return newNode;
    }

    public Position<E> insertBeginning(E elem) {
        LinkedNode<E> newNode = newPosition(head, elem);
        head = newNode;
        if (isEmpty()) {
            tail = head;
        }
        size++;
        return newNode;
    }

    public Position<E> insertEnd(E elem) {
        LinkedNode<E> newNode = newPosition(null, elem);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
        return newNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> next, E elem) {
        return new LinkedNode<E>();
    }

    public Traversal<E> positions() {
        return new LinkedPositionalTraversal();
    }

    protected LinkedNode<E> previous(LinkedNode<E> node) {
        LinkedNode<E> current = head;
        while (current.getNext() != node) {
            current = current.getNext();
        }
        return current;
    }

    public int size() {
        return size;
    }

    public void swap(Position<E> node1, Position<E> node2) {
        LinkedNode<E> nodeA = (LinkedNode<E>) node1;
        LinkedNode<E> nodeB = (LinkedNode<E>) node2;
        E temp = nodeA.getElement();
        nodeA.setElement(nodeB.getElement());
        nodeB.setElement(temp);
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

    public E update(Position<E> node, E elem) {
        LinkedNode<E> current = (LinkedNode<E>) node;
        E old = current.getElement();
        current.setElement(elem);
        return old;
    }

    public Iterator<E> values() {
        ArrayList<E> list = new ArrayList<>();
        LinkedNode<E> current = head;
        while (current != null) {
            list.add(current.getElement());
            current = current.getNext();
        }
        return list.iterator();
    }

    private class LinkedPositionalTraversal implements Traversal<E> {

        private Position<E> current = head;

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            E element = current.getElement();
            current = ((LinkedNode<E>)current).getNext();
            return element;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
