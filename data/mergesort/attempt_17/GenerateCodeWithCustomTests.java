
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {}

    public void sort(E[] vector, int n) {
        if(n < 2) {
            return;
        }

        int half = n / 2;
        E[] leftVector = (E[]) new Object[half];
        E[] rightVector = (E[]) new Object[n - half];

        for(int i = 0; i < half; i++) {
            leftVector[i] = vector[i];
        }

        for(int i = half; i < n; i++) {
            rightVector[i - half] = vector[i];
        }

        sort(leftVector, half);
        sort(rightVector, n - half);

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < half && j < n - half) {
            if(((Comparable<E>) leftVector[i]).compareTo(rightVector[j]) <= 0) {
                vector[k++] = leftVector[i++];
            } else {
                vector[k++] = rightVector[j++];
            }
        }

        while(i < half) {
            vector[k++] = leftVector[i++];
        }

        while(j < n - half) {
            vector[k++] = rightVector[j++];
        }
    }
}
