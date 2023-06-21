
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
    private int MAX_SIZE;
    private E[] elements;
    private int top;
    
    public StackArrayImpl(){
        MAX_SIZE = 1000;
        elements = (E[]) new Object[MAX_SIZE];
        top = -1;
    }
    
    public StackArrayImpl(int max){
        MAX_SIZE = max;
        elements = (E[]) new Object[MAX_SIZE];
        top = -1;
    }
    
    public boolean isEmpty(){
        return top == -1;
    }
    
    public boolean isFull(){
        return top == MAX_SIZE - 1;
    }
    
    public E peek(){
        if(isEmpty()) {
            return null;
        }
        return elements[top];
    } 

    public E pop(){
        if(isEmpty()) {
            return null;
        }
        E elem = elements[top];
        elements[top] = null;
        top--;
        return elem;
    }
    
    public void push(E elem){
        if(!isFull()){
            top++;
            elements[top] = elem;
        }
    }
    
    public int size(){
        return top + 1;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i <= top; i++){
            sb.append(elements[i]);      
            if(i != top){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Iterator<E> values(){
        List<E> list = new ArrayList<E>();
        for (int i = 0; i <= top; i++){
            list.add(elements[i]);
        }
        return list.iterator();
    }
}
