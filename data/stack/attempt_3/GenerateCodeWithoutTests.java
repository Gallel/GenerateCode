
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private int maxCapacity;
    private E[] stack;
    private int topIndex;

    public StackArrayImpl() {
        this.maxCapacity = 100;
        this.stack = (E[]) new Object[maxCapacity];
        this.topIndex = -1;
    }

    public StackArrayImpl(int max) {
        this.maxCapacity = max;
        this.stack = (E[]) new Object[maxCapacity];
        this.topIndex = -1;
    }

    public boolean isEmpty() {
        return topIndex == -1;
    }

    public boolean isFull() {
        return topIndex == maxCapacity - 1;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[topIndex];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E elem = stack[topIndex];
        stack[topIndex] = null;
        topIndex--;
        return elem;
    }

    public void push(E elem) {
        if (!isFull()) {
            topIndex++;
            stack[topIndex] = elem;
        }
    }

    public int size() {
        return topIndex + 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (E item : stack) {
            if (item != null) {
                sb.append(item.toString() + " ");
            }
        }
        return sb.toString().trim();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int cursor = topIndex;

            public boolean hasNext() {
                return cursor >= 0;
            }

            public E next() {
                return stack[cursor--];
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported");
            }
        };
    }
}
