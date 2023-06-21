
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] container;
    private int max;
    private int top;
    
    public StackArrayImpl() {
        this(100);
    }
    
    public StackArrayImpl(int max) {
        this.max = max;
        container = (E[]) new Object[max];
        top = -1;
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
        }
        return container[top];
    }
    
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E value = container[top];
        container[top--] = null;
        return value;
    }
    
    public void push(E elem) {
        if (isFull()) {
            return;
        }
        container[++top] = elem;
    }
    
    public int size() {
        return top + 1;
    }
    
    public Iterator<E> values() {
        List<E> elements = new ArrayList<>();
        for (int i = top; i >= 0; i--) {
            elements.add(container[i]);
        }
        return elements.iterator();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = top; i >= 0; i--) {
            sb.append(container[i]);
            if (i > 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
