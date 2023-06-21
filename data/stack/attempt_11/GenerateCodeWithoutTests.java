
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
    private E[] elements;
    private int top;

    public StackArrayImpl() {
        this(10);
    }

    public StackArrayImpl(int max) {
        elements = (E[]) new Object[max];
        top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == elements.length - 1);
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[top];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return elements[top--];
    }

    public void push(E elem) {
        if (!isFull()) {
            elements[++top] = elem;
        }
    }

    public int size() {
        return top + 1;
    }

    public String toString() {
        String result = "[";
        for (int i = 0; i <= top; i++) {
            result += elements[i];
            if (i < top) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

    public Iterator<E> values() {
        List<E> list = new ArrayList<E>();
        for (int i = 0; i <= top; i++) {
            list.add(elements[i]);
        }
        return list.iterator();
    }
}
