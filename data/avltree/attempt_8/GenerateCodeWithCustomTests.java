
import java.util.Comparator;

public class AVLTree<E> extends LinkedBinaryTreeImpl<E> {
    
    /**
     * Constructor without parameters.
     */
    public AVLTree() {
        super();
    }
    
    /**
     * Constructor with a parameter.
     */
    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }
    
    /**
     * Add an item to the appropriate position, if possible.
     */
    public void add(E elemComp) {
        if (elemComp == null) {
            throw new IllegalArgumentException();
        }
        Position<E> p = super.add(elemComp);
        rebalance(p);
    }
    
    protected void rebalance(Position<E> bst) {
        if (bst == null) {
            throw new IllegalArgumentException();
        }
        int difference = super.height(super.left(bst)) - super.height(super.right(bst));
        if (Math.abs(difference) > 1) {
            Position<E> futureRoot = bst;
            if (difference < 0) {
                if (super.height(super.right(super.right(bst))) > super.height(super.left(super.right(bst)))) {
                    futureRoot = super.rotateLeftRight(bst);
                } else {
                    futureRoot = super.rotateLeftLeft(bst);
                }
            } else if (difference > 0) {
                if (super.height(super.left(super.left(bst))) > super.height(super.right(super.left(bst)))) {
                    futureRoot = super.rotateRightLeft(bst);
                } else {
                    futureRoot = super.rotateRightRight(bst);
                }
            }
            rebalance(futureRoot);
        }
        super.updateHeight(bst);
    }
    
    protected int compare(E elem1, E elem2) {
        if (super.getComparator() != null) {
            return super.getComparator().compare(elem1, elem2);
        } else {
            return ((Comparable<E>)elem1).compareTo(elem2);
        }
    }
    
    public boolean thereIsComparator() {
        return (super.getComparator() != null);
    }
    
    public E delete(E elemComp) {
        if (elemComp == null || super.size() == 0 || (thereIsComparator() == false && !(elemComp instanceof Comparable))) {
            throw new IllegalArgumentException();
        }
        Position<E> p = super.find(elemComp);
        E toReturn = get(p);
        if (super.isExternal(p)) {
            return null;
        } else if (super.isInternal(super.left(p)) && super.isInternal(super.right(p))) {
            Position<E> r = super.minIMPL(super.right(p));
            super.swap(p, r);
            p = r;
        }
        Position<E> successor = super.left(p);
        if (super.isExternal(successor)) {
            successor = super.right(p);
        }
        if (super.isExternal(successor)) {
            successor = p;
            p = super.parent(p);
        } else {
            p = super.parent(successor);
        }
        super.remove(successor);
        rebalance(p);
        return toReturn;
    }
    
    public E get(E elemComp) {
        if (elemComp == null || super.size() == 0 || (thereIsComparator() == false && !(elemComp instanceof Comparable))) {
            throw new IllegalArgumentException();
        }
        Position<E> p = super.find(elemComp);
        if (p == null || super.isExternal(p)) {
            return null;
        }
        return p.getElement();
    }
    
    @Override
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new NodeTree<E>(parent, elemComp);
    }
    
    protected static class NodeTree<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
        private int height;
        
        public NodeTree(Position<E> par, E elemComp) {
            super(par, elemComp);
            this.height = 0;
        }
        
        public int getHeight() {
            return this.height;
        }
        
        public void setHeight(int height) {
            this.height = height;
        }
    }
    
    public boolean checkNodesHeight() {
        return checkNodesHeightRecursively(super.root());
    }
    
    protected boolean checkNodesHeightRecursively(Position<E> node) {
        if (node == null || super.isExternal(node)) {
            return true;
        } else {
            NodeTree<E> curNode = (NodeTree<E>)node;
            int leftHeight = curNode.hasLeft() ? ((NodeTree<E>)super.left(node)).getHeight() : -1;
            int rightHeight = curNode.hasRight() ? ((NodeTree<E>)super.right(node)).getHeight() : -1;
            boolean retval = Math.abs(leftHeight - rightHeight) < 2;
            return retval && checkNodesHeightRecursively(super.left(node))
                    && checkNodesHeightRecursively(super.right(node));
        }
    }
}
