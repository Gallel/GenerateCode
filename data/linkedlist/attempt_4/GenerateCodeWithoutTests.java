
public class LinkedList<E> extends Object implements List<E> {
    
    protected LinkedList.LinkedNode<E> head;
    protected LinkedList.LinkedNode<E> tail;
    protected int count;

    public LinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    public E delete(Position<E> node) {
        LinkedList.LinkedNode<E> temp = validate(node);
        if (temp == head) {
            return deleteFirst();
        } else if (temp == tail) {
            return deleteLast();
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            count--;
        }
        return temp.element;
    }

    public E deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        E temp = head.element;
        if (size() == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        count--;
        return temp;
    }

    public E deleteNext(Position<E> node) {
        LinkedList.LinkedNode<E> temp = validate(node).next;
        if (temp == tail) {
            return deleteLast();
        }
        LinkedList.LinkedNode<E> before = temp.prev;
        LinkedList.LinkedNode<E> after = temp.next;
        before.next = after;
        after.prev = before;
        count--;
        return temp.element;
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> temp = validate(node);
        count++;
        return newPosition(temp, elem);
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> temp = validate(node).prev;
        count++;
        return newPosition(temp, elem);
    }

    public Position<E> insertBeginning(E elem) {
        count++;
        return newPosition(null, elem);
    }

    public Position<E> insertEnd(E elem) {
        count++;
        return newPosition(tail, elem);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        LinkedList.LinkedNode<E> temp = new LinkedList.LinkedNode<>(node, elem, node == null ? head : node.next);
        if (temp.prev == null) {
            head = temp;
        } else {
            temp.prev.next = temp;
        }
        if (temp.next == null) {
            tail = temp;
        } else {
            temp.next.prev = temp;
        }
        return temp;
    }

    public Traversal<E> positions() {
        return new PositionIterable();
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        if (node == head) {
            return null;
        }
        return node.prev;
    }

    public int size() {
        return count;
    }

    public void swap(Position<E> node1, Position<E> node2) {
        LinkedList.LinkedNode<E> nodeA = validate(node1);
        LinkedList.LinkedNode<E> nodeB = validate(node2);
        E temp = nodeA.element;
        nodeA.element = nodeB.element;
        nodeB.element = temp;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("(");
        LinkedList.LinkedNode<E> temp = head;
        while (temp != null) {
            builder.append(temp.element);
            if (temp.next != null) {
                builder.append(", ");
            }
            temp = temp.next;
        }
        builder.append(")");
        return builder.toString();
    }

    public E update(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> temp = validate(node);
        E old = temp.element;
        temp.element = elem;
        return old;
    }

    protected LinkedList.LinkedNode<E> validate(Position<E> p) {
        if (!(p instanceof LinkedList.LinkedNode)) {
            throw new IllegalArgumentException("Invalid position type.");
        }
        LinkedList.LinkedNode<E> temp = (LinkedList.LinkedNode<E>) p;
        if (temp.list != this) {
            throw new IllegalArgumentException("Position does not belong to this list.");
        }
        return temp;
    }

    @Override
    public Iterator<E> values() {
        return new ValueIterator();
    }

    private class LinkedNode<E> implements Position<E> {

        private final LinkedList<E> list;
        protected E element;
        protected LinkedList.LinkedNode<E> prev;
        protected LinkedList.LinkedNode<E> next;

        public LinkedNode(LinkedList<E> list, E elem, LinkedList.LinkedNode<E> prev, LinkedList.LinkedNode<E> next) {
            this.list = list;
            this.element = elem;
            this.prev = prev;
            this.next = next;
        }

        public LinkedNode(LinkedList.LinkedNode<E> prev, E elem, LinkedList.LinkedNode<E> next) {
            this(null, elem, prev, next);
        }

        @Override
        public E getElement() {
            return element;
        }
    }

    private class PositionIterator implements Iterator<Position<E>> {

        private LinkedList.LinkedNode<E> current = head;
        private LinkedList.LinkedNode<E> lastAccessed = null;

        public boolean hasNext() {
            return current != null;
        }

        public Position<E> next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastAccessed = current;
            current = current.next;
            return lastAccessed;
        }

        public void remove() {
            if (lastAccessed == null) throw new IllegalStateException();
            LinkedList.LinkedNode<E> prev = lastAccessed.prev;
            LinkedList.LinkedNode<E> next = lastAccessed.next;
            prev.next = next;
            next.prev = prev;
            count--;
            lastAccessed = null;
        }
    }

    private class PositionIterable implements Traversal<E> {

        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }

        public boolean isEmpty() {
            return LinkedList.this.isEmpty();
        }

        public int size() {
            return LinkedList.this.size();
        }
    }

    private class ValueIterator implements Iterator<E> {

        private Iterator<Position<E>> it = new PositionIterator();

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
}
