
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    // Constructors
    public AVLTree() {}
    public AVLTree(Comparator<E> comparator) { super(comparator); }
    
    // Public methods
    public void add(E elemComp) { super.add(elemComp); balance(super.lastAddedNode()); }
    public E delete(E elemComp) { return super.delete(elemComp); }
    public E get(E elemComp) { return super.get(elemComp); }
    public boolean thereIsComparator() { return super.thereIsComparator(); }
    
    // Protected methods
    @Override
    protected void balance(Position<E> bst) {
        while (bst != null) {
            int diff = height(super.left(bst)) - height(super.right(bst));
            if (Math.abs(diff) > 1) {
                if (diff < 0 && height(super.right(super.right(bst))) > height(super.left(super.right(bst)))) {
                    rotateLeftRight(bst);
                } else if (diff > 0 && height(super.left(super.left(bst))) > height(super.right(super.left(bst)))) {
                    rotateRightLeft(bst);
                } else if (diff < 0) {
                    super.rotateLeft(bst);
                } else {
                    super.rotateRight(bst);
                }
            }
            super.updateHeight(bst);
            bst = super.parent(bst);
        }
    }
    
    @Override
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }
    
    @Override
    protected int compare(E elem1, E elem2) {
        if (super.thereIsComparator()) {
            return super.comparator().compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }
    
    // Private methods
    private int height(Position<E> bst) {
        if (bst == null) {
            return -1;
        } else {
            return super.height(bst);
        }
    }

    private void rotateLeftRight(Position<E> bst) {
        super.rotateLeft(super.right(bst));
        super.rotateRight(bst);
    }

    private void rotateRightLeft(Position<E> bst) {
        super.rotateRight(super.left(bst));
        super.rotateLeft(bst);
    }
}
