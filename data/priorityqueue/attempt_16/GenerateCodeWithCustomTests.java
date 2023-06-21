
public class PriorityQueue<E> extends Object implements Queue<E> {

    private int maxCapacity;
    private int size;
    private Comparator<E> comparator;
    private ArrayList<E> elements;

    public PriorityQueue() {
        maxCapacity = 10;
        size = 0;
        comparator = null;
        elements = new ArrayList<>(maxCapacity + 1);
    }

    public PriorityQueue(int max) {
        maxCapacity = max;
        size = 0;
        comparator = null;
        elements = new ArrayList<>(maxCapacity + 1);
    }

    public PriorityQueue(int max, Comparator<E> comparator) {
        maxCapacity = max;
        size = 0;
        this.comparator = comparator;
        elements = new ArrayList<>(maxCapacity + 1);
    }

    public PriorityQueue(Comparator<E> comparator) {
        maxCapacity = 10;
        size = 0;
        this.comparator = comparator;
        elements = new ArrayList<>(maxCapacity + 1);
    }

    public void add(E elem) {
        if (size == maxCapacity) {
            throw new FullContainerException();
        }
        size++;
        elements.add(null);
        Position<E> newPosition = nextLastPosition(elem);
        newPosition.set(elem);
        siftUp(newPosition);
    }

    public PriorityQueue<E> clone() {
        PriorityQueue<E> newPriorityQueue = new PriorityQueue<>(maxCapacity, comparator);
        for (E elem : elements) {
            newPriorityQueue.add(elem);
        }
        return newPriorityQueue;
    }

    protected int compare(Position<E> pos1, Position<E> pos2) {
        return comparator != null ? comparator.compare(pos1.get(), pos2.get()) : ((Comparable<E>) pos1.get()).compareTo(pos2.get());
    }

    protected Position<E> deleteLastPosition() {
        Position<E> lastPosition = elements.last();
        Position<E> previousToLastPosition = lastPosition.parent();
        elements.swap(lastPosition, previousToLastPosition.left());
        elements.remove(lastPosition);
        size--;
        siftDown(previousToLastPosition.left());
        return lastPosition;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxCapacity;
    }

    protected Position<E> nextLastPosition(E elem) {
        Position<E> newPosition = new Position<E>();
        newPosition.set(elem);
        if (isEmpty()) {
            elements.add(newPosition);
        } else {
            Traversal<Position<E>> traversal = new TreeTraversal<Position<E>>(elements.get(1));
            while (!(traversal.hasNext())) {
                Position<E> currentPosition = traversal.next();
                Position<E> leftPosition = currentPosition.left();
                if (leftPosition.isEmpty()) {
                    elements.add(newPosition);
                    leftPosition.set(newPosition);
                    break;
                }
                Position<E> rightPosition = currentPosition.right();
                if (rightPosition.isEmpty()) {
                    elements.add(newPosition);
                    rightPosition.set(newPosition);
                    break;
                }
            }
        }
        return newPosition;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            return elements.get(1).get();
        }
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        } else {
            E returnElem = elements.get(1).get();
            if (size == 1) {
                elements.remove(1);
                size--;
            } else {
                Position<E> lastPosition = deleteLastPosition();
                elements.get(1).set(lastPosition.get());
                siftDown(elements.get(1));
            }
            return returnElem;
        }
    }

    public Traversal<Position<E>> positions() {
        return new TreeTraversal<Position<E>>(elements.get(1));
    }

    protected void siftDown(Position<E> posToOrder) {
        while (!(posToOrder.isEmpty())) {
            Position<E> posToSwap = posToOrder;
            if (!posToOrder.left().isEmpty() && compare(posToOrder.left(), posToSwap) > 0) {
                posToSwap = posToOrder.left();
            }
            if (!posToOrder.right().isEmpty() && compare(posToOrder.right(), posToSwap) > 0) {
                posToSwap = posToOrder.right();
            }
            if (posToSwap == posToOrder) {
                break;
            }
            elements.swap(posToOrder, posToSwap);
            posToOrder = posToSwap;
        }
    }

    protected void siftUp(Position<E> newPosition) {
        Position<E> posToOrder = newPosition;
        while (!posToOrder.isRoot() && compare(posToOrder, posToOrder.parent()) > 0) {
            elements.swap(posToOrder, posToOrder.parent());
            posToOrder = posToOrder.parent();
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            Iterator<E> iterator = values();
            while (iterator.hasNext()) {
                sb.append(iterator.next().toString()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append("]");
            return sb.toString();
        }
    }

    public Iterator<E> values() {
        return new PriorityQueueIterator<>(this);
    }

}
