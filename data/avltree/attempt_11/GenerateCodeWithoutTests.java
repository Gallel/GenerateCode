
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
    Position<E> newNode = createNode(elemComp, null, null, null);
    if (isEmpty()) {
      setRoot(newNode);
    } else {
      Position<E> parent = parentToInsertItem(elemComp);
      addNode(parent, newNode);
      balance(parent);
    }
  }
  
  // Method that restores, if necessary, the balance of the node tree after each insertion or deletion
  protected void balance(Position<E> bst) {
    if (bst != null) {
      int balanceFactor = balanceFactor(bst);
      if (Math.abs(balanceFactor) > 1) {
        Position<E> heavy;
        Position<E> light;
        if (balanceFactor > 0) {
          heavy = left(bst);
          light = right(bst);
        } else {
          heavy = right(bst);
          light = left(bst);
        }
        if (balanceFactor(heavy) < 0) {
          rotate(heavy, right(heavy));
        }
        rotate(bst, heavy);
      }
      balance(parent(bst));
    }
  }
  
  // Protected method to compare received items
  protected int compare(E elem1, E elem2) {
    if (comparator != null) {
      return comparator.compare(elem1, elem2);
    }
    return ((Comparable<E>) elem1).compareTo(elem2);
  }
  
  // Delete the item, if you find it according to the comparator
  public E delete(E elemComp) {
    Position<E> bst = findNode(elemComp);
    if (bst == null) {
      return null;
    }
    E toReturn = bst.getElement();
    if (isLeaf(bst)) {
      deleteLeaf(bst);
    } else {
      Position<E> remplacement = bst;
      if (!isInternal(left(bst))) {
        remplacement = right(bst);
        while (isInternal(left(remplacement))) {
          remplacement = left(remplacement);
        }
      } else {
        remplacement = left(bst);
        while (isInternal(right(remplacement))) {
          remplacement = right(remplacement);
        }
      }
      bst.setElement(remplacement.getElement());
      deleteLeaf(remplacement);
    }
    return toReturn;
  }
  
  // Retrieves an element of the tree
  public E get(E elemComp) {
    Position<E> bst = findNode(elemComp);
    if (bst == null) {
      return null;
    }
    return bst.getElement();
  }
  
  // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree
  protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
    return new AVLNode<>(parent, elemComp);
  }
  
  // Method to check if there is a specific comparator
  public boolean thereIsComparator() {
    return comparator != null;
  }
  
  // Private inner class that represents the AVL nodes
  private static class AVLNode<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
    private int height;
    
    public AVLNode(Position<E> parent, E elemComp) {
      super(parent, elemComp);
      height = 0;
    }
    
    public int getHeight() {
      return height;
    }
    
    public void setHeight(int height) {
      this.height = height;
    }
  }
}
