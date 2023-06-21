
public class LinkedList<E> extends Object implements List<E> {
    
    protected LinkedList.LinkedNode<E> first;
    protected LinkedList.LinkedNode<E> last;
    protected int size;
    
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    public E delete(Position<E> node) {
        LinkedList.LinkedNode<E> toDelete = (LinkedList.LinkedNode<E>) node; 
        if (toDelete == this.first) {
            return this.deleteFirst();
        } else {
            return this.deleteNext(toDelete.prev);
        }
    }
    
    public E deleteFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            E value = this.first.value;
            if (this.first == this.last) {
                this.first = null;
                this.last = null;
            } else {
                this.first = this.first.next;
                this.first.prev = null;
            }
            this.size--;
            return value;
        }
    }
    
    public E deleteNext(Position<E> node) {
        LinkedList.LinkedNode<E> toDelete = ((LinkedList.LinkedNode<E>) node).next; 
        if (toDelete == this.last) {
            this.last = toDelete.prev;
            toDelete.prev.next = null;
        } else {
            toDelete.next.prev = toDelete.prev;
            toDelete.prev.next = toDelete.next;
        }
        this.size--;
        return toDelete.value; 
    }
    
    public Position<E> insertAfter(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> after = (LinkedList.LinkedNode<E>) node;
        LinkedList.LinkedNode<E> added = new LinkedList.LinkedNode<>(elem);
        
        if (after == null || this.isEmpty()) {
            added.prev = null;
            added.next = null;
            this.first = added;
            this.last = added;
        } else if (after == this.last) {
            added.prev = after;
            added.next = null;
            this.last = added;
            after.next = added;
        } else {
            added.prev = after;
            added.next = after.next;
            after.next.prev = added;
            after.next = added;
        }
        this.size++;
        return added;
    }
    
    public Position<E> insertBefore(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> before = (LinkedList.LinkedNode<E>) node;
        LinkedList.LinkedNode<E> added = new LinkedList.LinkedNode<>(elem);
        
        if (before == null || this.isEmpty()) {
            added.prev = null;
            added.next = null;
            this.first = added;
            this.last = added;
        } else if (before == this.first) {
            added.prev = null;
            added.next = before;
            this.first = added;
            before.prev = added;
        } else {
            added.prev = before.prev;
            added.next = before;
            before.prev.next = added;
            before.prev = added; 
        }
        this.size++;
        return added;
    }
    
    public Position<E> insertBeginning(E elem) {
        return this.insertBefore(this.first, elem);
    }
    
    public Position<E> insertEnd(E elem) {
        return this.insertAfter(this.last, elem);
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem) {
        LinkedList.LinkedNode<E> added = new LinkedList.LinkedNode<>(elem);
        added.next = node.next;
        added.prev = node;
        node.next = added;
        if (added.next != null) {
            added.next.prev = added;
        }
        this.size++;
        return added;
    }
    
    public Traversal<E> positions() {
        return new LinkedListTraversal<>(this.first, this.last);
    }
    
    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node) {
        return node.prev;
    }
    
    public int size() {
        return this.size;
    }
    
    public void swap(Position<E> node1, Position<E> node2) {
        LinkedList.LinkedNode<E> nodeA = (LinkedList.LinkedNode<E>) node1;
        LinkedList.LinkedNode<E> nodeB = (LinkedList.LinkedNode<E>) node2;
        E temp = nodeA.value;
        nodeA.value = nodeB.value;
        nodeB.value = temp;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Traversal<E> positions = this.positions();
        while (positions.hasNext()) {
            sb.append(positions.next().value);
            if (positions.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
    
    public E update(Position<E> node, E elem) {
        LinkedList.LinkedNode<E> toUpdate = (LinkedList.LinkedNode<E>) node;
        E oldValue = toUpdate.value;
        toUpdate.value = elem;
        return oldValue;
    }
    
    public Iterator<E> values() {
        List<E> values = new ArrayList<>();
        Traversal<E> traversal = this.positions();
        while (traversal.hasNext()) {
            values.add(traversal.next().value);
        }
        return values.iterator();
    }
    
    public static class LinkedNode<E> implements Position<E> {
        public E value;
        public LinkedList.LinkedNode<E> prev;
        public LinkedList.LinkedNode<E> next;
        
        public LinkedNode(E elem) {
            this.value = elem;
            this.prev = null;
            this.next = null;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (this.next == null && this.prev == null) {
                throw new IllegalStateException("Position no longer valid.");
            }
            return this.value;
        }
    }
}
