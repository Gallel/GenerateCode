
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{

    private int max;
    private int size;
    private E[] stackArray;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        this.max = Integer.MAX_VALUE;
        this.size = 0;
        this.stackArray = (E[]) new Object[max];
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.max = max;
        this.size = 0;
        this.stackArray = (E[]) new Object[max];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size >= max;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[size - 1];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E poppedElement = stackArray[--size];
        stackArray[size] = null;
        return poppedElement;
    }

    @Override
    public void push(E elem) {
        if (isFull()) {
            return;
        }
        stackArray[size++] = elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(stackArray[i]);
            sb.append(", ");
        }
        sb.append(stackArray[size - 1]);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> values() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && stackArray[currentIndex] != null;
            }

            @Override
            public E next() {
                return stackArray[currentIndex++];
            }
        };
    }
}
