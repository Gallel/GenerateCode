
public class LinkedList<E> extends Object implements List<E>{
    
    protected static class LinkedNode<E> implements Position<E>{
        
        private LinkedNode<E> prev, next;
        private E elem;
        
        public LinkedNode(LinkedNode<E> prev,
                          LinkedNode<E> next,
                          E elem){
            this.prev = prev;
            this.next = next;
            this.elem = elem;
        }
        
        public void setPrev(LinkedNode<E> prev){
            this.prev = prev;
        }
        
        public LinkedNode<E> getPrev(){
            return prev;
        }
        
        public void setNext(LinkedNode<E> next){
            this.next = next;
        }
        
        public LinkedNode<E> getNext(){
            return next;
        }
        
        @Override
        public E getElem(){
            return elem;
        }
        
        public void setElem(E elem){
            this.elem = elem;
        }
    }
    
    protected LinkedNode<E> header, trailer;
    protected int size;
    
    public LinkedList(){
        header = new LinkedNode<>(null, null, null);
        trailer = new LinkedNode<>(header, null, null);
        header.setNext(trailer);
        size = 0;
    }
    
    public E delete(Position<E> node) throws InvalidPositionException{
        LinkedNode<E> target = checkPosition(node);
        LinkedNode<E> next = target.getNext();
        LinkedNode<E> prev = target.getPrev();
        E result = target.getElem();
        prev.setNext(next);
        next.setPrev(prev);
        target.setPrev(null);
        target.setNext(null);
        target.setElem(null);
        size--;
        return result;
    }
    
    public E deleteFirst() throws EmptyContainerException{
        if (isEmpty()) throw new EmptyContainerException();
        return delete(header.getNext());
    }
    
    public E deleteNext(Position<E> node) throws InvalidPositionException{
        LinkedNode<E> target = checkPosition(node);
        return delete(target.getNext());
    }

    public Position<E> insertAfter(Position<E> node, E elem) throws InvalidPositionException{
        LinkedNode<E> target = checkPosition(node);
        LinkedNode<E> newnode = new LinkedNode<>(target, target.getNext(), elem);
        target.getNext().setPrev(newnode);
        target.setNext(newnode);
        size++;
        return newnode;
    }
    
    public Position<E> insertBefore(Position<E> node, E elem) throws InvalidPositionException{
        LinkedNode<E> target = checkPosition(node);
        return insertAfter(target.getPrev(), elem);
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
    
    protected LinkedNode<E> newPosition(LinkedNode<E> before, E elem){
        LinkedNode<E> res = new LinkedNode<>(before, before.getNext(), elem);
        before.getNext().setPrev(res);
        before.setNext(res);
        size++;
        return res;
    }
    
    public Traversal<E> positions(){
        return new PositionIterator();
    }
    
    protected LinkedNode<E> previous(LinkedNode<E> node){
        if (node == header) return null;
        return node.getPrev();
    }
    
    public int size(){
        return size;
    }
    
    public void swap(Position<E> node1, Position<E> node2) throws InvalidPositionException{
        LinkedNode<E> pos1 = checkPosition(node1);
        LinkedNode<E> pos2 = checkPosition(node2);
        E aux = pos1.getElem();
        pos1.setElem(pos2.getElem());
        pos2.setElem(aux);
    }
    
    @Override
    public String toString(){
        String res = "";
        LinkedNode<E> aux = header.getNext();
        while (aux != trailer){
            res += aux.getElem().toString();
            if (aux.getNext() != trailer) res += " ";
            aux = aux.getNext();
        }
        return res;
    }
    
    public E update(Position<E> node, E elem) throws InvalidPositionException{
        LinkedNode<E> target = checkPosition(node);
        E result = target.getElem();
        target.setElem(elem);
        return result;
    }
    
    public Iterator<E> values(){
        return new ElementIterator();
    }
    
    protected LinkedNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
        try {
            if (p == null) throw new InvalidPositionException();
            if (p == header) throw new InvalidPositionException();
            if (p == trailer) throw new InvalidPositionException();
            LinkedNode<E> aux = (LinkedNode<E>) p;
            if (aux.getPrev() == null || aux.getNext() == null) throw new InvalidPositionException();
            return aux;
        } catch (ClassCastException e) {
            throw new InvalidPositionException();
        }
    }
    
    protected class PositionIterator implements Traversal<E>{
        private LinkedNode<E> current;
        
        public PositionIterator(){
            current = header.getNext();
        }
        
        @Override
        public boolean hasNext(){
            return current != trailer;
        }
        
        @Override
        public Position<E> next(){
            if (!hasNext()) return null;
            LinkedNode<E> res = current;
            current = current.getNext();
            return res;
        }
    }
    
    protected class ElementIterator implements Iterator<E>{
        private Position<E> current;
        
        public ElementIterator(){
            current = header;
        }
        
        @Override
        public boolean hasNext(){
            return current != trailer;
        }
        
        @Override
        public E next(){
            if (!hasNext()) return null;
            current = current.getNext();
            return current.getElem();
        }
    }
}
 