
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
    private E[] elements;
    private int top;
    private int max;
    
    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        max = DEFAULT_SIZE; 
        elements = (E[]) new Object[max];
        top = -1;
    }
    
    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.max = max;
        elements = (E[]) new Object[max];
        top = -1;
    }
    
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == max-1;
    }
    
    public E peek() {
        if(isEmpty())
            return null;
        return elements[top];
    }

    public E pop() {
        if(isEmpty())
            return null;
        E elem = elements[top];
        elements[top--] = null;
        return elem;
    }

    public void push(E elem) {
        if(isFull())
            return;
        elements[++top] = elem;
    }

    public int size() {
        return top+1;
    }

    @Override
    public Iterator<E> values() {
        return new Iterator<E>(){
            int pos=top;
            @Override
            public boolean hasNext() {
                return pos>-1;
            }
            @Override
            public E next() {
                return elements[pos--];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=top; i>=0; i--) {
            sb.append(elements[i]);
            if(i != 0)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
