
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {

  // Constructor without parameters
  public AVLTree() {}

  // Constructor with a parameter.
  public AVLTree(Comparator<E> comparator) {
    super(comparator);
  }

  // Add an item to the appropriate position, if possible.
  public void add(E elemComp) {
    super.add(elemComp);
    balance(getNode(get(elemComp)));
  }

  // Method that restores, if necessary, the balance of the node tree after each insertion or deletion.
  protected void balance(Position<E> bst) {
    if (bst == null) {
      return;
    }
    if (balanceFactor(bst) > 1) {
      if (balanceFactor(bst.getLeft()) > 0) {
        rotateRight(bst);
      } else {
        rotateLeftRight(bst);
      }
    } else if (balanceFactor(bst) < -1) {
      if (balanceFactor(bst.getRight()) < 0) {
        rotateLeft(bst);
      } else {
        rotateRightLeft(bst);
      }
    }
    balance(bst.getParent());
  }

  // Protected method that compares received items.
  protected int compare(E elem1, E elem2) {
    if (thereIsComparator()) {
      return super.compare(elem1, elem2);
    } else {
      return ((Comparable<E>) elem1).compareTo(elem2);
    }
  }

  // Delete the item, if you find it according to the comparator.
  public E delete(E elemComp) {
    E removed = super.delete(elemComp);
    if (removed != null) {
      balance(getRoot());
    }
    return removed;
  }

  // Retrieves an element of the tree.
  public E get(E elemComp) {
    if (thereIsComparator()) {
      return super.get(elemComp);
    } else {
      return binarySearch(elemComp, getRoot());
    }
  }

  // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree.
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLNode<E>(parent, elemComp);
  }

  // Method to check if there is a specific comparator.
  public boolean thereIsComparator() {
    return comparator != null;
  }

  // Protected method that returns the balance factor for a node.
  protected int balanceFactor(Position<E> bst) {
    return height(getLeftChild(bst)) - height(getRightChild(bst));
  }

  // Performs a left rotation on the subtree rooted at bst.
  protected void rotateLeft(Position<E> bst) {
    Position<E> grandParent = getParent(getParent(bst));
    Position<E> parent = getParent(bst);
    Position<E> rightChild = getRightChild(bst);

    if (grandParent != null) {
      if (grandParent.getLeft() == parent) {
        setLeftChild(grandParent, rightChild);
      } else {
        setRightChild(grandParent, rightChild);
      }
    } else {
      setRoot(rightChild);
    }

    setParent(getLeftChild(rightChild), parent);
    setParent(parent, rightChild);
    setRightChild(parent, getLeftChild(rightChild));
    setLeftChild(rightChild, parent);
    setParent(rightChild, grandParent);
  }

  // Performs a right rotation on the subtree rooted at bst.
  protected void rotateRight(Position<E> bst) {
    Position<E> grandParent = getParent(getParent(bst));
    Position<E> parent = getParent(bst);
    Position<E> leftChild = getLeftChild(bst);

    if (grandParent != null) {
      if (grandParent.getLeft() == parent) {
        setLeftChild(grandParent, leftChild);
      } else {
        setRightChild(grandParent, leftChild);
      }
    } else {
      setRoot(leftChild);
    }

    setParent(getRightChild(leftChild), parent);
    setParent(parent, leftChild);
    setLeftChild(parent, getRightChild(leftChild));
    setRightChild(leftChild, parent);
    setParent(leftChild, grandParent);
  }

  // Performs a left-right rotation on the subtree rooted at bst.
  protected void rotateLeftRight(Position<E> bst) {
    rotateLeft(getLeftChild(bst));
    rotateRight(bst);
  }

  // Performs a right-left rotation on the subtree rooted at bst.
  protected void rotateRightLeft(Position<E> bst) {
    rotateRight(getRightChild(bst));
    rotateLeft(bst);
  }

  // AVL specific node
  protected static class AVLNode<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
    protected int height;

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

