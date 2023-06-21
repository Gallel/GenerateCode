
public class QueueArrayImpl<E> extends Object implements Queue<E>, FiniteContainer<E> {
   private int max;
   private int front;
   private int rear;
   private int size;
   private E[] queueArray;

   public QueueArrayImpl() {
      this.max = 10;
      this.front = 0;
      this.rear = -1;
      this.size = 0;
      this.queueArray = (E[]) new Object[max];
   }

   public QueueArrayImpl(int max) {
      this.max = max;
      this.front = 0;
      this.rear = -1;
      this.size = 0;
      this.queueArray = (E[]) new Object[max];
   }

   public void add(E elem) {
      if (isFull()) {
         throw new IllegalStateException("Queue is full!");
      }
      rear = (rear + 1) % max;
      queueArray[rear] = elem;
      size++;
   }

   public boolean isEmpty() {
      return (size == 0);
   }

   public boolean isFull() {
      return (size == max);
   }

   public E peek() {
      if (isEmpty()) {
         return null;
      }
      return queueArray[front];
   }

   public E poll() {
      if (isEmpty()) {
         return null;
      }
      E elem = queueArray[front];
      front = (front + 1) % max;
      size--;
      return elem;
   }

   public int size() {
      return size;
   }

   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder sb = new StringBuilder("[");
      for (int i = front; i != rear; i = (i + 1) % max) {
         sb.append(queueArray[i]).append(", ");
      }
      sb.append(queueArray[rear]).append("]");
      return sb.toString();
   }

   public Iterator<E> values() {
      return new Iterator<E>() {
         private int index = front;

         public boolean hasNext() {
            return index != rear;
         }

         public E next() {
            E elem = queueArray[index];
            index = (index + 1) % max;
            return elem;
         }
      };
   }
}
