
import java.util.Comparator;
import edu.uoc.ds.binarytree.LinkedBinaryTreeImpl;
import edu.uoc.ds.position.Position;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E> {
  
  private Comparator<E> comparator;

  public AVLTree() {
    super();
    comparator = null;
  }

  public AVLTree(Comparator<E> comparator) {
    super();
    this.comparator = comparator;
  }

  protected int compare(E elem1, E elem2) {
    if (comparator == null) {
      return elem1.compareTo(elem2);
    }
    return comparator.compare(elem1, elem2);
  }

  private int height(Position<E> n) {
    if (n == null) {
      return 0;
    }
    int left = height(left(n));
    int right = height(right(n));
    return Math.max(left, right) + 1;
  }

  private boolean balanced(Position<E> n) {
    int left = height(left(n));
    int right = height(right(n));
    return (Math.abs(left - right) <= 1);
  }

  private void balance(Position<E> bst) {
    if (bst != null) {
      balance(parent(bst));
      if (!balanced(bst)) {
        if (height(left(bst)) > height(right(bst))) {
          if (height(left(left(bst))) >= height(right(left(bst)))) {
            rotateRight(bst);
          } else {
            doubleRotateLeftRight(bst);
          }
        } else {
          if (height(right(right(bst))) >= height(left(right(bst)))) {
            rotateLeft(bst);
          } else {
            doubleRotateRightLeft(bst);
          }
        }
      }
    }
  }

  public void add(E elemComp) {
    Position<E> bst = searchNode(elemComp);
    if (bst == null) {
      addRoot(elemComp);
    } else if (compare(elemComp, bst.getElement()) != 0) {
      addLeaf(bst, elemComp);
    }
    balance(bst);
  }

  private void delete(Position<E> n) {
    Position<E> bst;
    if (numChildren(n) == 2) {
      bst = inorderSuccesor(n);
      n.setElement(bst.getElement());
      n = bst;
    }
    bst = (left(n) != null ? left(n) : right(n));
    if (bst != null) {
      bst.setParent(n.getParent());
    }
    if (n.getParent() == null) {
      setRoot(bst);
    } else if (n == left(n.getParent())) {
      n.getParent().setLeft(bst);
    } else {
      n.getParent().setRight(bst);
    }
    balance(n.getParent());
  }

  public E delete(E elemComp) {
    Position<E> bst = searchNode(elemComp);
    if (bst == null) {
      return null;
    }
    E oldElem = bst.getElement();
    if (compare(elemComp, bst.getElement()) == 0) {
      if (isInternal(bst)) {
        delete(inorderSuccesor(bst));
      } else {
        delete(bst);
      }
    }
    return oldElem;
  }

  private Position<E> searchNode(E elemComp) {
    Position<E> bst = root();
    while (bst != null) {
      int compResult = compare(elemComp, bst.getElement());
      if (compResult == 0) {
        return bst;
      } else if (compResult < 0) {
        bst = left(bst);
      } else {
        bst = right(bst);
      }
    }
    return null;
  }

  public E get(E elemComp) {
    Position<E> bst = searchNode(elemComp);
    if (bst != null && compare(elemComp, bst.getElement()) == 0) {
      return bst.getElement();
    }
    return null;  
  }

  private LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new NodeTree<E>(parent, elemComp);
  }

  private boolean thereIsComparator() {
    return comparator != null;
  }

  protected static class NodeTree<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
    int height;
    public NodeTree(Position<E> parent, E elemComp) {
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
