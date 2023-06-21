
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    // Constructor without parameters
    public AVLTree() {
        super();
    }

    // Constructor with a parameter
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    // Add an item to the appropriate position, if possible
    public void add(E elemComp) {
        add(elemComp);
        balance(actionNode());
    }

    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion
    protected void balance(Position<E> bst) {
        while (!isRoot(bst)) {
            bst = parent(bst);
            if (Math.abs(balanceFactor(bst)) > 1) {
                int olderHeight, newerHeight, tempHeight;
                Position<E> newSubroot = tallChild(tallChild(bst));
                olderHeight = height(newSubroot);
                newerHeight = height(tallChild(newSubroot)) >= height(shortChild(newSubroot)) ? height(tallChild(newSubroot)) : height(shortChild(newSubroot));
                if (bst == root()) {
                    setRoot(newSubroot);
                    parent(newSubroot, null);
                } else if (bst == left(parent(bst))) {
                    attachLeft(parent(bst), newSubroot);
                } else {
                    attachRight(parent(bst), newSubroot);
                }
                if (newSubroot == left(bst)) {
                    tempHeight = height(right(newSubroot));
                    setHeight(right(newSubroot), newerHeight);
                    setHeight(left(newSubroot), newerHeight - 1 - tempHeight);
                } else {
                    tempHeight = height(left(newSubroot));
                    setHeight(left(newSubroot), newerHeight);
                    setHeight(right(newSubroot), newerHeight - 1 - tempHeight);
                }
                setHeight(newSubroot, newerHeight);
                bst = newSubroot;
            }
            setHeight(bst, 1 + Math.max(height(left(bst)), height(right(bst))));
        }
    }

    // Protected method that compares received items
    protected int compare(E elem1, E elem2) {
        return elem1.compareTo(elem2);
    }

    // Delete the item, if you find it according to the comparator
    public E delete(E elemComp) {
        E toReturn = super.delete(elemComp);
        balance(actionNode());
        return toReturn;
    }

    // Retrieves an element of the tree
    public E get(E elemComp) {
        return super.get(elemComp).getElement();
    }

    // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree
    @Override
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<>(parent, elemComp);
    }

    // Method to check if there is a specific comparator
    public boolean thereIsComparator() {
        return comparator != null;
    }
}
