
import java.util.Comparator;
import edu.uoc.ds.adt.tree.LinkedBinaryTreeImpl;
import edu.uoc.ds.adt.tree.Position;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E> {

  private Comparator<E> comparator;

  public AVLTree() {
    
  }

  public AVLTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  @Override
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elem) {
    return new Node(parent, elem);
  }

  protected class Node extends LinkedBinaryTreeImpl<E>.Node {
    int height;

    public Node(Position<E> parent, E elemComp) {
      super(parent, elemComp);
      this.height = 1;
    }
  }

  private int compare(E elem1, E elem2) {
    if (comparator != null) {
      return comparator.compare(elem1, elem2);
    } else {
      return elem1.compareTo(elem2);
    }
  }

  private boolean thereIsComparator() {
    return comparator != null;
  }

  private void balance(Position<E> bst) {
    int leftHeight = height(left(bst));
    int rightHeight = height(right(bst));
    int factor = leftHeight - rightHeight;
    if (factor > 1 || factor < -1) {
      restructure(bst);
    }
  }

  private int height(Position<E> node) {
    if (node == null) {
      return 0;
    } else {
      return ((Node) node).height;
    }
  }

  private void updateHeight(Node node) {
    node.height = 1 + Math.max(height(left(node)), height(right(node)));
  }

  private void restructure(Position<E> posNode) {
    Position<E> position = posNode;
    Position<E> parent = parent(position);
    Position<E> child = (height(left(position)) > height(right(position))) ? left(position) : right(position);
    Position<E> middle = (height(left(child)) > height(right(child))) ? left(child) : right(child);
    Position<E> newRoot;
    if (position == root()) {
      setRoot(middle);
      middle.setParent(null);
      newRoot = middle;
    } else {
      if (position == left(parent)) {
        parent.setLeft(middle);
      } else {
        parent.setRight(middle);
      }
      middle.setParent(parent);
      newRoot = middle;
    }

    if (height(left(newRoot)) > height(right(newRoot))) {
      child = left(newRoot);
    } else {
      child = right(newRoot);
    }
    updateHeight((Node) child);
    updateHeight((Node) newRoot);
  }

  public void add(E elemComp) {
    Position<E> positionNew = addWithReturn(elemComp);
    balance(positionNew);
  }

  private Position<E> addWithReturn(E elemComp) {
    Node newNode = new Node(null, elemComp);
    Position<E> temp = root;
    Position<E> parent = null;
    while (temp != null) {
      parent = temp;
      int resultCompare = compare(elemComp, temp.getElement());
      if (resultCompare < 0) {
        temp = left(temp);
      } else if (resultCompare > 0) {
        temp = right(temp);
      } else {
        return temp;
      }
    }
    newNode.setParent(parent);

    if (parent == null) {
      setRoot(newNode);
    } else {
      int resultCompareParent = compare(elemComp, parent.getElement());
      if (resultCompareParent < 0) {
        setLeft(parent, newNode);
      } else {
        setRight(parent, newNode);
      }
    }
    Position<E> positionNew = newNode;
    while (!positionNew.equals(root())) {
      positionNew = positionNew.getParent();
      balance(positionNew);
      updateHeight((Node) positionNew);
    }
    return newNode;
  }

  public E delete(E elemComp) {
    Position<E> positionToRemove = find(elemComp);

    if (positionToRemove == null) {
      return null;
    }

    E elemRemoved = positionToRemove.getElement();
    remove(positionToRemove);
    return elemRemoved;
  }

  private void remove(Position<E> position) {
    if (isLeaf(position)) {
      if (position == root()) {
        setRoot(null);
      } else {
        Position<E> parent = parent(position);
        if (position == left(parent)) {
          setLeft(parent, null);
        } else {
          setRight(parent, null);
        }
        position = parent;
      }
    } else if (left(position) == null || right(position) == null) {
      Position<E> child;
      if (left(position) != null) {
        child = left(position);
      } else {
        child = right(position);
      }
      if (position == root()) {
        setRoot(child);
        child.setParent(null);
      } else {
        Position<E> parent = parent(position);
        if (position == left(parent)) {
          setLeft(parent, child);
        } else {
          setRight(parent, child);
        }
        child.setParent(parent);
      }
      position = child;
    } else {
      Position<E> successorNode = max(getLeft(position));
      position.setElement(successorNode.getElement());
      remove(successorNode);
    }

    Position<E> parent = parent(position);
    while (parent != null) {
      updateHeight((Node) parent);
      balance(parent);
      parent = parent(parent);
    }
  }

  private Position<E> max(Position<E> node) {
    while (right(node) != null) {
      node = right(node);
    }
    return node;
  }

  public E get(E elemComp) {
    Position<E> position = find(elemComp);
    return (position == null) ? null : position.getElement();
  }

  private Position<E> find(E elemComp) {
    Position<E> position = root;
    while (position != null) {
      int resultCompare = compare(elemComp, position.getElement());
      if (resultCompare < 0) {
        position = left(position);
      } else if (resultCompare > 0) {
        position = right(position);
      } else {
        return position;
      }
    }
    return null;
  }

}
