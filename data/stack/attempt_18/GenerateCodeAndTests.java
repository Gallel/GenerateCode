
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] elements;
    private int top;
    private int maxCapacity;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        this.maxCapacity = 10;
        elements = (E[])new Object[maxCapacity];
        top = -1;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.maxCapacity = max;
        elements = (E[])new Object[maxCapacity];
        top = -1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == maxCapacity - 1;
    }

    @Override
    public E peek() {
        return isEmpty() ? null : elements[top];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            E elem = elements[top];
            elements[top--] = null;
            return elem;
        }
    }

    @Override
    public void push(E elem) {
        if (isFull()) {
            return;
        } else {
            elements[++top] = elem;
        }
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i <= top; i++) {
            sb.append(elements[i]);
            sb.append(" ");
        }
       sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> values() {
        return Arrays.asList(elements).iterator();
    }
}
