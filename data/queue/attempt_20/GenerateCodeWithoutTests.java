
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private E[] elements;
    private int capacity;
    private int size;
    private int front;
    private int rear;

    public QueueArrayImpl() {
        this(100);
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.capacity = max;
        this.elements = (E[]) new Object[max];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    public void add(E elem) {
        rear = (rear + 1) % capacity;
        elements[rear] = elem;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[front];
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E dequeueItem = elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        size--;
        return dequeueItem;
    }
    
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front; i != rear; i = (i + 1) % capacity) {
            sb.append(elements[i]).append(", ");
        }
        sb.append(elements[rear]).append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> values() {
        return Arrays.stream(elements).iterator();
    }
}
