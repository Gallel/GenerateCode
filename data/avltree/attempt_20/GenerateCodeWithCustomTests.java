
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    // Constructor without parameters
    public AVLTree() {}
    
    // Constructor with a parameter
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    // Add an item to the appropriate position, if possible
    public void add(E elemComp) {
        Position<E> pos = findPosition(elemComp);
        addElement(elemComp, pos);
    }
    
    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion
    protected void balance(Position<E> bst) {
        if (bst != null) {
            int balanceFactor = height(left(bst)) - height(right(bst));
            if (balanceFactor > 1) {
                if (height(left(left(bst))) >= height(right(left(bst)))) {
                    rotateRight(bst);
                } else {
                    rotateLeftRight(bst);
                }
            } else if (balanceFactor < -1) {
                if (height(right(right(bst))) >= height(left(right(bst)))) {
                    rotateLeft(bst);
                } else {
                    rotateRightLeft(bst);
                }
            }
            balance(parent(bst));
        }
    }
    
    // Protected method that compares received items
    protected int compare(E elem1, E elem2) {
        if (comparator != null) {
            return comparator.compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }
    
    // Delete the item, if you find it according to the comparator
    public E delete(E elemComp) {
        Position<E> pos = findPosition(elemComp);
        if (!pos.getElement().equals(elemComp)) {
            return null;
        }
        E result = pos.getElement();
        if (numChildren(pos) == 2) {
            Position<E> sucessor = inorderFollowing(pos);
            swap(pos, sucessor);
            pos = sucessor;
        }
        Position<E> child = (left(pos) != null ? left(pos) : right(pos));
        if (child != null) {
            setParent(child, parent(pos));
        }
        if (pos == root()) {
            setRoot(child);
        } else {
            Position<E> parent = parent(pos);
            if (pos == left(parent)) {
                setLeft(parent, child);
            } else {
                setRight(parent, child);
            }
            balance(parent);
        }
        size--;
        return result;
    }
    
    // Retrieves an element of the tree
    public E get(E elemComp) {
        Position<E> pos = findPosition(elemComp);
        if (pos.getElement().equals(elemComp)) {
            return pos.getElement();
        } else {
            return null;
        }
    }
    
    // Overrides the superclass method to incorporate the attribute height at the node, which allows 
    // you to check the balance of the AVL tree
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<>(parent, elemComp);
    }
    
    // Method to check if there is a specific comparator
    public boolean thereIsComparator() {
        return comparator != null;
    }
}
