
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private E[] elements;
    private int size;
    private int max;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        this.max = FiniteContainer.DEFAULT_MAX_SIZE;
        elements = (E[]) new Object[max];
        size = 0;
    }
    
    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.max = max;
        elements = (E[]) new Object[max];
        size = 0;
    }
    
    @Override
    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        elements[size] = elem;
        size++;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean isFull() {
        return size == max;
    }
    
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return elements[0];
    }
    
    @Override
    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E result = elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return result;
    }
    
    @Override
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
    
    @Override
    public Iterator<E> values() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(elements[i]);
        }
        return list.iterator();
    }
}
