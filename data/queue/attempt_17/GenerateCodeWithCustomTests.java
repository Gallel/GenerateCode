
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E>{
    
    private E[] elements;
    private int front;
    private int rear;
    private int size;
    private int max;

    public QueueArrayImpl(){
        this.max = FiniteContainer.DEFAULT_MAX_SIZE;
        this.elements = (E[]) new Object[this.max];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public QueueArrayImpl(int max){
        this.max = max;
        this.elements = (E[]) new Object[this.max];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @Override
    public void add(E elem){
        if(size == max){
            throw new FullContainerException();
        }
        elements[rear++] = elem;
        rear %= max;
        size++;
    }

    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public boolean isFull(){
        return size() == max;
    }

    @Override
    public E peek(){
        if(isEmpty()){
            throw new EmptyContainerException();
        }
        return this.elements[front];
    }

    @Override
    public E poll(){
        if(isEmpty()){
            throw new EmptyContainerException();
        }
        E elem = this.elements[front];
        this.elements[front++] = null;
        front %= max;
        size--;
        return elem;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for(int i=0; i<size; i++){
            sb.append(elements[(front+i)%max]);
            if(i != size-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> values(){
        return new Iterator<E>(){
            private int index;
            @Override public boolean hasNext() { return index < size; }
            @Override public E next() { return elements[(front+(index++))%max]; }
        };
    }
}

