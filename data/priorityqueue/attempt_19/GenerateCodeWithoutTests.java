
public class PriorityQueue<E> extends Object implements Queue<E> {

    //Constructor without parameters
    public PriorityQueue() {}

    //Constructor with a parameter (given capacity)
    public PriorityQueue(int max) {}

    //Constructor with two parameters (given capacity) and elements of one class comparable to the given comparator
    public PriorityQueue(int max, Comparator<E> comparator) {}

    //Constructor with a parameter (maximum capacity, default) and items of a class comparable to the given comparator
    public PriorityQueue(Comparator<E> comparator) {}

    //Add an item to the appropriate position, if any
    public void add(E elem) {}

    //Clone method
    public PriorityQueue<E> clone() {return null;}

    //Protected method that compares the items contained in the positions received
    protected int compare(Position<E> pos1, Position<E> pos2) {return 0;}

    //Protected method that deletes the last position of the container
    protected Position<E> deleteLastPosition() {return null;}

    //Method to check if the container is empty
    public boolean isEmpty() {return false;}

    //Method to check if the container is full
    public boolean isFull() {return false;}

    //Protected method that retrieves the next last position
    protected Position<E> nextLastPosition(E elem) {return null;}

    //Retrieves the lowest priority item, if any
    public E peek() {return null;}

    //Delete the lowest priority item, if any
    public E poll() {return null;}

    //Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other
    public Traversal positions() {return null;}

    //Protected method that sifts down a position in the container to maintain the priority order
    protected void siftDown(Position<E> posAOrdenar) {}

    //Protected method that sifts up a position in the container to maintain the priority order
    protected void siftUp(Position<E> newPosition) {}

    //Retrieves the number of items in the container
    public int size() {return 0;}

    //Method overwriting Object.toString()
    public String toString() {return null;}

    //Retrieves the items in the container
    public Iterator<E> values() {return null;}
}
