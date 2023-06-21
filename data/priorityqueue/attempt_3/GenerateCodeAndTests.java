
public class PriorityQueue<E> extends Object implements Queue<E> {
    
    public PriorityQueue() {}
    
    public PriorityQueue(int max) {}

    public PriorityQueue(int max, Comparator<E> comparator) {}
    
    public PriorityQueue(Comparator<E> comparator) {}
    
    public void add(E elem) {}
    
    public PriorityQueue<E> clone() {}
    
    protected int compare(Position<E> pos1, Position<E> pos2) {}
    
    protected Position<E> deleteLastPosition() {}
    
    public boolean isEmpty() {
        return true;
    }
    
    public boolean isFull() {
        return true;
    }
    
    protected Position<E> nextLastPosition(E elem) {}
    
    public E peek() {
        return null;
    }
    
    public E poll() {
        return null;
    }
    
    public Traversal positions() {
        return null;
    }
    
    protected void siftDown(Position<E> posicioAOrdenar) {}
    
    protected void siftUp(Position<E> newPosition) {}
    
    public int size() {
        return 0;
    }
    
    public String toString() {
        return "";
    }
    
    public Iterator<E> values() {
        return null;
    }
}
