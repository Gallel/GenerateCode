
public class LinkedList<E> extends Object implements List<E>{

    public LinkedList(){} //Empty list.

    public E delete(Position<E> node){ //Delete received position.
        return null;
    }

    public E deleteFirst(){ //Delete the first position in the list.
        return null;
    }

    public E deleteNext(Position<E> node){ //Delete the next position.
        return null;
    }

    public Position<E> insertAfter(Position<E> node, E elem){ //Add an item after the received position.
        return null;
    }

    public Position<E> insertBefore(Position<E> node, E elem){ //Add an item before the received position.
        return null;
    }

    public Position<E> insertBeginning(E elem){ //Add an item to the top of the list.
        return null;
    }

    public Position<E> insertEnd(E elem){ //Add an item to the bottom of the list.
        return null;
    }

    public boolean isEmpty(){ //Method to check if the container is empty.
        return false;
    }

    protected LinkedList.LinkedNode<E> newPosition(LinkedList.LinkedNode<E> node, E elem){ //Creates a chained node, places it next to the receipt as parameterize and increase the number of items.
        return null;
    }

    public Traversal<E> positions(){ //Method that supports multiple paths, from the positions of the container, simultaneous and independent of each other.
        return null;
    }

    protected LinkedList.LinkedNode<E> previous(LinkedList.LinkedNode<E> node){ //Returns the node before the received one as a parameter.
        return null;
    }

    public int size(){ //Retrieves the number of items in the container.
        return 0;
    }

    public void swap(Position<E> node1, Position<E> node2){ //Exchange items contained in received positions.
    
    }

    @Override
    public String toString(){ //Method overwriting Object.toString ().
        return null;
    }

    public E update(Position<E> node, E elem){ //Replaces the item contained in the received position.
        return null;
    }

    @Override
    public Iterator<E> values(){ //Retrieves the items in the container.
        return null;
    }

}
