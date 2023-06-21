
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
  
  private int max;
  private E[] elements;
  private int top;
  
  @SuppressWarnings("unchecked")
  public StackArrayImpl() {
    this.max = 100;
    this.elements = (E[]) new Object[max];
    this.top = -1;
  }
  
  @SuppressWarnings("unchecked")
  public StackArrayImpl(int max) {
    this.max = max;
    this.elements = (E[]) new Object[max];
    this.top = -1;
  }
  
  public boolean isEmpty() {
    return top == -1;
  }
  
  public boolean isFull() {
    return top == max - 1;
  }
  
  public E peek() {
    if (isEmpty()) return null;
    return elements[top];
  }
  
  public E pop() {
    if (isEmpty()) return null;
    E elem = elements[top];
    elements[top--] = null;
    return elem;
  }
  
  public void push(E elem) {
    if (isFull()) return;
    elements[++top] = elem;
  }
  
  public int size() {
    return top + 1;
  }
  
  public String toString() {
    StringBuilder result = new StringBuilder("[");
    for (int i = 0; i <= top; i++) {
      result.append(elements[i]);
      if (i < top) result.append(", ");
    }
    result.append("]");
    return result.toString();
  }
  
  public Iterator<E> values() {
    return new Iterator<E>() {
      private int actual = top;
  
      public boolean hasNext() {
        return actual >= 0;
      }
  
      public E next() {
        return elements[actual--];
      }
    };
  }
}
