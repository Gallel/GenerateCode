
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E> {

  //default constructor
  StackArrayImpl() {}
  
  //constructor with maximum capacity parameter
  StackArrayImpl(int max) {}
  
  //method to check if container is empty
  public boolean isEmpty() {}
  
  //method to check if container is full
  public boolean isFull() {}
  
  //getter for the last item added to the stack, if any
  public E peek() {}
  
  //delete the top of the stack item, if any
  public E pop() {}
  
  //add an item to the stack, if any
  public void push(E elem) {}
  
  //getter for the number of items in the container
  public int size() {}
  
  //method overwriting Object.toString()
  public String toString() {}
  
  //retrieves the items in the container
  public Iterator<E> values() {}
  
}
