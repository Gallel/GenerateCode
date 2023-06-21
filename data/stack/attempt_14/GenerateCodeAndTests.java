
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
  
  private int capacity;
  private int size;
  private E[] stack;
  
  @SuppressWarnings("unchecked")
  StackArrayImpl() {
    this.capacity = 10;
    this.size = 0;
    stack = (E[]) new Object[capacity];
  }
  
  @SuppressWarnings("unchecked")
  StackArrayImpl(int max) {
    this.capacity = max;
    this.size = 0;
    stack = (E[]) new Object[capacity];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == capacity;
  }

  public E peek() {
    if (isEmpty()) {
      return null;
    }
    return stack[size-1];
  }

  public E pop() {
    if (isEmpty()) {
      return null;
    }
    E elem = stack[size-1];
    stack[size-1] = null;
    size--;
    return elem;
  }

  public void push(E elem) {
    if (!isFull()) {
      stack[size] = elem;
      size++;
    }
  }

  public int size() {
    return size;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (E elem: this) {
      sb.append(elem.toString()).append(" ");
    }
    return sb.toString();
  }

  public Iterator<E> values() {
    return new Iterator<E>() {
      private int currentIndex = size-1;

      @Override
      public boolean hasNext() {
        return currentIndex >= 0;
      }

      @Override
      public E next() {
        return stack[currentIndex--];
      }
    };
  }
}
