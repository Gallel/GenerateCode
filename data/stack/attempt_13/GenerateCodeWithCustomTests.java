
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private int max;
    private int top;
    private E[] array;

    public StackArrayImpl() {
        this.max = 1000;
        this.top = -1;
        this.array = (E[]) new Object[this.max];
    }

    public StackArrayImpl(int max) {
        this.max = max;
        this.top = -1;
        this.array = (E[]) new Object[this.max];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.max - 1;
    }

    public E peek() {
        if (this.top == -1) {
            return null;
        } else {
            return this.array[this.top];
        }
    }

    public E pop() {
        E elem = null;
        if (this.top != -1) {
            elem = this.array[this.top];
            this.array[this.top] = null;
            this.top--;
        }
        return elem;
    }

    public void push(E elem) {
        if (this.top < this.max - 1) {
            this.top++;
            this.array[this.top] = elem;
        }
    }

    public int size() {
        return this.top + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = this.top; i >= 0; i--) {
            sb.append(this.array[i]);
            if (i != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
