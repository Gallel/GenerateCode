
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {
	private int capacity; // maximum capacity of the container
	private E[] elements; // array that stores the elements
	private int size; // current number of elements in the container

	// Constructor without parameters (maximum capacity, default)
	public StackArrayImpl() {
		this.capacity = 10; // default value of maximum capacity
		this.elements = (E[]) new Object[this.capacity]; // create the array
		this.size = 0; // no elements yet
	}

	// Constructor with a parameter
	public StackArrayImpl(int max) {
		this.capacity = max; // set the maximum capacity
		this.elements = (E[]) new Object[this.capacity]; // create the array
		this.size = 0; // no elements yet
	}

	// Method to check if the container is empty
	public boolean isEmpty() {
		return (this.size == 0);
	}

	// Method to check if the container is full
	public boolean isFull() {
		return (this.size == this.capacity);
	}

	// Getter for the last item added to the stack, if any
	public E peek() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.elements[this.size-1];
		}
	}

	// Delete the top of the stack item, if any
	public E pop() {
		if (this.isEmpty()) {
			return null;
		} else {
			E elem = this.elements[this.size-1];
			this.elements[this.size-1] = null;
			this.size--;
			return elem;
		}
	}

	// Add an item to the stack, if any
	public void push(E elem) {
		if (!this.isFull()) {
			this.elements[this.size] = elem;
			this.size++;
		}
	}

	// Getter for the number of items in the container
	public int size() {
		return this.size;
	}

	// Method overwriting Object.toString()
	public String toString() {
		String s = "";
		for (int i = 0; i < this.size; i++) {
			s += this.elements[i].toString() + ", ";
		}
		if (s.contains(",")) // remove last separator
			s = s.substring(0, s.lastIndexOf(","));
		return "[" + s + "]";
	}

	// Retrieves the items in the container
	public Iterator<E> values() {
		return new Iterator<E>() {
			private int current = 0;

			public boolean hasNext() {
				return (current < size);
			}

			public E next() {
				return elements[current++];
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
