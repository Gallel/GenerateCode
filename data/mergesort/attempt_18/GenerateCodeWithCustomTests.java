
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {
    
    public MergeSort() {
    }
    
    public void sort(E[] vector, int n) {
        if (vector==null || n<=1) {
            return;
        }
        int mid = n / 2;
        E[] left = (E[]) new Object[mid];
        E[] right = (E[]) new Object[n-mid];
        for (int i = 0; i < mid; i++) {
            left[i] = vector[i];
        }
        for (int i = mid; i < n; i++) {
            right[i-mid] = vector[i];
        }
        sort(left, mid);
        sort(right, n-mid);
        merge(vector, left, right, mid, n-mid);
    }
    
    private void merge(E[] vector, E[] left, E[] right, int nL, int nR) {
        int i = 0, j = 0, k = 0;
        while (i < nL && j < nR) {
            if (left[i] != null && ((Comparable<E>) left[i]).compareTo(right[j]) <= 0) {
                vector[k++] = left[i++];
            } else {
                vector[k++] = right[j++];
            }
        }
        while (i < nL) {
            vector[k++] = left[i++];
        }
        while (j < nR) {
            vector[k++] = right[j++];
        }
    }
}
