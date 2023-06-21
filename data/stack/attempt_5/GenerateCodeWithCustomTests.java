
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
  
  private int maxCapacity;
  private E[] stackArray;
  private int top;
  
  @SuppressWarnings("unchecked")
  public StackArrayImpl() {
    this.maxCapacity = 100;
    this.top = -1;
    stackArray = (E[]) new Object[this.maxCapacity];
  }
  
  @SuppressWarnings("unchecked")
  public StackArrayImpl(int max) {
    this.maxCapacity = max;
    this.top = -1;
    stackArray = (E[]) new Object[this.maxCapacity];
  }
  
  public boolean isEmpty() {
    return this.top == -1;
  }
  
  public boolean isFull() {
    return this.top == this.maxCapacity - 1;
  }
  
  public E peek() {
    if (isEmpty()) {
      return null;
    } else {
      return stackArray[top];
    }
  }
  
  public E pop() {
    if (isEmpty()) {
      return null;
    } else {
      E elem = stackArray[top];
      stackArray[top] = null;
      top--;
      return elem;
    }
  }
  
  public void push(E elem) {
    if (!isFull()) {
      top++;
      stackArray[top] = elem;
    }
  }
  
  public int size() {
    return top + 1;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i <= this.top; i++) {
      sb.append(stackArray[i]);
      if (i != this.top) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
  
  public Iterator<E> values() {
    List<E> values = new ArrayList<E>();
    for (int i = 0; i <= this.top; i++) {
      values.add(stackArray[i]);
    }
    return values.iterator();
  }
}
