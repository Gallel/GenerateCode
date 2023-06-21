
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
        Position<E> pos = super.search(elemComp);
        super.insert(pos, elemComp);
        balance(pos);
    }

    // Delete the item, if you find it according to the comparator
    public E delete(E elemComp) {
        Position<E> pos = super.search(elemComp);
        return super.delete(pos);
    }

    // Retrieves an element of the tree
    public E get(E elemComp) {
        Position<E> pos = super.search(elemComp);
        return super.getElement(pos);
    }

    // Overrides the superclass method to incorporate the attribute height at the node,
    // which allows you to check the balance of the AVL tree
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new AVLUltimo.NodeTree<E>(parent, elemComp);
    }

    // Protected method that compares received items
    protected int compare(E elem1, E elem2) {
        if (thereIsComparator()) {
            return super.getComparator().compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }

    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion
    protected void balance(Position<E> bst) {
        if (bst != null && !(bst.getElement() == null)) {
            int balance = getBalance(bst);
            if (balance > 1) {
                if (height((BSTNode<E>) bst.getLeft()) >= height((BSTNode<E>) bst.getRight())) {
                    bst = rightRotate(bst);
                } else {
                    bst.setLeft(leftRotate((BSTNode<E>) bst.getLeft()));
                    bst = rightRotate(bst);
                }
            } else if (balance < -1) {
                if (height((BSTNode<E>) bst.getRight()) >= height((BSTNode<E>) bst.getLeft())) {
                    bst = leftRotate(bst);
                } else {
                    bst.setRight(rightRotate((BSTNode<E>) bst.getRight()));
                    bst = leftRotate(bst);
                }
            }
            if (bst.getParent() != null) {
                balance(bst.getParent());
            }
        }
    }

    // Method to check if there is a specific comparator
    public boolean thereIsComparator() {
        return super.getComparator() != null;
    }

    // Protected method to get the balance of a node
    protected int getBalance(Position<E> position) {
        int leftHeight = height((BSTNode<E>) position.getLeft());
        int rightHeight = height((BSTNode<E>) position.getRight());
        return (leftHeight - rightHeight);
    }

    // Protected method to rotate a node to the left
    protected Position<E> leftRotate(Position<E> node) {
        Position<E> right = node.getRight();
        node.setRight(right.getLeft());
        if (right.getLeft() != null) {
            right.getLeft().setParent(node);
        }
        right.setParent(node.getParent());
        if (node.getParent() == null) {
            super.setRoot(right);
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(right);
        } else {
            node.getParent().setRight(right);
        }
        right.setLeft(node);
        node.setParent(right);
        return right;
    }

    // Protected method to rotate a node to the right
    protected Position<E> rightRotate(Position<E> node) {
        Position<E> left = node.getLeft();
        node.setLeft(left.getRight());
        if (left.getRight() != null) {
            left.getRight().setParent(node);
        }
        left.setParent(node.getParent());
        if (node.getParent() == null) {
            super.setRoot(left);
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(left);
        } else {
            node.getParent().setRight(left);
        }
        left.setRight(node);
        node.setParent(left);
        return left;
    }

    // Protected method to get the height of a node
    protected int height(BSTNode<E> node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    // Private class that defines the structure of a node and adds the height attribute
    private static class NodeTree<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
        private int height;

        public NodeTree(Position<E> parent, E element) {
            super(parent, element);
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
