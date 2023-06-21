
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private int max;
    private E[] stackArray;
    private int top;

    public StackArrayImpl() {
        this(100);
    }

    public StackArrayImpl(int max) {
        this.max = max;
        this.stackArray = (E[])new Object[max];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == max - 1;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[top];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E elem = stackArray[top];
        stackArray[top--] = null;
        return elem;
    }

    public void push(E elem) {
        if (!isFull()) {
            stackArray[++top] = elem;
        }
    }

    public int size() {
        return top + 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = top; i >= 0; i--) {
            sb.append(stackArray[i]);
            if (i != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        ArrayList<E> list = new ArrayList<>();
        for (int i = top; i >= 0; i--) {
            list.add(stackArray[i]);
        }
        return list.iterator();
    }
}
