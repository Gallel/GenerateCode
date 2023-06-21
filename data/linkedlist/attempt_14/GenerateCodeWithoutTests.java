
public class LinkedList<E> extends Object implements List<E> {
    
    protected static class LinkedNode<E> implements Position<E> {
        //Implementation of the Position<E> interface here
    }
    
    private int size;
    private LinkedNode<E> head, tail;
    
    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
    }
    
    public E delete(Position<E> node) {
        //Implementation of delete() method here
    }
    
    public E deleteFirst() {
        //Implementation of deleteFirst() method here
    }
    
    public E deleteNext(Position<E> node) {
        //Implementation of deleteNext() method here
    }
    
    public Position<E> insertAfter(Position<E> node, E elem) {
        //Implementation of insertAfter() method here
    }
    
    public Position<E> insertBefore(Position<E> node, E elem) {
        //Implementation of insertBefore() method here
    }
    
    public Position<E> insertBeginning(E elem) {
        //Implementation of insertBeginning() method here
    }
    
    public Position<E> insertEnd(E elem) {
        //Implementation of insertEnd() method here
    }
    
    public boolean isEmpty() {
        //Implementation of isEmpty() method here
    }
    
    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        //Implementation of newPosition() method here
    }
    
    public Traversal<E> positions() {
        //Implementation of positions() method here
    }
    
    protected LinkedNode<E> previous(LinkedNode<E> node) {
        //Implementation of previous() method here
    }
    
    public int size() {
        //Implementation of size() method here
    }
    
    public void swap(Position<E> node1, Position<E> node2) {
        //Implementation of swap() method here
    }
    
    @Override
    public String toString() {
        //Implementation of toString() method here
    }
    
    public E update(Position<E> node, E elem) {
        //Implementation of update() method here
    }
    
    @Override
    public Iterator<E> values() {
        //Implementation of values() method here
    }
}
