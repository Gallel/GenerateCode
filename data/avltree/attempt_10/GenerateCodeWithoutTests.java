
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
        Position<E> node = treeAdd(elemComp);
        balance(node);
    }
    
    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion.
    protected void balance(Position<E> bst) {
        int factor = 0;
        Position<E> parent = bst;
        while (!isRoot(parent)) {
            parent = parent.getParent();
            int balanceFactor = getBF(parent);
            if (Math.abs(balanceFactor) > 1) {
                rebalance(parent);
                break;
            }
            factor = balanceFactor;
        }
        updateHeight(bst);
    }
    
    // Protected method that compares received items.
    protected int compare(E elem1, E elem2) {
        if (thereIsComparator()) {
            return getComparator().compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }
    
    // Delete the item, if you find it according to the comparator.
    public E delete(E elemComp) {
        Position<E> node = search(elemComp);
        if (node == null) {
            return null;
        }
        E result = node.getElement();
        Position<E> replacement = null;
        if (numChildren(node) == 2) {
            replacement = treePredecessor(node);
            replace(node, replacement.getElement());
            node = replacement;
        }
        remove(node);
        balance(node.getParent());
        return result;
    }
    
    // Retrieves an element of the tree.
    public E get(E elemComp) {
        Position<E> node = search(elemComp);
        if (node != null) {
            return node.getElement();
        } else {
            return null;
        }
    }
    
    // Overrides the superclass method to incorporate the attribute height at the node,
    // which allows you to check the balance of the AVL tree.
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        LinkedBinaryTreeImpl.NodeTree<E> node = new LinkedBinaryTreeImpl.NodeTree<E>(parent, elemComp);
        node.setHeight(1);
        return node;
    }
    
    // Method to check if a specific comparator exists.
    public boolean thereIsComparator() {
        return getComparator() != null;
    }
}
