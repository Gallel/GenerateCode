
import java.util.Comparator;
import edu.uoc.ds.adt.sequential.LinkedBinaryTreeImpl;
import edu.uoc.ds.traversal.Traversal;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E> {
    
    private Comparator<E> comparator;
    
    // Constructor without parameters
    public AVLTree() {
        super();
        this.comparator = null;
    }
    
    // Constructor with a parameter
    public AVLTree(Comparator<E> comparator) {
        super();
        this.comparator = comparator;
    }
    
    // Method to check if there is a specific comparator
    public boolean thereIsComparator() {
        return comparator != null;
    }
    
    // Add an item to the appropriate position, if possible
    public void add(E elemComp) {
        Position<E> node = add(elemComp, root);
        balance(node);
    }
    
    // Delete the item, if you find it according to the comparator
    public E delete(E elemComp) {
        Position<E> node = findNode(elemComp, root);
        if (node == null) return null;
        E element = node.getElement();
        deleteNode(node);
        return element;
    }
    
    // Retrieves an element of the tree
    public E get(E elemComp) {
        return findNode(elemComp, root).getElement();
    }
    
    // Overrides the superclass method to incorporate the attribute height at the node
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }
    
    // Method that restores the balance of the node tree after each insertion or deletion
    protected void balance(Position<E> bst) {
        while (bst != null) {
            int balanceFactor = height(left(bst)) - height(right(bst));
            if (Math.abs(balanceFactor) > 1) {
                if (height(left(left(bst))) >= height(right(left(bst)))) {
                    bst = rebalanceWithLeftChild(bst);
                } else {
                    bst = doubleWithLeftChild(bst);
                }
            } else {
                updateHeight(bst);
            }
            bst = parent(bst);
        }
    }
    
    // Protected method that compares received items
    protected int compare(E elem1, E elem2) {
        if (thereIsComparator()) {
            return comparator.compare(elem1, elem2);
        } else {
            return elem1.compareTo(elem2);
        }
    }
    
    // Private method that calculate the height of a node
    private int height(Position<E> p) {
        if (p == null) return -1;
        return ((NodeTree<E>)p).height;
    }
    
    // Private method to update the height of a node
    private void updateHeight(Position<E> p) {
        ((NodeTree<E>)p).height = 1 + Math.max(height(left(p)), height(right(p)));
    }
    
    // Private method to perform a single rotation to the left
    private Position<E> rotateWithLeftChild(Position<E> bst) {
        Position<E> newRoot = left(bst);
        setLeft(bst, right(newRoot));
        setRight(newRoot, bst);
        updateHeight(bst);
        updateHeight(newRoot);
        return newRoot;
    }
    
    // Private method to perform a single rotation to the right
    private Position<E> rotateWithRightChild(Position<E> bst) {
        Position<E> newRoot = right(bst);
        setRight(bst, left(newRoot));
        setLeft(newRoot, bst);
        updateHeight(bst);
        updateHeight(newRoot);
        return newRoot;
    }
    
    // Private method to perform a double rotation with left child
    private Position<E> doubleWithLeftChild(Position<E> bst) {
        setLeft(bst, rotateWithRightChild(left(bst)));
        return rotateWithLeftChild(bst);
    }
    
    // Private method to rebalance with left child
    private Position<E> rebalanceWithLeftChild(Position<E> bst) {
        return rotateWithRightChild(bst);
    }
    
    // Node tree with the attribute height
    private static class NodeTree<E> extends Node<E> {
        int height;
        
        public NodeTree(Position<E> parent, E element) {
            super(parent, element);
            height = 0;
        }
    }
}
