
import java.util.Comparator;
import edu.uoc.ds.adt.sequential.LinkedBinaryTreeImpl;
import edu.uoc.ds.traversal.Traversal;
import edu.uoc.ds.exceptions.ElementNotFoundException;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E>{
    protected Comparator<E> comparator;

    public AVLTree(){
        super();
        comparator = null;
    }

    public AVLTree(Comparator<E> comparator){
        super();
        this.comparator = comparator;
    }

    public void add(E elemComp){
        if(empty()){
            addRoot(elemComp);
        }else{
            LinkedBinaryTreeImpl.NodeTree<E> newNodeParent = searchAdd(elemComp);
            addNode(newNodeParent, elemComp);
            balance(newNodeParent);
        }
    }

    public E get(E elemComp){
        LinkedBinaryTreeImpl.NodeTree<E> node = searchNode(findPosition(elemComp));
        if(node != null){
            return node.getElem();
        }else{
            throw new ElementNotFoundException();
        }
    }

    public E delete(E elemComp){
        LinkedBinaryTreeImpl.NodeTree<E> foundNode = searchNode(findPosition(elemComp));
        E elemToReturn = null;
        if(foundNode != null){
            LinkedBinaryTreeImpl.NodeTree<E> nodeToDelete = deleteNode(foundNode);
            elemToReturn = nodeToDelete.getElem();
            balance(nodeToDelete.getParentsLeaf());
        }else{
            throw new ElementNotFoundException();
        }
        return elemToReturn;
    }

    protected void balance(Position<E> bst){
        if(bst != null){
            int compareValue = ((LinkedBinaryTreeImpl.NodeTree<E>) bst).getBalanceFactor();
            if(compareValue > 1){
                if(((LinkedBinaryTreeImpl.NodeTree<E>) bst.getLeft()).getBalanceFactor() > 0){
                    rotateRight(bst.getLeft());
                }
                rotateLeft(bst);
            }else{
                if(compareValue < -1){
                    if(((LinkedBinaryTreeImpl.NodeTree<E>) bst.getRight()).getBalanceFactor() < 0){
                        rotateLeft(bst.getRight());
                    }
                    rotateRight(bst);
                }
            }
            balance(bst.getParentsLeaf());
        }
    }

    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp){
        return new NodeTreeAVL(parent, elemComp);
    } 

    private class NodeTreeAVL extends LinkedBinaryTreeImpl.NodeTree<E>{
        private int height = 1;

        public NodeTreeAVL(Position<E> parent, E elemComp){
            super(parent, elemComp);
        }

        public void updateHeight(){
            int leftHeight = 0, rightHeight = 0;
            if(this.getLeft() != null){
                leftHeight = ((NodeTreeAVL) this.getLeft()).getHeight();
            }
            if(this.getRight() != null){
                rightHeight = ((NodeTreeAVL)this.getRight()).getHeight();
            }
            this.height = 1 + ((leftHeight > rightHeight)? leftHeight: rightHeight);
        }

        public int getHeight(){
            return this.height;
        }

        public int getBalanceFactor(){
            int leftValue = -1, rightValue = -1;
            if(this.getLeft() != null){
                leftValue = ((NodeTreeAVL) this.getLeft()).getHeight();
            }
            if(this.getRight() != null){
                rightValue = ((NodeTreeAVL)this.getRight()).getHeight();
            }
            return leftValue - rightValue;
        }
    }  

    protected int compare(E elem1, E elem2){
        if(comparator != null){
            return comparator.compare(elem1, elem2);
        }else{
            return elem1.compareTo(elem2);
        }
    }

    public Boolean thereIsComparator(){
        return comparator != null;
    }
}
