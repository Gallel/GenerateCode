
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    // Constructor without parameters.
    AVLTree() {
        super();
    }

    // Constructor with a parameter.
    AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    // Add an item to the appropriate position, if possible.
    void add(E elemComp) {
        Position<E> position = binaryTreeAdd(elemComp);
        if (position != null) {
            balance(position);
        }
    }

    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion.
    protected void balance(Position<E> bst) {
        while (bst != null) {
            int balanceFactor = getBalanceFactor(bst);
            if (Math.abs(balanceFactor) > 1) {
                restructure(bst);
                return;
            }
            updateHeight(bst);
            bst = bst.getParent();
        }
    }

    // Protected method that compares received items.
    protected int compare(E elem1, E elem2) {
        if (comparator != null) {
            return comparator.compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }

    // Delete the item, if you find it according to the comparator.
    E delete(E elemComp) {
        E deletedElement = binaryTreeDelete(elemComp);
        balance(positionToDelete);
        return deletedElement;
    }

    // Retrieves an element of the tree.
    E get(E elemComp) {
        Position<E> pos = searchPosition(elemComp);
        if (pos != null) {
            return pos.getElement();
        } else {
            return null;
        }
    }

    // Overrides the superclass method to incorporate the attribute height at the node,
    // which allows you to check the balance of the AVL tree.
    @Override
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }

    // Method to check if there is a specific comparator.
    boolean thereIsComparator() {
        return (comparator != null);
    }
}
