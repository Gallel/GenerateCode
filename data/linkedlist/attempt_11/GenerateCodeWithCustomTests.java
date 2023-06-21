
public class LinkedList<E> extends Object implements List<E> {

    public LinkedList() {}

    public E delete(Position<E> node) {
        if (isEmpty()) {
            throw new InvalidPositionException();
        }
        LinkedList.LinkedNode<E> prev = previous((LinkedList.LinkedNode<E>) node);
        LinkedList.LinkedNode<E> currentNode = (LinkedList.LinkedNode<E>) node;
        prev.setNext(currentNode.getNext());
        currentNode.setNext(null);
        size--;
        return currentNode.getElem();
    }

    public E deleteFirst() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        LinkedList.LinkedNode<E> first = head.getNext();
        head.setNext(first.getNext());
        first.setNext(null);
        size--;
        return first.getElem();
    }

    public E deleteNext(Position<E> node) {
        if (isEmpty()) {
            throw new InvalidPositionException();
        }
        LinkedList.LinkedNode<E> currentNode = (LinkedList.LinkedNode<E>) node;
        LinkedList.LinkedNode<E> nextNode = currentNode.getNext();
        if (nextNode == null) {
            throw new InvalidPositionException();
        }
        currentNode.setNext(nextNode.getNext());
        nextNode.setNext(null);
        size--;
        return nextNode.getElem();
    }

    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> currentNode = (LinkedList.LinkedNode<E>) node;
        LinkedList.LinkedNode<E> newNode = newPosition(currentNode, elem);
        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        size++;
        return newNode;
    }

    public Position<E> insertBefore(Position<E> node, E elem) {
        Position<E> prev = previous((LinkedList.LinkedNode<E>) node);
        return insertAfter(prev, elem);
    }

    public Position<E> insertBeginning(E elem) {
        return insertAfter(head, elem);
    }

    public Position<E> insertEnd(E elem) {
        return insertAfter(tail.getPrev().getPrev(), elem);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        return new LinkedList.LinkedNode<>(node, elem);
    }

    public Traversal<E> positions() {
        return new LinkedListIterator<>(head);
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        if (node == head || node == tail) {
            throw new InvalidPositionException();
        }
        LinkedList.LinkedNode<E> currentNode = head;
        while (currentNode.getNext() != node && currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        if (currentNode.getNext() == null) {
            throw new InvalidPositionException();
        }
        return currentNode;
    }

    public int size() {
        return size;
    }

    public void swap(Position<E> node1, Position<E> node2) {
        if (node1 == node2) {
            return;
        }
        LinkedList.LinkedNode<E> prev1 = previous((LinkedList.LinkedNode<E>) node1);
        LinkedList.LinkedNode<E> prev2 = previous((LinkedList.LinkedNode<E>) node2);
        LinkedList.LinkedNode<E> current1 = (LinkedList.LinkedNode<E>) node1;
        LinkedList.LinkedNode<E> current2 = (LinkedList.LinkedNode<E>) node2;
        LinkedList.LinkedNode<E> temp = current1.getNext();
        prev1.setNext(current2);
        current1.setNext(current2.getNext());
        prev2.setNext(current1);
        current2.setNext(temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        LinkedListIterator<E> it = new LinkedListIterator<>(head.getNext());
        while (it.hasNext()) {
            sb.append(it.next().getElem());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public E update(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> currentNode = (LinkedList.LinkedNode<E>) node;
        E oldElem = currentNode.getElem();
        currentNode.setElem(elem);
        return oldElem;
    }

    public Iterator<E> values() {
        return new LinkedListIterator<>(head.getNext());
    }

    protected static class LinkedNode<E> implements Position<E> {
        private LinkedList.LinkedNode<E> next;
        private E elem;

        LinkedNode(LinkedList.LinkedNode<E> next, E elem) {
            this.next = next;
            this.elem = elem;
        }

        LinkedList.LinkedNode<E> getNext() {
            return next;
        }

        void setNext(LinkedList.LinkedNode<E> next) {
            this.next = next;
        }

        E getElem() {
            return elem;
        }

        void setElem(E elem) {
            this.elem = elem;
        }
    }

    protected LinkedList.LinkedNode<E> head = new LinkedList.LinkedNode<>(null, null);
    protected LinkedList.LinkedNode<E> tail = new LinkedList.LinkedNode<>(null, null);
    protected int size = 0;

}
