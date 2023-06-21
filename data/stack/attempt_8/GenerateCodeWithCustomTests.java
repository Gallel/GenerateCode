
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{

    private int capacity;
    private E[] elements;
    private int size;

    StackArrayImpl() {
        this.capacity = 10;
        this.elements = (E[]) new Object[capacity];
        size = 0;
    }

    StackArrayImpl(int max) {
        this.capacity = max;
        this.elements = (E[]) new Object[capacity];
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[size - 1];
    }

    E pop() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elementRemoved = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return elementRemoved;
    }

    void push(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        elements[size] = elem;
        size++;
    }

    int size() {
        return size;
    }

    public Iterator<E> values(){
        List<E> list = new ArrayList<E>();
        for (int i = 0; i < size; i++){
          list.add(elements[i]);
        }
        return list.iterator();
    }

    public String toString(){
        String result = "";
        for (int i = size-1; i >= 0; i--){
          result += elements[i].toString() + "\n";
        }
        return result;
    }

}
