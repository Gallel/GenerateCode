
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
  
  private Comparator<E> comparator;
  
  // Constructor without parameters
  public AVLTree() {}
  
  // Constructor with a parameter
  public AVLTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }
  
  // Add an item to the appropriate position, if possible
  public void add(E elemComp) {
    LinkedBinaryTreeImpl.NodeTree<E> newNode = newNode(null, elemComp);
    if (this.isEmpty()) {
      this.createRoot(newNode);
    } else {
      LinkedBinaryTreeImpl.NodeTree<E> parent = searchAdd(this.root(), elemComp);
      if (compare(newNode.getElement(), parent.getElement()) == 0) {
        return;
      } else {
        if (compare(newNode.getElement(),parent.getElement()) < 0) {
          this.createLeft(newNode, parent);
        } else {
          this.createRight(newNode, parent);
        }
        balance(parent);
      }
    }
  }
  
  // Method that restores, if necessary, the balance of the node tree after each insertion or deletion
  protected void balance(Position<E> bst) {
    while (!bst.equals(this.root())) {
      bst = bst.getParent();
      int balanceFactor = height(rightChild(bst)) - height(leftChild(bst));
      if (Math.abs(balanceFactor) <= 1) {
        setHeight(bst);
      } else {
        if (balanceFactor > 0) {
          if (height(rightChild(rightChild(bst))) >= height(leftChild(rightChild(bst)))) {
            bst = rotateLeft(bst); // LL
          } else {
            bst = rotateRightLeft(bst); // RL
          }
        } else {
          if (height(leftChild(leftChild(bst))) >= height(rightChild(leftChild(bst)))) {
            bst = rotateRight(bst); // RR
          } else {
            bst = rotateLeftRight(bst); // LR
          }
        }
        setHeight(bst);
      }
    }
  }
  
  // Protected method that compares received items
  protected int compare(E elem1, E elem2) {
    if (comparator == null) {
      return ((Comparable<E>) elem1).compareTo(elem2);
    } else {
      return comparator.compare(elem1, elem2);
    }
  }
  
  // Delete the item, if you find it according to the comparator
  public E delete(E elemComp) {
    LinkedBinaryTreeImpl.NodeTree<E> nodeToDelete = searchNode(this.root(), elemComp);
    if (nodeToDelete != null) {
      E nodeToDeleteElement = nodeToDelete.getElement();
      deleteNode(nodeToDelete);
      return nodeToDeleteElement;
    }
    return null;
  }
  
  // Retrieves an element of the tree
  public E get(E elemComp) {
    LinkedBinaryTreeImpl.NodeTree<E> node = searchNode(this.root(), elemComp);
    if (node != null) {
      return node.getElement();
    }
    return null;
  }
  
  // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new NodeTree<E>(parent, elemComp);
  }
  
  // Method to check if there is a specific comparator
  public boolean thereIsComparator() {
    return comparator != null;
  }
}
