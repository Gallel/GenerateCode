
public class LinkedList<E> extends Object implements List<E>{

    protected static class LinkedNode<E> implements Position<E>{
        protected E elem;
        protected LinkedNode<E> next;
        public LinkedNode(E elem, LinkedNode<E> next){
            this.elem = elem;
            this.next = next;
        }
        public void setElement(E elem){
            this.elem = elem;
        }
        public E getElement(){
            return this.elem;
        }
        public LinkedNode<E> getNext(){
            return this.next;
        }
        public void setNext(LinkedNode<E> next){
            this.next = next;
        }
    }

    protected LinkedNode<E> head;
    protected LinkedNode<E> tail;
    protected int count;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    protected LinkedNode<E> newPosition(LinkedNode<E> node, E elem){
        LinkedNode<E> newNode = new LinkedNode<E>(elem, node.getNext());
        node.setNext(newNode);
        count++;
        return newNode;
    }

    protected LinkedNode<E> previous(LinkedNode<E> node){
        LinkedNode<E> previous = head;
        while(previous.getNext() != node){
            previous = previous.getNext();
        }
        return previous;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public int size(){
        return count;
    }

    public Position<E> insertAfter(Position<E> node, E elem){
        LinkedNode<E> newNode;
        if(node == null){
            newNode = new LinkedNode<E>(elem, head);
            head = newNode;
        }else{
            newNode = newPosition((LinkedNode<E> )node, elem);
            if(newNode.getNext() == null){
                tail = newNode;
            }
        }
        return newNode;
    }

    public Position<E> insertBefore(Position<E> node, E elem){
        if(node == head){
            LinkedNode<E> newNode = new LinkedNode<E>(elem, head);
            head = newNode;
            count++;
            return newNode;
        }
        LinkedNode<E> previous = previous((LinkedNode<E> )node);
        return newPosition(previous, elem);
    }

    public Position<E> insertBeginning(E elem){
        return insertAfter(null, elem);
    }

    public Position<E> insertEnd(E elem){
        return insertAfter((Position<E> )tail, elem);
    }

    public E delete(Position<E> node){
        if(count == 0){
            return null;
        }
        LinkedNode<E> toDelete = (LinkedNode<E>)node;
        if(toDelete == head){
            head = head.getNext();
        }else{
            LinkedNode<E> previous = previous(toDelete);
            previous.setNext(toDelete.getNext());
        }
        if(toDelete == tail){
            tail = previous(toDelete);
        }
        count--;
        return toDelete.getElement();
    }

    public E deleteFirst(){
        return delete(head);
    }

    public E deleteNext(Position<E> node){
        LinkedNode<E> toDelete = ((LinkedNode<E>) node).getNext();
        return delete(toDelete);
    }

    public void swap(Position<E> node1, Position<E> node2){
        LinkedNode<E> n1 = (LinkedNode<E>)node1;
        LinkedNode<E> n2 = (LinkedNode<E>)node2;
        E temp = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(temp);
    }

    public E update(Position<E> node, E elem){
        LinkedNode<E> toUpdate = (LinkedNode<E>) node;
        E oldElem = toUpdate.getElement();
        toUpdate.setElement(elem);
        return oldElem;
    }

    public Iterator<E> values(){
        return new LinkedListIterator<E>(this);
    }

    protected static class LinkedListIterator<E> implements Iterator<E>{
        private LinkedList<E> list;
        private Position<E> cursor;

        public LinkedListIterator(LinkedList<E> list){
            this.list = list;
            this.cursor = list.head;
        }

        public boolean hasNext(){
            return cursor != null;
        }

        public E next(){
            LinkedNode<E> node = (LinkedNode<E>) cursor;
            cursor = node.getNext();
            return node.getElement();
        }
    }

    public Traversal<E> positions(){
        return new LinkedListPositions<E>(this);
    }

    protected static class LinkedListPositions<E> implements Traversal<E>{
        private LinkedList<E> list;
        private Position<E> cursor;

        public LinkedListPositions(LinkedList<E> list){
            this.list = list;
            this.cursor = list.head;
        }

        public boolean hasNext(){
            return cursor != null;
        }

        public Position<E> next(){
            Position<E> node = cursor;
            cursor = ((LinkedNode<E>) cursor).getNext();
            return node;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iterator = values();
        while(iterator.hasNext()){
            sb.append(iterator.next());
            if(iterator.hasNext()){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
