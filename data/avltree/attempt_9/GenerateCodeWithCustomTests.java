
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
        Position<E> position = super.search(elemComp);
        super.insert(position, elemComp);
        balance(position);
    }
    
    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion
    protected void balance(Position<E> bst) {
        if (bst != null) {
            int balance = height(super.left(bst)) - height(super.right(bst));
            if (Math.abs(balance) > 1) {
                if (balance > 0) {
                    if (height(super.left(super.left(bst))) >= height(super.right(super.left(bst)))) {
                        rightRotate(bst);
                    } else {
                        leftRotate(super.left(bst));
                        rightRotate(bst);
                    }
                } else {
                    if (height(super.right(super.right(bst))) >= height(super.left(super.right(bst)))) {
                        leftRotate(bst);
                    } else {
                        rightRotate(super.right(bst));
                        leftRotate(bst);
                    }
                }
            }
            balance(super.parent(bst));
        }
    }
    
    // Protected method that compares received items
    protected int compare(E elem1, E elem2) {
        if (super.getComparator() != null) {
            return super.getComparator().compare(elem1, elem2);
        } else {
            return elem1.compareTo(elem2);
        }
    }
    
    // Delete the item, if you find it according to the comparator
    public E delete(E elemComp) {
        Position<E> position = super.search(elemComp);
        E deleted = super.delete(position);
        balance(position);
        return deleted;
    }
    
    // Retrieves an element of the tree
    public E get(E elemComp) {
        Position<E> position = super.search(elemComp);
        if (position != null) {
            return position.getElement();
        } else {
            return null;
        }
    }
    
    // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }
    
    // Method to check if there is a specific comparator
    public boolean thereIsComparator() {
        return super.getComparator() != null;
    }
    
    // Auxiliary methods to calculate the height and perform the necessary rotations
    protected int height(Position<E> bst) {
        if (bst == null) {
            return -1;
        } else {
            return 1 + Math.max(height(super.left(bst)), height(super.right(bst)));
        }
    }
    
    protected void leftRotate(Position<E> bst) {
        Position<E> aux = super.right(bst);
        super.setRight(bst, super.left(aux));
        if (super.parent(bst) == null) {
            super.setRoot(aux);
        } else if (bst == super.left(super.parent(bst))) {
            super.setLeft(super.parent(bst), aux);
        } else {
            super.setRight(super.parent(bst), aux);
        }
        super.setLeft(aux, bst);
    }
    
    protected void rightRotate(Position<E> bst) {
        Position<E> aux = super.left(bst);
        super.setLeft(bst, super.right(aux));
        if (super.parent(bst) == null) {
            super.setRoot(aux);
        } else if (bst == super.right(super.parent(bst))) {
            super.setRight(super.parent(bst), aux);
        } else {
            super.setLeft(super.parent(bst), aux);
        }
        super.setRight(aux, bst);
    }
    
}
