
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

  private E[] queueArray; // declaring the array to hold the queue
  private int front; // declaring the index value of front element
  private int rear; // declaring the index value of rear element
  private int capacity; // declaring the capacity of the queue
  private int size; // declaring the size of the queue

  // Constructor without parameters  (Maximum capacity of the container by default).
  public QueueArrayImpl() {
    this.capacity = 10; // default capacity
    this.front = 0;
    this.rear = -1;
    this.size = 0;
    this.queueArray = (E[]) new Object[this.capacity]; // initializing the array
  }

  // Constructor with a parameter.
  public QueueArrayImpl(int max) {
    this.capacity = max;
    this.front = 0;
    this.rear = -1;
    this.size = 0;
    this.queueArray = (E[]) new Object[this.capacity]; // initializing the array
  }

  // Add an item to the queue
  public void add(E elem) {
    if (isFull()) { // checking if the queue is full
      throw new IllegalStateException("Queue is full");
    }
    rear = (rear + 1) % capacity; // getting the rear index value
    queueArray[rear] = elem; // adding element to the queue
    size++; // increasing the size of the queue
  }

  // Method to check if the container is empty.
  public boolean isEmpty() {
    return (size == 0);
  }

  // Method to check if the container is full.
  public boolean isFull() {
    return (size == capacity);
  }

  // Retrieves the first item added to the queue
  public E peek() {
    if (isEmpty()) { // checking if the queue is empty
      throw new NoSuchElementException("Queue is empty");
    }
    return queueArray[front]; // returning the first element in the queue
  }

  // Delete the first item in the queue
  public E poll() {
    if (isEmpty()) { // checking if the queue is empty
      throw new NoSuchElementException("Queue is empty");
    }
    E elem = queueArray[front]; // getting the first element in the queue
    queueArray[front] = null; // deleting the first element from the queue
    front = (front + 1) % capacity; // getting the updated front index value
    size--; // decreasing the size of the queue
    return elem; // returning the deleted element
  }

  // Retrieves the number of items in the container.
  public int size() {
    return size;
  }

  // Method overwriting Object.toString ().
  public String toString() {
    if (isEmpty()) { // checking if the queue is empty
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < size; i++) {
      sb.append(queueArray[(front + i) % capacity]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString(); // returning the string representation of the queue
  }

  // Retrieves the items in the container.
  public Iterator<E> values() {
    List<E> list = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      list.add(queueArray[(front + i) % capacity]); // adding each element in the queue to the list
    }
    return list.iterator(); // returning the iterator for the list
  }

}
