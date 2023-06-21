
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E> implements Position<E> {
        private LinkedNode<E> prev, next;
        private E elem;

        public LinkedNode(LinkedNode<E> p, E e, LinkedNode<E> n) {
            prev = p;
            next = n;
            elem = e;
        }

        public E getElem() {
            return elem;
        }

        public void setElem(E e) {
            elem = e;
        }

        public LinkedNode<E> getNext() {
            return next;
        }

        public LinkedNode<E> getPrev() {
            return prev;
        }

        public void setNext(LinkedNode<E> node) {
            next = node;
        }

        public void setPrev(LinkedNode<E> node) {
            prev = node;
        }
    }

    private LinkedNode<E> head;
    private LinkedNode<E> tail;
    private int size;

    public LinkedList() {
        head = new LinkedNode<E>(null, null, null);
        tail = new LinkedNode<E>(head, null, null);
        head.setNext(tail);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Traversal<E> positions() {
        return new Traversal<E>() {
            private LinkedNode<E> current = head;

            public boolean hasNext() {
                return current.getNext() != tail;
            }

            public Position<E> next() {
                current = current.getNext();
                return current;
            }
        };
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> p, E e) {
        LinkedNode<E> node = new LinkedNode<>(p, e, p.getNext());
        node.getNext().setPrev(node);
        node.setPrev(p);
        p.setNext(node);
        size++;
        return node;
    }

    protected LinkedNode<E> previous(LinkedNode<E> p) {
        if (p == head) return null;
        return p.getPrev();
    }

    public Position<E> insertBeginning(E e) {
        return newPosition(head, e);
    }

    public Position<E> insertEnd(E e) {
        return newPosition(tail.getPrev(), e);
    }

    public Position<E> insertBefore(Position<E> p, E e) {
        LinkedNode<E> node = (LinkedNode<E>) p;
        return newPosition(node.getPrev(), e);
    }

    public Position<E> insertAfter(Position<E> p, E e) {
        LinkedNode<E> node = (LinkedNode<E>) p;
        return newPosition(node, e);
    }

    private E remove(LinkedNode<E> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        size--;
        return node.getElem();
    }

    public E remove(Position<E> p) {
        LinkedNode<E> node = (LinkedNode<E>) p;
        return remove(node);
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(head.getNext());
    }

    public E removeNext(Position<E> p) {
        LinkedNode<E> node = (LinkedNode<E>) p;
        return remove(node.getNext());
    }

    public E get(Position<E> p) {
        LinkedNode<E> node = (LinkedNode<E>) p;
        return node.getElem();
    }

    public E set(Position<E> p, E e) {
        LinkedNode<E> node = (LinkedNode<E>) p;
        E temp = node.getElem();
        node.setElem(e);
        return temp;
    }

    public void swap(Position<E> p, Position<E> q) {
        LinkedNode<E> nodeP = (LinkedNode<E>) p;
        LinkedNode<E> nodeQ = (LinkedNode<E>) q;
        E temp = nodeP.getElem();
        nodeP.setElem(nodeQ.getElem());
        nodeQ.setElem(temp);
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private LinkedNode<E> current = head;

            public boolean hasNext() {
                return current.getNext() != tail;
            }

            public E next() {
                current = current.getNext();
                return current.getElem();
            }
        };
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[");
        LinkedNode<E> p = head.getNext();
        while (p != tail) {
            s.append(p.getElem());
            if (p.getNext() != tail) s.append(", ");
            p = p.getNext();
        }
        s.append("]");
        return s.toString();
    }

}
