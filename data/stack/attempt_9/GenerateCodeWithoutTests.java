
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
  private E[] stack;
  private int top;
  private int max;

  @SuppressWarnings("unchecked")
  public StackArrayImpl() {
    this.max = 1000; // default maximum capacity
    this.stack = (E[]) new Object[this.max];
    this.top = -1;
  }

  @SuppressWarnings("unchecked")
  public StackArrayImpl(int max) {
    this.max = max;
    this.stack = (E[]) new Object[this.max];
    this.top = -1;
  }

  public boolean isEmpty() {
    return (top == -1);
  }

  public boolean isFull() {
    return (top == max - 1);
  }

  public E peek() {
    if (isEmpty()) {
      return null;
    }
    return stack[top];
  }

  public E pop() {
    if (isEmpty()) {
      return null;
    }
    E elem = stack[top];
    top--;
    return elem;
  }

  public void push(E elem) {
    if (isFull()) {
      return;
    }
    top++;
    stack[top] = elem;
  }

  public int size() {
    return top + 1;
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (int i = 0; i <= top; i++) {
      if (i > 0) {
        stringBuilder.append(", ");
      }
      stringBuilder.append(stack[i].toString());
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  public Iterator<E> values() {
    List<E> list = new ArrayList<>();
    for (int i = 0; i <= top; i++) {
      list.add(stack[i]);
    }
    return list.iterator();
  }
}
