
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    // Constructor without parameters
    public AVLTree() {}
    
    // Constructor with a parameter
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    // Add an item to the appropriate position, if possible.
    public void add(E elemComp) {
        Position<E> node = createNode(elemComp, null, null, null);
        addBST(node);
        balance(node);
    }
    
    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion.
    protected void balance(Position<E> bst) {
        while (bst != null) {
            int balanceFactor = height(right(bst)) - height(left(bst));
            if (balanceFactor > 1) {
                if (height(right(right(bst))) >= height(left(right(bst)))) {
                    bst = rotateLeft(bst);
                } else {
                    bst = doubleRotateRightLeft(bst);
                }
            } else if (balanceFactor < -1) {
                if (height(left(left(bst))) >= height(right(left(bst)))) {
                    bst = rotateRight(bst);
                } else {
                    bst = doubleRotateLeftRight(bst);
                }
            }
            bst = parent(bst);
        }
    }
    
    // Protected method that compares received items.
    protected int compare(E elem1, E elem2) {
        if (super.comparator != null) {
            return super.comparator.compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }
    
    // Delete the item, if you find it according to the comparator.
    public E delete(E elemComp) {
        Position<E> node = findNode(elemComp);
        if (node == null) {
            return null;
        }
        E temp = node.element();
        if (isLeaf(node)) {
            remove(node);
        } else if (left(node) == null) {
            remove(node);
        } else if (right(node) == null) {
            remove(node);
        } else {
            Position<E> heir = maximoEnSubarbol(left(node));
            E e = node.element();
            node.setElement(heir.element());
            heir.setElement(e);
            remove(heir);
        }
        balance(node);
        return temp;
    }
    
    // Retrieves an element of the tree.
    public E get(E elemComp) {
        Position<E> node = findNode(elemComp);
        if (node != null) {
            return node.element();
        }
        return null;
    }
    
    // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree.
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new Node(parent, elemComp);
    }
    
    // Method to check if there is a specific comparator.
    public boolean thereIsComparator() {
        return (super.comparator != null);
    }
    
    // Class for node of AVL tree
    protected static class Node<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
        protected int height;
        
        public Node(Position<E> p, E e) {
            super(p, e);
            height = 0;
        }
        
        public int getHeight() {
            return height;
        }
        
        public void setHeight(int h) {
            height = h;
        }
    }
}
