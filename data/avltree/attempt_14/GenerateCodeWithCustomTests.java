
import java.util.Comparator;

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
        int diff = this.height(this.left(bst)) - this.height(this.right(bst));
        if (Math.abs(diff) <= 1) {
            return;
        }
        if (diff > 0) {
            if (this.height(this.left(this.left(bst))) >= this.height(this.right(this.left(bst)))) {
                this.rightRotation(bst);
            } else {
                this.leftRotation(this.left(bst));
                this.rightRotation(bst);
            }
        } else {
            if (this.height(this.right(this.right(bst))) >= this.height(this.left(this.right(bst)))) {
                this.leftRotation(bst);
            } else {
                this.rightRotation(this.right(bst));
                this.leftRotation(bst);
            }
        }
    }

    public E delete(E elemComp) {
        Position<E> rem = this.search(elemComp);
        if (rem != null) {
            E result = rem.getElement();
            Position<E> aux = null;
            if (this.numChildren(rem) == 2) {
                aux = rem;
                rem = this.sucesor(aux);
                result = aux.getElement();
                aux.setElement(rem.getElement());
            }
            Position<E> pall = (rem.getLeft() != null ? rem.getLeft() : rem.getRight());
            if (pall != null) {
                pall.setParent(rem.getParent());
            }
            if (rem == this.root()) {
                this.setRoot(pall);
            } else {
                if (rem == rem.getParent().getLeft()) {
                    rem.getParent().setLeft(pall);
                } else {
                    rem.getParent().setRight(pall);
                }
                this.balance(rem.getParent());
            }
            this.size--;
            return result;
        }
        return null;
    }

    public boolean thereIsComparator() {
        return this.comparator != null;
    }

    public void add(E elemComp) {
        Position<E> pos = this.insertItem(elemComp);
        if (pos != null) {
            this.balance(pos);
        }
    }

    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp) {
        return new AVLNode<E>(parent, elemComp);
    }

    protected int compare(E elem1, E elem2) {
        return (this.comparator != null ? this.comparator.compare(elem1, elem2) : ((Comparable<E>) elem1).compareTo(elem2));
    }

    public E get(E elemComp) {
        Position<E> pos = this.search(elemComp);
        return (pos != null ? pos.getElement() : null);
    }

    protected static class AVLNode<E> extends LinkedBinaryTreeImpl.NodeTree<E> implements Position<E> {
        private int height = 1;

        public AVLNode(Position<E> parent, E elemComp) {
            super(parent, elemComp);
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
