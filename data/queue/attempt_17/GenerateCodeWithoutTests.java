
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
  
  private int front;
  private int rear;
  private E[] arr;
  private int capacity;
  private int count;
  
  //Constructor without parameters (Maximum capacity of the container by default)
  public QueueArrayImpl() {
    this(1000);
  }
  
  //Constructor with a parameter
  public QueueArrayImpl(int max) {
    this.capacity = max;
    this.front = 0;
    this.rear = -1;
    this.arr = (E[]) new Object[max];
    this.count = 0;
  }
  
  //Add an item to the queue
  public void add(E elem) {
    if (isFull()) {
      throw new RuntimeException("Queue is full");
    }
    rear = (rear + 1) % capacity;
    arr[rear] = elem;
    count++;
  }
  
  //Method to check if the container is empty
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  //Method to check if the container is full
  public boolean isFull() {
    return (size() == capacity);
  }
  
  //Retrieves the first item added to the queue
  public E peek() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
    return arr[front];
  }
  
  //Delete the first item in the queue
  public E poll() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
    E elemRemoved = arr[front];
    arr[front] = null;
    front = (front + 1) % capacity;
    count--;
    return elemRemoved;
  }
  
  //Retrieves the number of items in the container
  public int size() {
    return count;
  }
  
  //Method overwriting Object.toString()
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < count; i++) {
      sb.append(arr[(front + i) % capacity]);
      if (i < count - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
  
  //Retrieves the items in the container
  public Iterator<E> values() {
    return new QueueArrayIterator();
  }
  
  private class QueueArrayIterator implements Iterator<E> {
    private int current;
    
    public QueueArrayIterator() {
      this.current = 0;
    }
    
    public boolean hasNext() {
      return current < count;
    }
    
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      E elem = arr[(front + current) % capacity];
      current++;
      return elem;
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
