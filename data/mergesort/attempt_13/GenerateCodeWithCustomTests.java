
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {}

    public void sort(E[] vector, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        E[] left = (E[]) new Object[mid];
        E[] right = (E[]) new Object[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = vector[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = vector[i];
        }
        sort(left, mid);
        sort(right, n - mid);

        int i = 0, j = 0, k = 0;
        while (i < mid && j < n - mid) {
            if (left[i].hashCode() <= right[j].hashCode()) {
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
