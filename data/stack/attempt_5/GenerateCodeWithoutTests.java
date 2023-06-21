
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    private int maxCapacity;
    private Object[] stackArray;
    private int currentSize;

    public StackArrayImpl(){
        this.maxCapacity = 10; //default capacity value
        this.stackArray = new Object[maxCapacity];
        this.currentSize = 0;
    }

    public StackArrayImpl(int max){
        this.maxCapacity = max;
        this.stackArray = new Object[maxCapacity];
        this.currentSize = 0;
    }

    public boolean isEmpty(){
        return (currentSize == 0);
    }

    public boolean isFull(){
        return (currentSize == maxCapacity);
    }

    public E peek(){
        if(currentSize == 0){
            return null;
        } else {
            return (E) stackArray[currentSize - 1];
        }
    }

    public E pop(){
        if(currentSize == 0){
            return null;
        } else {
            E result = (E) stackArray[currentSize - 1];
            stackArray[currentSize - 1] = null;
            currentSize--;
            return result;
        }
    }

    public void push(E elem){
        if(currentSize < maxCapacity){
            stackArray[currentSize++] = elem;
        }
    }

    public int size(){
        return currentSize;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < currentSize; i++){
            sb.append(stackArray[i].toString());
            if(i != currentSize - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values(){
        List<E> list = new ArrayList<>();
        for(int i = 0; i < currentSize; i++){
            list.add((E) stackArray[i]);
        }
        return list.iterator();
    }
}
