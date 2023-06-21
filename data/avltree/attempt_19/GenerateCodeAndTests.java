
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {

  // Constructor without parameters
  public AVLTree() {}
  
  // Constructor with a parameter
  public AVLTree(Comparator<E> comparator) {
    super(comparator);
  }
  
  // Add an item to the appropriate position, if possible
  public void add(E elemComp) {
    super.add(elemComp);
    balance(last);
  }
  
  // Method that restores, if necessary, the balance of the node tree after each insertion or deletion
  protected void balance(Position<E> bst) {
    while (!isRoot(bst)) {
      bst = bst.getParent();
      int diff = getHeight(left(bst)) - getHeight(right(bst));
      if (Math.abs(diff) > 1) {
        rebalance(bst);
      } else {
        updateHeight(bst);
      }
    }
  }
  
  // Protected method that compares received items
  protected int compare(E elem1, E elem2) {
    return super.compare(elem1, elem2);
  }
  
  // Delete the item, if you find it according to the comparator
  public E delete(E elemComp) {
    E elem = super.delete(elemComp);
    if (elem != null) {
      balance(last.getParent());
    }
    return elem;
  }
  
  // Retrieves an element of the tree
  public E get(E elemComp) {
    return super.get(elemComp);
  }
  
  // Overrides the superclass method to incorporate the attribute height at the node,
  // which allows you to check the balance of the AVL tree.
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLNode(parent, elemComp);
  }
  
  // Method to check if there is a specific comparator
  public boolean thereIsComparator() {
    return super.thereIsComparator();
  }
  
  // Private method that rebalances the tree
  private void rebalance(Position<E> bst) {
    Position<E> pivot;
    Position<E> y;
    Position<E> x;
    
    if (getHeight(left(bst)) > getHeight(right(bst))) {
      pivot = left(bst);
    } else {
      pivot = right(bst);
    }
    
    if (getHeight(left(pivot)) > getHeight(right(pivot))) {
      y = left(pivot);
    } else if (getHeight(left(pivot)) < getHeight(right(pivot))) {
      y = right(pivot);
    } else {
      if (bst == left(pivot)) {
        y = left(pivot);
      } else {
        y = right(pivot);
      }
    }
    
    if (pivot == left(bst) && y == left(pivot)) {
      x = left(y);
      rotateRight(bst);
    } else if (pivot == right(bst) && y == right(pivot)) {
      x = right(y);
      rotateLeft(bst);
    } else if (pivot == left(bst) && y == right(pivot)) {
      x = right(y);
      rotateLeft(pivot);
      rotateRight(bst);
    } else {
      x = left(y);
      rotateRight(pivot);
      rotateLeft(bst);
    }
    
    updateHeight(x);
    updateHeight(pivot);
    updateHeight(bst);
  }
  
  // Class that represents a node of the AVL tree
  private class AVLNode extends NodeTree<E> {
    private int height;
    
    public AVLNode(Position<E> parent, E elemComp) {
      super(parent, elemComp);
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
