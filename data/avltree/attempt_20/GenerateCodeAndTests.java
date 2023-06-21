
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    private Comparator<E> comparator;
    
    // Constructor without parameters.
    public AVLTree() {
        this.comparator = null;
    }
    
    // Constructor with a parameter.
    public AVLTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    
    // Add an item to the appropriate position, if possible.
    public void add(E elemComp) {
        add(elemComp, root());
    }
    
    private void add(E elemComp, Position<E> v) {
        if (isEmpty()) {
            addRoot(elemComp);
        } else {
            int comp = compare(elemComp, v.getElement());
            if (comp < 0) {
                if (!hasLeft(v)) {
                    expandExternal(v, elemComp);
                    rebalance(v);
                } else {
                    add(elemComp, left(v));
                }
            } else if (comp > 0) {
                if (!hasRight(v)) {
                    expandExternal(v, elemComp);
                    rebalance(v);
                } else {
                    add(elemComp, right(v));
                }
            }
        }
    }
    
    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion.
    protected void balance(Position<E> v) {
        if (v != null) {
            if (height(left(v)) > u * height(right(v))) {
                if (height(left(left(v))) >= height(right(left(v)))) {
                    rotateRight(v);
                } else {
                    doubleRotateLeft(v);
                }
            } else if (height(right(v)) > u * height(left(v))) {
                if (height(right(right(v))) >= height(left(right(v)))) {
                    rotateLeft(v);
                } else {
                    doubleRotateRight(v);
                }
            }
            recomputeHeight(v);
        }
    }
    
    // Protected method that compares received items.
    protected int compare(E elem1, E elem2) {
        if (comparator != null) {
            return comparator.compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }
    
    // Delete the item, if you find it according to the comparator.
    public E delete(E elemComp) {
        Position<E> p = findPosition(elemComp);
        if (isExternal(p)) {
            throw new RuntimeException("The element does not exist.");
        }
        E removed = p.getElement();
        if (isInternal(left(p)) && isInternal(right(p))) {
            Position<E> replacement = max(left(p));
            p.setElement(replacement.getElement());
            p = replacement;
        }
        Position<E> leaf;
        if (isExternal(left(p))) {
            leaf = left(p);
        } else {
            leaf = right(p);
        }
        Position<E> sib = sibling(leaf);
        remove(leaf);
        remove(p);
        recomputeHeight(sib);
        rebalance(sib);
        return removed;
    }
    
    // Retrieves an element of the tree.
    public E get(E elemComp) {
        Position<E> p = findPosition(elemComp);
        if (isExternal(p)) {
            return null;
        } else {
            return p.getElement();
        }
    }
    
    // Overrides the superclass method to incorporate the attribute height at the node, 
    // which allows you to check the balance of the AVL tree.
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }
    
    // Method to check if there is a specific comparator.
    public boolean thereIsComparator() {
        return this.comparator != null;
    }
}
