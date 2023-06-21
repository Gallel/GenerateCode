
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {} //constructor
    
    public void sort(E[] vector, int n) {
        //implementation of merge sort algorithm using divide and conquer technique
        if (n < 2) { //base case
            return;
        }
        int mid = n / 2;
        E[] left = (E[]) new Object[mid];
        E[] right = (E[]) new Object[n - mid];
        //divide the vector into two parts
        for (int i = 0; i < mid; i++) {
            left[i] = vector[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = vector[i];
        }
        //recursively apply the merge sort algorithm to both parts
        sort(left, mid);
        sort(right, n - mid);
        //merge the two sorted arrays
        merge(vector, left, right, mid, n - mid);
    }
    
    private void merge(E[] vector, E[] left, E[] right, int l, int r) {
        int i = 0, j = 0, k = 0;
        //merge the left and right arrays in ascending order
        while (i < l && j < r) {
            if (((Comparable<E>)left[i]).compareTo(right[j]) <= 0) {
                vector[k++] = left[i++];
            } else {
                vector[k++] = right[j++];
            }
        }
        //copy any remaining elements from left or right array
        while (i < l) {
            vector[k++] = left[i++];
        }
        while (j < r) {
            vector[k++] = right[j++];
        }
    }
}
