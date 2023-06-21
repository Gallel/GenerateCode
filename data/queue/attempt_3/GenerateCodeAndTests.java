
public class QueueArrayImpl<E> implements Queue<E>, FiniteContainer<E>{
	
	private int max;
	private int size;
	private E[] container;
	
	public QueueArrayImpl() {
		this.max = 1000; //default maximum capacity
		this.size = 0;
		container = (E[]) new Object[max];
	}
	
	public QueueArrayImpl(int max) {
		this.max = max;
		this.size = 0;
		container = (E[]) new Object[max];
	}
	
	public void add(E elem) {
		if(isFull()) {
			throw new IllegalStateException("Cannot add element: container is full");
		}
		container[size++] = elem;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == max;
	}
	
	public E peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("Cannot peek: container is empty");
		}
		return container[0];
	}
	
	public E poll() {
		if(isEmpty()) {
			throw new NoSuchElementException("Cannot poll: container is empty");
		}
		E elem = container[0];
		for(int i = 0; i < size - 1; i++) {
			container[i] = container[i+1];
		}
		size--;
		return elem;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < size; i++) {
			sb.append(container[i].toString());
			if(i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	public Iterator<E> values() {
		return new Iterator<E>() {
			private int pos = 0;
			
			public boolean hasNext() {
				return pos < size;
			}
			
			public E next() {
				if(!hasNext()) {
					throw new NoSuchElementException("No more elements");
				}
				return container[pos++];
			}
		};
	}
}
