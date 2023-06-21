
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        public LinkedNode<E> next;
        public E elem;
    }

    private LinkedNode<E> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public E delete(Position<E> node) {
        LinkedNode<E> current = checkPosition(node);
        if (current == head) {
            return deleteFirst();
        } else {
            LinkedNode<E> previous = previous(current);
            return deleteNext(previous);
        }
    }

    public E deleteFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        E element = head.elem;
        head = head.next;
        size--;
        return element;
    }

    public E deleteNext(Position<E> node) {
        LinkedNode<E> current = checkPosition(node);
        if (current.next == null) {
            throw new IndexOutOfBoundsException("End of list");
        }
        E element = current.next.elem;
        current.next = current.next.next;
        size--;
        return element;
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedNode<E> current = checkPosition(node);
        LinkedNode<E> newNode = newPosition(current.next, elem);
        current.next = newNode;
        size++;
        return newNode;
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedNode<E> current = checkPosition(node);
        if (current == head) {
            return insertBeginning(elem);
        } else {
            LinkedNode<E> previous = previous(current);
            return insertAfter(previous, elem);
        }
    }

    public Position<E> insertBeginning(E elem) {
        LinkedNode<E> newNode = newPosition(head, elem);
        head = newNode;
        size++;
        return newNode;
    }

    public Position<E> insertEnd(E elem) {
        LinkedNode<E> current = head;
        if (isEmpty()) {
            return insertBeginning(elem);
        }
        while (current.next != null) {
            current = current.next;
        }
        return insertAfter(current, elem);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        LinkedNode<E> newNode = new LinkedNode<>();
        newNode.next = node;
        newNode.elem = elem;
        return newNode;
    }

    public Traversal<E> positions() {
        return new LinkedNodeTraversal<>(this, head);
    }

    protected LinkedNode<E> previous(LinkedNode<E> node) {
        LinkedNode<E> current = head;
        while (current != null && current.next != node) {
            current = current.next;
        }
        return current;
    }

    public int size() {
        return size;
    }

    public void swap(Position<E> node1, Position<E> node2) {
        LinkedNode<E> current1 = checkPosition(node1);
        LinkedNode<E> current2 = checkPosition(node2);
        E temp = current1.elem;
        current1.elem = current2.elem;
        current2.elem = temp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        LinkedNode<E> current = head;
        while (current != null) {
            sb.append(current.elem.toString());
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public E update(Position<E> node, E elem) {
        LinkedNode<E> current = checkPosition(node);
        E old = current.elem;
        current.elem = elem;
        return old;
    }

    public Iterator<E> values() {
        List<E> list = new ArrayList<>();
        LinkedNode<E> current = head;
        while (current != null) {
            list.add(current.elem);
            current = current.next;
        }
        return list.iterator();
    }

    private LinkedNode<E> checkPosition(Position<E> node) {
        if (node == null || !(node instanceof LinkedNode)) {
            throw new IllegalArgumentException("Invalid position type");
        }
        LinkedNode<E> current = (LinkedNode<E>) node;
        if (current.next == current || current == head) {
            throw new IllegalArgumentException("Invalid position");
        }
        return current;
    }

}
