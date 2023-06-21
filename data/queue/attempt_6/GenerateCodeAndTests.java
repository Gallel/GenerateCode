
public class QueueArrayImpl<E> implements Queue<E>, FiniteContainer<E> {
    private int max;
    private E[] queue;

    public QueueArrayImpl() {
        this.max = 100;
        this.queue = (E[]) new Object[max];
    }

    public QueueArrayImpl(int max) {
        this.max = max;
        this.queue = (E[]) new Object[max];
    }

    public void add(E elem) {
        if(isFull()) {
            throw new IllegalStateException();
        } else {
            int pos = size();
            queue[pos] = elem;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == max;
    }

    public E peek() {
        if(isEmpty()) {
            return null;
        } else {
            return queue[0];
        }
    }

    public E poll() {
        if(isEmpty()) {
            return null;
        } else {
            E elem = queue[0];
            for(int i = 0; i < size()-1; i++) {
                queue[i] = queue[i+1];
            }
            queue[size()-1] = null;
            return elem;
        }
    }

    public int size() {
        int count = 0;
        for(E elem : queue) {
            if(elem != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < size(); i++) {
            sb.append(queue[i].toString());
            if(i < size()-1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size();
            }

            @Override
            public E next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    E elem = queue[pos];
                    pos++;
                    return elem;
                }
            }
        };
    }
}
