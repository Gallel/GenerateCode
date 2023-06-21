
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        array = (E[]) new Object[max];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[size - 1];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E elem = array[size - 1];
        array[--size] = null;
        return elem;
    }

    public void push(E elem) {
        if (isFull()) {
            return;
        }
        array[size++] = elem;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> values() {
        List<E> items = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            items.add(array[i]);
        }
        return items.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> it = values();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
