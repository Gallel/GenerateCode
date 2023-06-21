
public class PriorityQueue<E> extends Object implements Queue<E> {

    // Attributes
    private int size;
    private int maxCapacity;
    private Object[] queue;
    private Comparator<E> comparator;

    // Constructors
    public PriorityQueue() {
        this.maxCapacity = 10;
        this.queue = new Object[maxCapacity];
        this.comparator = null;
        this.size = 0;
    }

    public PriorityQueue(int max) {
        this.maxCapacity = max;
        this.queue = new Object[maxCapacity];
        this.comparator = null;
        this.size = 0;
    }

    public PriorityQueue(int max, Comparator<E> comparator) {
        this.maxCapacity = max;
        this.queue = new Object[maxCapacity];
        this.comparator = comparator;
        this.size = 0;
    }

    public PriorityQueue(Comparator<E> comparator) {
        this.maxCapacity = 10;
        this.queue = new Object[maxCapacity];
        this.comparator = comparator;
        this.size = 0;
    }

    // Methods
    public void add(E elem) {
        if(size >= maxCapacity) throw new FullContainerException();
        queue[size] = elem;
        siftUp(size);
        size++;
    }

    public PriorityQueue<E> clone() {
        PriorityQueue<E> newQueue = new PriorityQueue<>(this.maxCapacity, this.comparator);
        for(int i=0; i<size; i++){
            newQueue.add((E) queue[i]);
        }
        return newQueue;
    }

    protected int compare(Position<E> pos1, Position<E> pos2) {
        E elem1 = (E) pos1.getElement();
        E elem2 = (E) pos2.getElement();

        if(this.comparator == null) {
            return ((Comparable<E>) elem1).compareTo(elem2);
        } else {
            return this.comparator.compare(elem1, elem2);
        }
    }

    protected Position<E> deleteLastPosition() {
        if (size == 0) throw new EmptyContainerException();
        size--;
        Position<E> lastPos = (Position<E>) queue[size];
        queue[size] = null;
        return lastPos;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxCapacity;
    }

    protected Position<E> nextLastPosition(E elem){
        Position<E> parent = (Position<E>) queue[(size-1)/2];
        Position<E> last = new Position<E>((E)elem, size);
        queue[size] = last;

        if(size > 0){
            int result = compare(last, parent);
            if(result < 0) {
                siftUp(size);
            } else {
                siftDown(last);
            }
        }
        size++;
        return last;
    }

    public E peek() {
        if (size == 0) return null;
        return (E) queue[0];
    }

    public E poll() {
        if (size == 0) throw new EmptyContainerException();
        E elem = (E) queue[0];
        Position<E> lastPos = deleteLastPosition();
        if(size > 0){
            queue[0] = lastPos;
            siftDown(0);
        }
        return elem;
    }

    public Traversal<Position<E>> positions() {

        return new Traversal<Position<E>>() {
            @Override
            public Iterator<Position<E>> iterator() {
                return new Iterator<Position<E>>() {
                    int count = 0;

                    @Override
                    public boolean hasNext() {
                        return count < size;
                    }

                    @Override
                    public Position<E> next() {
                        if (count == size) throw new IndexOutOfBoundsException();
                        return (Position<E>) queue[count++];
                    }
                };
            }
        };
    }

    protected void siftDown(Position<E> posicioAOrdenar) {
        int parentPos = posicioAOrdenar.getIndex();
        while (parentPos > 0) {
            int childPos = parentPos * 2 + 1;
            if (childPos < size) {
                if (compare((Position<E>) queue[childPos], (Position<E>) queue[childPos - 1]) < 0) {
                    childPos--;
                }
            } else {
                childPos--;
            }
            if (compare(posicioAOrdenar, (Position<E>) queue[childPos]) <= 0) {
                break;
            }
            queue[parentPos] = queue[childPos];
            ((Position<E>) queue[parentPos]).setIndex(parentPos);
            parentPos = childPos;
        }
        queue[parentPos] = posicioAOrdenar;
        posicioAOrdenar.setIndex(parentPos);
    }

    protected void siftUp(Position<E> newPosition) {

        int childpos = newPosition.getIndex();
        while (childpos > 0) {
            int parentpos = (childpos - 1) / 2;
            Position<E> parent = (Position<E>) queue[parentpos];
            if (compare(newPosition, parent) >= 0) break;
            queue[childpos] = parent;
            parent.setIndex(childpos);
            childpos = parentpos;
        }
        queue[childpos] = newPosition;
        newPosition.setIndex(childpos);
    }
    public int size() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i=0; i<this.size; i++){
            sb.append(this.queue[i]);
            if(i<this.size-1) sb.append(", ");
        }
        sb.append(" ]");
        return sb.toString();

    }


    public Iterator<E> values() {
        return new Iterator<E>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                if (count == size) throw new IndexOutOfBoundsException();
                count++;
                return (E) queue[count-1];
            }
        };
    }

}
