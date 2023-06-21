
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

  // attribute declaration
  private E[] queueArray;
  private int frontIndex;
  private int rearIndex;
  private int elementsCount;
  private int maxCapacity;

  // Constructor without parameters
  public QueueArrayImpl() {
    this.maxCapacity = 100; // default capacity
    queueArray = (E[]) new Object[this.maxCapacity]; // casting Object[] to E[]
    frontIndex = 0;
    rearIndex = -1;
    elementsCount = 0;
  }

  // Constructor with a parameter
  public QueueArrayImpl(int max) {
    this.maxCapacity = max;
    queueArray = (E[]) new Object[this.maxCapacity]; // casting Object[] to E[]
    frontIndex = 0;
    rearIndex = -1;
    elementsCount = 0;
  }

  // Add an item to the queue
  public void add(E elem) {
    if (isFull()) {
      throw new RuntimeException("Queue is full.");
    } else {
      rearIndex = (rearIndex + 1) % maxCapacity;
      queueArray[rearIndex] = elem;
      elementsCount++;
    }
  }

  // Method to check if the container is empty
  public boolean isEmpty() {
    return (elementsCount == 0);
  }

  // Method to check if the container is full
  public boolean isFull() {
    return (elementsCount == maxCapacity);
  }

  // Retrieves the first item added to the queue
  public E peek() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty.");
    } else {
      return queueArray[frontIndex];
    }
  }

  // Delete the first item in the queue
  public E poll() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty.");
    } else {
      E elem = queueArray[frontIndex];
      frontIndex = (frontIndex + 1) % maxCapacity;
      elementsCount--;
      return elem;
    }
  }

  // Retrieves the number of items in the container
  public int size() {
    return elementsCount;
  }

  // Method overwriting Object.toString()
  public String toString() {
    String result = "";
    for (int i = 0; i < elementsCount; i++) {
      int index = (frontIndex + i) % maxCapacity;
      result += queueArray[index].toString() + " ";
    }
    return result;
  }

  // Retrieves the items in the container
  public Iterator<E> values() {
    return new QueueIterator();
  }

  // Inner class to iterate over the elements of the queue
  private class QueueIterator implements Iterator<E> {
    private int iteratorIndex = 0;

    public boolean hasNext() {
      return (iteratorIndex < elementsCount);
    }

    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      } else {
        int index = (frontIndex + iteratorIndex) % maxCapacity;
        iteratorIndex++;
        return queueArray[index];
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
