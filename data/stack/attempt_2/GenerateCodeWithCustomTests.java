
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] array;
    private int top;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        array = (E[]) new Object[DEFAULT_SIZE];
        top = -1;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        array = (E[]) new Object[max];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == array.length - 1;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E elem = array[top];
        array[top] = null;
        top--;
        return elem;
    }

    public void push(E elem) {
        if (isFull()) {
            return;
        }
        top++;
        array[top] = elem;
    }

    public int size() {
        return top + 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = top; i >= 0; i--) {
            sb.append(array[i]);
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
            list.add(array[i]);
        }
        return list.iterator();
    }
}
