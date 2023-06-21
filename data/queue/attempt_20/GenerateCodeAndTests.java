
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E>{
    private E[] queueArray;
    private int front, rear, capacity, count;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        capacity = 10;
        queueArray = (E[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        capacity = max;
        queueArray = (E[]) new Object[capacity];
    }

    public void add(E elem) {
        if (count == capacity) throw new IllegalStateException();
        queueArray[rear] = elem;
        rear = (rear + 1) % capacity;
        count++;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public E peek() {
        if (count == 0) return null;
        return queueArray[front];
    }

    public E poll() {
        if (count == 0) return null;
        E elem = queueArray[front];
        queueArray[front] = null;
        front = (front + 1) % capacity;
        count--;
        return elem;
    }

    public int size() {
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int index = front;
        for (int i = 0; i < count; i++) {
            sb.append(queueArray[index]);
            if (i != count - 1) sb.append(", ");
            index = (index + 1) % capacity;
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int index = front;
            private int cnt = 0;

            public boolean hasNext() {
                return cnt != count;
            }

            public E next() {
                E elem = queueArray[index];
                index = (index + 1) % capacity;
                cnt++;
                return elem;
            }
        };
    }
}
