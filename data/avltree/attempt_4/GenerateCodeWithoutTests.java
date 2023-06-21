
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
    Position<E> bst = createNode(elemComp);
    super.add(bst);
    balance(bst);
  }

  // Method that restores, if necessary, the balance of the node tree after each insertion or deletion
  protected void balance(Position<E> bst) {
    BinaryNodeTree<E> node = checkPosition(bst);

    if (isHeavyLeft(node)) {
      if (balanceFactor(node.getLeft()) < 0) {
        rotateLeft(node.getLeft());
      }
      rotateRight(node);
    } else if (isHeavyRight(node)) {
      if (balanceFactor(node.getRight()) > 0)  {
        rotateRight(node.getRight());
      }
      rotateLeft(node);
    }
    updateHeight(node);
  }

  // Protected method that compares received items
  protected int compare(E elem1, E elem2) {
    if (thereIsComparator()) {
      return super.compare(elem1, elem2);
    }
    return ((Comparable<E>) elem1).compareTo(elem2);
  }

  // Delete the item, if you find it according to the comparator
  public E delete(E elemComp)  {
    E toReturn = super.delete(elemComp);
    balance(root());
    return toReturn;
  }

  // Retrieves an element of the tree
  public E get(E elemComp) {
    Position<E> p = findPosition(elemComp);
    if (p == null) {
      return null;
    }
    return p.getElement();
  }

  // Overrides the superclass method to incorporate the attribute height at the node,
  // which allows you to check the balance of the AVL tree
  protected BinaryNodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLBinaryNode<>(parent, elemComp);
  }

  // Method to check if there is a specific comparator
  public boolean thereIsComparator() {
    return super.thereIsComparator();
  }

  private int balanceFactor(BinaryNodeTree<E> node)  {
    return height(node.getLeft()) - height(node.getRight());
  }

  private boolean isHeavyLeft(BinaryNodeTree<E> node) {
    return balanceFactor(node) > 1;
  }

  private boolean isHeavyRight(BinaryNodeTree<E> node) {
    return balanceFactor(node) < -1;
  }

  private void rotateLeft(BinaryNodeTree<E> node) {
    BinaryNodeTree<E> rightChild = node.getRight();
    replace(node, rightChild);

    node.setRight(rightChild.getLeft());

    if (rightChild.getLeft() != null) {
      rightChild.getLeft().setParent(node);
    }

    rightChild.setLeft(node);
    node.setParent(rightChild);

    updateHeight(node);
    updateHeight(rightChild);
  }

  private void rotateRight(BinaryNodeTree<E> node) {
    BinaryNodeTree<E> leftChild = node.getLeft();
    replace(node, leftChild);

    node.setLeft(leftChild.getRight());

    if (leftChild.getRight() != null) {
      leftChild.getRight().setParent(node);
    }

    leftChild.setRight(node);
    node.setParent(leftChild);

    updateHeight(node);
    updateHeight(leftChild);
  }

  private void updateHeight(BinaryNodeTree<E> node) {
    node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
  }
  
  private static class AVLBinaryNode<E> extends LinkedBinaryTreeImpl.BinaryNodeTree<E> {

    private int height = 1;

    public AVLBinaryNode(Position<E> position, E element) {
      super(position, element);
    }

    public int getHeight() {
      return height;
    }

    public void setHeight(int height) {
      this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || getClass() != obj.getClass()) return false;
      AVLBinaryNode<?> that = (AVLBinaryNode<?>) obj;
      return element.equals(that.element);
    }

    @Override
    public int hashCode() {
      return element.hashCode();
    }
  }
}
