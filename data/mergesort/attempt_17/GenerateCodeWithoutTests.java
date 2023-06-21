
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {
        // constructor code
    }

    public void sort(E[] vector, int n) {
        // sort method code
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        E[] l = (E[]) new Object[mid];
        E[] r = (E[]) new Object[n - mid];
        for (int i = 0; i < mid; i++) {
            l[i] = vector[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = vector[i];
        }
        sort(l, mid);
        sort(r, n - mid);
        merge(vector, l, r, mid, n - mid);
    }

    private void merge(E[] a, E[] l, E[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (((Comparable<E>) l[i]).compareTo(r[j]) <= 0) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
