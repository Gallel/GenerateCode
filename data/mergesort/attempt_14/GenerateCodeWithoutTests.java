
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {
        // constructor code here
    }

    public void sort(E[] vector, int n) {
        // sort method code here
        if (n < 2)
            return;

        int mid = n / 2;
        E[] left = (E[]) new Comparable[mid];
        E[] right = (E[]) new Comparable[n - mid];

        for (int i = 0; i < mid; i++)
            left[i] = vector[i];
        for (int i = mid; i < n; i++)
            right[i - mid] = vector[i];

        sort(left, mid);
        sort(right, n - mid);

        merge(vector, left, right, mid, n - mid);
    }

    private void merge(E[] vector, E[] left, E[] right, int l, int r) {
        // merge method code here
        int i = 0, j = 0, k = 0;
        while (i < l && j < r) {
            if (left[i].compareTo(right[j]) <= 0)
                vector[k++] = left[i++];
            else
                vector[k++] = right[j++];
        }

        while (i < l)
            vector[k++] = left[i++];

        while (j < r)
            vector[k++] = right[j++];
    }
}
