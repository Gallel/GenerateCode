
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {

  AVLTree() {}

  AVLTree(Comparator<E> comparator) {super(comparator);}

  void add(E elemComp) { addNode(elemComp); }

  private int max(int a, int b) { return Math.max(a, b); }

  protected void balance(Position<E> bst) {
    if (bst != null) {
      int bf = height(left(bst)) - height(right(bst));
      if (bf > 1) {
        if (height(left(left(bst))) >= height(right(left(bst)))) {
          rightRotate(bst);
        } else {
          leftRotate(left(bst));
          rightRotate(bst);
        }
      } else if (bf < -1) {
        if (height(right(right(bst))) >= height(left(right(bst)))) {
          leftRotate(bst);
        } else {
          rightRotate(right(bst));
          leftRotate(bst);
        }
      }
      if (!isRoot(bst)) {
        balance(parent(bst));
      }
    }
  }

  protected int height(Position<E> bst) { return ((BSTNode<E>) bst).getHeight(); }

  public boolean isEmpty() { return size() == 0; }

  public E get(E elemComp) {
    Position<E> ptr = getRoot();
    while (ptr != null) {
      int cf = compare(ptr.getElement(), elemComp);
      if (cf == 0) {
        return ptr.getElement();
      } else if (cf > 0) {
        ptr = left(ptr);
      } else {
        ptr = right(ptr);
      }
    }
    return null;
  }

  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    BSTNode<E> node = new BSTNode<E>(parent, elemComp);
    node.setHeight(1);
    return node;
  }

  protected void rebalance(Position<E> bst) {
    int oldHeight, newHeight;
    do {
      oldHeight = height(bst);
      if (!isRoot(bst)) {
        bst = parent(bst);
      }
      newHeight = 1 + max(height(left(bst)), height(right(bst)));
      ((BSTNode<E>) bst).setHeight(newHeight);
    } while (oldHeight != newHeight && !isRoot(bst));
  }

  E delete(E elemComp) {
    Position<E> bstPos = search(elemComp);
    if (bstPos == null) {
      return null;
    }
    E toReturn = bstPos.getElement();
    if (left(bstPos) != null && right(bstPos) != null) {
      Position<E> predPos = maxPosition(left(bstPos));
      ((BSTNode<E>) bstPos).setElement(predPos.getElement());
      bstPos = predPos;
    }
    if (left(bstPos) == null) {
      splice(bstPos);
    } else {
      splice(bstPos);
    }
    rebalance(parent(bstPos));
    return toReturn;
  }

  void leftRotate(Position<E> bst) {
    BSTNode<E> leftChild = (BSTNode<E>) left(bst);
    if (isRoot(bst)) {
      setRoot(leftChild);
      leftChild.setParent(null);
    } else {
      BSTNode<E> parent = (BSTNode<E>) parent(bst);
      if (bst == left(parent)) {
        parent.setLeft(leftChild);
      } else {
        parent.setRight(leftChild);
      }
      leftChild.setParent(parent);
    }
    bst.setLeft(leftChild.getRight());
    if (leftChild.getRight() != null) {
      leftChild.getRight().setParent(bst);
    }
    leftChild.setRight(bst);
    bst.setParent(leftChild);
    ((BSTNode<E>) bst).setHeight(max(height(left(bst)), height(right(bst))) + 1);
    ((BSTNode<E>) leftChild).setHeight(max(height(left(leftChild)), height(right(leftChild))) + 1);
  }

  Position<E> maxPosition(Position<E> bst) {
    Position<E> ptr = bst;
    while (right(ptr) != null) {
      ptr = right(ptr);
    }
    return ptr;
  }

  Position<E> search(E elemComp) {
    Position<E> ptr = getRoot();
    while (ptr != null) {
      int cf = compare(ptr.getElement(), elemComp);
      if (cf == 0) {
        return ptr;
      } else if (cf > 0) {
        ptr = left(ptr);
      } else {
        ptr = right(ptr);
      }
    }
    return null;
  }

  void rightRotate(Position<E> bst) {
    BSTNode<E> rightChild = (BSTNode<E>) right(bst);
    if (isRoot(bst)) {
      setRoot(rightChild);
      rightChild.setParent(null);
    } else {
      BSTNode<E> parent = (BSTNode<E>) parent(bst);
      if (bst == left(parent)) {
        parent.setLeft(rightChild);
      } else {
        parent.setRight(rightChild);
      }
      rightChild.setParent(parent);
    }
    bst.setRight(rightChild.getLeft());
    if (rightChild.getLeft() != null) {
      rightChild.getLeft().setParent(bst);
    }
    rightChild.setLeft(bst);
    bst.setParent(rightChild);
    ((BSTNode<E>) bst).setHeight(max(height(left(bst)), height(right(bst))) + 1);
    ((BSTNode<E>) rightChild).setHeight(max(height(left(rightChild)), height(right(rightChild))) + 1);
  }

  boolean thereIsComparator() { return super.getComparator() != null; }

  protected class BSTNode<E> extends LinkedBinaryTreeImpl.NodeTree<E> {

    int height;

    public BSTNode(Position<E> p, E e) { super(p, e); }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

  }

}
