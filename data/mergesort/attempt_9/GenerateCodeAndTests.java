
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {
    public MergeSort() {}
    
    public void sort(E[] vector, int n) {
        mergeSort(vector, 0, n-1);
    }
    
    private void mergeSort(E[] vector, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) return;
        
        int middleIndex = (leftIndex + rightIndex) / 2;
        
        mergeSort(vector, leftIndex, middleIndex);
        mergeSort(vector, middleIndex+1, rightIndex);
        
        merge(vector, leftIndex, middleIndex, rightIndex);
    }
    
    private void merge(E[] vector, int leftIndex, int middleIndex, int rightIndex) {
        E[] helper = (E[]) new Object[vector.length];
        
        for (int i = leftIndex; i <= rightIndex; i++) {
            helper[i] = vector[i];
        }
        
        int helperLeft = leftIndex;
        int helperRight = middleIndex+1;
        int current = leftIndex;
        
        while (helperLeft <= middleIndex && helperRight <= rightIndex) {
            if (((Comparable<E>)helper[helperLeft]).compareTo(helper[helperRight]) < 0) {
                vector[current] = helper[helperLeft];
                helperLeft++;
            } else {
                vector[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }
        
        while (helperLeft <= middleIndex) {
            vector[current] = helper[helperLeft];
            current++;
            helperLeft++;
        }
    }
}
