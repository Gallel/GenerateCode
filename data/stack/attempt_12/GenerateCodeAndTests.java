
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] array;
    private int top;
  
    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        this.array = (E[]) new Object[10];
        this.top = -1;
    }
  
    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.array = (E[]) new Object[max];
        this.top = -1;
    }
  
    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }
  
    @Override
    public boolean isFull() {
        return this.top == this.array.length - 1;
    }
  
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
    
        return this.array[this.top];
    }
  
    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
    
        E elem = this.array[this.top];
        this.array[this.top] = null;
        this.top--;
        return elem;
    }
  
    @Override
    public void push(E elem) {
        if (isFull()) {
            throw new RuntimeException("overflow");
        }
    
        this.top++;
        this.array[this.top] = elem;
    }
  
    @Override
    public int size() {
        return this.top + 1;
    }
  
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
    
        StringBuilder sb = new StringBuilder();
        sb.append("[");
    
        for (int i = 0; i <= this.top; i++) {
            sb.append(this.array[i]);
            if (i != this.top) {
                sb.append(", ");
            }
        }
    
        sb.append("]");
        return sb.toString();
    }
  
    @Override
    public Iterator<E> values() {
        List<E> list = new ArrayList<>();
    
        for (int i = 0; i <= this.top; i++) {
            list.add(this.array[i]);
        }
    
        return list.iterator();
    }
}
