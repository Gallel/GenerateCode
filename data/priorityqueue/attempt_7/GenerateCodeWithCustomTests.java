
import java.util.Comparator;
import java.util.Iterator;
import edu.uoc.ds.adt.nonlinear.Position;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class PriorityQueue<E> extends Object implements Queue<E>{
    public PriorityQueue() {}
    public PriorityQueue(int max) {}
    public PriorityQueue(int max, Comparator<E> comparator) {}
    public PriorityQueue(Comparator<E> comparator) {}
    public void add(E elem) {}
    PriorityQueue<E> clone() {}
    protected int compare(Position<E> pos1,Position<E> pos2) {}
    protected Position<E> deleteLastPosition() {}
    public boolean isEmpty() {}
    public boolean isFull() {}
    protected Position<E> nextLastPosition(E elem) {}
    public E peek() {}
    public E poll() {}
    Traversal positions() {}
    protected void siftDown(Position<E> posicioAOrdenar) {}
    protected void siftUp(Position<E> newPosition) {}
    public int size() {}
    @Override
    public String toString() {}
    public Iterator<E> values() {}
}
