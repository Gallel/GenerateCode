
public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {

    // Constructor without parameters.
    public AVLTree() {
        super();
    }

    // Constructor with a parameter.
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    // Add an item to the appropriate position, if possible.
    public void add(E elemComp) {
        Position<E> foundPos = search(elemComp);
        NodeTree<E> newNode = (NodeTree<E>) createNode(elemComp);
        if (foundPos == null) {
            addRoot(newNode);
            return;
        }
        NodeTree<E> node = (NodeTree<E>) foundPos;
        newNode.setParent(node);
        if (compareTo(elemComp, node.getElement()) < 0) {
            node.setLeft(newNode);
        } else {
            node.setRight(newNode);
        }
        balance(newNode);
    }

    // Delete the item, if you find it according to the comparator.
    public E delete(E elemComp) {
        Position<E> foundPos = search(elemComp);
        if (foundPos == null) {
            return null;
        }
        NodeTree<E> node = (NodeTree<E>) foundPos;
        E result = node.getElement();
        if (node.getLeft() != null && node.getRight() != null) {
            Position<E> pos = predecessor(node);
            NodeTree<E> predecessor = (NodeTree<E>) pos;
            node.setElement(predecessor.getElement());
            node = predecessor;
        }
        NodeTree<E> parent = (NodeTree<E>) node.getParent();
        super.deleteNode(node);
        balanceDelete(parent);
        return result;
    }

    // Retrieves an element of the tree.
    public E get(E elemComp) {
        Position<E> foundPos = search(elemComp);
        if (foundPos == null) {
            return null;
        }
        NodeTree<E> node = (NodeTree<E>) foundPos;
        return node.getElement();
    }

    // Protected method that compares received items.
    protected int compare(E elem1, E elem2) {
        if (thereIsComparator()) {
            return getComparator.compare(elem1, elem2);
        }
        return ((Comparable<E>) elem1).compareTo(elem2);
    }

    // Method to check if there is a specific comparator.
    public boolean thereIsComparator() {
        return getComparator() != null;
    }

    // Method that restores, if necessary, the balance of the node tree
    // after each insertion or deletion.
    protected void balance(Position<E> bst) {
        int balance = getBalance(bst);
        if (balance == 2) {
            if (getBalance(bst.getLeft()) < 0) {
                rotateLeft(bst.getLeft());
            }
            rotateRight(bst);
        } else if (balance == -2) {
            if (getBalance(bst.getRight()) > 0) {
                rotateRight(bst.getRight());
            }
            rotateLeft(bst);
        }
        if (bst.getParent() != null) {
            balance(bst.getParent());
        }
    }

    // Overrides the superclass method to incorporate the attribute
    // height at the node, which allows you to check the balance of the AVL tree.
    protected NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }

}
