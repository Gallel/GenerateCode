
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
  
  //Constructor without parameters
  public AVLTree() {}
  
  //Constructor with a parameter
  public AVLTree(Comparator<E> comparator) {
    super(comparator);
  }
  
  //Add an item to the appropriate position, if possible
  public void add(E elemComp) {
    Position<E> node = createNode(elemComp);
    super.add(node);
    balance(node);
  }
  
  //Method that restores, if necessary, the balance of the node tree
  //after each insertion or deletion
  protected void balance(Position<E> bst) {
    if(bst == null) {
      return;
    }
    int value = getBalance(bst);
    if(value <= 1 || value >= -1) {
      balance(parent(bst));
    } else {
      if(value > 1 && getBalance(left(bst)) >= 0) {
        rightRotate(bst);
        balance(parent(bst));
      } else if(value > 1 && getBalance(left(bst)) < 0) {
        leftRotate(left(bst));
        rightRotate(bst);
        balance(parent(bst));
      } else if(value < -1 && getBalance(right(bst)) <= 0) {
        leftRotate(bst);
        balance(parent(bst));
      } else if(value < -1 && getBalance(right(bst)) > 0) {
        rightRotate(right(bst));
        leftRotate(bst);
        balance(parent(bst));
      }
    }
  }
  
  //Protected method that compares received items
  protected int compare(E elem1, E elem2) {
    if (comparator != null) {
      return comparator.compare(elem1, elem2);
    } else {
      return ((Comparable<E>) elem1).compareTo(elem2);
    }
  }
  
  //Delete the item, if you find it according to the comparator
  public E delete(E elemComp) {
    Position<E> p = search(elemComp, root);
    if (p != null) {
      Position<E> q = delete(p);
      return getElement(q);
    }
    return null;
  }
  
  //Retrieves an element of the tree
  public E get(E elemComp) {
    Position<E> node = search(elemComp, root);
    if (node == null) {
      return null;
    }
    return getElement(node);
  }
  
  //Overrides the superclass method to incorporate the attribute
  //height at the node, which allows you to check the balance of the AVL tree
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLNode<E>(parent, elemComp);
  }
  
  //Method to check if there is a specific comparator
  public boolean thereIsComparator() {
    return comparator != null;
  }
  
  //Calculates the balance of a node
  protected int getBalance(Position<E> node) {
    if (node == null) {
      return 0;
    }
    int leftHeight = (left(node) == null) ? 0 : height((AVLNode<E>) left(node));
    int rightHeight = (right(node) == null) ? 0 : height((AVLNode<E>) right(node));
    return leftHeight - rightHeight;
  }
  
  //Performs the right rotation
  protected void rightRotate(Position<E> node) {
    Position<E> leftNode = left(node);
    Position<E> parent = parent(node);
    replace(node, leftNode);
    setLeft(node, right(leftNode));
    setRight(leftNode, node);
  }
  
  //Performs the left rotation
  protected void leftRotate(Position<E> node) {
    Position<E> rightNode = right(node);
    Position<E> parent = parent(node);
    replace(node, rightNode);
    setRight(node, left(rightNode));
    setLeft(rightNode, node);
  }
  
  //Node to incorporate the height attribute
  protected static class AVLNode<E> extends LinkedBinaryTreeImpl.NodeTree<E>{
    int height;
    
    AVLNode(Position<E> parent, E element) {
      super(parent, element);
      height = 1;
    }
  }
}
