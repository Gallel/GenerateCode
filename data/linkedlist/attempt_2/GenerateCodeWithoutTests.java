
public class LinkedList<E> extends Object implements List<E>{
    //attributes
    
    //empty constructor 
    LinkedList(){}
    
    //delete received position
    E delete(Position<E> node){
        //code here
    }
    
    //delete the first position in the list
    E deleteFirst(){
        //code here
    }
    
    //delete the next position
    E deleteNext(Position<E> node){
        //code here
    }
    
    //add an item after the received position
    Position<E> insertAfter(Position<E> node, E elem){
        //code here
    }
    
    //add an item before the received position
    Position<E> insertBefore(Position<E> node, E elem){
        //code here
    }
    
    //add an item to the top of the list
    Position<E> insertBeginning(E elem){
        //code here
    }
    
    //add an item to the bottom of the list
    Position<E> insertEnd(E elem){
        //code here
    }
    
    //method to check if the container is empty
    boolean isEmpty(){
        //code here
    }
    
    //creates a chained node, places it next to the receipt as parameterize 
    //and increases the number of items
    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem){
        //code here
    }
    
    //method that supports multiple paths, from the positions of the container, 
    //simultaneous and independent of each other
    Traversal<E> positions(){
        //code here
    }
    
    //returns the node before the received one as a parameter
    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node){
        //code here
    }
    
    //retrieves the number of items in the container
    int size(){
        //code here
    }
    
    //exchange items contained in received positions
    void swap(Position<E> node1, Position<E> node2){
        //code here
    }
    
    //method overwriting Object.toString()
    String toString(){
        //code here
    }
    
    //replaces the item contained in the received position
    E update(Position<E> node, E elem){
        //code here
    }
    
    //retrieves the items in the container
    Iterator<E> values(){
        //code here
    }
}
