
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
  // Constructor without parameters
  public AVLTree() { }

  // Constructor with a parameter
  public AVLTree(Comparator<E> comparator) {
    super(comparator);
  }

  // Add an item to the appropriate position, if possible
  public void add(E elemComp) {
    this.add(elemComp);
    this.balance(this.getPosition(elemComp));
  }

  //Method that restores, if necessary, the balance of the node tree
  //after each insertion or deletion.
  protected void balance(Position<E> bst) {
    if (bst != null) {
      int leftHeight = this.getHeight(this.getLeft(bst));
      int rightHeight = this.getHeight(this.getRight(bst));
      if (Math.abs(leftHeight - rightHeight) > 1) {
        if (leftHeight > rightHeight) {
          Position<E> leftChild = this.getLeft(bst);
          if (this.getHeight(this.getLeft(leftChild)) > this.getHeight(this.getRight(leftChild))) {
            this.rightRotation(leftChild);
          }
          this.leftRotation(bst);
        } else {
          Position<E> rightChild = this.getRight(bst);
          if (this.getHeight(this.getRight(rightChild)) > this.getHeight(this.getLeft(rightChild))) {
            this.leftRotation(rightChild);
          }
          this.rightRotation(bst);
        }
      }
      this.balance(this.getParent(bst));
    }
  }

  //Protected method that compares received items.
  protected int compare(E elem1, E elem2) {
    if (this.getComparator() != null) {
      return this.getComparator().compare(elem1, elem2);
    } else {
      return ((Comparable<E>) elem1).compareTo(elem2);
    }
  }

  //Delete the item, if you find it according to the comparator.
  public E delete(E elemComp) {
    Position<E> position = this.getPosition(elemComp);
    if (position == null) {
      return null;
    }
    E result = this.getRoot().getElement();
    if (position != this.getRoot()) {
      this.setRoot(this.getParent(position));
    }
    if (this.getLeft(position) != null && this.getRight(position) != null) {
      Position<E> newNode = this.middleSubtree(this.getRight(position));
      result = newNode.getElement();
      position.setElement(result);
      position = newNode;
    }
    Position<E> child = this.getLeft(position);
    if (child == null) {
      child = this.getRight(position);
    }
    this.remove(position);
    this.balance(this.getParent(child));
    return result;
  }

  //Retrieves an element of the tree.
  public E get(E elemComp) {
    Position<E> node = this.getPosition(elemComp);
    if (node != null) {
      return node.getElement();
    } else {
      return null;
    }
  }
  
  //Overrides the superclass method to incorporate the attribute
  //height at the node, which allows you to check the balance of the AVL tree.
  @Override
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLNodeTree<E>(this, parent, elemComp);
  }

  //Method to check if there is a specific comparator.
  public boolean thereIsComparator() {
    return this.getComparator() != null;
  }

  //Inner class to define the AVLNodeTree type
  private static class AVLNodeTree<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
    private int height;

    public AVLNodeTree(Tree<E> tree, Position<E> position, E element) {
      super(tree, position, element);
      height = 1;
    }

    public void setHeight(int newHeight) {
      height = newHeight;
    }

    public int getHeight() {
      return height;
    }
  }
}
