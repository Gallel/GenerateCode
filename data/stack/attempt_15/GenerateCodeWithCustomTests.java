
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] elements;
    private int max;
    private int topIndex;
    
    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        max = FULL_SIZE;
        elements = (E[]) new Object[max];
        topIndex = -1;
    }
    
    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.max = max;
        elements = (E[]) new Object[max];
        topIndex = -1;
    }
    
    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }
    
    @Override
    public boolean isFull() {
        return topIndex == max - 1;
    }
    
    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        else{
            return elements[topIndex];
        }
    }
    
    @Override
    public E pop() {
        if(isEmpty()){
            return null;
        }
        else{
            E element = elements[topIndex];
            elements[topIndex] = null;
            topIndex--;
            return element;
        }
    }
    
    @Override
    public void push(E elem) {
        if(isFull()){
            throw new FullContainerException();
        }
        else{
            topIndex++;
            elements[topIndex] = elem;
        }
    }
    
    @Override
    public int size() {
        return topIndex + 1;
    }
    
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for(int index = topIndex; index >= 0; index--){
            buffer.append(elements[index].toString());
            if(index > 0){
                buffer.append(", ");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
    
    @Override
    public Iterator<E> values() {
        List<E> values = new ArrayList<>();
        for(int index = topIndex; index >= 0; index--){
            values.add(elements[index]);
        }
        return values.iterator();
    }
}
