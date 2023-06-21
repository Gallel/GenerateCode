
public class LinkedList<E> extends Object implements List<E>{

    private LinkedNode<E> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public E delete(Position<E> node) {
        LinkedNode<E> n = validate(node);
        LinkedNode<E> prev = previous(n);
        LinkedNode<E> next = n.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        size--;
        return n.element;
    }

    public E deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        return delete(head);
    }

    public E deleteNext(Position<E> node) {
        LinkedNode<E> n = validate(node);
        if (n.next == null) {
            return null;
        }
        return delete(n.next);
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedNode<E> current = validate(node);
        LinkedNode<E> newNode = new LinkedNode<>(elem, current.next);

        current.next = newNode;
        size++;
        return newNode;
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedNode<E> current = validate(node);
        LinkedNode<E> newNode = new LinkedNode<>(elem, current);

        if (current == head) {
            head = newNode;
        } else {
            LinkedNode<E> prev = previous(current);
            prev.next = newNode;
        }
        size++;
        return newNode;
    }

    public Position<E> insertBeginning(E elem) {
        LinkedNode<E> newNode = new LinkedNode<>(elem, head);

        head = newNode;
        size++;
        return newNode;
    }

    public Position<E> insertEnd(E elem) {
        if (isEmpty()) {
            return insertBeginning(elem);
        }

        LinkedNode<E> current = head;

        while (current.next != null) {
            current = current.next;
        }

        LinkedNode<E> newNode = new LinkedNode<>(elem, null);
        current.next = newNode;
        size++;
        return newNode;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        LinkedNode<E> newNode = new LinkedNode<>(elem, node.next);
        node.next = newNode;
        size++;
        return newNode;
    }

    public Traversal<E> positions() {
        return new PositionIterator(head);
    }

    protected LinkedNode<E> previous(LinkedNode<E> node) {
        LinkedNode<E> current = head;
        while (current.next != node) {
            current = current.next;
        }
        return current;
    }

    public int size() {
        return size;
    }

    public void swap(Position<E> node1, Position<E> node2) {
        LinkedNode<E> n1 = validate(node1);
        LinkedNode<E> n2 = validate(node2);
        E temp = n1.element;
        n1.element = n2.element;
        n2.element = temp;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        LinkedNode<E> current = head;
        while (current != null) {
            if (current != head) {
                s.append(", ");
            }
            s.append(current.element);
            current = current.next;
        }
        s.append("]");
        return s.toString();
    }

    public E update(Position<E> node, E elem) {
        LinkedNode<E> n = validate(node);
        E old = n.element;
        n.element = elem;
        return old;
    }

    public Iterator<E> values() {
        return new ValueIterator(head);
    }

    private LinkedNode<E> validate(Position<E> p) {
        if (!(p instanceof LinkedNode)) {
            throw new IllegalArgumentException("Invalid position");
        }
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node.next == null) {
            throw new IllegalArgumentException("Position is no longer in the list");
        }
        return node;
    }

    private static class LinkedNode<E> implements Position<E> {
        E element;
        LinkedNode<E> next;

        LinkedNode(E elem, LinkedNode<E> n) {
            element = elem;
            next = n;
        }

        @Override
        public E getElement() {
            return element;
        }
    }

    private class PositionIterator implements Traversal<E> {
        private LinkedNode<E> current;

        public PositionIterator(LinkedNode<E> start) {
            current = start;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public E getNext() {
            LinkedNode<E> temp = current;
            current = current.next;
            return temp.element;
        }
    }

    private class ValueIterator implements Iterator<E> {
        private LinkedNode<E> current;

        public ValueIterator(LinkedNode<E> start) {
            current = start;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public E next() {
            LinkedNode<E> temp = current;
            current = current.next;
            return temp.element;
        }
    }
}
