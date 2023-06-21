
public class MergeSort<E> implements SortingAlgorithm<E> {
    
    public MergeSort() {
    }
    
    public void sort(E[] vector, int n) {
        if (n<2) {
            return;
        }
        int mid = n / 2;
        E[] l = Arrays.copyOfRange(vector, 0, mid);
        E[] r = Arrays.copyOfRange(vector, mid, n);
        sort(l, mid);
        sort(r, n - mid);
        merge(vector, l, r, mid, n - mid);
    }
 
    private void merge(E[] v, E[] l, E[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (((Comparable<E>) l[i]).compareTo(r[j]) <= 0) {
                v[k++] = l[i++];
            } else {
                v[k++] = r[j++];
            }
        }
        while (i < left) {
            v[k++] = l[i++];
        }
        while (j < right) {
            v[k++] = r[j++];
        }
    }
}

