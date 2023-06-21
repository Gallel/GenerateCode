
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {
    }
    
    private void merge(E[] v, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        E[] L = (E[]) new Object[n1];
        E[] R = (E[]) new Object[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = v[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = v[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (((Comparable) L[i]).compareTo(R[j]) <= 0) {
                v[k] = L[i];
                i++;
            } else {
                v[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            v[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            v[k] = R[j];
            j++;
            k++;
        }
    }

    private void mergeSort(E[] v, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = (l + r) / 2;
        mergeSort(v, l, m);
        mergeSort(v, m + 1, r);
        merge(v, l, m, r);
    }

    public void sort(E[] vector, int n) {
        mergeSort(vector, 0, n - 1);
    }
}
