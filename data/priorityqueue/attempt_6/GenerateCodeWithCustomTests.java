
import java.util.Comparator;
import edu.uoc.ds.adt.nonlinear.Position;
import edu.uoc.ds.exceptions.EmptyContainerException;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.Traversal;

public class PriorityQueue<E> extends Object implements Queue<E> {
  
  private int max; 
  private E[] elementos;
  private Comparator<E> comparator;
  private int size = 0;
  
  public PriorityQueue() {
    this.max = 10;
    elementos = (E[]) new Object[this.max];
    this.comparator = null;    
  } 
  
  public PriorityQueue(int max) {
    this.max = max;
    elementos = (E[])new Object[this.max];
    this.comparator = null;
      
  }
  
  public PriorityQueue(int max, Comparator<E> comparator) {
    this.max = max;
    elementos = (E[])new Object[this.max];
    this.comparator = comparator;
  }
  
  public PriorityQueue(Comparator<E> comparator) {
    this.max = 10;
    elementos = (E[])new Object[this.max];
    this.comparator = comparator;
  }
  
  public void add(E elem) {
    if (size >= max) {
      throw new FullContainerException();
    } else {
      elementos[++size] = elem;
      siftUp(size);
    }
  }
  
  protected void siftUp(Position<E> newPosition) {
    Position<E> padre;
    while (newPosition.getIndice() > 1) { 
      padre = getPosition(newPosition.getIndice() / 2);
      if (compare(newPosition, padre) >= 0) {
        return;
      } else {
        swap(newPosition, padre);
        newPosition = padre;
      }
    }
  }

  protected void siftDown(Position<E> posicioAOrdenar) {
    int movimiento;
    Position<E> posHijoIzq, posHijoDer;    
    while (2 * posicioAOrdenar.getIndice() <= size) { 
      posHijoIzq = getPosition(2 * posicioAOrdenar.getIndice());                
      posHijoDer = getPosition(2 * posicioAOrdenar.getIndice() + 1);
      if (compare(posHijoIzq, posicioAOrdenar) >= 0) {
        if (posHijoDer == null || compare(posHijoDer, posHijoIzq) >= 0) {
          return;
        } else {
          movimiento = 2 * posicioAOrdenar.getIndice() + 1;
        }
      } else {
        if (posHijoDer == null || compare(posHijoDer, posicioAOrdenar) >= 0) {
          movimiento = 2 * posicioAOrdenar.getIndice();
        } else {
          if (compare(posHijoDer, posHijoIzq) < 0) {
            movimiento = 2 * posicioAOrdenar.getIndice() + 1;
          } else {
            movimiento = 2 * posicioAOrdenar.getIndice();
          }
        }
      }          
      swap(posicioAOrdenar, getPosition(movimiento));
      posicioAOrdenar = getPosition(movimiento);    
    }    
  }
  
  private void swap(Position<E> pos1, Position<E> pos2) {
    E AUX;
    AUX = pos1.getElemento();
    pos1.setElement(pos2.getElemento());
    pos2.setElement(AUX);    
  }
  
  protected int compare(Position<E> pos1, Position<E> pos2) {
    if (comparator != null) {
      return comparator.compare(pos1.getElemento(), pos2.getElemento());
    } else {
      return ((Comparable<E>) pos1.getElemento()).compareTo(pos2.getElemento());
    }    
  }
  
  protected Position<E> getPosition(int indicePos) {
    if (indicePos >= max) {
      return null;
    }
    E element = elementos[indicePos];
    if (element == null) {
      return null;
    }
    return new Position(indicePos, element);
  }
  
  public E peek() {
    if (size > 0) {
      E first = elementos[1];
      return first;
    } else {
      return null;
    }
  }
  
  public E poll() {
    if (size == 0) {
      throw new EmptyContainerException();
    } else {
      E primerElement = elementos[1];
      elementos[1] = elementos[size];
      size--;
      siftDown(getPosition(1));
      return primerElement;      
    }
  }
    
  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == max;
  }
  
  public int size() {
    return size;
  }
  
  @Override
  public Iterator<E> values() {
    return new Traversal<E>(this, Traversal.Type.PREORDER);
  }
    
  protected Position<E> deleteLastPosition() {
    if (isEmpty()) {
      return null;
    } else {
      Position<E> posContenedor = getPosition(size);
      elementos[size--] = null;
      return posContenedor;
    }
  }
    
  protected Position<E> nextLastPosition(E elem) {
    if (size == max) {
      throw new FullContainerException();
    } else {
      elementos[++size] = elem;
      return getPosition(size);
    }
  }

  @Override
  public PriorityQueue<E> clone() {
    PriorityQueue<E> cola = new PriorityQueue(max, comparator);
    int newPos = 0;
    while (newPos <= size) {
      cola.elementos[newPos] = elementos[newPos];
      newPos++;
    }
    return cola;
  }    
  
  @Override
  public String toString() {
    return "Cola de tamaño max = " + max + " (núm. elem.: " + size + ")";
  } 
}
