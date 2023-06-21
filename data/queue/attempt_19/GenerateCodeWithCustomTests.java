
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
    private E[] elements;
    private int capacity;
    private int front;
    private int rear;
    private int size;

    public QueueArrayImpl() {
        this(1000);
    }

    public QueueArrayImpl(int max) {
        capacity = max;
        elements = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void add(E elem) {
        if (size == capacity) {
            throw new FullContainerException();
        }
        rear = (rear + 1) % capacity;
        elements[rear] = elem;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return elements[front];
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E elem = elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        size--;
        return elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String result = "[";
        int i = front;
        while (i != rear) {
            result += elements[i] + ", ";
            i = (i + 1) % capacity;
        }
        result += elements[rear] + "]";
        return result;
    }

    @Override
    public Iterator<E> values() {
        return new Iterator<E>() {
            private int index = front;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E elem = elements[index];
                index = (index + 1) % capacity;
                count++;
                return elem;
            }
        };
    }
}
