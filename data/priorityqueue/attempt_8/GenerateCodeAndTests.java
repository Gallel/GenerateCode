
import java.util.Comparator;
import java.util.Iterator;

public class PriorityQueue<E> extends Object implements Queue<E> {
  
  // constructor without parameters
  public PriorityQueue() {}
  
  // constructor with given capacity
  public PriorityQueue(int max) {}
  
  // constructor with given capacity and comparator
  public PriorityQueue(int max, Comparator<E> comparator) {}
  
  // constructor with given comparator
  public PriorityQueue(Comparator<E> comparator) {}
  
  // add an element to the appropriate position
  public void add(E elem) {}
  
  // clone method
  public PriorityQueue<E> clone() {}
  
  // compare method
  protected int compare(Position<E> pos1, Position<E> pos2) {}
  
  // delete last position method
  protected Position<E> deleteLastPosition() {}
  
  // check if queue is empty
  public boolean isEmpty() {}
  
  // check if queue is full
  public boolean isFull() {}
  
  // get next last position
  protected Position<E> nextLastPosition(E elem) {}
  
  // peek method
  public E peek() {}
  
  // poll method
  public E poll() {}
  
  // positions method
  public Traversal positions() {}
  
  // sift down method
  protected void siftDown(Position<E> posicioAOrdenar) {}
  
  // sift up method
  protected void siftUp(Position<E> newPosition) {}
  
  // size method
  public int size() {}
  
  // toString method
  public String toString() {}
  
  // values method
  public Iterator<E> values() {}
  
}
