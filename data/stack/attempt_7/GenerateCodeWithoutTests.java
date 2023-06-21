
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
    private E[] stackArray; 
    private int top;  
    private int maxSize; 
    
    public StackArrayImpl(){
        this.maxSize = 100; 
        this.stackArray = (E[]) new Object[100]; 
        this.top = -1; 
    }
    
    public StackArrayImpl(int max){ 
        this.maxSize = max; 
        this.stackArray = (E[]) new Object[max]; 
        this.top = -1; 
    }
    
    public boolean isEmpty(){
        return (top == -1);
    }
    
    public boolean isFull(){
        return (top == maxSize-1);
    }
    
    public E peek(){
        if (isEmpty()) 
            throw new EmptyStackException(); 
        return stackArray[top];
    }
    
    public E pop(){
        if (isEmpty()) 
            throw new EmptyStackException(); 
        return stackArray[top--];
    }
    
    public void push(E elem){
        if (isFull()) 
            throw new IllegalStateException("Stack is full."); 
        stackArray[++top] = elem;
    }
    
    public int size(){
        return top+1;
    }
    
    public String toString(){
        String s = "[";
        for (int i=0;i<=top;i++) 
            s += stackArray[i]+",";
        if (top >=0) 
            s = s.substring(0,s.length()-1); 
        s += "]";
        return s;
    }
    
    public Iterator<E> values(){
        List<E> list = new ArrayList<E>();
        for (E e : stackArray) {
            if (e == null) {
                break;
            }
            list.add(e);
        }
        return list.iterator();
    }
}
