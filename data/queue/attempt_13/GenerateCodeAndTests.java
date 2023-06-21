
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

  private int maxCapacity;
  private int currentSize;
  private int frontElementIndex;
  private int rearElementIndex;
  private E[] arrayQueue;

  public QueueArrayImpl() {
    this.maxCapacity = 100;
    this.currentSize = 0;
    this.frontElementIndex = 0;
    this.rearElementIndex = -1;
    this.arrayQueue = (E[]) new Object[maxCapacity];
  }

  public QueueArrayImpl(int max) {
    this.maxCapacity = max;
    this.currentSize = 0;
    this.frontElementIndex = 0;
    this.rearElementIndex = -1;
    this.arrayQueue = (E[]) new Object[maxCapacity];
  }

  @Override
  public void add(E elem) {
    if (!isFull()) {
      rearElementIndex = (rearElementIndex + 1) % maxCapacity;
      arrayQueue[rearElementIndex] = elem;
      currentSize++;
    } else {
      throw new IllegalStateException("Queue is full");
    }
  }

  @Override
  public boolean isEmpty() {
    return currentSize == 0;
  }

  @Override
  public boolean isFull() {
    return currentSize == maxCapacity;
  }

  @Override
  public E peek() {
    if (isEmpty()) {
      return null;
    } else {
      return arrayQueue[frontElementIndex];
    }
  }

  @Override
  public E poll() {
    if (isEmpty()) {
      return null;
    } else {
      E element = arrayQueue[frontElementIndex];
      frontElementIndex = (frontElementIndex + 1) % maxCapacity;
      currentSize--;
      return element;
    }
  }

  @Override
  public int size() {
    return currentSize;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < currentSize; i++) {
      int index = (frontElementIndex + i) % maxCapacity;
      sb.append(arrayQueue[index]);
      if (i != currentSize - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public Iterator<E> values() {
    return new Iterator<E>() {
      private int currentIndex = 0;

      @Override
      public boolean hasNext() {
        return currentIndex < currentSize;
      }

      @Override
      public E next() {
        int index = (frontElementIndex + currentIndex) % maxCapacity;
        currentIndex++;
        return arrayQueue[index];
      }
    };
  }

}
