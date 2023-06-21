
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
    private E[] stackArray;
    private int top;
    private int max;

    @SuppressWarnings("unchecked")
    public StackArrayImpl(){
        this.max = 100;
        this.top = -1;
        stackArray = (E[]) new Object[max];
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max){
        this.max = max;
        this.top = -1;
        stackArray = (E[]) new Object[max];
    }

    public boolean isEmpty(){
        return (top==-1);
    }

    public boolean isFull(){
        return (top==max-1);
    }

    public E peek(){
        if(!isEmpty()) {
            return stackArray[top];
        }else{
            return null;
        }
    }

    public E pop(){
        if(!isEmpty()){
            E item = stackArray[top];
            top--;
            return item;
        }else{
            return null;
        }
    }

    public void push(E elem){
        if(!isFull()){
            top++;
            stackArray[top] = elem;
        }
    }

    public int size(){
        return top+1;
    }

    public String toString(){
        String stack = "";
        for(int i=top; i>=0; i--){
            stack+= stackArray[i].toString() + "\n";
        }
        return stack;
    }

    public Iterator<E> values(){
        ArrayList<E> list = new ArrayList<>();
        for(int i=top; i>=0; i--){
            list.add(stackArray[i]);
        }
        return list.iterator();
    }
}
