
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {
    public MergeSort() {}

    public void sort(E[] vector, int n) {
        if (n < 2) {
            return;
        }
        int middle = n / 2;
        E[] left = (E[]) new Object[middle];
        E[] right = (E[]) new Object[n - middle];
        for (int i = 0; i < middle; i++) {
            left[i] = vector[i];
        }
        for (int i = middle; i < n; i++) {
            right[i - middle] = vector[i];
        }
        sort(left, middle);
        sort(right, n - middle);
        merge(vector, left, right, middle, n - middle);
    }

    private void merge(E[] vector, E[] left, E[] right, int i, int j) {
        int k = 0, l = 0, m = 0;
        while (k < i && l < j) {
            if (((Comparable<E>) left[k]).compareTo(right[l]) < 0) {
                vector[m++] = left[k++];
            } else {
                vector[m++] = right[l++];
            }
        }
        while (k < i) {
            vector[m++] = left[k++];
        }
        while (l < j) {
            vector[m++] = right[l++];
        }
    }
}
