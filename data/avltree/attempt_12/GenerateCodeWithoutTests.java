
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
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
        Position<E> nodo = createNode(elemComp, null, null, null);
        insert(nodo);
        balance(nodo);
    }
    
    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion.
    protected void balance(Position<E> bst) {
        // Code to balance the AVL tree after insertion or deletion
    }
    
    // Protected method that compares received items.
    protected int compare(E elem1, E elem2) {
        // Code to compare two items
    }
    
    // Delete the item, if you find it according to the comparator.
    public E delete(E elemComp) {
        Position<E> nodo = searchNode(elemComp);
        return delete(nodo);
    }
    
    // Retrieves an element of the tree.
    public E get(E elemComp) {
        Position<E> nodo = searchNode(elemComp);
        return (nodo == null ? null : nodo.getElement());
    }
    
    // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree.
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new AVLNode<E>(parent, elemComp);
    }
    
    // Method to check if there is a specific comparator.
    public boolean thereIsComparator() {
        return comparator != null;
    }
    
    // Code to implement AVLNode class as a nested class of AVLTree
    
    private static class AVLNode<E> extends Node<E> {
        private int height;
        
        public AVLNode(Position<E> parent, E elemComp) {
            super(parent, elemComp);
            height = 0;
        }
        
        public int getHeight() {
            return height;
        }
        
        public void setHeight(int height) {
            this.height = height;
        }
    }
}
