
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {

    //attributes
    private int maxCapacity;
    private int size;
    private E[] elements;

    //constructors
    public QueueArrayImpl() {
        this.maxCapacity = 100;
        this.size = 0;
        this.elements = (E[]) new Object[this.maxCapacity];
    }

    public QueueArrayImpl(int max) {
        this.maxCapacity = max;
        this.size = 0;
        this.elements = (E[]) new Object[this.maxCapacity];
    }

    //methods
    public void add(E elem) {
        if (this.size < this.maxCapacity) {
            this.elements[size++] = elem;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.maxCapacity;
    }

    public E peek() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.elements[0];
        }
    }

    public E poll() {
        if (this.isEmpty()) {
            return null;
        } else {
            E result = this.elements[0];
            for (int i = 0; i < this.size - 1; i++) {
                this.elements[i] = this.elements[i + 1];
            }
            this.elements[--size] = null;
            return result;
        }
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        String result = "[";
        for (int i = 0; i < this.size; i++) {
            result += this.elements[i].toString();
            if (i < this.size - 1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

    public Iterator<E> values() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            list.add(this.elements[i]);
        }
        return list.iterator();
    }
}
