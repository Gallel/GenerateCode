
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] array;
    private int top;
    private int max;

    public StackArrayImpl() {
        this(100);
    }

    public StackArrayImpl(int max) {
        this.array = (E[]) new Object[max];
        this.top = -1;
        this.max = max;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == max-1;
    }

    public E peek() {
        if(!isEmpty()) {
            return array[top];
        }
        return null;
    }

    public E pop() {
        if(!isEmpty()) {
            E elem = array[top];
            array[top] = null;
            top--;
            return elem;
        }
        return null;
    }

    public void push(E elem) {
        if(!isFull()) {
            top++;
            array[top] = elem;
        }
    }

    public int size() {
        return top+1;
    }

    public String toString() {
        String str = "";
        for(int i=0; i<=top; i++) {
            str += array[i].toString() + "\n";
        }
        return str;
    }

    public Iterator<E> values() {
        ArrayList<E> list = new ArrayList<E>();
        for(int i=0; i<=top; i++) {
            list.add(array[i]);
        }
        return list.iterator();
    }
}
