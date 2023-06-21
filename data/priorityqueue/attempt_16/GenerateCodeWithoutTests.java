
public class PriorityQueue<E> extends Object implements Queue<E> {
    // Attributes
    private ArrayList<E> queue;
    private Comparator<E> comparator;

    // Constructors
    public PriorityQueue() {
        this.queue = new ArrayList<E>();
        this.comparator = null;
    }

    public PriorityQueue(int max) {
        this.queue = new ArrayList<E>(max);
        this.comparator = null;
    }

    public PriorityQueue(int max, Comparator<E> comparator) {
        this.queue = new ArrayList<E>(max);
        this.comparator = comparator;
    }

    public PriorityQueue(Comparator<E> comparator) {
        this.queue = new ArrayList<E>();
        this.comparator = comparator;
    }

    // Methods
    public void add(E elem) {
        this.queue.add(elem);
        siftUp(this.queue.size() - 1);
    }
    
    public PriorityQueue<E> clone() {
        PriorityQueue<E> newQueue = new PriorityQueue<E>(this.queue.size(), this.comparator);
        for (E elem : this.queue) {
            newQueue.add(elem);
        }
        return newQueue;
    }
    
    protected int compare(Position<E> pos1, Position<E> pos2) {
        E elem1 = pos1.getElement();
        E elem2 = pos2.getElement();
        if (this.comparator != null) {
            return this.comparator.compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }
    
    protected Position<E> deleteLastPosition() {
        if (this.queue.isEmpty()) {
            return null;
        } else {
            return new ArrayPosition<E>(this.queue, this.queue.size() - 1);
        }
    }
    
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    
    public boolean isFull() {
        return false;
    }
    
    protected Position<E> nextLastPosition(E elem) {
        return new ArrayPosition<E>(this.queue, this.queue.size());
    }
    
    public E peek() {
        if (this.queue.isEmpty()) {
            return null;
        } else {
            return this.queue.get(0);
        }
    }
    
    public E poll() {
        if (this.queue.isEmpty()) {
            return null;
        } else {
            E result = this.queue.get(0);
            Position<E> last = deleteLastPosition();
            if (last != null && last != this.queue.get(0)) {
                ((ArrayPosition<E>) last).setElement(this.queue.get(0));
                siftDown(0);
            }
            this.queue.remove(this.queue.size() - 1);
            return result;
        }
    }    
    
    public Traversal positions() {
        return new PriorityQueueTraversal(this.queue);
    }
    
    protected void siftDown(Position<E> posToOrder) {
        while (true) {
            int leftIndex = posToOrder.getIndex() * 2 + 1;
            int rightIndex = posToOrder.getIndex() * 2 + 2;
            if (leftIndex >= this.queue.size()) {
                break;
            }
            Position<E> minChild = new ArrayPosition<E>(this.queue, leftIndex);
            if (rightIndex < this.queue.size()) {
                Position<E> rightChild = new ArrayPosition<E>(this.queue, rightIndex);
                if (compare(rightChild, minChild) < 0) {
                    minChild = rightChild;
                }
            }
            if (compare(minChild, posToOrder) < 0) {
                E temp = posToOrder.getElement();
                ((ArrayPosition<E>) posToOrder).setElement(minChild.getElement());
                ((ArrayPosition<E>) minChild).setElement(temp);
                posToOrder = minChild;
            } else {
                break;
            }
        }
    }
    
    protected void siftUp(Position<E> newPosition) {
        Position<E> parent = new ArrayPosition<E>(this.queue, (newPosition.getIndex() - 1) / 2);
        while (newPosition != this.queue.get(0) && compare(newPosition, parent) < 0) {
            E temp = parent.getElement();
            ((ArrayPosition<E>) parent).setElement(newPosition.getElement());
            ((ArrayPosition<E>) newPosition).setElement(temp);
            newPosition = parent;
            parent = new ArrayPosition<E>(this.queue, (newPosition.getIndex() - 1) / 2);
        }
    }
    
    public int size() {
        return this.queue.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<E> iter = this.values();
        while (iter.hasNext()) {
            sb.append(iter.next());
            if (iter.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Iterator<E> values() {
        return this.queue.iterator();
    }
}
