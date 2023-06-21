
public class PriorityQueue<E> extends Object implements Queue<E> {

  private E[] elements;
  private Comparator<E> comp;
  private int count;

  public PriorityQueue() { // Constructor without parameters
    this(10, null);
  }

  public PriorityQueue(int max) { // Constructor with a parameter
    this(max, null);
  }

  public PriorityQueue(int max, Comparator<E> comparator) { // Constructor with two parameters
    comp = comparator;
    elements = (E[]) new Object[max];
  }

  public PriorityQueue(Comparator<E> comparator) { // Constructor with a parameter
    this(10, comparator);
  }

  public void add(E elem) { // Add an item to the appropriate position
    if (isFull())
      throw new FullContainerException();
    elements[nextLastPosition(elem)] = elem;
    siftUp(new Position<>(nextLastPosition(elem) - 1));
    count++;
  }

  public PriorityQueue<E> clone() {
    PriorityQueue<E> newQueue = new PriorityQueue<>(elements.length, comp);
    for (E e : elements) {
      newQueue.add(e);
    }
    return newQueue;
  }

  protected int compare(Position<E> pos1, Position<E> pos2) { // Compare two positions
    E item1 = elements[pos1.getPosition()];
    E item2 = elements[pos2.getPosition()];
    return comp != null ?
        comp.compare(item1, item2) : ((Comparable<E>) item1).compareTo(item2);
  }

  protected Position<E> deleteLastPosition() { // Return the last element and set it to null
    Position<E> pos = new Position<>(count - 1);
    elements[count - 1] = null;
    count--;
    return pos;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public boolean isFull() {
    return count == elements.length;
  }

  protected Position<E> nextLastPosition(E elem) {
    if (isFull())
      throw new FullContainerException();
    int position = count;
    Position<E> parent = null;
    if (count != 0) {
      int parentPos = (position - 1) / 2;
      parent = new Position<>(parentPos);
    }
    siftUp(parent);
    return new Position<>(count);
  }

  public E peek() { // Retrieves the lowest priority item
    if (isEmpty()) throw new EmptyContainerException();
    return elements[0];
  }

  public E poll() { // Delete the lowest priority item
    if (isEmpty()) throw new EmptyContainerException();
    E first = elements[0];
    elements[0] = elements[count - 1];
    elements[count - 1] = null;
    count--;
    siftDown(new Position<>(0));
    return first;
  }

  @Override
  public Traversal<Position<E>> positions() {
    return null;
  }

  protected void siftDown(Position<E> pos) {
    while (pos.left().getPosition() < count) {
      Position<E> minPosition = pos.left();
      if (pos.right().getPosition() < count) {
        if (compare(pos.right(), minPosition) < 0) {
          minPosition = pos.right();
        }
      }
      if (compare(minPosition, pos) < 0) {
        swap(pos, minPosition);
        pos = minPosition;
      } else {
        return;
      }
    }
  }

  protected void siftUp(Position<E> newPosition) {
    Position<E> parentPosition = newPosition.getParent();
    while (newPosition.getPosition() != 0 && compare(newPosition, parentPosition) < 0) {
      swap(newPosition, parentPosition);
      newPosition = parentPosition;
      parentPosition = newPosition.getParent();
    }
  }

  public int size() { // Retrieves the number of items in the container
    return count;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("[");
    boolean first = true;
    for (E elem : elements) {
      if (!first) {
        builder.append(", ");
      } else {
        first = false;
      }
      if (elem == null) {
        builder.append("null");
      } else {
        builder.append(elem);
      }
    }
    builder.append("]");
    return builder.toString();
  }

  @Override
  public Iterator<E> values() { // Retrieves the items in the container
    return new Iterator<>() {
      private int pos = 0;

      @Override
      public boolean hasNext() {
        return pos < count;
      }

      @Override
      public E next() {
        if (!hasNext()) throw new EmptyContainerException();
        E elem = elements[pos];
        pos++;
        return elem;
      }
    };
  }

  private void swap(Position<E> p1, Position<E> p2) {
    E temp = elements[p1.getPosition()];
    elements[p1.getPosition()] = elements[p2.getPosition()];
    elements[p2.getPosition()] = temp;
  }
}
