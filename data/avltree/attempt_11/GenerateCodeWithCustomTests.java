
import java.util.Comparator;

public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTreeImpl<E>{
    
    private Comparator<E> comparator = null;
    
    public AVLTree(){}
    
    public AVLTree(Comparator<E> comparator){
        this.comparator = comparator;
    }
    
    private int compare(E elem1, E elem2){
        if(comparator != null){
            return comparator.compare(elem1, elem2);
        } else {
            return elem1.compareTo(elem2);
        }
    }
    
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp){
        return new AVLNode<>(parent, elemComp);
    }
    
    public boolean thereIsComparator(){
        return comparator != null;
    }
    
    private class AVLNode<E> extends LinkedBinaryTreeImpl.NodeTree<E> {
        private int height;
        
        AVLNode(Position<E> parent, E elemComp) {
            super(parent, elemComp);
            height = 1;
        }
    }
    
    public void add(E elemComp) {
        Position<E> posNode = this.root();
        while(!this.isLeaf(posNode)){
            int cmp = compare(elemComp, posNode.getElement());
            if(cmp < 0) {
                posNode = this.leftSubtree(posNode);
            } else if (cmp > 0){
                posNode = this.rightSubtree(posNode);
            } else {
                return;
            }
        }
        super.add(posNode, elemComp);
        rebalance(posNode);
    }
    
    private void rebalance(Position<E> posNode) {
        int newHeight = 1 + Math.max(height(leftSubtree(posNode)), height(rightSubtree(posNode)));
        ((AVLNode<E>)posNode).height = newHeight;
        int balanceFactor = height(leftSubtree(posNode)) - height(rightSubtree(posNode));
        if(Math.abs(balanceFactor) > 1) {
            int factorInsertion = compare(posNode.getElement(), parent(posNode).getElement());
            if(balanceFactor > 1) {
                if(factorInsertion < 0) {
                    avlRightRotation(posNode);
                } else {
                    avlLeftRotation(rightSubtree(posNode));
                    avlRightRotation(posNode);
                }
            } else if(factorInsertion > 0) {
                avlLeftRotation(posNode);
            } else {
                avlRightRotation(leftSubtree(posNode));
                avlLeftRotation(posNode);
            }
        }
        if(parent(posNode) != null) {
            rebalance(parent(posNode));
        }
    }
    
    private void avlLeftRotation(Position<E> posNode) {
        Position<E> newPosition = rotateLeft(posNode);
        ((AVLNode<E>)newPosition).height = Math.max(height(leftSubtree(newPosition)), height(rightSubtree(newPosition))) + 1;
        ((AVLNode<E>)posNode).height = Math.max(height(leftSubtree(posNode)), height(rightSubtree(posNode))) + 1;
    }
    
    private void avlRightRotation(Position<E> posNode) {
        Position<E> newPosition = rotateRight(posNode);
        ((AVLNode<E>)newPosition).height = Math.max(height(leftSubtree(newPosition)), height(rightSubtree(newPosition))) + 1;
        ((AVLNode<E>)posNode).height = Math.max(height(leftSubtree(posNode)), height(rightSubtree(posNode))) + 1;
    }
    
    public E delete(E elemComp) {
        if(this.root() == null) {
            return null;
        }
        Position<E> posNode = position(elemComp);
        if(posNode == null) {
            return null;
        }
        E oldNode = super.delete(posNode);
        if(oldNode != null) {
            rebalance(parent(posNode));
        }
        return oldNode;
    }

    public E get(E elemComp) {
        Position<E> posNode = position(elemComp);
        if(posNode == null){
            return null;
        } else {
            return posNode.getElement();
        }
    }
    
}
