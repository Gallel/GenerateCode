
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

    // Default constructor
    public StackArrayImpl() {
        // Initialize stack with default maximum capacity
    }
    
    // Constructor with maximum capacity parameter
    public StackArrayImpl(int max) {
        // Initialize stack with maximum capacity
    }
    
    // Check if the container is empty
    public boolean isEmpty() {
        // Check if there are any elements in the stack
    }
    
    // Check if the container is full
    public boolean isFull() {
        // Check if the stack is at maximum capacity
    }
    
    // Get the last item added to the stack, if any
    public E peek() {
        // Return the last element in the stack without removing it
    }
    
    // Delete the top of the stack item, if any
    public E pop() {
        // Remove and return the last element in the stack
    }
    
    // Add an item to the stack, if any
    public void push(E elem) {
        // Add the specified element to the top of the stack
    }
    
    // Get the number of items in the container
    public int size() {
        // Return the number of elements in the stack
    }
    
    // Method overwriting Object.toString()
    public String toString() {
        // Return a string representation of the stack
    }
    
    // Retrieves the items in the container
    public Iterator<E> values() {
        // Return an iterator over the elements in the stack
    }
}
