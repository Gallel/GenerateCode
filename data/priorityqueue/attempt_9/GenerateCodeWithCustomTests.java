
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;
import java.util.Comparator;

public class PriorityQueue<E> extends Object implements Queue<E> {

  PriorityQueue() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  PriorityQueue(int max) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  PriorityQueue(int max, Comparator<E> comparator) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  PriorityQueue(Comparator<E> comparator) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  void add(E elem) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  PriorityQueue<E> clone() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  protected int compare(Position<E> pos1, Position<E> pos2) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  protected Position<E> deleteLastPosition() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  boolean isEmpty() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  boolean isFull() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  protected Position<E> nextLastPosition(E elem) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  E peek() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  E poll() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  Traversal positions() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  protected void siftDown(Position<E> posicioAOrdenar) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  protected void siftUp(Position<E> newPosition) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  int size() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  Iterator<E> values() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
