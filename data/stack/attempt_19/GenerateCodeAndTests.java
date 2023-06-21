
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] stack;
    private int top;
    private int capacity;

    public StackArrayImpl() {
        this(1000);
    }

    public StackArrayImpl(int max) {
        capacity = max;
        stack = (E[]) new Object[max];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
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
        return stack[top--];
    }

    public void push(E elem) {
        if (!isFull()) {
            stack[++top] = elem;
        }
    }

    public int size() {
        return top + 1;
    }

    public String toString() {
        return "Stack: " + Arrays.toString(stack);
    }

    public Iterator<E> values() {
        return Arrays.stream(stack).iterator();
    }
}
