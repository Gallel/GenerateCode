
public class LinkedList<E> extends Object implements List<E> {

    protected static final class LinkedNode<E> implements Position<E> {
        public E getElement() throws IllegalStateException;
        public LinkedNode<E> getNext() throws IllegalStateException;
        public LinkedNode<E> getPrevious() throws IllegalStateException;
        public void setElement(E o);
        public void setNext(LinkedNode<E> n);
        public void setPrevious(LinkedNode<E> p);
    }

   public LinkedList();

   public int size();

   public boolean isEmpty();

   public Position<E> first() throws NoSuchElementException;

   public Position<E> last() throws NoSuchElementException;

   public Position<E> before(Position<E> p) throws IllegalArgumentException, NoSuchElementException;

   public Position<E> after(Position<E> p) throws IllegalArgumentException, NoSuchElementException;

   public Position<E> insertFirst(E e);

   public Position<E> insertLast(E e);

   public Position<E> insertBefore(Position<E> p, E e) throws IllegalArgumentException;

   public Position<E> insertAfter(Position<E> p, E e) throws IllegalArgumentException;

   public E replace(Position<E> p, E e) throws IllegalArgumentException;

   public E remove(Position<E> p) throws IllegalArgumentException;
}
