
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
  private int maxCapacity;
  private int size;
  private E[] elements;
  private int head;
  private int tail;

  @SuppressWarnings("unchecked")
  public QueueArrayImpl() {
    maxCapacity = 100;
    elements = (E[]) new Object[maxCapacity];
  }

  @SuppressWarnings("unchecked")
  public QueueArrayImpl(int max) {
    if (max <= 0) {
      throw new IllegalArgumentException("Max value should be greater than 0");
    }
    maxCapacity = max;
    elements = (E[]) new Object[maxCapacity];
  }

  public void add(E elem) {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    elements[tail++] = elem;
    size++;
    if (tail == maxCapacity) {
      tail = 0;
    }
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
    return elements[head];
  }

  public E poll() {
    if (isEmpty()) {
      return null;
    }
    E elem = elements[head];
    elements[head++] = null;
    size--;
    if (head == maxCapacity) {
      head = 0;
    }
    return elem;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    for (int i = head; i < head + size; i++) {
      int index = i % maxCapacity;
      sb.append(elements[index].toString());
      if (i != head + size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  public Iterator<E> values() {
    return new Iterator<E>() {
      private int count;
      private int index;

      @Override
      public boolean hasNext() {
        return count < size;
      }

      @Override
      public E next() {
        E elem = elements[(index + head) % maxCapacity];
        index++;
        count++;
        return elem;
      }
    };
  }
}
