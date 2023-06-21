
public class PriorityQueue<E> extends Object implements Queue<E> {

    // attributes
    private Vector<Position<E>> vec;
    private Comparator<E> comp;
    private int size;

    // constructors
    public PriorityQueue(){
        this.vec = new Vector<>(11);
        this.vec.add(null);
        this.comp = Comparable::compareTo;
        this.size = 0;
    }

    public PriorityQueue(int max){
        this.vec = new Vector<>(max+1);
        this.vec.add(null);
        this.comp = Comparable::compareTo;
        this.size = 0;
    }

    public PriorityQueue(int max, Comparator<E> comparator){
        this.vec = new Vector<>(max+1);
        this.vec.add(null);
        this.comp = comparator;
        this.size = 0;
    }

    public PriorityQueue(Comparator<E> comparator){
        this.vec = new Vector<>(11);
        this.vec.add(null);
        this.comp = comparator;
        this.size = 0;
    }

    // methods
    public void add(E elem){
        Position<E> newPos = this.nextLastPosition(elem);
        this.vec.set(newPos.index, newPos);
        this.siftUp(newPos);
        this.size++;
    }

    public PriorityQueue<E> clone(){
        PriorityQueue<E> clone = new PriorityQueue<E>(this.vec.size()-1, this.comp);
        for(int i=1; i<this.vec.size(); i++){
            if(this.vec.elementAt(i) != null){
                clone.vec.set(i, new Position<E>(this.vec.elementAt(i).value, i));
            }
        }
        clone.size = this.size;
        return clone;
    }

    protected int compare(Position<E> pos1, Position<E> pos2){
        return this.comp.compare(pos1.value, pos2.value);
    }

    protected Position<E> deleteLastPosition(){
        Position<E> lastPos = this.vec.elementAt(this.vec.size()-1);
        this.vec.remove(this.vec.size()-1);
        return lastPos;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public boolean isFull(){
        return this.size == this.vec.size()-1;
    }

    protected Position<E> nextLastPosition(E elem){
        int index = this.vec.size();
        this.vec.add(new Position<E>(elem, index));
        return this.vec.lastElement();
    }

    public E peek(){
        if(isEmpty()){
            return null;
        } else {
            return this.vec.elementAt(1).value;
        }
    }

    public E poll(){
        if(isEmpty()){
            throw new EmptyContainerException();
        } else {
            E result = this.vec.elementAt(1).value;
            Position<E> lastPos = this.deleteLastPosition();
            if(this.vec.size() > 1){
                this.vec.set(1, lastPos);
                this.siftDown(lastPos);
            }
            this.size--;
            return result;
        }
    }

    public Traversal positions(){
        return new PriorityQueuePositions<E>(this.vec);
    }

    protected void siftDown(Position<E> posicioAOrdenar){
        int index = posicioAOrdenar.index;
        boolean trobat = false;
        while(2*index <= this.vec.size()-1 && !trobat){
            int selectedChildIndex = 2*index;
            if(selectedChildIndex < this.vec.size()-1 && compare(this.vec.elementAt(selectedChildIndex+1), this.vec.elementAt(selectedChildIndex)) < 0){
                selectedChildIndex++;
            }
            if(compare(this.vec.elementAt(selectedChildIndex), posicioAOrdenar) < 0){
                this.vec.set(index, this.vec.elementAt(selectedChildIndex));
                index = selectedChildIndex;
            } else {
                trobat = true;
            }
        }
        this.vec.set(index, posicioAOrdenar);
    }

    protected void siftUp(Position<E> newPos){
        Position<E> node = this.vec.elementAt(newPos.index);
        boolean trobat = false;
        while(node.index > 1 && !trobat){
            Position<E> parent = this.vec.elementAt(node.index / 2);
            if(compare(node, parent) < 0){
                this.vec.set(node.index, parent);
                this.vec.set(parent.index, node);
                node = parent;
            } else {
                trobat = true;
            }
        }
    }

    public int size(){
        return this.size;
    }

    @Override
    public String toString() {
        Iterator<E> values = values();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(values.hasNext()){
            sb.append(values.next());
            if(values.hasNext()){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> values(){
        return new PriorityQueueIterator<E>(this.vec);
    }
}
