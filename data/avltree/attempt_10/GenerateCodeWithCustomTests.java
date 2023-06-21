
import java.util.Comparator;
import edu.uoc.ds.adt.tree.LinkedBinaryTreeImpl;
import edu.uoc.ds.adt.tree.Position;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    public AVLTree() {
        super();
    }
    
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    protected void balance(Position<E> bst) {
        if (bst == null) {
            return;
        }
        int diff = height(right(bst)) - height(left(bst));
        if (diff > 1) {
            if (height(right(right(bst))) > height(left(right(bst)))) {
                bst = rotateLeft(bst);
            } else {
                bst = doubleRotateLeft(bst);
            }
        } else if (diff < -1) {
            if (height(left(left(bst))) > height(right(left(bst)))) {
                bst = rotateRight(bst);
            } else {
                bst = doubleRotateRight(bst);
            }
        }
        if (bst.getParent() != null) {
            balance(bst.getParent());
        } else {
            setRoot(bst);
        }
    }
    
    protected int compare(E elem1, E elem2) {
        if (comparator() != null) {
            return comparator().compare(elem1, elem2);
        } else {
            return ((Comparable<E>) elem1).compareTo(elem2);
        }
    }
    
    public E delete(E elemComp) {
        Position<E> bst = find(elemComp);
        if (bst == null) {
            return null;
        }
        E result = bst.getElement();
        if (numChildren(bst) == 2) {
            Position<E> successor = treeMin(right(bst));
            bst.setElement(successor.getElement());
            bst = successor;
        }
        Position<E> fix = parent(bst);
        super.delete(bst);
        balance(fix);
        return result;
    }
    
    public E get(E elemComp) {
        Position<E> bst = find(elemComp);
        if (bst == null) {
            return null;
        }
        return bst.getElement();
    }
    
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<>(parent, elemComp);
    }
    
    public void add(E elemComp) {
        NodeTree<E> newNode = (NodeTree<E>) createNode(elemComp);
        if (isEmpty()) {
            setRoot(newNode);
        } else {
            Position<E> pos = find(elemComp);
            int cmp = compare(elemComp, pos.getElement());
            if (cmp == 0) {
                pos.setElement(elemComp);
            } else {
                newNode.setParent(pos);
                if (cmp < 0) {
                    pos.setLeft(newNode);
                } else {
                    pos.setRight(newNode);
                }
                balance(newNode);
            }
        }
        size++;
    }
    
    public boolean thereIsComparator() {
        return (comparator() != null);
    }
}
