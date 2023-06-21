
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {
    
    public MergeSort() {
    }
    
    public void sort(E[] vector, int n) {
        if (n < 2) {
            // base case
            return;
        }
        int mid = n / 2;
        E[] left = (E[]) new Object[mid];
        E[] right = (E[]) new Object[n - mid];
        
        // copy values to left and right arrays
        for (int i = 0; i < mid; i++) {
            left[i] = vector[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = vector[i];
        }
        // apply the algorithm recursively to sort both halves
        sort(left, mid);
        sort(right, n - mid);
        // merge the sorted halves
        merge(vector, left, right, mid, n - mid);
    }
    
    private void merge(E[] vector, E[] left, E[] right, int leftSize, int rightSize) {
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (((Comparable<E>) left[i]).compareTo(right[j]) <= 0) {
                vector[k++] = left[i++];
            } else {
                vector[k++] = right[j++];
            }
        }
        // copy remaining elements from left array
        while (i < leftSize) {
            vector[k++] = left[i++];
        }
        //copy remaining elements from right array
        while (j < rightSize) {
            vector[k++] = right[j++];
        }
    }
}
