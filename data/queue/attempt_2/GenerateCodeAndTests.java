
public class QueueArrayImpl<E> implements Queue<E>, FiniteContainer<E> {
    private E[] queue;
    private int head;
    private int tail;
    private int size;
    private int capacity;
    
    public QueueArrayImpl() {
        this(1000);
    }
    
    public QueueArrayImpl(int max) {
        capacity = max;
        queue = (E[]) new Object[capacity];
        head = 0;
        tail = -1;
        size = 0;
    }
    
    public void add(E elem) {
        if(size == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        tail = (tail + 1) % capacity;
        queue[tail] = elem;
        size++;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return queue[head];
    }
    
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        E elem = queue[head];
        queue[head] = null;
        head = (head + 1) % capacity;
        size--;
        return elem;
    }
    
    public int size() {
        return size;
    }
    
    @Override
    public String toString() {
        if(isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int index = head;
        for(int i = 0; i < size-1; i++) {
            sb.append(queue[index]).append(", ");
            index = (index + 1) % capacity;
        }
        sb.append(queue[tail]).append("]");
        return sb.toString();
    }
    
    public Iterator<E> values() {
        return new Iterator<E>() {
            private int current = head;
            private int count = 0;
            
            public boolean hasNext() {
                return count < size;
            }
            
            public E next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                E elem = queue[current];
                current = (current + 1) % capacity;
                count++;
                return elem;
            }
        };
    }
}
