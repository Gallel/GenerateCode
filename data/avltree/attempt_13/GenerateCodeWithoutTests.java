
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    /**
     * Constructor without parameters.
     */
    public AVLTree() {
        super();
    }

    /**
     * Constructor with a parameter.
     */
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Add an item to the appropriate position, if possible.
     */
    public void add(E elemComp) {
        super.add(elemComp);
        balance(last);
    }

    /**
     * Delete the item, if you find it according to the comparator.
     */
    public E delete(E elemComp) {
        E deleted = super.delete(elemComp);
        if (deleted != null) {
            balance(last);
        }
        return deleted;
    }

    /**
     * Retrieves an element of the tree.
     */
    public E get(E elemComp) {
        return super.get(elemComp);
    }

    /**
     * Overrides the superclass method to incorporate the attribute height
     * at the node, which allows you to check the balance of the AVL tree.
     */
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<>(parent, elemComp);
    }

    /**
     * Method to check if there is a specific comparator.
     */
    public boolean thereIsComparator() {
        return super.thereIsComparator();
    }

    /**
     * Method that restores, if necessary, the balance of the node tree
     * after each insertion or deletion.
     */
    protected void balance(Position<E> bst) {
        int balanceFactor = super.balanceFactor(bst);

        if (Math.abs(balanceFactor) > 1) {
            Position<E> child = null;
            if (balanceFactor > 1) {
                if (super.balanceFactor(super.right(bst)) < 0) {
                    super.rotateRight(super.right(bst));
                }
                child = super.rotateLeft(bst);
            } else if (balanceFactor < -1) {
                if (super.balanceFactor(super.left(bst)) > 0) {
                    super.rotateLeft(super.left(bst));
                }
                child = super.rotateRight(bst);
            }
            balance(child);
            balance(bst);
        } else if (!super.isRoot(bst)) {
            balance(super.parent(bst));
        }
    }

    /**
     * Protected method that compares received items.
     */
    protected int compare(E elem1, E elem2) {
        if (super.thereIsComparator()) {
            return super.getComparator().compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }

    /**
     * Class that defines a node in an AVL tree.
     */
    protected class NodeTree<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
        private int height;

        public NodeTree(Position<E> parent, E elemComp) {
            super(parent, elemComp);
            this.height = 1;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
