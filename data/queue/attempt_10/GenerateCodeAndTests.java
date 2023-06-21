
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

  private int maxCapacity;
  private E[] queueArray;
  private int head;
  private int tail;
  private int size;

  public QueueArrayImpl() {
    this.maxCapacity = 10;
    this.queueArray = (E[]) new Object[maxCapacity];
    this.head = 0;
    this.tail = 0;
    this.size = 0;
  }

  public QueueArrayImpl(int max) {
    this.maxCapacity = max;
    this.queueArray = (E[]) new Object[maxCapacity];
    this.head = 0;
    this.tail = 0;
    this.size = 0;
  }

  public void add(E elem) {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    queueArray[tail] = elem;
    tail = (tail + 1) % maxCapacity;
    size++;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == maxCapacity;
  }

  public E peek() {
    if (isEmpty()) {
      return null;
    }
    return queueArray[head];
  }

  public E poll() {
    if (isEmpty()) {
      return null;
    }
    E elem = queueArray[head];
    queueArray[head] = null;
    head = (head + 1) % maxCapacity;
    size--;
    return elem;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < size; i++) {
      sb.append(queueArray[(head + i) % maxCapacity]);
      if (i != size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  public Iterator<E> values() {
    return new QueueArrayIterator();
  }

  private class QueueArrayIterator implements Iterator<E> {
    private int index = 0;

    public boolean hasNext() {
      return index < size();
    }

    public E next() {
      return queueArray[(head + index++) % maxCapacity];
    }

    public void remove() {
      throw new UnsupportedOperationException("Remove not supported");
    }
  }
}
