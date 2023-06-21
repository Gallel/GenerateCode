
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {

   public AVLTree() {

   }

   public AVLTree(Comparator<E> comparator) {

   }

   protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
      return null;
   }

   protected int compare(E elem1, E elem2) {
      return 0;
   }

   protected void balance(Position<E> bst) {

   }

   boolean thereIsComparator() {
      return false;
   }

   void add(E elemComp) {

   }

   E get(E elemComp) {
      return null;
   }

   E delete(E elemComp) {
      return null;
   }
}
