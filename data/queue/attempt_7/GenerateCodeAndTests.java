
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private int size;
    private int front;
    private int rear;
    private E[] queue;

    public QueueArrayImpl() {
        this(10);
    }

    public QueueArrayImpl(int max) {
        queue = (E[]) new Object[max];
        size = front = rear = 0;
    }

    public void add(E elem) {
        if (isFull()) throw new RuntimeException("Queue is full");
        queue[rear++] = elem;
        size++;
        rear %= queue.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public E peek() {
        if (isEmpty()) return null;
        return queue[front];
    }

    public E poll() {
        if (isEmpty()) return null;
        E elem = queue[front++];
        size--;
        front %= queue.length;
        return elem;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front, count = 0; count < size; i++, count++) {
            sb.append(queue[i % queue.length]);
            if (count < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int count = size;
            private int index = front;

            public boolean hasNext() {
                return count > 0;
            }

            public E next() {
                E elem = queue[index++];
                count--;
                index %= queue.length;
                return elem;
            }
        };
    }
}
