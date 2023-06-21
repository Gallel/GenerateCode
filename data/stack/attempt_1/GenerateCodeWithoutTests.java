
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

  private int capacity;
  private Object[] elements;
  private int topIndex;

  public StackArrayImpl() {
    this.capacity = 10;
    this.elements = new Object[capacity];
    this.topIndex = -1;
  }

  public StackArrayImpl(int max) {
    this.capacity = max;
    this.elements = new Object[capacity];
    this.topIndex = -1;
  }

  public boolean isEmpty() {
    return (topIndex == -1);
  }

  public boolean isFull() {
    return (topIndex == capacity - 1);
  }

  public E peek() {
    if (isEmpty()) {
      return null;
    }
    return (E) elements[topIndex];
  }

  public E pop() {
    if (isEmpty()) {
      return null;
    }
    E elem = (E) elements[topIndex];
    elements[topIndex--] = null;
    return elem;
  }

  public void push(E elem) {
    if (!isFull()) {
      elements[++topIndex] = elem;
    }
  }

  public int size() {
    return topIndex + 1;
  }

  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
    for (int i = 0; i <= topIndex; i++) {
      strBuilder.append(elements[i]).append(" ");
    }
    return strBuilder.toString();
  }

  public Iterator<E> values() {
    return new Iterator<E>() {
      int currentIndex = topIndex;

      public boolean hasNext() {
        return currentIndex >= 0 && elements[currentIndex] != null;
      }

      @SuppressWarnings("unchecked")
      public E next() {
        return (E) elements[currentIndex--];
      }

    };
  }

}
