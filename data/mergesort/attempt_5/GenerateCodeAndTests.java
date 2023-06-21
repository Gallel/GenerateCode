
public class MergeSort<E> extends Object implements SortingAlgorithm<E>{
    public MergeSort() {}
    
    private void merge(E[] left, E[] right, E[] result) {
        int i=0, j=0, k=0;
        while(i<left.length && j<right.length) {
            if(((Comparable<E>)left[i]).compareTo(right[j])<=0) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }
        System.arraycopy(left,  i,  result, k, left.length-i);
        System.arraycopy(right, j, result, k, right.length-j);
    }

    @SuppressWarnings("unchecked")
    public void sort(E[] vector, int n) {
        if(n<2) { return; }
        int mid = n/2;
        E[] left = (E[]) new Object[mid];
        E[] right = (E[]) new Object[n-mid];
        System.arraycopy(vector, 0, left, 0, mid);
        System.arraycopy(vector, mid, right, 0, n-mid);
        sort(left, mid);
        sort(right, n-mid);
        merge(left, right, vector);
    }
}
