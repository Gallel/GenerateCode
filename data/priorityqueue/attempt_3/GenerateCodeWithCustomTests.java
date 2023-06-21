
import java.util.Comparator;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.adt.nonlinear.Position;
import edu.uoc.ds.adt.nonlinear.Queue;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.traversal.Iterator;

public class PriorityQueue<E> extends Object implements Queue<E> {
  
  // Attribute declarations
  
  // Constructors
  public PriorityQueue() {}
  
  public PriorityQueue(int max) {}
  
  public PriorityQueue(int max, Comparator<E> comparator) {}
  
  public PriorityQueue(Comparator<E> comparator) {}
  
  // Methods
  public void add(E elem) {}
  
  @Override
  public PriorityQueue<E> clone() { return null; }
  
  protected int compare(Position<E> pos1, Position<E> pos2) { return 0; }
  
  protected Position<E> deleteLastPosition() { return null; }
  
  @Override
  public boolean isEmpty() { return false; }
  
  @Override
  public boolean isFull() { return false; }
  
  protected Position<E> nextLastPosition(E elem) { return null; }
  
  @Override
  public E peek() { return null; }
  
  @Override
  public E poll() throws EmptyContainerException { return null; }
  
  @Override
  public Traversal positions() { return null; }
  
  protected void siftDown(Position<E> posicionAOrdenar) {}
  
  protected void siftUp(Position<E> newPosition) {}
  
  @Override
  public int size() { return 0; }
  
  @Override
  public String toString() { return null; }
  
  @Override
  public Iterator<E> values() { return null; }
}
