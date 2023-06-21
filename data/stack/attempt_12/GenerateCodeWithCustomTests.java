
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private E[] stack;
    private int top;
    private int max;

    public StackArrayImpl() {
        this(100);
    }

    public StackArrayImpl(int max) {
        this.max = max;
        this.stack = (E[]) new Object[max];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == max - 1;
    }

    public E peek() {
        if (isEmpty())
            return null;
        return stack[top];
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elem = stack[top];
        stack[top--] = null;
        return elem;
    }

    public void push(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        stack[++top] = elem;
    }

    public int size() {
        return top + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(stack[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int current = top;

            public boolean hasNext() {
                return current >= 0;
            }

            public E next() {
                if (hasNext()) {
                    return stack[current--];
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
