
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {

  //Constructor without parameters
  public AVLTree() {}

  //Constructor with a comparator parameter
  public AVLTree(Comparator<E> comparator) {
    super(comparator);
  }

  //Add an item to the appropriate position, if possible
  public void add(E elemComp) {
    Position<E> node = createNode(elemComp);
    if (isEmpty()) {
      setRoot(node);
    } else {
      Position<E> parent = findPosition(elemComp);
      if (compare(elemComp, parent.getElement()) == 0) {
        parent.setElement(elemComp);
        return;
      } else {
        LinkedBinaryTreeImpl.NodeTree<E> nodeTreeParent = cast(parent);
        if (compare(elemComp, parent.getElement()) < 0) {
          nodeTreeParent.setLeft(node);
        } else {
          nodeTreeParent.setRight(node);
        }
        node.setParent(nodeTreeParent);
        balance(node);
      }
    }
  }

  //Method that restores, if necessary, the equilibrium of the node tree after each insertion or deletion
  protected void balance(Position<E> bst) {
    if (bst != null) {
      LinkedBinaryTreeImpl.NodeTree<E> n = cast(bst);
      int factor = calculateBalance(n);
      if (factor > 1) {
        if (calculateBalance(n.getLeft()) > 0) {
          rightRotation(n);
        } else {
          leftRightRotation(n);
        }
      } else if (factor < -1) {
        if (calculateBalance(n.getRight()) < 0) {
          leftRotation(n);
        } else {
          rightLeftRotation(n);
        }
      }
    }
  }

  //Protected method that compares the received items
  protected int compare(E elem1, E elem2) {
    if (thereIsComparator()) {
      return getComparator().compare(elem1, elem2);
    } else {
      return ((Comparable<E>) elem1).compareTo(elem2);
    }
  }

  //Delete the item, if you find it according to the comparator
  public E delete(E elemComp) {
    Position<E> b = findPosition(elemComp);
    E old = b.getElement();
    if (areBothChildrenEmpty(b)) {
      if (b == root()) {
        setRoot(null);
      } else {
        LinkedBinaryTreeImpl.NodeTree<E> parent = cast(b.getParent());
        if (parent.getLeft() == b) {
          parent.setLeft(null);
        } else {
          parent.setRight(null);
        }
        balance(parent);
      }
    } else if (areBothChildrenFull(b)) {
      Position<E> minRight = getMin(b.getRight());
      b.setElement(minRight.getElement());
      LinkedBinaryTreeImpl.NodeTree<E> nodeTreeParent = cast(minRight.getParent());
      if (nodeTreeParent.getLeft() == minRight) {
        nodeTreeParent.setLeft(null);
      } else {
        nodeTreeParent.setRight(null);
      }
      balance(nodeTreeParent);
    } else {
      replaceDelete(b, (b.getLeft() != null) ? b.getLeft() : b.getRight());
      balance(b.getParent());
    }
    return old;
  }

  //Retrieves an element from the tree
  public E get(E elemComp) {
    Position<E> pos = findPosition(elemComp);
    if (compare(elemComp, pos.getElement()) != 0) {
      throw new NoSuchElementException("Element not found.");
    }
    return pos.getElement();
  }

  //Overrides the superclass method to incorporate the attribute height to the node,
  //which allows to check the balance of the AVL tree
  @Override
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new LinkedBinaryTreeImpl.NodeTree<>(parent, elemComp);
  }

  //Method to check if there is a specific comparator
  public boolean thereIsComparator() {
    return getComparator() != null;
  }
}
