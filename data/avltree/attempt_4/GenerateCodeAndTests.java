
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    //Constructor without parameters
    public AVLTree() {
        super();
    }
    
    //Constructor with a parameter
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    //Method to add an item to the appropriate position
    public void add(E elemComp) {
        if (elemComp == null) {
            throw new NullPointerException("Item not valid");
        }
        Position<E> node = createNode(elemComp);
        addNode(node);
        balance(node);
    }

    //Method that restores the balance of the AVL tree
    protected void balance(Position<E> bst) {
        if (bst != null) {
            int factor = height(left(bst)) - height(right(bst));
            if (factor > 1) {
                if (height(left(left(bst))) >= height(right(left(bst)))) {
                    bst = rightRotation(bst);
                } else {
                    bst = doubleRightRotation(bst);
                }
            } else if (factor < -1) {
                if (height(right(right(bst))) >= height(left(right(bst)))) {
                    bst = leftRotation(bst);
                } else {
                    bst = doubleLeftRotation(bst);
                }
            }
            if (bst != null) {
                balance(parent(bst));
            }
        }
    }

    //Protected method that compares received items
    protected int compare(E elem1, E elem2) {
        return (comparator != null) ? comparator.compare(elem1, elem2)
                : ((Comparable<E>) elem1).compareTo(elem2);
    }

    //Method to delete item
    public E delete(E elemComp) {
        if (elemComp == null) {
            throw new NullPointerException("Item not valid");
        }
        Position<E> node = searchNode(elemComp);
        if (node == null) {
            return null;
        }
        E toReturn = node.getElement();
        Position<E> toBalance = isLeaf(node) ? parent(node) : node;
        Position<E> replacement = null;
        if (!isLeaf(node)) {
            Position<E> aux = node;
            aux = right(aux);
            while (!isLeaf(aux)) {
                aux = left(aux);
            }
            replacement = parent(aux) != node ? parent(aux) : node;
            toBalance = parent(aux);
            node.setElement(aux.getElement());
            aux.setElement(elemComp);
        }
        deleteNode(node);
        balance(toBalance);
        return toReturn;
    }

    //Method to retrieve an element of the tree
    public E get(E elemComp) {
        if (elemComp == null) {
            throw new NullPointerException("Item not valid");
        }
        Position<E> node = searchNode(elemComp);
        return node != null ? node.getElement() : null;
    }

    //Method that adds height attribute to NodeTree objects to keep track of changes
    protected NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new AVLNode<E>(parent, elemComp);
    }
    
    //Method to check if there is a specific comparator
    public boolean thereIsComparator() {
        return comparator != null;
    }

    //Subclass that extends NodeTree to include height attribute
    protected static class AVLNode<E> extends NodeTree<E> {
        private int height;

        public AVLNode(Position<E> parent, E element) {
            super(parent, element);
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
