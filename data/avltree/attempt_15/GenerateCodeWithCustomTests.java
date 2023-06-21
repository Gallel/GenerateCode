
import java.util.Comparator;
import edu.uoc.ds.traversal.Traversal;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E> {

  private Comparator<E> comparator;

  public AVLTree() {}

  public AVLTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  @Override
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E element) {
    return new LinkedBinaryTreeImpl.NodeTree<E>(parent, element) {
      int height = 1;
      @Override
      public String toString() {
        return element + "[" + height + "]";
      };
    };
  }

  public boolean thereIsComparator() {
    return comparator != null;
  }

  protected int compare(E elem1, E elem2) {
    if (comparator != null) {
      return comparator.compare(elem1, elem2);
    }
    return elem1.compareTo(elem2);
  }

  public void add(E elem) {
    Position<E> pos = add(newNode(null, elem));
    Position<E> p = pos;
    while (p.getParent() != null) {
      BalancedNode<E> parent = (BalancedNode<E>) p.getParent();
      if (Math.abs(getHeight(parent.getLeft()) - getHeight(parent.getRight())) > 1) {
        balance((BalancedNode<E>) p.getParent());
        break;
      }
      updateHeight(parent);
      p = parent;
    }
  }

  public E delete(E elem) {
    Position<E> p = find(elem);
    if (p == null) {
      return null;
    } else {
      E retVal = p.getElement();
      if (isExternal(p)) {
        remove(p);
      } else {
        Position<E> q = getRight(p);
        while (isInternal(q)) {
          q = getLeft(q);
        }
        replace(p, q.getElement());
        remove(q);
        p = q.getParent();
      }
      while (p != null) {
        updateHeight(p);
        if (Math.abs(getHeight(getLeft(p)) - getHeight(getRight(p))) > 1) {
          balance(p);
        }
        p = p.getParent();
      }
      return retVal;
    }
  }

  private int getHeight(Position<E> p) {
    if (p == null) {
      return 0;
    }
    BalancedNode<E> node = (BalancedNode<E>) p;
    return node.height;
  }

  private void updateHeight(Position<E> p) {
    BalancedNode<E> node = (BalancedNode<E>) p;
    node.height = 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
  }

  protected void balance(BalancedNode<E> bst) {
    if (getHeight(bst.getLeft()) > getHeight(bst.getRight())) {
      if (getHeight(bst.getLeft().getLeft()) >= getHeight(bst.getLeft().getRight())) {
        llRotation(bst);
      } else {
        lrRotation(bst);
      }
    } else {
      if (getHeight(bst.getRight().getRight()) >= getHeight(bst.getRight().getLeft())) {
        rrRotation(bst);
      } else {
        rlRotation(bst);
      }
    }
  }

  private void llRotation(BalancedNode<E> bst) {
    LinkedBinaryTreeImpl.NodeTree<E> z = bst.getParent();
    LinkedBinaryTreeImpl.NodeTree<E> y = bst.getLeft();
    LinkedBinaryTreeImpl.NodeTree<E> x = y.getLeft();
    bst.setLeft(y.getRight());
    if (bst.getLeft() != null) {
      bst.getLeft().setParent(bst);
    }
    y.setLeft(x);
    if (y.getLeft() != null) {
      y.getLeft().setParent(y);
    }
    y.setRight(bst);
    bst.setParent(y);
    y.setParent(z);
    if (z == null) {
      setRoot(y);
    } else if (z.getLeft() == bst) {
      z.setLeft(y);
    } else {
      z.setRight(y);
    }
    updateHeight(bst);
    updateHeight(y);
  }

  private void rrRotation(BalancedNode<E> bst) {
    LinkedBinaryTreeImpl.NodeTree<E> z = bst.getParent();
    LinkedBinaryTreeImpl.NodeTree<E> y = bst.getRight();
    LinkedBinaryTreeImpl.NodeTree<E> x = y.getRight();
    bst.setRight(y.getLeft());
    if (bst.getRight() != null) {
      bst.getRight().setParent(bst);
    }
    y.setRight(x);
    if (y.getRight() != null) {
      y.getRight().setParent(y);
    }
    y.setLeft(bst);
    bst.setParent(y);
    y.setParent(z);
    if (z == null) {
      setRoot(y);
    } else if (z.getLeft() == bst) {
      z.setLeft(y);
    } else {
      z.setRight(y);
    }
    updateHeight(bst);
    updateHeight(y);
  }

  private void lrRotation(BalancedNode<E> bst) {
    rrRotation(bst.getLeft());
    llRotation(bst);
  }

  private void rlRotation(BalancedNode<E> bst) {
    llRotation(bst.getRight());
    rrRotation(bst);
  }

  public E get(E elem) {
    Position<E> p = find(elem);
    if (p == null) {
      return null;
    } else {
      return p.getElement();
    }
  }

  protected static class BalancedNode<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
    private int height = 1;

    public BalancedNode(Position<E> parent, E element) {
      super(parent, element);
    }
  }
}
