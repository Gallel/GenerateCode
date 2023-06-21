
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
  
  private Comparator<E> comparator;
  
  // Constructor without parameters
  public AVLTree() {
    super();
  }
  
  // Constructor with a parameter
  public AVLTree(Comparator<E> comparator) {
    super();
    this.comparator = comparator;
  }
  
  // Add an item to the appropriate position, if possible
  public void add(E elemComp) {
    if(isEmpty()) {
      addRoot(elemComp);
    } else {
      Position<E> p = createNode(elemComp, null, null);
      addBST(p);
      balance(p);
    }
  }
  
  // Method that restores, if necessary, the balance of the node tree
  protected void balance(Position<E> bst) {
    if(bst != null) {
      int balance = getBalance(bst);
      if (balance == -2) {
        if (height(left(bst).getElem()) > height(right(left(bst)).getElem())) {
          bst = rotateDoubleWithLeftChild(bst);
        } else {
          bst = rotateWithLeftChild(bst);
        }
      } else if (balance == 2) {
        if (height(right(bst).getElem()) > height(left(right(bst)).getElem())) {
          bst = rotateDoubleWithRightChild(bst);
        } else {
          bst = rotateWithRightChild(bst);
        }
      }
      balance(parent(bst));
    }
  }
  
  // Protected method that compares received items
  protected int compare(E elem1, E elem2) {
    if(comparator != null) {
      return comparator.compare(elem1, elem2);
    } else {
      return ((Comparable<E>) elem1).compareTo(elem2);
    }
  }
  
  // Delete the item, if you find it according to the element comparator
  public E delete(E elemComp) {
    Position<E> bst = searchBST(elemComp, root());
    LinkedBinaryTreeImpl.NodeTree<E> p = deleteBST(bst);
    balance(p.getPosition());
    return p.getElem();
  }
  
  // Retrieves an element of the tree
  public E get(E elemComp) {
    return searchBST(elemComp, root()).getElem();
  }
  
  // Overrides the superclass method to incorporate the attribute height at the node
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLNodeTree<E>(parent, elemComp);
  }
  
  // Method to check if there is a specific comparator
  public boolean thereIsComparator() {
    return comparator != null;
  }
  
  private Position<E> createNode(E elemComp, Position<E> left, Position<E> right) {
    return new AVLNodeTree<E>(null, elemComp, left, right);
  }
  
  private int height(E elemComp) {
    AVLNodeTree<E> node = (AVLNodeTree<E>) checkPosition(searchBST(elemComp, root()));
    if (node == null) {
      return -1;
    }
    return node.getHeight();
  }
  
  private int getBalance(Position<E> g) {
    return height(left(g).getElem()) - height(right(g).getElem());
  }
  
  private Position<E> rotateWithLeftChild(Position<E> k2) {
    AVLNodeTree<E> k1 = (AVLNodeTree<E>) checkPosition(left(k2));
    setLeft(k2, right(k1));
    setRight(k1, k2);
    ((AVLNodeTree<E>) k2).setHeight(Math.max(height(left(k2).getElem()), height(right(k2).getElem())) + 1);
    ((AVLNodeTree<E>) k1).setHeight(Math.max(height(left(k1).getElem()), ((AVLNodeTree<E>) k2).getHeight()) + 1);
    return k1;
  }
  
  private Position<E> rotateWithRightChild(Position<E> k1) {
    AVLNodeTree<E> k2 = (AVLNodeTree<E>) checkPosition(right(k1));
    setRight(k1, left(k2));
    setLeft(k2, k1);
    ((AVLNodeTree<E>) k1).setHeight(Math.max(height(left(k1).getElem()), height(right(k1).getElem())) + 1);
    ((AVLNodeTree<E>) k2).setHeight(Math.max(height(right(k2).getElem()), ((AVLNodeTree<E>) k1).getHeight()) + 1);
    return k2;
  }
  
  private Position<E> rotateDoubleWithLeftChild(Position<E> k3) {
    setLeft(k3, rotateWithRightChild(left(k3)));
    return rotateWithLeftChild(k3);
  }
  
  private Position<E> rotateDoubleWithRightChild(Position<E> k1) {
    setRight(k1, rotateWithLeftChild(right(k1)));
    return rotateWithRightChild(k1);
  }

  // AVL node 
  protected static class AVLNodeTree<E> extends NodeTree<E> {
    private int height = 0;

    public AVLNodeTree(Position<E> parent, E elemComp) {
      super(parent, elemComp);
      height = 0;
    }

    public AVLNodeTree(Position<E> parent, E elemComp, Position<E> left, Position<E> right) {
      super(parent, elemComp, left, right);
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
