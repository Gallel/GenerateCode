
public class LinkedList<E> extends Object implements List<E> {
    
    // Private class to represent a node in the linked list
    private static class LinkedNode<E> implements Position<E> {
        private E element;
        private LinkedNode<E> next;
        
        public LinkedNode(E element, LinkedNode<E> next) {
            this.element = element;
            this.next = next;
        }
        
        public E getElement() {
            return element;
        }
        
        public void setElement(E element) {
            this.element = element;
        }
        
        public LinkedNode<E> getNext() {
            return next;
        }
        
        public void setNext(LinkedNode<E> next) {
            this.next = next;
        }
    }
    
    // LinkedList attributes
    private LinkedNode<E> head;
    private LinkedNode<E> tail;
    private int size;
    
    // Default constructor creates an empty list
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    // Deletes the given position and returns the element stored in it
    public E delete(Position<E> node) {
        LinkedNode<E> current = checkPosition(node);
        E element = current.getElement();
        
        if (current == head) {
            head = current.getNext();
        } else {
            LinkedNode<E> previous = previous(current);
            previous.setNext(current.getNext());
        }
        
        current.setElement(null);
        current.setNext(null);
        size--;
        return element;
    }
    
    // Deletes the first position in the list and returns its element
    public E deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        return delete(head);
    }
    
    // Deletes the position after the given one and returns its element
    public E deleteNext(Position<E> node) {
        LinkedNode<E> current = checkPosition(node);
        if (current == tail) {
            return null;
        }
        LinkedNode<E> next = current.getNext();
        return delete(next);
    }
    
    // Inserts an element after the given position and returns the new position
    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedNode<E> current = checkPosition(node);
        LinkedNode<E> newNode = new LinkedNode<E>(elem, current.getNext());
        current.setNext(newNode);
        if (current == tail) {
            tail = newNode;
        }
        size++;
        return newNode;
    }
    
    // Inserts an element before the given position and returns the new position
    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedNode<E> current = checkPosition(node);
        LinkedNode<E> previous = previous(current);
        LinkedNode<E> newNode = new LinkedNode<E>(elem, current);
        if (current == head) {
            head = newNode;
        } else {
            previous.setNext(newNode);
        }
        size++;
        return newNode;
    }
    
    // Inserts an element at the beginning of the list and returns the new position
    public Position<E> insertBeginning(E elem) {
        LinkedNode<E> newNode = new LinkedNode<E>(elem, head);
        if (isEmpty()) {
            tail = newNode;
        }
        head = newNode;
        size++;
        return newNode;
    }
    
    // Inserts an element at the end of the list and returns the new position
    public Position<E> insertEnd(E elem) {
        LinkedNode<E> newNode = new LinkedNode<E>(elem, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
        return newNode;
    }
    
    // Returns whether the list is empty or not
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Creates a new LinkedNode with the given element, places it next to the given node, and increases the size of the list
    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem) {
        LinkedNode<E> newNode = new LinkedNode<E>(elem, node.getNext());
        node.setNext(newNode);
        size++;
        return newNode;
    }
    
    // Returns a Traversal object with positions of all elements in the list
    public Traversal<E> positions() {
        return new ElementIterator<>(this);
    }
    
    // Returns the node before the given one
    protected LinkedNode<E> previous(LinkedNode<E> node) {
        LinkedNode<E> current = head;
        while (current != null && current.getNext() != node) {
            current = current.getNext();
        }
        return current;
    }
    
    // Returns the number of elements in the list
    public int size() {
        return size;
    }
    
    // Swaps the elements stored in the given positions
    public void swap(Position<E> node1, Position<E> node2) {
        LinkedNode<E> position1 = checkPosition(node1);
        LinkedNode<E> position2 = checkPosition(node2);
        E temp = position1.getElement();
        position1.setElement(position2.getElement());
        position2.setElement(temp);
    }
    
    // Returns a String representation of the list
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        LinkedNode<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current != tail) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append(")");
        return sb.toString();
    }
    
    // Updates the element stored in the given position and returns the old element
    public E update(Position<E> node, E elem) {
        LinkedNode<E> current = checkPosition(node);
        E oldElement = current.getElement();
        current.setElement(elem);
        return oldElement;
    }
    
    // Returns an Iterator object with all the elements in the list
    public Iterator<E> values() {
        return new ElementIterator<>(this);
    }
    
    // Helper method to check if the received Position is valid and of the appropriate type
    protected LinkedNode<E> checkPosition(Position<E> p) throws IllegalArgumentException {
        if (p == null || !(p instanceof LinkedNode)) {
            throw new IllegalArgumentException("Invalid position");
        }
        LinkedNode<E> node = (LinkedNode<E>) p;
        if (node.getElement() == null) {
            throw new IllegalArgumentException("Position is no longer in the list");
        }
        return node;
    }
    
}
