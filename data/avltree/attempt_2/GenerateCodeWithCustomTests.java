
import java.util.Comparator;
import edu.uoc.ds.adt.sequential.LinkedBinaryTreeImpl;
import edu.uoc.ds.traversal.Traversal;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E> {
  
  private Comparator<E> comparator;

  public AVLTree() {}

  public AVLTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  @Override
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new NodeTree<E>(parent, elemComp);
  }

  protected int compare(E elem1, E elem2) {
    if (comparator == null) {
      return elem1.compareTo(elem2);
    } else {
      return comparator.compare(elem1, elem2);
    }
  }

  @Override
  public void add(E elemComp) {
    LinkedBinaryTreeImpl.NodeTree<E> node = findNodeToInsert(elemComp);

    int cmp = compare(elemComp, node.getElement());
    if (cmp < 0) { 
      node.setLeft(newNode(node, elemComp)); 
    } else if (cmp > 0) { 
      node.setRight(newNode(node, elemComp)); 
    } else { 
      node.setElement(elemComp); 
    }
    balance(node);
  }

  @Override
  protected void balance(Position<E> bst) {
    if (bst != null) {
      balance(bst.getLeft());
      balance(bst.getRight());
      if (nodeBalance(bst) == -2) {
        if (height(bst.getRight().getRight()) >= height(bst.getRight().getLeft())) {
          rotateLeft(bst);
        } 
        else {
          doubleRightRotation(bst);
        }
      } 
      else if (nodeBalance(bst) == 2) {
        if (height(bst.getLeft().getLeft()) >= height(bst.getLeft().getRight())) {
          rotateRight(bst);
        } 
        else {
          doubleLeftRotation(bst);
        }
      } 

      
    }
  }

  private int nodeBalance(Position<E> bst) {
    if (bst == null) {
      return 0;
    } else {
      return height(bst.getLeft()) - height(bst.getRight());
    }
  }

  private int height(Position<E> node) {
    if (node == null) {
      return -1;
    } else {
      return ((NodeTree<E>) node).getHeight();
    }
  }

  private void resetHeight(Position<E> node) {
    ((NodeTree<E>) node).resetHeight();
  }

  private void updateHeight(Position<E> node) {
    ((NodeTree<E>) node).resetHeight();
  }

  private void doubleLeftRotation(Position<E> bst) {
    Position<E> k3 = bst;
    Position<E> k1 = k3.getLeft();
    Position<E> k2 = k1.getRight();
    if (k3 == root()) {
      setRoot(k2);
    } else {
      NodeTree<E> aux = createNode((NodeTree<E>) bst.getParent(), k2.getElement());
      if (bst == bst.getParent().getLeft()) {
        bst.getParent().setLeft(aux);
      } else {
        bst.getParent().setRight(aux);
      }
    }
    k1.setParent(k2);
    k1.setRight(k2.getLeft());
    resetHeight(k1.getRight());
    k2.setParent(k3.getParent());
    k2.setLeft(k1);
    k2.setRight(k3);
    resetHeight(k2.getLeft());
    resetHeight(k2.getRight());
    k3.setParent(k2);
    resetHeight(k3);
  }

  private void doubleRightRotation(Position<E> bst) {
    Position<E> k3 = bst;
    Position<E> k1 = k3.getRight();
    Position<E> k2 = k1.getLeft();
    if (k3 == root()) {
      setRoot(k2);
    } else {
      NodeTree<E> aux = createNode((NodeTree<E>) bst.getParent(), k2.getElement());
      if (bst == bst.getParent().getLeft()) {
        bst.getParent().setLeft(aux);
      } else {
        bst.getParent().setRight(aux);
      }
    }
    k1.setParent(k2);
    k1.setLeft(k2.getRight());
    resetHeight(k1.getLeft());
    k2.setParent(k3.getParent());
    k2.setRight(k1);
    k2.setLeft(k3);
    resetHeight(k2.getLeft());
    resetHeight(k2.getRight());
    k3.setParent(k2);
    resetHeight(k3);
  }

  private void rotateLeft(Position<E> bst) {
    Position<E> k1 = bst.getRight();
    bst.setRight(k1.getLeft());
    if (bst.getParent() == null) {
      setRoot(k1);
      k1.setParent(null);
    } else if (bst.getParent().getLeft() == bst) {
      bst.getParent().setLeft(k1);
      k1.setParent(bst.getParent());
    } else {
      bst.getParent().setRight(k1);
      k1.setParent(bst.getParent());
    }
    k1.setLeft(bst);
    bst.setParent(k1);
    resetHeight(bst);
    resetHeight(k1.getRight());
  }

  private void rotateRight(Position<E> bst) {
    Position<E> k1 = bst.getLeft();
    bst.setLeft(k1.getRight());
    if (bst.getParent() == null) {
      setRoot(k1);
      k1.setParent(null);
    } else if (bst.getParent().getLeft() == bst) {
      bst.getParent().setLeft(k1);
      k1.setParent(bst.getParent());
    } else {
      bst.getParent().setRight(k1);
      k1.setParent(bst.getParent());
    }
    k1.setRight(bst);
    bst.setParent(k1);
    resetHeight(bst);
    resetHeight(k1.getLeft());
  }

  public boolean isBalanced() {
    return isBalancedAux(root(), true);
  }

  private boolean isBalancedAux(Position<E> p, boolean isBalanced) {
    if (p == null || !isBalanced) {
      return isBalanced;
    } else {
      int bf = nodeBalance(p);
      if (bf > 1 || bf < -1) {
        isBalanced = false;
      } else {
        isBalanced = isBalancedAux(p.getLeft(), isBalanced);
        isBalanced = isBalancedAux(p.getRight(), isBalanced);
      }
      return isBalanced;
    }
  }

  public E get(E elemComp) {
    LinkedBinaryTreeImpl.NodeTree<E> node = findNode(elemComp);
    if (node == null) {
      return null;
    } else {
      return node.getElement();
    }
  }

  public boolean thereIsComparator() {
    return comparator != null;
  }

  public E delete(E elemComp) {
    LinkedBinaryTreeImpl.NodeTree<E> node = findNode(elemComp);
    if (node == null) {
      return null;
    } else {
      E element = node.getElement();
      if (node.getLeft() == null) {
        transplant(node, node.getRight());
      } else if (node.getRight() == null) {
        transplant(node, node.getLeft());
      } else {
        NodeTree<E> p = (NodeTree<E>) minimum(node.getRight());
        if (p.getParent() != node) {
          transplant(p, p.getRight());
          p.setRight(node.getRight());
          p.getRight().setParent(p);
        }
        transplant(node, p);
        p.setLeft(node.getLeft());
        p.getLeft().setParent(p);
      }
      balance(node.getParent());
      return element;
    }
  }

  private void transplant(Position<E> u, Position<E> v) {
    if (u == root()) {
      setRoot(v);
      u.setParent(null);
    } else {
      NodeTree<E> aux = createNode(null, u.getElement());
      if (u == u.getParent().getLeft()) {
        u.getParent().setLeft(aux);
      } else {
        u.getParent().setRight(aux);
      }
      u.setParent(null);
      if (v != null) {
        if (v == v.getParent().getLeft()) {
          v.getParent().setLeft(u);
        } else {
          v.getParent().setRight(u);
        }
        u.setParent(v.getParent());
      } else {
        u.setParent(null);
      }
    }
  }

  private LinkedBinaryTreeImpl.NodeTree<E> minimum(Position<E> r) {
    if (r == null) {
      return null;
    } else if (r.getLeft() == null) {
      return (LinkedBinaryTreeImpl.NodeTree<E>) r;
    } else {
      return minimum(r.getLeft());
    }
  }

  private LinkedBinaryTreeImpl.NodeTree<E> findNodeToInsert(E elemComp) {
    LinkedBinaryTreeImpl.NodeTree<E> p = (NodeTree<E>) root();
    LinkedBinaryTreeImpl.NodeTree<E> prev = null;

    while (p != null) {
      int cmp = compare(elemComp, p.getElement());
      if (cmp == 0) {
        return p;
      } else {
        prev = p;
        if (cmp < 0) {
          p = (NodeTree<E>) p.getLeft();
        } else {
          p = (NodeTree<E>) p.getRight();
        }
      }
    }
    return prev;
  }

  private LinkedBinaryTreeImpl.NodeTree<E> findNode(E elemComp) {
    LinkedBinaryTreeImpl.NodeTree<E> p = (NodeTree<E>) root();

    while (p != null) {
      int cmp = compare(elemComp, p.getElement());
      if (cmp == 0) {
        return p;
      } else if (cmp < 0) {
        p = (NodeTree<E>) p.getLeft();
      } else {
        p = (NodeTree<E>) p.getRight();
      }
    }
    return null;
  }

  public static class NodeTree<E> extends LinkedBinaryTreeImpl.NodeTree<E> {

    private int height;

    public NodeTree(Position<E> parent, E elemComp) {
      super(parent, elemComp);
      resetHeight();
    }

    public void resetHeight() {
      int leftHeight = ((NodeTree<E>) getLeft()).getHeight();
      int rightHeight = ((NodeTree<E>) getRight()).getHeight();
      height = 1 + Math.max(leftHeight, rightHeight);
    }

    public int getHeight() {
      return height;
    }
  }
}
