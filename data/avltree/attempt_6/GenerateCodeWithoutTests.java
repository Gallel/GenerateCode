
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {

   //Constructor without parameters
   public AVLTree() {
   
   }
   
   //Constructor with a parameter
   public AVLTree(Comparator<E> comparator) {
   
   }
   
   //Add an item to the appropriate position, if possible
   public void add(E elemComp) {
   
   }
   
   //Method that restores, if necessary, the balance of the node tree
   //after each insertion or deletion
   protected void balance(Position<E> bst) {
   
   }
   
   //Protected method that compares received items
   protected int compare(E elem1, E elem2) {
   
   }
   
   //Delete the item, if you find it according to the comparator
   public E delete(E elemComp) {
   
   }
   
   //Retrieves an element of the tree
   public E get(E elemComp) {
   
   }
   
   //Overrides the superclass method to incorporate the attribute
   //height at the node, which allows you to check the balance of the AVL tree
   protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
   
   }
   
   //Method to check if there is a specific comparator
   public boolean thereIsComparator() {
   
   }
}
