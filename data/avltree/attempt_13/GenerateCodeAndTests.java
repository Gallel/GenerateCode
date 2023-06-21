
import java.util.Comparator;

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
    super.add(elemComp);
    balance(this.lastV);
  }
  
  // Method that restores, if necessary, the balance of the node tree
  protected void balance(Position<E> bst) {
    int balanceFactor = super.height(super.left(bst)) - super.height(super.right(bst));
    if (Math.abs(balanceFactor) > 1) {
      Position<E> nodeToFix = super.tallerChild(super.tallerChild(bst));
      if (super.root() != bst) {
        if (super.left(super.parent(bst)) == bst) {
          super.attachLeft(super.parent(bst), nodeToFix);
        } else {
          super.attachRight(super.parent(bst), nodeToFix);
        }
      } else {
        super.setRoot(nodeToFix);
      }
      if (nodeToFix == super.left(bst)) {
        super.attachRight(nodeToFix, bst);
      } else {
        super.attachLeft(nodeToFix, bst);
      }
      super.setHeight(super.left(bst));
      super.setHeight(super.right(bst));
      super.setHeight(bst);
    }
    if (super.parent(bst) != null) {
      balance(super.parent(bst));
    }
  }
  
  // Protected method that compares received items
  protected int compare(E elem1, E elem2) {
    if (super.comparator() != null) {
      return super.comparator().compare(elem1, elem2);
    } else {
      return ((Comparable<E>) elem1).compareTo(elem2);
    }
  }
  
  // Delete the item, if you find it according to the comparator
  public E delete(E elemComp) {
    Position<E> bst = super.search(elemComp, super.root());
    if (bst == null) {
      return null;
    }
    E elemToDelete = bst.getElement();
    if (super.left(bst) != null && super.right(bst) != null) {
      Position<E> successor = super.successor(bst);
      super.swapElements(bst, successor);
      bst = successor;
    }
    Position<E> leaf = (super.left(bst) != null ? super.left(bst) : super.right(bst));
    if (super.root() == leaf) {
      super.setRoot(null);
    } else {
      Position<E> parent = super.parent(bst);
      if (super.left(parent) == bst) {
        super.disconnect(super.left(parent));
      } else {
        super.disconnect(super.right(parent));
      }
      super.setHeight(parent);
      balance(parent);
    }
    return elemToDelete;
  }
  
  // Retrieves an element of the tree
  public E get(E elemComp) {
    Position<E> bst = super.search(elemComp, super.root());
    if (bst != null) {
      return bst.getElement();
    } else {
      return null;
    }
  }
  
  // Overrides the superclass method to incorporate the attribute
  // height at the node, which allows you to check the balance of the AVL tree
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLNode<>(parent, elemComp);
  }
  
  // Method to check if there is a specific comparator
  public boolean thereIsComparator() {
    return super.comparator() != null;
  }
  
  // AVLNode class to represent a node in the AVL tree with its height
  private static class AVLNode<E> extends NodeTree<E> {
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
