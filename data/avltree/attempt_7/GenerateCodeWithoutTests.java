
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    // Constructor without parameters
    public AVLTree() {
        super();
    }
    
    // Constructor with a parameter
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    // Method to add an item to the appropriate position, if possible
    public void add(E elemComp) {
        Position<E> node = treeSearch(elemComp);
        if (node.getElement().equals(elemComp)) {
            return; // Element already exists in tree
        }
        addWithParent(node, elemComp); // Adds element to tree
        balance(node); // Restores balance of tree, if necessary
    }
    
    // Protected method to restore balance of node tree after insertion or deletion
    protected void balance(Position<E> bst) {
        if (bst == null) {
            return; // Base case
        }
        if (height(getLeft(bst)) > u * height(getRight(bst))) {
            // Left subtree too tall, right rotation necessary
            if (height(getLeft(getLeft(bst))) >= height(getRight(getLeft(bst)))) {
                // Left-left case
                bst = rotateRight(bst);
            } else {
                // Left-right case
                bst = doubleRotateLeftRight(bst);
            }
        } else if (height(getRight(bst)) > u * height(getLeft(bst))) {
            // Right subtree too tall, left rotation necessary
            if (height(getRight(getRight(bst))) >= height(getLeft(getRight(bst)))) {
                // Right-right case
                bst = rotateLeft(bst);
            } else {
                // Right-left case
                bst = doubleRotateRightLeft(bst);
            }
        }
        // Recursively balance remaining nodes
        balance(getParent(bst));
    }
    
    // Protected method to compare received items
    protected int compare(E elem1, E elem2) {
        if (comparator != null) {
            return comparator.compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }
    
    // Method to delete an item, if found according to the comparator
    public E delete(E elemComp) {
        Position<E> node = treeSearch(elemComp);
        if (!node.getElement().equals(elemComp)) {
            return null; // Element not found in tree
        }
        Position<E> parent = getParent(node);
        E deletedElem = deleteNode(node);
        balance(parent); // Restores balance of tree, if necessary
        return deletedElem;
    }
    
    // Method to retrieve an element of the tree
    public E get(E elemComp) {
        Position<E> node = treeSearch(elemComp);
        if (!node.getElement().equals(elemComp)) {
            return null; // Element not found in tree
        }
        return node.getElement();
    }
    
    // Overridden method from superclass to incorporate height attribute at node
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }
    
    // Method to check if there is a specific comparator
    public boolean thereIsComparator() {
        return comparator != null;
    }
}
