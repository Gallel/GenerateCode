
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    // Constructor
    MergeSort() {}

    // Sort method
    void sort(E[] vector, int n) {
        if (n < 2) {
            return; // base case
        }
        int mid = n / 2;

        // Divide the vector
        E[] left = (E[]) new Object[mid];
        E[] right = (E[]) new Object[n - mid];
        System.arraycopy(vector, 0, left, 0, mid);
        System.arraycopy(vector, mid, right, 0, n - mid);

        // Recursively apply the algorithm
        sort(left, mid);
        sort(right, n - mid);

        // Merge the two parts
        merge(vector, left, right, mid, n - mid);
    }

    // Merge method
    private void merge(E[] vector, E[] left, E[] right, int l, int r) {
        int i = 0, j = 0, k = 0;
        while (i < l && j < r) {
            if (((Comparable<E>) left[i]).compareTo(right[j]) <= 0) {
                vector[k++] = left[i++];
            } else {
                vector[k++] = right[j++];
            }
        }
        while (i < l) {
            vector[k++] = left[i++];
        }
        while (j < r) {
            vector[k++] = right[j++];
        }
    }
}
