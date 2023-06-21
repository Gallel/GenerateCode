
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private int maxSize;
    private int top;
    private E[] stackArray;

    public StackArrayImpl() {
        this(100);
    }

    public StackArrayImpl(int max) {
        maxSize = max;
        stackArray = (E[]) new Object[maxSize];
        top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
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
        return stackArray[top--];
    }

    public void push(E elem) {
        if (isFull()) {
            return;
        }
        stackArray[++top] = elem;
    }

    public int size() {
        return top + 1;
    }

    public String toString() {
        String result = "";
        for (int i = top; i >= 0; i--) {
            result += stackArray[i].toString() + "\n";
        }
        return result;
    }

    public Iterator<E> values() {
        return new ArrayIterator<E>(stackArray, size());
    }

}
