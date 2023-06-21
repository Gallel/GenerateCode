
public class MergeSort<E> extends Object implements SortingAlgorithm<E>{
	public MergeSort() {
	}
	
	public void sort(E[] vector, int n) {
		if (n < 2) {
			return;
		}

		int mid = n / 2;
		E[] left = (E[]) new Object[mid];
		E[] right = (E[]) new Object[n - mid];

		System.arraycopy(vector, 0, left, 0, mid);
		System.arraycopy(vector, mid, right, 0, n - mid);

		sort(left, mid);
		sort(right, n - mid);

		merge(vector, left, right, mid, n - mid);
	}

	public void merge(E[] vector, E[] left, E[] right, int i, int j) {
		int k = 0;
		int l = 0;
		int r = 0;
		while (l < i && r < j) {
			if (((Comparable<E>) left[l]).compareTo(right[r]) <= 0) {
				vector[k++] = left[l++];
			} else {
				vector[k++] = right[r++];
			}
		}
		while (l < i) {
			vector[k++] = left[l++];
		}
		while (r < j) {
			vector[k++] = right[r++];
		}
	}
}
