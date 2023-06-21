
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E>{
  
  private int max;
  private int count;
  private E[] elements;
  private int head;
  private int tail;
  
  //Constructor without parameters
  public QueueArrayImpl(){
    this.max = 1000;
    this.count = 0;
    this.elements = (E[]) new Object[1000];
    this.head = 0;
    this.tail = 0;
  }

  //Constructor with a parameter
  public QueueArrayImpl(int max){
    this.max = max;
    this.count = 0;
    this.elements = (E[]) new Object[max];
    this.head = 0;
    this.tail = 0;
  }
  
  //Add an item to the queue
  public void add(E elem) throws FullContainerException{
    if(this.count == this.max)
      throw new FullContainerException();
      
    this.elements[this.tail] = elem;
    this.tail = (this.tail + 1) % this.max;
    this.count++;    
  }
  
  //Method to check if the container is empty
  public boolean isEmpty(){
    return this.count == 0;
  }
  
  //Method to check if the container is full
  public boolean isFull(){
    return this.count == this.max;
  }
  
  //Retrieves the first item added to the queue
  public E peek() throws EmptyContainerException{
    if(this.count == 0)
      throw new EmptyContainerException();
      
    return this.elements[this.head];    
  }
  
  //Delete the first item in the queue
  public E poll() throws EmptyContainerException{
    if(this.count == 0)
      throw new EmptyContainerException();
      
    E result = this.elements[this.head];
    this.head = (this.head + 1) % this.max;
    this.count--;
    return result;    
  }
  
  //Retrieves the number of items in the container
  public int size(){
    return this.count;
  }
  
  //Method overwriting Object.toString ()
  public String toString(){
    StringBuilder str = new StringBuilder();
    str.append("[");
    int i = 0;
    while(i < this.count){
      str.append(this.elements[(this.head + i) % this.max]);
      if(i < this.count - 1)
        str.append(", ");
      i++;
    }
    str.append("]");
    return str.toString();
  }
  
  //Retrieves the items in the container
  public Iterator<E> values(){
    List<E> list = new ArrayList<E>();
    int i = 0;
    while(i < this.count){
      list.add(this.elements[(this.head + i) % this.max]);
      i++;
    }
    return list.iterator();
  }
} 
