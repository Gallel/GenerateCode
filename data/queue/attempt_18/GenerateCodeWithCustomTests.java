
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E>{
  
  private E[] array;
  private int capacity;
  private int size;
  
  public QueueArrayImpl(){
    this.capacity = FiniteContainer.DEFAULT_MAX_SIZE;
    this.array = (E[]) new Object[this.capacity];
    this.size = 0;
  }
  
  public QueueArrayImpl(int max){
    if (max <= 0) throw new IllegalArgumentException("Maximum capacity must be greater than zero");
    this.capacity = max;
    this.array = (E[]) new Object[this.capacity];
    this.size = 0;
  }
  
  @Override
  public void add(E elem){
    if (this.isFull()) throw new FullContainerException();
    this.array[this.size] = elem;
    this.size++;
  }
  
  @Override
  public boolean isEmpty(){
    return this.size == 0;
  }
  
  @Override
  public boolean isFull(){
    return this.size == this.capacity;
  }
  
  @Override
  public E peek(){
    if (this.isEmpty()) throw new EmptyContainerException();
    return this.array[0];
  }
  
  @Override
  public E poll(){
    if (this.isEmpty()) throw new EmptyContainerException();
    E elem = this.array[0];
    this.size--;
    System.arraycopy(this.array, 1, this.array, 0, this.size);
    return elem;
  }
  
  @Override
  public int size(){
    return this.size;
  }
  
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < this.size; i++){
      sb.append(this.array[i]);
      if (i != this.size - 1) sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }
  
  @Override
  public Iterator<E> values(){
      return new Iterator<E>() {

          int current = 0;
          
          @Override
          public boolean hasNext() {
              return current < size;
          }

          @Override
          public E next() {
              return array[current++];
          }
      };
  }
}
