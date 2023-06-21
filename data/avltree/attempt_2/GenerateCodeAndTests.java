
import java.util.Comparator;
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
  private Comparator<E> comparator;

  public AVLTree() {
    super();
  }

  public AVLTree(Comparator<E> comparator) {
    super();
    this.comparator = comparator;
  }

  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLNode<>(parent, elemComp);
  }

  @Override
  public void add(E elemComp) {
    AVLNode<E> actualNode = (AVLNode<E>) super.searchNode(elemComp);
    AVLNode<E> newNode = new AVLNode<>(null, elemComp);
    if (actualNode == null) {
      super.add(elemComp);
      actualNode = (AVLNode<E>) super.lastAdded;
      if (!actualNode.isBalanced() && actualNode.getHeightDifference() > 0) {
        if (actualNode.getRightChild().getHeightDifference() < 0) {
          super.rotateRight(actualNode.getRightChild());
        }
        super.rotateLeft(actualNode);
      } else if (!actualNode.isBalanced() && actualNode.getHeightDifference() < 0) {
        if (actualNode.getLeftChild().getHeightDifference() > 0) {
          super.rotateLeft(actualNode.getLeftChild());
        }
        super.rotateRight(actualNode);
      }
      if (actualNode.getParent() != null) {
        this.updateBalance((AVLNode<E>) actualNode.getParent());
      }
    }
  }

  private void updateBalance(AVLNode<E> node) {
    if (!node.isBalanced() && node.getHeightDifference() > 0) {
      if (node.getRightChild().getHeightDifference() < 0) {
        super.rotateRight(node.getRightChild());
      }
      super.rotateLeft(node);
    } else if (!node.isBalanced() && node.getHeightDifference() < 0) {
      if (node.getLeftChild().getHeightDifference() > 0) {
        super.rotateLeft(node.getLeftChild());
      }
      super.rotateRight(node);
    }
    if (node.getParent() != null) {
      this.updateBalance((AVLNode<E>) node.getParent());
    }
  }

  public boolean thereIsComparator() {
    return this.comparator != null;
  }

  @Override
  protected int compare(E elem1, E elem2) {
    if (this.thereIsComparator()) {
      return this.comparator.compare(elem1, elem2);
    } else {
      Comparable<E> comparableElem1 = (Comparable<E>) elem1;
      return comparableElem1.compareTo(elem2);
    }
  }

  @Override
  public E delete(E elemComp) {
    AVLNode<E> actualNode = (AVLNode<E>) super.searchNode(elemComp);
    if (actualNode != null) {
      super.delete(elemComp);
      this.updateBalance((AVLNode<E>) actualNode.getParent());
      return actualNode.getElement();
    }
    return null;
  }

  @Override
  public E get(E elemComp) {
    AVLNode<E> actualNode = (AVLNode<E>) super.searchNode(elemComp);
    if (actualNode != null) {
      return actualNode.getElement();
    }
    return null;
  }

  private class AVLNode<E> extends NodeTree<E> {

    private int height = 0;

    public AVLNode(Position<E> p, E elem) {
      super(p, elem);
    }

    public boolean isBalanced() {
      return Math.abs(this.getHeightDifference()) <= 1;
    }

    public int getHeightDifference() {
      int leftHeight = this.getLeftChild() != null ? ((AVLNode<E>) this.getLeftChild()).height : -1;
      int rightHeight = this.getRightChild() != null ? ((AVLNode<E>) this.getRightChild()).height : -1;
      return rightHeight - leftHeight;
    }

    public void refreshHeight() {
      int leftHeight = this.getLeftChild() != null ? ((AVLNode<E>) this.getLeftChild()).height : -1;
      int rightHeight = this.getRightChild() != null ? ((AVLNode<E>) this.getRightChild()).height : -1;
      this.height = 1 + Math.max(leftHeight, rightHeight);
    }
  }
}
