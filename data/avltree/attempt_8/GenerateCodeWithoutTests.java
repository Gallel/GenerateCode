
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    // Constructor without parameters
    public AVLTree() {
        super();
    }
    
    // Constructor with a parameter
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    // Add an item to the appropriate position, if possible.
    public void add(E elemComp) {
        Position<E> bst = createNode(elemComp, null, null, null);
        super.add(bst);
        balance(bst);
    }
    
    // Method that restores, if necessary, the balance of the node tree
    // after each insertion or deletion.
    protected void balance(Position<E> bst) {
        if (bst != null) {
            int factor = treeHeight(left(bst)) - treeHeight(right(bst));
            if (Math.abs(factor) > 1) {
                if (factor > 1) {
                    if (treeHeight(left(left(bst))) >= treeHeight(right(left(bst)))) {
                        bst = rightRotation(bst);
                    } else {
                        bst = doubleRightRotation(bst);
                    }
                } else {
                    if (treeHeight(right(right(bst))) >= treeHeight(left(right(bst)))) {
                        bst = leftRotation(bst);
                    } else {
                        bst = doubleLeftRotation(bst);
                    }
                }
                balance(parent(bst));
            }
            updateHeight(bst);
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
    public E delete(E elemComp) {
        Position<E> p = search(elemComp);
        if (p != null && compare(p.get(), elemComp) == 0) {
            Position<E> bst = deleteNode(p);
            balance(bst);
            return p.get();
        }
        return null;
    }
    
    // Retrieves an element of the tree.
    public E get(E elemComp) {
        Position<E> p = search(elemComp);
        if (p != null && compare(p.get(), elemComp) == 0) {
            return p.get();
        }
        return null;
    }
    
    // Overrides the superclass method to incorporate the attribute
    // height at the node, which allows you to check the balance of the AVL tree.
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new AVLNode<E>(this, parent, elemComp);
    }
    
    // Method to check if there is a specific comparator.
    public boolean thereIsComparator() {
        return comparator != null;
    }
    
    // Inner AVLNode class.
    private static class AVLNode<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
        private int height;
        
        public AVLNode(LinkedBinaryTreeImpl<E> tree, Position<E> parent, E element) {
            super(tree, parent, element);
            height = 1;
        }
        
        public int getHeight() {
            return height;
        }
        
        public void setHeight(int height) {
            this.height = height;
        }
    }
}
