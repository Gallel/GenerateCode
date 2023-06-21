
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
    private E[] value;
    private int numElems;
    private int capacity;

    @SuppressWarnings("unchecked")
    public StackArrayImpl(){
        this.capacity = 1000;
        this.value =  (E[]) new Object[capacity];
        this.numElems=0;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max){
        this.capacity = max;
        this.value = (E[]) new Object[capacity];
        this.numElems=0;
    }

    public boolean isEmpty(){
        if(numElems == 0){
            return true;
        }
        return false;
    }

    public boolean isFull(){
        if(numElems == capacity){
            return true;
        }
        return false;
    }

    public E peek(){
        if(numElems == 0){
            return null;
        }
        return value[numElems-1];
    }

    public E pop(){
        if(numElems == 0){
            return null;
        }
        E removedElem = this.value[numElems-1];
        this.value[numElems-1] = null;
        numElems--;
        return removedElem;
    }

    public void push(E elem){
        if(numElems < capacity){
            value[numElems] = elem;
            numElems++;
        }
    }

    public int size(){
        return numElems;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < numElems; i++) {
            result.append(value[i]);
            if(i!=numElems -1){
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }

    public Iterator<E> values(){
        Iterator<E> iter = new Iterator<E>() {
            private int currentIndex = 0;

            @Override
                public boolean hasNext() {
                return currentIndex < numElems && value[currentIndex] != null;
            }

            @Override
            public E next() {
                return value[currentIndex++];
            }
        };
        return iter;
    }
}
