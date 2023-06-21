
public class LinkedList<E> extends Object implements List<E>{

    protected static class LinkedNode<T>{
        private T elem;
        private LinkedNode<T> next;
        private LinkedNode<T> previous;

        public LinkedNode(T elem, LinkedNode<T> previous, LinkedNode<T> next) {
            this.elem = elem;
            this.previous = previous;
            this.next = next;
        }
    }

    protected LinkedNode<E> header;
    protected LinkedNode<E> trailer;
    protected int size;

    public LinkedList(){
        this.header = new LinkedNode<E>(null, null, null);
        this.trailer = new LinkedNode<E>(null, header, null);
        header.next = trailer;
        this.size = 0;
    }

    protected LinkedNode<E> previous(LinkedNode<E> node){
        return node.previous;
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem){
        LinkedNode<E> newNode = new LinkedNode<>(elem, node, node.next);
        node.next.previous = newNode;
        node.next = newNode;
        size++;

        return newNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> insertBeginning(E elem) {
        return newPosition(header, elem);
    }

    @Override
    public Position<E> insertEnd(E elem) {
        return newPosition(trailer.previous, elem);
    }

    @Override
    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedNode<E> p = checkPosition(node);
        return newPosition(p.previous, elem);
    }

    @Override
    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedNode<E> p = checkPosition(node);
        return newPosition(p, elem);
    }

    @Override
    public E update(Position<E> p, E elem) {
        LinkedNode<E> node = checkPosition(p);
        E oldElem = node.elem;
        node.elem = elem;
        return oldElem;
    }

    @Override
    public E get(Position<E> p) {
        LinkedNode<E> node = checkPosition(p);
        return node.elem;
    }

    @Override
    public E delete(Position<E> p) {
        LinkedNode<E> node = checkPosition(p);
        E oldElem = node.elem;
        node.previous.next = node.next;
        node.next.previous = node.previous;
        size--;

        return oldElem;
    }

    @Override
    public E deleteFirst() {
        if(size == 0){
            throw new EmptyContainerException();
        }

        return delete(header.next);
    }

    @Override
    public E deleteNext(Position<E> p) {
        if(size == 0){
            throw new EmptyContainerException();
        }

        LinkedNode<E> pos = checkPosition(p);
        if(pos.next == trailer){
            throw new InvalidPositionException();
        }

        return delete(pos.next);
    }

    @Override
    public Traversal<E> positions() {

        return new LinkedNodesTraversal();
    }

    private class LinkedNodesTraversal implements Traversal<E>{
        LinkedNode<E> current = header;

        @Override
        public boolean hasNext() {
            return current.next != trailer;
        }

        @Override
        public Position<E> next() {
            current = current.next;
            return current;
        }
    }

    @Override
    public Iterator<E> values() {
        return new LinkedValuesIterator();
    }

    protected class LinkedValuesIterator implements Iterator<E>{
        private LinkedNode<E> current = header.next;

        @Override
        public boolean hasNext() {
            return current != trailer;
        }

        @Override
        public E next() {
            E elem = current.elem;
            current = current.next;
            return elem;
        }
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        LinkedNode<E> node1 = checkPosition(p1);
        LinkedNode<E> node2 = checkPosition(p2);

        E temp = node1.elem;
        node1.elem = node2.elem;
        node2.elem = temp;
    }

    protected LinkedNode<E> checkPosition(Position<E> p) {
        if (p == null || !(p instanceof LinkedNode)){
            throw new InvalidPositionException();
        }
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node.previous.next != node || node.next.previous != node) {
            throw new InvalidPositionException();
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> it = values();
        while (it.hasNext()){
            sb.append(it.next());
            if(it.hasNext()){
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
