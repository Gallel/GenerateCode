
public class PriorityQueue<E> extends Object implements Queue<E> {
    
    //Declare necessary attributes here
    
    public PriorityQueue() {
        //Constructor without parameters
    }
    
    public PriorityQueue(int max) {
        //Constructor with a given capacity parameter
    }
    
    public PriorityQueue(int max, Comparator<E> comparator) {
        //Constructor with two parameters (given capacity and comparator)
    }
    
    public PriorityQueue(Comparator<E> comparator) {
        //Constructor with a given comparator parameter
    }
    
    public void add(E elem) {
        //Add an item to the appropriate position
    }
    
    public PriorityQueue<E> clone() {
        //Clone method
    }
    
    protected int compare(Position<E> pos1, Position<E> pos2) {
        //Protected method that compares positions
    }
    
    protected Position<E> deleteLastPosition() {
        //Delete last position method
    }
    
    public boolean isEmpty() {
        //Check if container is empty
    }
    
    public boolean isFull() {
        //Check if container is full
    }
    
    protected Position<E> nextLastPosition(E elem) {
        //Get next last position method
    }
    
    public E peek() {
        //Get lowest priority item method
    }
    
    public E poll() {
        //Delete lowest priority item method
    }
    
    public Traversal positions() {
        //Positions method
    }
    
    protected void siftDown(Position<E> posicioAOrdenar) {
        //Sift down method
    }
    
    protected void siftUp(Position<E> newPosition) {
        //Shift up method
    }
    
    public int size() {
        //Get number of items in container method
    }
    
    public String toString() {
        //ToString method
    }
    
    public Iterator<E> values() {
        //Get values method
    }
}
