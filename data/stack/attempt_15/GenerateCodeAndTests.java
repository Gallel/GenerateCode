
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private int capacity;
    private int size;
    private E[] elements;

    public StackArrayImpl() {
        this.capacity = 10;
        this.size = 0;
        this.elements = (E[]) new Object[capacity];
    }

    public StackArrayImpl(int max) {
        this.capacity = max;
        this.size = 0;
        this.elements = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public E peek() {
        return elements[size - 1];
    }

    public E pop() {
        E element = elements[--size];
        elements[size] = null;
        return element;
    }

    public void push(E elem) {
        elements[size] = elem;
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return Arrays.asList(Arrays.copyOf(elements, size)).iterator();
    }
}
