
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private int max;
    private E[] elements;
    private int top;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        this.max = 1000;
        this.elements = (E[]) new Object[max];
        this.top = -1;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.max = max;
        this.elements = (E[]) new Object[max];
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
        } else {
            return elements[top];
        }
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            E elem = elements[top];
            elements[top] = null;
            top--;
            return elem;
        }
    }

    public void push(E elem) {
        if (!isFull()) {
            top++;
            elements[top] = elem;
        }
    }

    public int size() {
        return top + 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack Array (");
        sb.append(size());
        sb.append("/");
        sb.append(max);
        sb.append("): [");
        for (int i = top; i >= 0; i--) {
            sb.append(elements[i].toString());
            sb.append(", ");
        }
        if (size() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int currentIndex = top;

            public boolean hasNext() {
                return currentIndex >= 0 && elements[currentIndex] != null;
            }

            public E next() {
                E elem = elements[currentIndex];
                currentIndex--;
                return elem;
            }
        };
    }
}
