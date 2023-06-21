
public class LinkedList<E> extends Object implements List<E>{
    
    //inner class LinkedNode
    protected class LinkedNode<E> implements Position<E>{
        
        private E elem;
        private LinkedNode<E> next;
        
        public LinkedNode(E elem, LinkedNode<E> next){
            this.elem = elem;
            this.next = next;
        }
        
        public E getElem(){
            return this.elem;
        }
        
        public void setElem(E elem){
            this.elem = elem;
        }
        
        public LinkedNode<E> getNext(){
            return this.next;
        }
        
        public void setNext(LinkedNode<E> node){
            this.next = node;
        }
    }
    
    //implementation of the LinkedList
    protected LinkedNode<E> header;
    protected LinkedNode<E> trailer;
    protected int size;
    
    public LinkedList(){
        header = new LinkedNode<>(null, null);
        trailer = new LinkedNode<>(null, null);
        header.setNext(trailer);
        size = 0;
    }

    public E delete(Position<E> node) throws InvalidPositionException{

        if(node == null){
            throw new InvalidPositionException("The given position is null");
        }
        
        LinkedNode<E> linkedNode = (LinkedNode<E>) node;
        LinkedNode<E> prevNode = previous(linkedNode);
        prevNode.setNext(linkedNode.getNext());
        linkedNode.setNext(null);
        E deletedNode = linkedNode.getElem();
        linkedNode.setElem(null);
        size--;
        return deletedNode;
    }

    public E deleteFirst() throws EmptyContainerException{
        if(isEmpty()){
            throw new EmptyContainerException("The container is empty");
        }
        return deleteAfter(header);
    }
    
    protected E deleteAfter(LinkedNode<E> node){
        LinkedNode<E> toDelete = node.getNext();
        E elem = toDelete.getElem();
        node.setNext(toDelete.getNext());
        toDelete.setNext(null);
        toDelete.setElem(null);
        size--;
        return elem;
    }
    
    public E deleteNext(Position<E> node) throws InvalidPositionException{
        if(node == null){
            throw new InvalidPositionException("The given position is null");
        }
        return deleteAfter((LinkedNode<E>) node);
    }
    
    public Position<E> insertAfter(Position<E> node, E elem) throws InvalidPositionException{
        if(node == null){
            throw new InvalidPositionException("The given position is null");
        }
        LinkedNode<E> linkedNode = (LinkedNode<E>) node;
        LinkedNode<E> newNode = new LinkedNode<>(elem, linkedNode.getNext());
        linkedNode.setNext(newNode);
        size++;
        return newNode;
    }
    
    public Position<E> insertBefore(Position<E> node, E elem) throws InvalidPositionException{
        if(node == null){
            throw new InvalidPositionException("The given position is null");
        }
        LinkedNode<E> prevNode = previous((LinkedNode<E>) node);
        return insertAfter(prevNode, elem);
    }

    public Position<E> insertBeginning(E elem){
        return insertAfter(header, elem);
    }

    public Position<E> insertEnd(E elem){
        return insertBefore(trailer, elem);
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem){
        LinkedNode<E> newNode = new LinkedNode<>(elem, node.getNext());
        node.setNext(newNode);
        size++;
        return newNode;
    }
    
    public Traversal<E> positions(){
        return new LinkedListIterator<>();
    }
    
    protected LinkedNode<E> previous(LinkedNode<E> node){
        LinkedNode<E> it = header;
        while(it.getNext() != node){
            it = it.getNext();
        }
        return it;
    }

    public int size(){
        return size;
    }
    
    public void swap(Position<E> node1, Position<E> node2) throws InvalidPositionException{
        E elem1 = node1.getElem();
        E elem2 = node2.getElem();
        LinkedNode<E> linkedNode1 = (LinkedNode<E>) node1;
        LinkedNode<E> linkedNode2 = (LinkedNode<E>) node2;
        linkedNode1.setElem(elem2);
        linkedNode2.setElem(elem1);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("{ ");
        LinkedNode<E> it = header.getNext();
        while(it != trailer){
            sb.append(it.getElem().toString()).append(" ");
            it = it.getNext();
        }
        sb.append("}");
        return sb.toString();
    }
    
    public E update(Position<E> node, E elem) throws InvalidPositionException{
        if(node == null){
            throw new InvalidPositionException("The given position is null");
        }
        ((LinkedNode<E>) node).setElem(elem);
        return node.getElem();
    }
    
    public Iterator<E> values(){
        LinkedListIterator<E> iterator = new LinkedListIterator<>();
        Iterator<E> it = (Iterator<E>) iterator;
        return it;
    }
    
    private class LinkedListIterator<T> implements Iterator<T>, Traversal<T>{
        LinkedNode<T> currentNode;
        public LinkedListIterator(){
            currentNode = (LinkedNode<T>) header.getNext();
        }
        
        public boolean hasNext(){
            return currentNode != trailer;
        }
        
        public T next() throws RuntimeException{
            if(!hasNext()){
                throw new RuntimeException("There are no more elements to traverse");
            }
            T elem = currentNode.getElem();
            currentNode = currentNode.getNext();
            return elem;
        }
        
        public void remove(){
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }
}
