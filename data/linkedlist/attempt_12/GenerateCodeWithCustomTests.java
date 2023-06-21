
public class LinkedList<E> extends Object implements List<E> {

    protected static class LinkedNode<E>
    implements Position<E> {
        public LinkedNode(E elem, LinkedNode<E> next,
         LinkedNode<E> prev);
        public E getElem();
        public LinkedNode<E> getNext();
        public LinkedNode<E> getPrev();
        public void setElem(E elem);
        public void setNext(LinkedNode<E> next);
        public void setPrev(LinkedNode<E> prev);
        private E elem;
        private LinkedNode<E> next;
        private LinkedNode<E> prev;
    }

    public LinkedList() {
        size=0;
        header= new LinkedNode<>(null, null, null);
        trailer= new LinkedNode<>(null, header, null);
        header.setNext(trailer);
    }
    
    public E delete(Position<E> node) {
        LinkedNode<E> n= checkPosition(node);
        E result= n.getElem();
        LinkedNode<E> prev= n.getPrev();
        LinkedNode<E> next= n.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return result;
    }
    
    public E deleteFirst() {
        checkEmpty();
        return delete(header.getNext());
    }
    
    public E deleteNext(Position<E> node) {
        LinkedNode<E> n= checkPosition(node);
        checkEmpty();
        return delete(n.getNext());
    }
    
    public Position<E> insertAfter(Position<E> node,
     E elem) {
        LinkedNode<E> p= checkPosition(node);
        size++;
        LinkedNode<E> newNode= newPosition(p, elem);
        return newNode;
    }
    
    public Position<E> insertBefore(Position<E> node,
     E elem) {
        LinkedNode<E> n= checkPosition(node);
        size++;
        LinkedNode<E> newNode= newPosition(n.getPrev(), elem);
        return newNode;
    }
    
    public Position<E> insertBeginning(E elem) {
        size++;
        LinkedNode<E> newNode= newPosition(header, elem);
        return newNode;
    }
    
    public Position<E> insertEnd(E elem) {
        size++;
        LinkedNode<E> newNode= newPosition(trailer.getPrev(), elem);
        return newNode;
    }
    
    public boolean isEmpty() {
        return size==0;
    }
    
    protected LinkedNode<E> newPosition(LinkedNode<E> node,
     E elem) {
        LinkedNode<E> newNode= new LinkedNode<>(elem,
         node.getNext(), node);
        node.getNext().setPrev(newNode);
        node.setNext(newNode);
        return newNode;
    }
    
    public Traversal<E> positions() {
        return new PositionIterator<>(this, header);
    }
    
    protected LinkedNode<E> previous(LinkedNode<E> node) {
        checkPosition(node);
        return node.getPrev();
    }
    
    public int size() {
        return size;
    }
    
    public void swap(Position<E> node1, Position<E> node2) {
        LinkedNode<E> n1= checkPosition(node1);
        LinkedNode<E> n2= checkPosition(node2);
        E temp= n1.getElem();
        n1.setElem(n2.getElem());
        n2.setElem(temp);
    }
    
    public E update(Position<E> node, E elem) {
        LinkedNode<E> n= checkPosition(node);
        E result= n.getElem();
        n.setElem(elem);
        return result;
    }
    
    public Iterator<E> values() {
        return new ElementIterator<>(this);
    }
    
    private LinkedNode<E> checkPosition(Position<E> p)
     throws InvalidPositionException {
        if (!(p instanceof LinkedNode))
            throw new InvalidPositionException("This position is not of the type LinkedNode");
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node.getPrev()==null || node.getNext()==null)
            throw new InvalidPositionException("This node has been previously deleted");
        return node;
    }
    
    private void checkEmpty() throws EmptyContainerException {
        if (isEmpty())
            throw new EmptyContainerException("The container is empty");
    }
    
    private int size;
    private LinkedNode<E> header;
    private LinkedNode<E> trailer;
    
}
