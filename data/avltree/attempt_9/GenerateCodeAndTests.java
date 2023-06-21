
public class AVLTree<E> extends LinkedBinaryTreeImpl<E>{
    //Constructor without parameter
    public AVLTree(){ }

    //Constructor with Comparator<E> parameter
    public AVLTree(Comparator<E> comparator){ }

    //Method that restores the balance of the tree if necessary
    protected void balance(Position<E> bst){ }

    //void add(E elemComp) method for adding an item to AVL tree
    public void add(E elemComp){ }

    //protected int compare (E elem1, E elem2) method for comparing two elements
    protected int compare(E elem1, E elem2){ }

    //E delete(E elementComp) method for deleting an item from AVL tree
    public E delete(E elemComp){ }

    //E get(E elemComp) method for retrieving an item from AVL tree
    public E get(E elemComp){ }

    //newNode(Position<E> parent, E elemComp) method for creating a new node
    protected LinkedBinaryTreeImpl.NodeTree<E> newNode(Position<E> parent, E elemComp){ }

    //boolean thereIsComparator() method to check whether there is a comparator or not
    public boolean thereIsComparator(){ }
}
