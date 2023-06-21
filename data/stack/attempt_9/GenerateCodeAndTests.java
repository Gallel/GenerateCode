
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private int max;
    private int size;
    private E[] stack;
    
    public StackArrayImpl() {
        this.max = 100;
        this.size = 0;
        this.stack = (E[]) new Object[max];
    }
    
    public StackArrayImpl(int max) {
        this.max = max;
        this.size = 0;
        this.stack = (E[]) new Object[max];
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
        return stack[size-1];
    }
    
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        size--;
        E elem = stack[size];
        stack[size] = null;
        return elem;
    }
    
    public void push(E elem) {
        if (isFull()) {
            return;
        }
        stack[size] = elem;
        size++;
    }
    
    public int size() {
        return size;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            str.append(stack[i].toString());
            if (i != size-1) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }
    
    public Iterator<E> values() {
        List<E> values = new ArrayList<E>();
        for (int i = 0; i < size; i++) {
            values.add(stack[i]);
        }
        return values.iterator();
    }
}
