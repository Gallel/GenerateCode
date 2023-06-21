
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {
    
    public MergeSort() {}
    
    public void sort(E[] vector, int n) {
        if(n < 2) {
            return;
        }
        
        int middle = n / 2;
        E[] left = (E[]) new Object[middle];
        E[] right = (E[]) new Object[n - middle];
        
        for(int i = 0; i < middle; i++) {
            left[i] = vector[i];
        }
        for(int i = middle; i < n; i++) {
            right[i - middle] = vector[i];
        }
        
        sort(left, middle);
        sort(right, n - middle);
        merge(vector, left, right, middle, n - middle);
    }
    
    private void merge(E[] vector, E[] left, E[] right, int leftIndex, int rightIndex) {
        int i = 0, j = 0, k = 0;
        
        while(i < leftIndex && j < rightIndex) {
            if(((Comparable<E>) left[i]).compareTo(right[j]) <= 0) {
                vector[k++] = left[i++];
            } else {
                vector[k++] = right[j++];
            }
        }
        
        while(i < leftIndex) {
            vector[k++] = left[i++];
        }
        while(j < rightIndex) {
            vector[k++] = right[j++];
        }
    }
}
