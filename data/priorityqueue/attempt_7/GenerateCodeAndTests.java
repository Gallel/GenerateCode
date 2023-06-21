
import java.util.*;

public class PriorityQueue<E> extends Object implements Queue<E> {

  // Constructors
  public PriorityQueue() {}
  public PriorityQueue(int max) {}
  public PriorityQueue(int max, Comparator<E> comparator) {}
  public PriorityQueue(Comparator<E> comparator) {}

  // Methods
  public void add(E elem) {}
  public PriorityQueue<E> clone() {return null;}
  protected int compare(Position<E> pos1, Position<E> pos2) {return 0;}
  protected Position<E> deleteLastPosition() {return null;}
  public boolean isEmpty() {return false;}
  public boolean isFull() {return false;}
  protected Position<E> nextLastPosition(E elem) {return null;}
  public E peek() {return null;}
  public E poll() {return null;}
  public Traversal positions() {return null;}
  protected void siftDown(Position<E> posicioAOrdenar) {}
  protected void siftUp(Position<E> newPosition) {}
  public int size() {return 0;}
  public String toString() {return null;}
  public Iterator<E> values() {return null;}

}
