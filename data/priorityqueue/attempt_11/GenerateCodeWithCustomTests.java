
public class PriorityQueue<E> extends Object implements Queue<E> {

    protected class Position<E>{
        E elem;
        Position<E> parent;
        Position<E> leftChild;
        Position<E> rightChild;

        Position(){
            elem = null;
            parent = null;
            leftChild = null;
            rightChild = null;
        }

        Position(E elem, Position<E> parent, Position<E> leftChild, Position<E> rightChild){
            this.elem = elem;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        boolean isInternal(){
            return leftChild != null || rightChild != null;
        }

        boolean isExternal(){
            return leftChild == null && rightChild == null;
        }
    }

    protected Position<E>[] priorityQueue;
    protected Comparator<E> comparator;
    protected int currentSize;
    protected static final int DEFAULT_CAPACITY = 10000;

    public PriorityQueue(){
        this(DEFAULT_CAPACITY);
    }

    public PriorityQueue(int max){
        this(max, null);
    }

    public PriorityQueue(int max, Comparator<E> comparator){
        currentSize = 0;
        this.comparator = comparator;
        priorityQueue = new Position[max + 1];
        priorityQueue[0] = new Position<>();
    }

    public PriorityQueue(Comparator<E> comparator){
        this(DEFAULT_CAPACITY, comparator);
    }

    private void addNode(Position<E> p){
        priorityQueue[++currentSize] = p;
        siftUp(currentSize);
    }

    public void add(E elem){
        Position<E> p = new Position<>(elem, null, null, null);
        addNode(p);
    }

    public E poll(){
        if(currentSize == 0){
            throw new EmptyContainerException();
        }
        E value = priorityQueue[1].elem;
        priorityQueue[1].elem = priorityQueue[currentSize].elem;
        priorityQueue[currentSize--] = null;
        if(currentSize > 0){
            siftDown(1);
        }
        return value;
    }

    public E peek(){
        if(currentSize == 0){
            return null;
        }
        return priorityQueue[1].elem;
    }

    public PriorityQueue<E> clone(){
        PriorityQueue<E> newPriorityQueue = new PriorityQueue<>(priorityQueue.length,comparator);
        for(int i = 1; i <= currentSize; i++){
            newPriorityQueue.priorityQueue[i] = priorityQueue[i];
        }
        newPriorityQueue.currentSize = currentSize;
        return newPriorityQueue;
    }

    protected int compare(Position<E> p1, Position<E> p2){
        if(comparator == null){
            return ((Comparable<E>)p1.elem).compareTo(p2.elem);
        } else {
            return comparator.compare(p1.elem, p2.elem);
        }
    }

    protected void siftUp(Position<E> p){
        Position<E> current = p;
        while(current.parent != null && compare(current, current.parent) < 0){
            E elem = current.parent.elem;
            current.parent.elem = current.elem;
            current.elem = elem;
            current = current.parent;
        }
    }

    protected void siftDown(Position<E> p){
        Position<E> current = p;
        while(current.leftChild != null){
            Position<E> smaller;
            if(current.rightChild == null || compare(current.leftChild, current.rightChild) < 0){
                smaller = current.leftChild;
            } else {
                smaller = current.rightChild;
            }
            if(compare(smaller, current) < 0){
                E tmp = smaller.elem;
                smaller.elem = current.elem;
                current.elem = tmp;
                current = smaller;
            } else {
                break;
            }
        }
    }

    protected void siftDown(int p){
        siftDown(priorityQueue[p]);
    }

    protected Position<E> deleteLastPosition(){
        Position<E> p = priorityQueue[currentSize];
        priorityQueue[currentSize] = null;
        currentSize--;
        return p;
    }

    protected Position<E> nextLastPosition(E elem) {
        Position<E> lastPosition = new Position<>(elem, null, null, null);
        currentSize++;
        priorityQueue[currentSize] = lastPosition;
        lastPosition.parent = priorityQueue[currentSize/2];
        if(currentSize%2 == 0){
            lastPosition.parent.leftChild = lastPosition;
        }else{
            lastPosition.parent.rightChild = lastPosition;
        }
        return lastPosition;
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    public boolean isFull(){
        return currentSize == priorityQueue.length - 1;
    }

    public int size(){
        return currentSize;
    }

    public Iterator<E> values(){
        PriorityQueue<E> clonedPriorityQueue = clone();
        Traversal<Position<E>> traversal = clonedPriorityQueue.traversal();
        List<E> elements = new ArrayList<>();
        while(traversal.hasNext()){
            elements.add(traversal.next().elem);
        }
        return new Iterator<E>() {
            private int currentPosition = 0;
            @Override
            public boolean hasNext() {
                return currentPosition < elements.size();
            }

            @Override
            public E next() {
                return elements.get(currentPosition++);
            }
        };
    }

    public Traversal<Position<E>> traversal(){
        return new Traversal<Position<E>>(){
            @Override
            public Iterator<Position<E>> iterator() {
                return new Iterator<Position<E>>() {
                    PriorityQueue<E> clonedPriorityQueue = clone();
                    int current = 1;
                    @Override
                    public boolean hasNext() {
                        return current <= currentSize;
                    }

                    @Override
                    public Position<E> next() {
                        return clonedPriorityQueue.priorityQueue[current++];
                    }
                };
            }
        };
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 1; i <= currentSize; i++){
            sb.append(priorityQueue[i].elem.toString());
            if(i < currentSize){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
