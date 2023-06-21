
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

  private int capacity;
  private int size;
  private int front;
  private int rear;
  private Object[] elements;

  public QueueArrayImpl() {
    this(100);
  }

  public QueueArrayImpl(int max) {
    this.capacity = max;
    this.elements = new Object[capacity];
    this.front = 0;
    this.rear = -1;
  }

  public void add(E elem) {
    if (isFull()) {
      throw new IndexOutOfBoundsException("Queue is full!");
    } else {
      size++;
      rear = (rear + 1) % capacity;
      elements[rear] = elem;
    }
  }

  public boolean isEmpty() {
    return (size == 0);
  }

  public boolean isFull() {
    return (size == capacity);
  }

  public E peek() {
    if (isEmpty()) {
      return null;
    } else {
      return (E) elements[front];
    }
  }

  public E poll() {
    if (isEmpty()) {
      return null;
    } else {
      size--;
      E elem = (E) elements[front];
      front = (front + 1) % capacity;
      return elem;
    }
  }

  public int size() {
    return size;
  }

  public String toString() {
    String result = "[";
    for (int i = 0; i < size; i++) {
      result += elements[(front + i) % capacity].toString();
      if (i != size - 1) {
        result += ", ";
      }
    }
    return result + "]";
  }

  public Iterator<E> values() {
    List<E> list = new ArrayList<E>();
    for (int i = 0; i < size; i++) {
      list.add((E) elements[(front + i) % capacity]);
    }
    return list.iterator();
  }
}
