
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private E[] elements;
    private int front;
    private int rear;
    private int capacity;

    @SuppressWarnings("unchecked")
    public QueueArrayImpl() {
        this.capacity = MAX_SIZE;
        this.front = 0;
        this.rear = -1;
        this.elements = (E[]) new Object[this.capacity];
    }

    @SuppressWarnings("unchecked")
    public QueueArrayImpl(int max) {
        this.capacity = max;
        this.front = 0;
        this.rear = -1;
        this.elements = (E[]) new Object[this.capacity];
    }

    @Override
    public void add(E elem) {
        if (this.isFull())
            throw new FullContainerException();
        this.rear++;
        this.elements[rear] = elem;
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public boolean isFull() {
        return (this.size() == this.capacity);
    }

    @Override
    public E peek() {
        if (this.isEmpty())
            return null;
        return this.elements[front];
    }

    @Override
    public E poll() {
        if (this.isEmpty())
            throw new EmptyContainerException();
        E res = this.elements[front];
        this.front++;
        return res;
    }

    @Override
    public int size() {
        return (this.rear - this.front + 1);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = this.front; i <= this.rear; i++) {
            res += "" + this.elements[i];
            if (i < this.rear)
                res += ",";
        }
        return "[" + res + "]";
    }

    @Override
    public Iterator<E> values() {
        return new Iterator<E>() {
            private int currentIndex = front;

            @Override
            public boolean hasNext() {
                return currentIndex <= rear;
            }

            @Override
            public E next() {
                return elements[currentIndex++];
            }
        };
    }
}
