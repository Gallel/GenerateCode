
public class StackArrayImpl<E> extends Object implements Stack<E>, FiniteContainer<E>{
   private E[] elements;
   private int top;
   private int max;

   public StackArrayImpl() {
      this(1000);
   }

   @SuppressWarnings("unchecked")
   public StackArrayImpl(int max) {
      this.max = max;
      this.top = -1;
      this.elements = (E[]) new Object[max];
   }

   public boolean isEmpty() {
      return top == -1;
   }

   public boolean isFull() {
      return top == max - 1;
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
      E element = elements[top];
      elements[top] = null;
      top--;
      return element;
   }

   public void push(E elem) {
      if (isFull()) {
         return;
      }
      top++;
      elements[top] = elem;
   }

   public int size() {
      return top + 1;
   }

   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder sb = new StringBuilder("[");
      for (int i = 0; i <= top; i++) {
         sb.append(elements[i]);
         if (i < top) {
            sb.append(", ");
         }
      }
      sb.append("]");
      return sb.toString();
  }

   public Iterator<E> values() {
      return new Iterator<E>() {
         int position = top;

         public boolean hasNext() {
            return position >= 0;
         }

         public E next() {
            return elements[position--];
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }
}
