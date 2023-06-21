
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private int capacity;
    private Object[] elements;
    private int front;
    private int rear;
    private int size;

    public QueueArrayImpl(){
        this(1000);
    }

    public QueueArrayImpl(int max){
        capacity = max;
        elements = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void add(E elem) throws FullContainerException{
        if(isFull()){
            throw new FullContainerException();
        }
        rear = (++rear) % capacity;
        elements[rear] = elem;
        ++size;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public boolean isFull(){
        return size()==capacity;
    }

    public E peek() throws EmptyContainerException{
        if(isEmpty()){
            throw new EmptyContainerException();
        }
        return (E)elements[front];
    }

    public E poll() throws EmptyContainerException{
        if(isEmpty()){
            throw new EmptyContainerException();
        }
        E result = (E)elements[front];
        front = (++front) % capacity;
        --size;
        return result;
    }

    public int size(){
        return size;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(int i=0; i<size; ++i){
            sb.append(elements[(front+i)%capacity]);
            if(i<size-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values(){
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E>{
        private int count;
        private int current;

        public QueueIterator(){
            count = size;
            current = front;
        }

        public boolean hasNext(){
            return count>0;
        }

        public E next(){
            E result = (E)elements[current];
            current = (++current)%capacity;
            --count;
            return result;
        }
    }
}
