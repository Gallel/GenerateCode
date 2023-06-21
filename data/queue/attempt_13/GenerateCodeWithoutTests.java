
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

  private Object[] elems;
  private int head;
  private int tail;
  private int count;
  private int capacity;

  public QueueArrayImpl() {
    this(10);
  }

  public QueueArrayImpl(int max) {
    capacity = max;
    elems = new Object[capacity];
    head = 0;
    tail = -1;
    count = 0;
  }

  public void add(E elem) {
    if (count == capacity) {
      throw new IllegalStateException("Queue is full");
    }
    tail = (tail + 1) % capacity;
    elems[tail] = elem;
    count++;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public boolean isFull() {
    return count == capacity;
  }

  public E peek() {
    if (count == 0) {
      return null;
    }
    return (E) elems[head];
  }

  public E poll() {
    if (count == 0) {
      return null;
    }
    E elem = (E) elems[head];
    head = (head + 1) % capacity;
    count--;
    return elem;
  }

  public int size() {
    return count;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < count; i++) {
      sb.append(elems[(head + i) % capacity]);
      if (i < count - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  public Iterator<E> values() {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<E> {
    private int index = 0;

    public boolean hasNext() {
      return index < count;
    }

    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      E elem = (E) elems[(head + index) % capacity];
      index++;
      return elem;
    }
  }
}
