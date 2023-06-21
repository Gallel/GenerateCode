
public class PriorityQueue<E> extends Object implements Queue<E> {

    //constructor without parameters
    public PriorityQueue() {
        //code goes here
    }

    //constructor with a parameter (given capacity)
    public PriorityQueue(int max) {
        //code goes here
    }

    //constructor with two parameters (given capacity) and elements of one class comparable to the given comparator
    public PriorityQueue(int max, Comparator<E> comparator) {
        //code goes here
    }

    //constructor with a parameter (maximum capacity, default) and items of a class comparable to the given comparator
    public PriorityQueue(Comparator<E> comparator) {
        //code goes here
    }

    //add an item to the appropriate position, if any
    public void add(E elem) {
        //code goes here
    }

    //clone method
    public PriorityQueue<E> clone() {
        //code goes here
    }

    //protected method that compares the items contained in the positions received
    protected int compare(Position<E> pos1, Position<E> pos2) {
        //code goes here
    }

    //protected method that deletes the last position
    protected Position<E> deleteLastPosition() {
        //code goes here
    }

    //method to check if the container is empty
    public boolean isEmpty() {
        //code goes here
    }

    //method to check if the container is full
    public boolean isFull() {
        //code goes here
    }

    //protected method that retrieves the next last position
    protected Position<E> nextLastPosition(E elem) {
        //code goes here
    }

    //retrieve the lowest priority item, if any
    public E peek() {
        //code goes here
    }

    //delete the lowest priority item, if any
    public E poll() {
        //code goes here
    }

    //method that supports multiple paths, from the positions of the container, simultaneous and independent of each other
    Traversal positions() {
        //code goes here
    }

    //protected method that sifts down a position
    protected void siftDown(Position<E> posicioAOrdenar) {
        //code goes here
    }

    //protected method that sifts up a position
    protected void siftUp(Position<E> newPosition) {
        //code goes here
    }

    //retrieve the number of items in the container
    public int size() {
        //code goes here
    }

    //method overwriting Object.toString ()
    public String toString() {
        //code goes here
    }

    //retrieve the items in the container
    public Iterator<E> values() {
        //code goes here
    }
}
