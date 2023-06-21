
public class LinkedList<E> extends Object implements List<E> {

    protected int size;

    protected LinkedList.LinkedNode<E> first;

    public LinkedList() {
        size = 0;
        first = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        LinkedList.LinkedNode<E> n = new LinkedList.LinkedNode<>(elem);
        n.setNext(node.getNext());
        node.setNext(n);
        size++;
        return n;
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        LinkedList.LinkedNode<E> p = first;
        while (p!=null && p.getNext()!=node)
            p = p.getNext();
        return p;
    }

    public Position<E> insertBeginning(E elem){
        LinkedList.LinkedNode<E> n = new LinkedList.LinkedNode<>(elem);
        n.setNext(first);
        first = n;
        size++;
        return n;
    }

    public Position<E> insertEnd(E elem){
        LinkedList.LinkedNode<E> n = new LinkedList.LinkedNode<>(elem);
        if (isEmpty())
            first = n;
        else {
            LinkedList.LinkedNode<E> p = first;
            while (p.getNext()!=null)
                p = p.getNext();
            p.setNext(n);
        }
        size++;
        return n;
    }

    public Position<E> insertAfter(Position<E> node, E elem){
        LinkedList.LinkedNode<E> p = checkPosition(node);
        return newPosition(p,elem);
    }

    public Position<E> insertBefore(Position<E> node, E elem){
        LinkedList.LinkedNode<E> n = checkPosition(node);
        if (n==first)
            return insertBeginning(elem);
        else
            return newPosition(previous(n),elem);
    }

    public E delete(Position<E> node){
        LinkedList.LinkedNode<E> n = checkPosition(node);
        if (n==first)
            first = n.getNext();
        else
            previous(n).setNext(n.getNext());
        size--;
        return n.getElement();
    }

    public E deleteFirst() {
        if (isEmpty())
            return null;
        LinkedList.LinkedNode<E> n = first;
        first = n.getNext();
        size--;
        return n.getElement();
    }

    public E deleteNext(Position<E> node){
        LinkedList.LinkedNode<E> n = checkPosition(node);
        if (n.getNext()==null)
            return null;
        LinkedList.LinkedNode<E> aux = n.getNext();
        n.setNext(aux.getNext());
        size--;
        return aux.getElement();
    }

    public void swap(Position<E> node1, Position<E> node2){
        LinkedList.LinkedNode<E> n1 = checkPosition(node1);
        LinkedList.LinkedNode<E> n2 = checkPosition(node2);
        E aux = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(aux);
    }

    public E update(Position<E> node, E elem){
        LinkedList.LinkedNode<E> n = checkPosition(node);
        E old = n.getElement();
        n.setElement(elem);
        return old;
    }

    public Traversal<E> positions(){
        return new LinkedListPositionTraversal<>(first,this);
    }

    public Iterator<E> iterator(){
        return new LinkedListIterator<>(first);
    }

    public String toString(){
        String ans = "";
        LinkedList.LinkedNode<E> p = first;
        while (p!=null) {
            ans += p.getElement();
            if (p.getNext()!=null)
                ans += ", ";
            p = p.getNext();
        }
        return ans;
    }

    protected LinkedList.LinkedNode<E> checkPosition(Position<E> pos) {
        if (pos == null || !(pos instanceof LinkedList.LinkedNode))
            throw new RuntimeException("The position is invalid");
        LinkedList.LinkedNode<E> n = (LinkedList.LinkedNode<E>) pos;
        if (n.getList() != this)
            throw new RuntimeException("The position does not belong to this list");
        return n;
    }

    protected static class LinkedNode<T> implements Position<T> {
        private T element;
        private LinkedList.LinkedNode<T> next;
        private LinkedList<T> list;

        public LinkedNode(T elem) {
            element = elem;
            list = null;
            next = null;
        }

        public T getElement() throws RuntimeException {
            return element;
        }

        public LinkedList.LinkedNode<T> getNext() {
            return next;
        }

        public void setNext(LinkedList.LinkedNode<T> newNext) {
            next = newNext;
        }

        public void setElement(T elem) {
            element = elem;
        }

        public LinkedList<T> getList() {
            return list;
        }

        public void setList(LinkedList<T> newList) {
            list = newList;
        }
    }

    protected static class LinkedListIterator<T> implements Iterator<T> {
        private LinkedList.LinkedNode<T> current;

        public LinkedListIterator(LinkedList.LinkedNode<T> f){
            current = f;
        }

        public boolean hasNext(){
            return current!=null;
        }

        public T next() throws NoSuchElementException {
            if (!hasNext())
                throw new NoSuchElementException("No next element");
            T ans = current.getElement();
            current = current.getNext();
            return ans;
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Unsupported operation");
        }
    }

    protected static class LinkedListPositionTraversal<T> implements Traversal<T> {
        protected LinkedList.LinkedNode<T> current;
        protected LinkedList.LinkedNode<T> previous;
        protected LinkedList<T> list;

        public LinkedListPositionTraversal(LinkedList.LinkedNode<T> f, LinkedList<T> p){
            current = f;
            previous = null;
            list = p;
        }

        public boolean hasNext(){
            if (current == null)
                return false;
            if (current==list.first && previous==null)
                return true;
            if (current.getNext() == previous)
                return false;
            return true;
        }

        public Position<T> next() throws NoSuchElementException {
            if (!hasNext())
                throw new NoSuchElementException("No next position");
            try {
                previous = current;
                current = current.getNext();
            }
            catch (RuntimeException e) {
                throw new NoSuchElementException("No next position");
            }
            return previous;
        }
    }
}
