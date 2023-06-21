
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] array;
    private int top;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        array = (E[]) new Object[10];
        top = -1;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        array = (E[]) new Object[max];
        top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == (array.length - 1));
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            return array[top];
        }
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            E value = array[top];
            array[top] = null;
            top--;
            return value;
        }
    }

    public void push(E elem) {
        if (isFull()) {
            return;
        } else {
            top++;
            array[top] = elem;
        }
    }

    public int size() {
        return (top + 1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Stack [ ");
        for (int i = 0; i <= top; i++) {
            sb.append(array[i]).append(" ");
        }
        sb.append("]");

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public Iterator<E> values() {
        E[] copy = (E[]) new Object[size()];
        System.arraycopy(array, 0, copy, 0, size());
        return new ArrayIteratorImpl<E>(copy);
    }
}
