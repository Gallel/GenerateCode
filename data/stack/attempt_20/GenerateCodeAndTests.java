
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
    private E[] elements;
    private int capacity, top;

    // Constructor without parameters (maximum capacity, default).
    StackArrayImpl(){
        this.capacity = 1000;
        this.elements = (E[]) new Object[capacity];
        this.top = -1;
    }

    // Constructor with a parameter.
    StackArrayImpl(int max){
        this.capacity = max;
        this.elements = (E[]) new Object[capacity];
        this.top = -1;
    }

    // Method to check if the container is empty.
    public boolean isEmpty(){
        return top == -1;
    }

    // Method to check if the container is full.
    public boolean isFull(){
        return top == capacity-1 ;
    }

    // Getter for the last item added to the stack, if any.
    public E peek(){
        if(top == -1) throw new EmptyStackException();
        return elements[top];
    }

    // Delete the top of the stack item, if any.
    public E pop(){
        if(top == -1) throw new EmptyStackException();
        return elements[top--];
    }

    // Add an item to the stack, if any.
    public void push(E elem){
        if(top == capacity-1) throw new StackOverflowError();
        elements[++top] = elem;
    }

    // Getter for the number of items in the container.
    public int size(){
        return top+1;
    }
  
    // Method overwriting Object.toString ().
    public String toString(){
        String res = "[";
        for(int i=0; i<=top; i++){
            res += elements[i];
            if(i<top) res += ",";
        }
        res += "]";
        return res;
    }

    // Retrieves the items in the container.
    public Iterator<E> values(){
        List<E> list = new ArrayList<>();
        for(int i=top; i>=0; i--){
            list.add(elements[i]);
        }
        return list.iterator();
    }
}
