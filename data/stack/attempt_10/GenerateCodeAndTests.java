
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private int capacity;
    private E[] stackArray;
    private int topIndex;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        this.capacity = 10;
        this.stackArray = (E[]) new Object[capacity];
        this.topIndex = -1;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.capacity = max;
        this.stackArray = (E[]) new Object[capacity];
        this.topIndex = -1;
    }
    
    public boolean isEmpty() {
        return (topIndex == -1);
    }

    public boolean isFull() {
        return (topIndex == capacity - 1);
    }
    
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[topIndex];
    }
    
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E elem = stackArray[topIndex];
        topIndex--;
        return elem;
    }

    public void push(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        topIndex++;
        stackArray[topIndex] = elem;
    }
    
    public int size() {
        return topIndex + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iter = values();
        while (iter.hasNext()) {
            sb.append(iter.next().toString());
            if (iter.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Iterator<E> values() {
        List<E> elements = new ArrayList<>();
        for (int i = 0; i <= topIndex; i++) {
            elements.add(stackArray[i]);
        }
        return elements.iterator();
    }
}
