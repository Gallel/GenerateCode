
public class LinkedList<E> extends Object implements List<E> {
    protected static class LinkedNode<E> implements Position<E> {
        private E elem;
        protected LinkedNode<E> next;
        public LinkedNode(E elem, LinkedNode<E> next) {
            this.elem = elem;
            this.next = next;
        }
        public void setNext(LinkedNode<E> next) {
            this.next = next;
        }
        public LinkedNode<E> getNext() {
            return next;
        }
        @Override
        public E getElem() {
            return elem;
        }
        public void setElem(E elem) {
            this.elem = elem;
        }
    }

    protected LinkedNode<E> head;
    protected int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public Position<E> insertBeginning(E elem) {
        head = new LinkedNode<E>(elem, head);
        size++;
        return head;
    }

    public Position<E> insertEnd(E elem) {
        if (isEmpty()) {
            return insertBeginning(elem);
        }
        LinkedNode<E> node = head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new LinkedNode<E>(elem, null));
        size++;
        return node.getNext();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        LinkedNode<E> newNode = new LinkedNode<E>(elem, node.getNext());
        node.setNext(newNode);
        size++;
        return newNode;
    }

    public Position<E> insertAfter(Position<E> pos, E elem) {
        LinkedNode<E> node = checkPosition(pos);
        return newPosition(node, elem);
    }

    public Position<E> insertBefore(Position<E> pos, E elem) {
        LinkedNode<E> node = checkPosition(pos);
        if (node == head) {
            return insertBeginning(elem);
        }
        LinkedNode<E> previousNode = previous(node);
        return newPosition(previousNode, elem);
    }

    protected LinkedNode<E> previous(LinkedNode<E> node) {
        LinkedNode<E> previousNode = head;
        while (previousNode != null && previousNode.getNext() != node) {
            previousNode = previousNode.getNext();
        }
        return previousNode;
    }

    public E delete(Position<E> pos) {
        LinkedNode<E> node = checkPosition(pos);
        if (node == head) {
            return deleteFirst();
        }
        LinkedNode<E> previousNode = previous(node);
        previousNode.setNext(node.getNext());
        size--;
        return node.getElem();
    }

    public E deleteFirst() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elem = head.getElem();
        head = head.getNext();
        size--;
        return elem;
    }

    public E deleteNext(Position<E> pos) {
        LinkedNode<E> node = checkPosition(pos);
        if (node.getNext() == null) {
            throw new InvalidPositionException();
        }
        LinkedNode<E> nextNode = node.getNext();
        node.setNext(nextNode.getNext());
        size--;
        return nextNode.getElem();
    }

    public int size() {
        return size;
    }

    public void swap(Position<E> pos1, Position<E> pos2) {
        LinkedNode<E> node1 = checkPosition(pos1);
        LinkedNode<E> node2 = checkPosition(pos2);
        E tempElem = node1.getElem();
        node1.setElem(node2.getElem());
        node2.setElem(tempElem);
    }

    public E update(Position<E> pos, E elem) {
        LinkedNode<E> node = checkPosition(pos);
        E tempElem = node.getElem();
        node.setElem(elem);
        return tempElem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        LinkedNode<E> node = head;
        while (node != null) {
            sb.append(node.getElem());
            if (node.getNext() != null) {
                sb.append(", ");
            }
            node = node.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public Iterator<E> values() {
        LinkedNode<E> node = head;
        List<E> values = new ArrayList<E>();
        while (node != null) {
            values.add(node.getElem());
            node = node.getNext();
        }
        return new ArrayIterator<E>(values);
    }

    public Traversal<E> positions() {
        return new LinkedNodeIterator<E>(head);
    }

    protected LinkedNode<E> checkPosition(Position<E> pos) {
        if (pos == null || !(pos instanceof LinkedNode)) {
            throw new InvalidPositionException();
        }
        LinkedNode<E> node = (LinkedNode<E>) pos;
        if (node.getNext() == null) {
            throw new InvalidPositionException();
        }
        return node;
    }

}
