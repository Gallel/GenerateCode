
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] container;
    private int top;
    private int max;

    @SuppressWarnings("unchecked")
    public StackArrayImpl() {
        this.max = 100;
        this.container = (E[]) new Object[this.max];
        this.top = -1;
    }

    @SuppressWarnings("unchecked")
    public StackArrayImpl(int max) {
        this.max = max;
        this.container = (E[]) new Object[this.max];
        this.top = -1;
    }

    @Override
    public boolean isEmpty() {
        return (this.top == -1);
    }

    @Override
    public boolean isFull() {
        return (this.top == (this.max - 1));
    }

    @Override
    public E peek() {
        if (this.isEmpty())
            return null;

        return this.container[this.top];
    }

    @Override
    public E pop() {
        if (this.isEmpty())
            return null;

        E result = this.container[this.top];
        this.container[this.top--] = null;

        return result;
    }

    @Override
    public void push(E elem) {
        if (this.isFull())
            return;

        this.container[++this.top] = elem;
    }

    @Override
    public int size() {
        return (this.top + 1);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("[ ");
        for (int i = this.top; i >= 0; i--) {
            result.append(this.container[i].toString());
            result.append(" ");
        }
        result.append("]");

        return result.toString();
    }

    @Override
    public Iterator<E> values() {

        return new Iterator<E>() {
            private int current = top;

            @Override
            public boolean hasNext() {
                return (current >= 0);
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                return container[current--];
            }
        };
    }
}
