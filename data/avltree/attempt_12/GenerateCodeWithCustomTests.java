
import java.util.Comparator;
import edu.uoc.ds.adt.sequential.LinkedBinaryTreeImpl;
import edu.uoc.ds.adt.sequential.Position;
import edu.uoc.ds.traversal.Traversal;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E> {

    private Comparator<E> comparator;

    public AVLTree() {}

    public AVLTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E elemComp) {
        Position<E> pos = genericAdd(elemComp);
        balance(pos);
    }

    public E delete(E elemComp) {
        Position<E> pos = findPosition(elemComp);
        if (pos == null)
            return null;
        E elem = pos.getElement();
        if (!isLeaf(pos) || size() == 1) {
            Position<E> toSwap = genericDelete(pos);
            if (toSwap != null)
                balance(toSwap);
        }
        return elem;
    }

    public E get(E elemComp) {
        Position<E> pos = findPosition(elemComp);
        return pos != null ? pos.getElement() : null;
    }

    public boolean thereIsComparator() {
        return comparator != null;
    }

    protected void balance(Position<E> bst) {
        if (bst==null) return;

        int balance = height(left(bst)) - height(right(bst));
        if (balance > 1) {
            if (height(left(left(bst))) >= height(right(left(bst))))
                bst = rightRotation(bst);
            else {
                bst = leftRightRotation(bst);
            }
        } else if (balance < -1) {
            if (height(right(right(bst))) >= height(left(right(bst))))
                bst = leftRotation(bst);
            else {
                bst = rightLeftRotation(bst);
            }
        }
        if (bst.getParent() != null)
            balance(bst.getParent());
        else
            super.setRoot(bst);
    }

    protected int compare(E elem1, E elem2) {
        if (comparator != null)
            return comparator.compare(elem1, elem2);
        return elem1.compareTo(elem2);
    }

    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree(parent, elemComp);
    }

    // Rotation methods (left, right, left-right, right-left)
    // are not provided

}
