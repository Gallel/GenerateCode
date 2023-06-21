
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        private E elem;
        private LinkedNode<E> next;
        private LinkedNode<E> previous;

        LinkedNode(E elem, LinkedNode<E> next, LinkedNode<E> previous) {
            this.elem = elem;
            this.next = next;
            this.previous = previous;
        }

        public E getElem() {
            return this.elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }

        public LinkedNode<E> getNext() {
            return next;
        }

        public void setNext(LinkedNode<E> next) {
            this.next = next;
        }

        public LinkedNode<E> getPrevious() {
            return previous;
        }

        public void setPrevious(LinkedNode<E> previous) {
            this.previous = previous;
        }
    }

    protected LinkedNode<E> header = null;
    protected LinkedNode<E> trailer = null;
    protected int size = 0;

    public LinkedList() {
        header = new LinkedNode<E>(null, null, null);
        trailer = new LinkedNode<E>(null, null, header);
        header.setNext(trailer);
    }

    private LinkedNode<E> validate(Position<E> p) throws InvalidPositionException {
        if (!(p instanceof LinkedNode)) {
            throw new InvalidPositionException("Invalid position");
        }
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node.getNext() == null) {
            throw new InvalidPositionException("Position is not longer valid");
        }
        return node;
    }

    private E remove(LinkedNode<E> node) {
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
        size--;
        E object = node.getElem();
        node.setNext(null);
        node.setPrevious(null);
        return object;
    }

    public E delete(Position<E> node) throws InvalidPositionException {
        return remove(validate(node));
    }

    public E deleteFirst() throws EmptyContainerException {
        if (isEmpty()) {
            throw new EmptyContainerException("List is empty");
        }
        return remove(header.getNext());
    }

    public E deleteNext(Position<E> node) throws InvalidPositionException {
        LinkedNode<E> followingNode = validate(node).getNext();
        if (followingNode == trailer || followingNode == null) {
            throw new InvalidPositionException("Position is not longer valid");
        }
        return remove(followingNode);
    }

    public Position<E> insertAfter(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> currentNode = validate(node);
        size++;
        return newPosition(currentNode, elem);
    }

    public Position<E> insertBefore(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> currentNode = validate(node);
        size++;
        return newPosition(currentNode.getPrevious(), elem);
    }

    public Position<E> insertBeginning(E elem) {
        size++;
        return newPosition(header, elem);
    }

    public Position<E> insertEnd(E elem) {
        size++;
        return newPosition(trailer.getPrevious(), elem);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        LinkedNode<E> newNode = new LinkedNode<E>(elem, node.getNext(), node);
        node.getNext().setPrevious(newNode);
        node.setNext(newNode);
        return newNode;
    }

    public Traversal<E> positions() {
        return new PositionIterator();
    }

    protected LinkedNode<E> previous(LinkedNode<E> node) {
        return node.getPrevious();
    }

    public Iterator<E> values() {
        return new ValueIterator();
    }

    public int size() {
        return this.size;
    }

    public void swap(Position<E> node1, Position<E> node2) throws InvalidPositionException {
        LinkedNode<E> firstNode = validate(node1);
        LinkedNode<E> secondNode = validate(node2);
        E temp = firstNode.getElem();
        firstNode.setElem(secondNode.getElem());
        secondNode.setElem(temp);
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        Iterator<E> listIterator = this.values();
        while (listIterator.hasNext()) {
            buffer.append(listIterator.next());
            if (listIterator.hasNext()) {
                buffer.append(", ");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }

    public E update(Position<E> node, E elem) throws InvalidPositionException {
        LinkedNode<E> currentNode = validate(node);
        E oldElem = currentNode.getElem();
        currentNode.setElem(elem);
        return oldElem;
    }

    private class PositionIterator implements Traversal<E> {
        private LinkedNode<E> current = header;

        public boolean hasNext() {
            return current.getNext() != trailer;
        }

        public Position<E> next() throws InvalidPositionException {
            if (!hasNext()) {
                throw new InvalidPositionException("No next position");
            }
            current = current.getNext();
            return current;
        }
    }

    private class ValueIterator implements Iterator<E> {
        private Traversal<E> positions = positions();

        public boolean hasNext() {
            return positions.hasNext();
        }

        public E next() throws InvalidPositionException {
            return positions.next().getElem();
        }
    }

}
