
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

  private int MAX_CAPACITY;
  private int top;
  private E[] array;

  // Constructor without parameters (maximum capacity, default).
  public StackArrayImpl() {
    this.MAX_CAPACITY = 10;
    this.top = -1;
    this.array = (E[]) new Object[MAX_CAPACITY];
  }

  // Constructor with a parameter.
  public StackArrayImpl(int max) {
    this.MAX_CAPACITY = max;
    this.top = -1;
    this.array = (E[]) new Object[MAX_CAPACITY];
  }

  // Method to check if the container is empty.
  public boolean isEmpty() {
    return (top == -1);
  }

  // Method to check if the container is full.
  public boolean isFull() {
    return (top == MAX_CAPACITY - 1);
  }

  // Getter for the last item added to the stack, if any.
  public E peek() {
    if (isEmpty())
      return null;
    return array[top];
  }

  // Delete the top of the stack item, if any.
  public E pop() {
    if (isEmpty())
      return null;

    E element = array[top];
    array[top] = null;
    top--;

    return element;
  }

  // Add an item to the stack, if any.
  public void push(E elem) {
    if (isFull())
      return;

    top++;
    array[top] = elem;
  }

  // Getter for the number of items in the container.
  public int size() {
    return (top + 1);
  }

  // Method overwriting Object.toString ().
  public String toString() {
    if (isEmpty())
      return "[]";

    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i <= top; i++) {
      sb.append(array[i]);
      if (i != top)
        sb.append(", ");
    }
    sb.append("]");

    return sb.toString();
  }

  // Retrieves the items in the container.
  public Iterator<E> values() {
    List<E> list = new ArrayList<E>();
    for (int i = 0; i <= top; i++) {
      list.add(array[i]);
    }
    return list.iterator();
  }
}
