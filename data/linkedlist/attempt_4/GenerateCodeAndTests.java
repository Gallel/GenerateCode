
public class LinkedList<E> extends Object implements List<E>{
    
    // nested class for node
    protected static class LinkedNode<E> implements Position<E>{
        private E elem;
        private LinkedNode<E> next;
        private LinkedNode<E> prev;
        
        public LinkedNode(E elem, LinkedNode<E> next, LinkedNode<E> prev){
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
        
        public E getElement(){
            return elem;
        }
        
        public void setElement(E elem){
            this.elem = elem;
        }
        
        public LinkedNode<E> getNext(){
            return next;
        }
        
        public void setNext(LinkedNode<E> next){
            this.next = next;
        }
        
        public LinkedNode<E> getPrev(){
            return prev;
        }
        
        public void setPrev(LinkedNode<E> prev){
            this.prev = prev;
        }
    }
    
    private LinkedNode<E> header;
    private LinkedNode<E> trailer;
    private int size = 0;
    
    // constructor
    public LinkedList(){
        header = new LinkedNode<>(null, null, null);
        trailer = new LinkedNode<>(null, null, header);
        header.setNext(trailer);
    }
    
    // delete received position
    public E delete(Position<E> node){
        LinkedNode<E> n = (LinkedNode<E>) node;
        n.getNext().setPrev(n.getPrev());
        n.getPrev().setNext(n.getNext());
        size--;
        E e = n.getElement();
        n.setElement(null);
        n.setPrev(null);
        n.setNext(null);
        return e;
    }
    
    // delete the first position in the list
    public E deleteFirst(){
        if(isEmpty()){
            return null;
        }
        return delete(header.getNext());
    }
    
    // delete the next position
    public E deleteNext(Position<E> node){
        LinkedNode<E> n = (LinkedNode<E>) node;
        if(n.getNext() == trailer){
            return null;
        }
        return delete(n.getNext());
    }
    
    // add an item after the received position
    public Position<E> insertAfter(Position<E> node, E elem){
        LinkedNode<E> n = (LinkedNode<E>) node;
        LinkedNode<E> newNode = new LinkedNode<>(elem, n.getNext(), n);
        n.getNext().setPrev(newNode);
        n.setNext(newNode);
        size++;
        return newNode;
    }
    
    // add an item before the received position
    public Position<E> insertBefore(Position<E> node, E elem){
        LinkedNode<E> n = (LinkedNode<E>) node;
        return insertAfter(n.getPrev(), elem);
    }
    
    // add an item to the top of the list
    public Position<E> insertBeginning(E elem){
        return insertAfter(header, elem);
    }
    
    // add an item to the bottom of the list
    public Position<E> insertEnd(E elem){
        return insertBefore(trailer, elem);
    }
    
    // check if the container is empty
    public boolean isEmpty(){
        return size == 0;
    }
    
    // creates a chained node, places it next to the receipt as parameterize and increase the number of items
    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem){
        LinkedNode<E> newNode = new LinkedNode<>(elem, null, node);
        node.setNext(newNode);
        size++;
        return newNode;
    }
    
    // method that supports multiple paths from the positions of the container, simultaneous and independent of each other
    public Traversal<E> positions(){
        return new ElementIterator<>(this);
    }
    
    // returns the node before the received one as a parameter
    protected LinkedNode<E> previous(LinkedNode<E> node){
        if(node == header){
            return null;
        }
        return node.getPrev();
    }
    
    // retrieves the number of items in the container
    public int size(){
        return size;
    }
    
    // exchange items contained in received positions
    public void swap(Position<E> node1, Position<E> node2){
        LinkedNode<E> n1 = (LinkedNode<E>) node1;
        LinkedNode<E> n2 = (LinkedNode<E>) node2;
        E temp = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(temp);
    }
    
    // method overwriting Object.toString()
    public String toString(){
        StringBuilder s = new StringBuilder("(");
        LinkedNode<E> currentNode = header.getNext();
        while(currentNode != trailer){
            s.append(currentNode.getElement());
            currentNode = currentNode.getNext();
            if(currentNode != trailer){
                s.append(",").append(" ");
            }
        }
        s.append(")");
        return s.toString();
    }
    
    // replaces the item contained in the received position
    public E update(Position<E> node, E elem){
        LinkedNode<E> n = (LinkedNode<E>) node;
        E temp = n.getElement();
        n.setElement(elem);
        return temp;
    }
    
    // retrieves the items in the container
    public Iterator<E> values(){
        return new ElementIterator<>(this);
    }
    
}
