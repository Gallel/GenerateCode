
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Constructor without parameters (maximum capacity, default) and elements of a class that implements java.lang.Comparable.
    public PriorityQueue() {...}

    // Constructor with a parameter (given capacity) and elements of a class that implements java.lang.Comparable.
    public PriorityQueue(int max) {...}

    // Constructor with two parameters (given capacity) and elements of one class comparable to the given comparator.
    public PriorityQueue(int max, Comparator<E> comparator) {...}

    // Constructor with a parameter (maximum capacity, default) and items of a class comparable to the given comparator.
    public PriorityQueue(Comparator<E> comparator) {...}

    // Add an item to the appropriate position, if any.
    public void add(E elem) {...}

    // Returns a new PriorityQueue object that is a clone of this PriorityQueue.
    public PriorityQueue<E> clone() {...}

    // Protected method that compares the items contained in the positions received.
    protected int compare(Position<E> pos1, Position<E> pos2) {...}

    // Protected method that deletes the last position.
    protected Position<E> deleteLastPosition() {...}

    // Method to check if the container is empty.
    public boolean isEmpty() {...}

    // Method to check if the container is full.
    public boolean isFull() {...}

    // Protected method that obtains the next last position of the element.
    protected Position<E> nextLastPosition(E elem) {...}

    // Retrieves the lowest priority item, if any.
    public E peek() {...}

    // Remove and return the lowest priority item, if any.
    public E poll() {...}

    // Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other.
    public Traversal positions() {...}

    // Protected method that sorts the heap as the sift down affects the positions greater than pos.
    protected void siftDown(Position<E> pos) {...}

    // Protected method that sorts the heap as the sift up affects the positions less than or equal to the position of newPosition.
    protected void siftUp(Position<E> newPosition) {...}

    // Retrieves the number of items in the container.
    public int size() {...}

    // Method overwriting Object.toString().
    public String toString() {...}

    // Retrieves the items in the container.
    public Iterator<E> values() {...}
}
