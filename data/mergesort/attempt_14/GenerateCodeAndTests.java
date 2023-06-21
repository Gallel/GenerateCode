
public class MergeSort<E> extends Object implements SortingAlgorithm<E> {

    public MergeSort() {
    }

    @Override
    public void sort(E[] vector, int n) {
        mergeSort(vector, 0, n - 1);
    }

    private void mergeSort(E[] vector, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(vector, left, center);
            mergeSort(vector, center + 1, right);
            merge(vector, left, center, right);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(E[] vector, int left, int center, int right) {
        E[] tmpVector = (E[]) new Object[vector.length];

        int leftEnd = center;
        int rightStart = center + 1;
        int tmpPos = left;

        while (left <= leftEnd && rightStart <= right) {
            if (((Comparable<E>) vector[left]).compareTo(vector[rightStart]) <= 0) {
                tmpVector[tmpPos++] = vector[left++];
            } else {
                tmpVector[tmpPos++] = vector[rightStart++];
            }
        }

        while (left <= leftEnd) {
            tmpVector[tmpPos++] = vector[left++];
        }

        while (rightStart <= right) {
            tmpVector[tmpPos++] = vector[rightStart++];
        }

        for (int i = left; i <= right; i++) {
            vector[i] = tmpVector[i];
        }
    }
}
