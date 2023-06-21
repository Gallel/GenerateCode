
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private int max;
    private E[] data;
    private int top;

    public StackArrayImpl() {
        this(DEFAULT_SIZE);
    }

    public StackArrayImpl(int max) {
        this.max = max;
        this.top = -1;
        this.data = (E[]) new Object[max];
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
        } else {
            return data[top];
        }
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            E elem = data[top];
            data[top--] = null;
            return elem;
        }
    }

    public void push(E elem) {
        if (!isFull()) {
            data[++top] = elem;
        }
    }

    public int size() {
        return top + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(data[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i <= top; i++) {
            list.add(data[i]);
        }
        return list.iterator();
    }
}
