
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
 
    public AVLTree() {
        super();
    }
  
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
  
    protected void balance(Position<E> bst) {
        if (bst == null)
            return;
        int balance = height((NodeTree<E>) left(bst)) - height((NodeTree<E>) right(bst));
        if ((Math.abs(balance) <= 1) && isNodeTree(bst)) {
            balance(left(bst));
            balance(right(bst));
        } else {
            if (balance > 1) {
                if (height((NodeTree<E>) left(left(bst))) >= height((NodeTree<E>) right(left(bst)))) {
                    rotateRight(bst);
                } else {
                    rotateLeft(right(bst));
                    rotateRight(bst);
                }
            } else if (balance < -1) {
                if (height((NodeTree<E>) right(right(bst))) >= height((NodeTree<E>) left(right(bst)))) {
                    rotateLeft(bst);
                } else {
                    rotateRight(left(bst));
                    rotateLeft(bst);
                }
            }
        }
    }
 
    public void add(E elemComp) {
        if (elemComp == null)
            throw new IllegalArgumentException("Null elements are not allowed.");
        NodeTree<E> newNode = new NodeTree<E>(elemComp, null, null, null);
        if (super.isEmpty()) {
            root = newNode;
        } else {
            NodeTree<E> temp = super.addBST(newNode, null, comparator);
            while (temp != null) {
                balance(temp);
                temp = temp.getParent();
            }
        }
        size++;
    }
  
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<>(parent, elemComp);
    }
 
    public E delete(E elemComp) {
        NodeTree<E> node = (NodeTree<E>) searchNodeTree(elemComp, (NodeTree<E>) root);
        E item = node.getElement();
        node = deleteNodeTree(node);
        return item;
    }
 
    public boolean thereIsComparator() {
        return super.thereIsComparator();
    }
 
}
