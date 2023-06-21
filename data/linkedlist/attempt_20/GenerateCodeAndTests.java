
public class LinkedList<E> extends Object implements List<E>{

    protected static class LinkedNode<E> implements Position<E>{
        public LinkedNode(){
        }
        public LinkedNode(E element, LinkedNode<E> next, LinkedNode<E> prev){
        }
        public E element() throws InvalidPositionException{
        }
        public Position<E> next() throws InvalidPositionException{
        }
        public Position<E> prev() throws InvalidPositionException{
        }
        public void setElement(E element) throws InvalidPositionException{
        }
        public void setNext(LinkedNode<E> next){
        }
        public void setPrev(LinkedNode<E> prev){
        }
    }

    public LinkedList(){
    }

    public boolean isEmpty(){
    }

    public int size(){
    }

    public Position<E> insertBeginning(E elem){
    }

    public Position<E> insertEnd(E elem){
    }

    public Position<E> insertAfter(Position<E> node, E elem) throws InvalidPositionException{
    }

    public Position<E> insertBefore(Position<E> node, E elem) throws InvalidPositionException{
    }

    public E delete(Position<E> node) throws InvalidPositionException{
    }

    public E deleteFirst() throws EmptyListException{
    }

    public E deleteNext(Position<E> node) throws InvalidPositionException, BoundaryViolationException{
    }

    public E update(Position<E> node, E elem) throws InvalidPositionException{
    }

    public void swap(Position<E> node1, Position<E> node2) throws InvalidPositionException{
    }

    public Traversal<E> positions(){
    }

    protected LinkedNode<E> previous(LinkedNode<E> node){
    }

    public Iterator<E> values(){
    }

    public String toString(){
    }
}
