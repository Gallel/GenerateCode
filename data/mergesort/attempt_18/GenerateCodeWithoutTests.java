
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {
        //constructor code
        
    }
    
    public void sort(E[] vector, int n) {
        //sort code
        
        if (n < 2) {
            return;
        }
        
        int mid = n / 2;
        E[] left = Arrays.copyOfRange(vector, 0, mid);
        E[] right = Arrays.copyOfRange(vector, mid, n);
        
        sort(left, mid);
        sort(right, n - mid);
        merge(vector, left, right, mid, n - mid);
    }
    
    void merge(E[] vector, E[] left, E[] right, int leftLength, int rightLength) {
        //merge code
        
        int i = 0, j = 0, k = 0;
        
        while (i < leftLength && j < rightLength) {
            if (left[i].compareTo(right[j]) <= 0) {
                vector[k++] = left[i++];
            } else {
                vector[k++] = right[j++];
            }
        }
        
        while (i < leftLength) {
            vector[k++] = left[i++];
        }
        
        while (j < rightLength) {
            vector[k++] = right[j++];
        }
    }
}
