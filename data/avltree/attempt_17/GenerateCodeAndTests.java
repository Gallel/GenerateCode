
import java.util.Comparator;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E> {

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
        position<E> pos = find(elemComp);

        if (pos.getElement() == null) {
            addElement(elemComp);
            rebalance(pos);
        }
    }

    // Method that restores, if necessary, the balance of the node tree after each insertion or deletion.
    protected void rebalance(Position<E> p) {
        if (p != null) {
            int lh = getLeftHeight(p);
            int rh = getRightHeight(p);

            if (lh > u * rh) {
                Position<E> childL = tree.getLeft(p);
                int llh = getLeftHeight(childL);
                int lrh = getRightHeight(childL);

                if (llh >= lrh)
                    rotateRight(p);
                else {
                    rotateLeft(childL);
                    rotateRight(p);
                }
            } else if (rh > u * lh) {
                Position<E> childR = tree.getRight(p);
                int rlh = getLeftHeight(childR);
                int rrh = getRightHeight(childR);

                if (rrh >= rlh)
                    rotateLeft(p);
                else {
                    rotateRight(childR);
                    rotateLeft(p);
                }
            }

            if (tree.isRoot(p))
                tree.setHeight(p, Math.max(getLeftHeight(p), getRightHeight(p)) + 1);
            rebalance(tree.getParent(p));
        }
    }

    // Protected method to calculate the height of a node.
    protected int getHeight(Position<E> p) {
        return tree.getHeight(p);
    }

    // Protected method to calculate the height of the left subtree.
    protected int getLeftHeight(Position<E> p) {
        return getHeight(tree.getLeft(p));
    }

    // Protected method to calculate the height of the right subtree.
    protected int getRightHeight(Position<E> p) {
        return getHeight(tree.getRight(p));
    }

    // Delete the item, if you find it according to the comparator.
    public E delete(E elemComp) {
        Position<E> pos = find(elemComp);
        if (isExternal(pos)) return null;
        E toReturn = pos.getElement();
        if (isInternal(tree.getLeft(pos)) && isInternal(tree.getRight(pos))) {
            Position<E> exchange = tree.getMax(tree.getLeft(pos));
            pos.setElement(exchange.getElement());
            pos = exchange;
        }
        Position<E> cPos;
        if (isExternal(tree.getLeft(pos))) cPos = tree.getLeft(pos);
        else cPos = tree.getRight(pos);
        Position<E> parent = tree.getParent(pos);
        tree.attach(parent, cPos);
        rebalance(parent);
        return toReturn;
    }

    // Retrieves an element of the tree.
    public E get(E elemComp) {
        Position<E> pos = find(elemComp);
        if (isExternal(pos)) return null;
        return pos.getElement();
    }

    // Overrides the superclass method to incorporate the attribute height at the node, which allows you to check the balance of the AVL tree.
    protected NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new AVLNode<>(parent, elemComp);
    }

    // Method to check if there is a specific comparator.
    public boolean thereIsComparator() {
        return comparator != null;
    }

    private final static double u=1.44;

    // Internal class of AVLNode.
    private static class AVLNode<E> extends NodeTree<E> {
        private int height;

        // Constructor.
        public AVLNode(Position<E> parent, E elemComp) {
            super(parent, elemComp);
            height = 1;
        }

        // Get the height of the node.
        public int getHeight() {
            return height;
        }

        // Set the height of the node.
        public void setHeight(int h) {
            height = h;
        }
    }
}
