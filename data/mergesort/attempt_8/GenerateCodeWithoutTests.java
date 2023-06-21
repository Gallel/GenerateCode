
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {}

    public void sort(E[] vector, int n) {
        // Base case
        if (n < 2) {
            return;
        }

        // Divide the array in two halves
        int mid = n / 2;
        E[] left = (E[]) new Object[mid];
        E[] right = (E[]) new Object[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = vector[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = vector[i];
        }

        // Recursively sort the two halves
        sort(left, mid);
        sort(right, n - mid);

        // Merge the sorted halves
        int i = 0, j = 0, k = 0;
        while (i < mid && j < n - mid) {
            if (((Comparable<E>) left[i]).compareTo(right[j]) <= 0) {
                vector[k++] = left[i++];
            } else {
                vector[k++] = right[j++];
            }
        }
        while (i < mid) {
            vector[k++] = left[i++];
        }
        while (j < n - mid) {
            vector[k++] = right[j++];
        }
    }
}
