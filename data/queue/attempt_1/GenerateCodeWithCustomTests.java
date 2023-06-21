
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    private int max;
    private E[] queue;
    private int first;
    private int last;
    private int size;

    public QueueArrayImpl() {
        this.max = 1000;
        this.queue = (E[]) new Object[max];
        this.first = 0;
        this.last = 0;
        this.size = 0;
    }

    public QueueArrayImpl(int max) {
        this.max = max;
        this.queue = (E[]) new Object[max];
        this.first = 0;
        this.last = 0;
        this.size = 0;
    }

    public void add(E elem) {
        if (isFull()) {
            throw new FullContainerException();
        }
        queue[last++] = elem;
        size++;
        if (last == max) {
            last = 0;
        }
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    public boolean isFull() {
        if (size == max) return true;
        else return false;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return queue[first];
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        E item = queue[first];
        queue[first++] = null;
        size--;
        if (first == max) {
            first = 0;
        }
        return item;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String s = "[";
        if (size > 0) {
            s += queue[0];
        }
        for (int i = 1; i < size; i++) {
            s += ", " + queue[i];
        }
        return s + "]";
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int i = first;
            private int cnt = 0;

            public boolean hasNext() {
                return cnt < size;
            }

            public E next() {
                E item = queue[i];
                i++;
                cnt++;
                if (i == max) {
                    i = 0;
                }
                return item;
            }
        };
    }
}
