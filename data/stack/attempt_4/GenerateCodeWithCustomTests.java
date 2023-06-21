
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] elements;
    private int max;
    private int size;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        max = 1000;
        elements = (E[]) new Object[max];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.max = max;
        elements = (E[]) new Object[max];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == max;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[size-1];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E last = elements[size-1];
        elements[size-1] = null;
        size--;
        return last;
    }

    public void push(E elem) {
        if (isFull()) {
            return;
        }
        elements[size] = elem;
        size++;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i].toString());
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int index = 0;

            public boolean hasNext() {
                return index < size;
            }

            public E next() {
                if (!hasNext()) {
                    return null;
                }
                E value = elements[index];
                index++;
                return value;
            }
        };
    }
}
