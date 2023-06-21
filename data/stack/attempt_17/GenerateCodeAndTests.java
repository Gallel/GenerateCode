
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private int maxCapacity;
    private int size;
    private E[] elements;

    public StackArrayImpl() {
        this.maxCapacity = 100;
        this.size = 0;
        this.elements = (E[]) new Object[this.maxCapacity];
    }

    public StackArrayImpl(int max) {
        this.maxCapacity = max;
        this.size = 0;
        this.elements = (E[]) new Object[this.maxCapacity];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.maxCapacity;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return this.elements[this.size - 1];
    }

    public E pop() {
        E elem = peek();
        this.elements[this.size - 1] = null;
        this.size--;
        return elem;
    }

    public void push(E elem) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }
        this.elements[this.size] = elem;
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.elements[i].toString());
            if (i != this.size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            public boolean hasNext() {
                return this.currentIndex < size;
            }

            public E next() {
                E elem = elements[this.currentIndex];
                this.currentIndex++;
                return elem;
            }
        };
    }
}
