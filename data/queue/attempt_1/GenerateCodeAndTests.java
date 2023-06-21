
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private int front, rear, size, capacity;
    private E[] elements;
    
    public QueueArrayImpl() {
        this.capacity = 10;
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.elements = (E[]) new Object[this.capacity];
    }
    
    public QueueArrayImpl(int max) {
        this.capacity = max;
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.elements = (E[]) new Object[this.capacity];
    }
    
    public void add(E elem) {
        if (isFull()) {
            throw new IllegalStateException("Queue is already full");
        }
        rear = (rear + 1) % capacity;
        elements[rear] = elem;
        size++;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return (size == capacity);
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[front];
    }
    
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E elem = elements[front];
        front = (front + 1) % capacity;
        size--;
        return elem;
    }
    
    public int size() {
        return size;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[(front + i) % capacity]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Iterator<E> values() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<E> {
        private int current = 0;
        
        public boolean hasNext() {
            return current < size;
        }
        
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            E elem = elements[(front + current) % capacity];
            current++;
            return elem;
        }
    }
}
