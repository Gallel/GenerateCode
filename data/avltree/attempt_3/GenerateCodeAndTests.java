
import java.util.Comparator;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E> {

    // Constructor without parameters.
    public AVLTree() {
        super();
    }

    // Constructor with a parameter.
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    // Add an item to the appropriate position, if possible.
    public void add(E elemComp) {
        Position<E> newPosition = searchAddPosition(elemComp);
        createNode(newPosition, elemComp);
    }

    // Method that restores, if necessary, the balance of the node tree
    // after each insertion or deletion.
    protected void balance(Position<E> bst) {
        while (bst != null) {
            int balanceFactor = height(getLeft(bst)) - height(getRight(bst));

            if (balanceFactor > 1) {
                if (height(getLeft(getLeft(bst))) >= height(getRight(getLeft(bst)))) {
                    bst = rotateRight(bst);
                } else {
                    bst = doubleRotateLeftRight(bst);
                }

            } else if (balanceFactor < -1) {
                if (height(getRight(getRight(bst))) >= height(getLeft(getRight(bst)))) {
                    bst = rotateLeft(bst);
                } else {
                    bst = doubleRotateRightLeft(bst);
                }
            }
            recalculateHeight(bst);
            bst = getParent(bst);
        }
    }

    // Protected method that compares received items.
    protected int compare(E elem1, E elem2) {
        return elem1.compareTo(elem2);
    }

    // Delete the item, if you find it according to the comparator.
    public E delete(E elemComp) {
        Position<E> p = searchPosition(elemComp);
        // ...
        return null;
    }

    // Retrieves an element of the tree.
    public E get(E elemComp) {
        Position<E> p = searchPosition(elemComp);
        if (p == null) {
            return null;
        } else {
            return p.getElement();
        }
    }

    // Overrides the superclass method to incorporate the attribute
    // height at the node, which allows you to check the balance of the AVL tree.
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<>(parent, elemComp);
    }

    // Method to check if there is a specific comparator.
    public boolean thereIsComparator() {
        return comparator != null;
    }
}
