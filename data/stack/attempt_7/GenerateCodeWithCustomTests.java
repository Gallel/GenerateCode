
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
	private E[] stackArray;
	private int top=-1;
	private int maxSize;
	
	//Constructor without parameters (maximum capacity, default).
	public StackArrayImpl() {
		this(1000);
	}
	
	//Constructor with a parameter.
	public StackArrayImpl(int max) {
		maxSize = max;
		stackArray = (E[]) new Object[maxSize];
	}
	
	//Method to check if the container is empty.
	public boolean isEmpty() {
		return (top == -1);
	}

	//Method to check if the container is full.
	public boolean isFull() {
		return (top == maxSize - 1);
	}

	//Getter for the last item added to the stack, if any.
	public E peek() {
		if (isEmpty())
			return null;
		return stackArray[top];
	}
	
	//Delete the top of the stack item, if any.
	public E pop() {
		if (isEmpty())
			return null;
		E temp = stackArray[top];
		stackArray[top--] = null;
		return temp;
	}

	//Add an item to the stack, if any.
	public void push(E elem) {
		if (isFull())
			return;
		stackArray[++top] = elem;
	}
	
	//Getter for the number of items in the container.
	public int size() {
		return top+1;
	}

	//Method overwriting Object.toString ().
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i <= top; i++) {
			sb.append(stackArray[i]);
			if (i < top) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	//Retrieves the items in the container.
	public Iterator<E> values() {
		ArrayList<E> list = new ArrayList<>();
		for (int i = 0; i <= top; i++) {
			list.add(stackArray[i]);
		}
		return list.iterator();
	}
}
