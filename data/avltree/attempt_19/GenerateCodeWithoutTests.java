
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    // Constructors
    
    public AVLTree() { }
    
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    // Public methods
    
    public void add(E elemComp) {
        // Add item to the tree
    }
    
    // Protected methods
    
    protected void balance(Position<E> bst) {
        // Restore balance of node in the tree
    }
    
    protected int compare(E elem1, E elem2) {
        // Compare received items
    }
    
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        // Create new node with height attribute
    }
    
    // Private methods
    
    private E delete(E elemComp) {
        // Delete item from the tree
    }
    
    // Public getters
    
    public E get(E elemComp) {
        // Retrieve element from the tree
    }
    
    public boolean thereIsComparator() {
        // Check if there is a specific comparator
    }
}
