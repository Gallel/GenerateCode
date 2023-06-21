
import java.util.Comparator;
import edu.uoc.ds.adt.binarytree.LinkedBinaryTreeImpl;
import edu.uoc.ds.adt.binarytree.Position;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    private Comparator<E> comparator;
    
    //Empty constructor
    public AVLTree() {
        super();
    }
    
    //Constructor with a Comparator parameter
    public AVLTree(Comparator<E> comparator) {
        super();
        this.comparator = comparator;
    }
    
    //Add an item to the appropriate position, if possible.
    public void add(E elemComp) {
        this.addBSTNode(elemComp);
        this.balance(this.position(elemComp, this.root()));
    }
    
    //Method that restores, if necessary, the balance of the node tree
    protected void balance(Position<E> bst) {
        if (bst != null) {
            int diff = this.depth(this.left(bst)) - this.depth(this.right(bst));
            if (diff > 1) {
                if (this.depth(this.left(this.left(bst))) >= this.depth(this.right(this.left(bst)))) {
                    this.rotateRight(bst);
                } else {
                    this.rotateLeftRight(bst);
                }
            } else if (diff < -1) {
                if (this.depth(this.right(this.right(bst))) >= this.depth(this.left(this.right(bst)))) {
                    this.rotateLeft(bst);
                } else {
                    this.rotateRightLeft(bst);
                }
            }
            this.balance(this.parent(bst));
        }
    }
    
    //Protected method that compares the received items
    protected int compare(E elem1, E elem2) {
        int resp;
        if (comparator != null) {
            resp = comparator.compare(elem1, elem2);
        } else {
            resp = ((Comparable<E>) elem1).compareTo(elem2);
        }
        return resp;
    }
    
    //Delete the item if you find it according to the comparator.
    public E delete(E elemComp) {
        Position<E> p = this.position(elemComp, this.root());
        if(p != null && elemComp.equals(p.getElement())) {
            return this.deletePosition(p);
        }
        return null;
    }
    
    //Retrieve an element of the tree.
    public E get(E elemComp) {
        Position<E> p = this.position(elemComp, this.root());
        if(p != null && elemComp.equals(p.getElement())) {
            return p.getElement();
        }
        return null;
    }
    
    //Overrides the superclass method to incorporate the attribute height at the node
    protected NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }
    
    //Method to check if there is a specific comparator.
    public boolean thereIsComparator() {
        return comparator != null;
    }
}
