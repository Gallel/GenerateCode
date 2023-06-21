
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    // Constructor without parameters
    public AVLTree() {
        super();
    }
    
    // Constructor with a parameter. Param: java.util.Comparator
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    // Add an item to the appropriate position, if possible. Param: E element
    public void add(E elemComp) {
        // Implementation goes here
    }
    
    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion.
    protected void balance(Position<E> bst) {
        // Implementation goes here
    }
    
    // Protected method that compares received items. Params: E element1, E element2
    protected int compare(E elem1, E elem2) {
        // Implementation goes here
    }
    
    // Delete the item, if you find it according to the comparator. Param: E element
    public E delete(E elemComp) {
        // Implementation goes here
    }
    
    // Retrieves an element of the tree. Param: E element
    public E get(E elemComp) {
        // Implementation goes here
    }
    
    // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree.
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        // Implementation goes here
    }
    
    // Method to check if there is a specific comparator. Return: boolean
    public boolean thereIsComparator() {
        // Implementation goes here
        return false;
    }
}
