
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {
        //constructor
    }
    
    public void sort(E[] vector, int n) {
        if (n < 2) { //base case
            return;
        }
        int middle = n/2;
        E[] left = (E[]) new Object[middle];
        E[] right = (E[]) new Object[n-middle];
        for (int i=0; i<middle; i++) {
            left[i] = vector[i];
        }
        for (int i=middle; i<n; i++) {
            right[i-middle] = vector[i];
        }
        sort(left, middle); //recursive call
        sort(right, n-middle); //recursive call
        merge(vector, left, right, middle, n-middle); //merge the parts
    }
    
    private void merge(E[] vector, E[] left, E[] right, int leftSize, int rightSize) {
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (left[i].compareTo(right[j]) <= 0) {
                vector[k++] = left[i++];
            }
            else {
                vector[k++] = right[j++];
            }
        }
        while (i < leftSize) {
            vector[k++] = left[i++];
        }
        while (j < rightSize) {
            vector[k++] = right[j++];
        }
    }
}
