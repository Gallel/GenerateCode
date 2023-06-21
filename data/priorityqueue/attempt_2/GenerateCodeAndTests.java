
import java.util.*;

public class PriorityQueue<E> extends Object implements Queue<E> {
  
  //Attributes
  protected ArrayList<E> elements;
  protected Comparator<E> comparator;
  
  //Constructors
  public PriorityQueue() {
    this(10);
  }
  
  public PriorityQueue(int max) {
    this(max, null);
  }
  
  public PriorityQueue(Comparator<E> comparator) {
    this(10, comparator);
  }
  
  public PriorityQueue(int max, Comparator<E> comparator) {
    if (max < 1)
      throw new RuntimeException("Size must be at least 1");
    this.elements = new ArrayList<>(max + 1);
    this.elements.add(null); // put a null at the first position
    this.comparator = comparator;
  }
  
  // Methods
  public PriorityQueue<E> clone() {
    PriorityQueue<E> copy = new PriorityQueue<>(elements.size() - 1, comparator);
    copy.elements.addAll(this.elements);
    return copy;
  }
  
  protected int compare(int i, int j) {
    if (comparator == null)
      return ((Comparable<E>) elements.get(i)).compareTo(elements.get(j));
    else
      return comparator.compare(elements.get(i), elements.get(j));
  }
  
  protected boolean heapify(int idx) {
    if (idx <= 0)
      throw new RuntimeException("Index is less than 1.");
    int parent;
    while (idx > 1 && compare(idx, parent = idx / 2) < 0) {
      swap(idx, parent);
      idx = parent;
    }
    return idx > 1;
  }
  
  protected boolean sink(int idx) {
    if (idx < 1 || idx >= elements.size())
      throw new RuntimeException("Invalid index for sink operation.");
    boolean swap;
    do {
      swap = false;
      int leftIdx = 2 * idx;
      int rightIdx = leftIdx + 1;
      int smallestIdx;
      if (rightIdx < elements.size()) { // if rightIdx exists then leftIdx does too
        smallestIdx = compare(leftIdx, rightIdx) <= 0 ? leftIdx : rightIdx;
      } else if (leftIdx < elements.size()) {
        smallestIdx = leftIdx;
      } else {
        return false; // both left and right child missing
      }
      if (compare(idx, smallestIdx) > 0) {
        swap = true;
        swap(idx, smallestIdx);
        idx = smallestIdx;
      }
    } while (swap);
    return true;
  }
  
  public boolean add(E elem) {
    elements.add(elem);
    return heapify(elements.size() - 1);
  }
  
  public E element() { // throws an exception if the queue is empty
    E elem = peek();
    if (elem == null)
      throw new NoSuchElementException();
    return elem;
  }
  
  public boolean offer(E elem) {
    return add(elem);
  }
  
  public E peek() {
    if (elements.size() == 1)
      return null;
    else
      return elements.get(1);
  }
  
  public E poll() {
    if (elements.size() == 1) {
      return null;
    } else {
      E elem = elements.get(1);
      elements.set(1, elements.remove(elements.size() - 1));
      sink(1);
      return elem;
    }
  }
  
  public E remove() { // throws an exception if the queue is empty
    E elem = poll();
    if (elem == null)
      throw new NoSuchElementException();
    return elem;
  }
  
  public int size() {
    return elements.size() - 1;
  }
  
  //Helper methods
  protected void swap(int i, int j) {
    E tmp = elements.get(i);
    elements.set(i, elements.get(j));
    elements.set(j, tmp);
  }
  
  public String toString() {
    return elements.toString();
  }
  
  public Iterator<E> iterator() {
    return elements.subList(1, elements.size()).iterator();
  }
}
