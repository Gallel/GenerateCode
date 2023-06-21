
public class HeapSort<E> extends Object implements SortingAlgorithm<E> {

    public HeapSort() {
    }

    public HeapSort(E[] vector) {
    }

    public void sort(int n) {
    }

    public void sort(E[] vector, int n) {
        PriorityQueue<E> pq = new PriorityQueue<>(vector[0], Collections.reverseOrder());

        for (int i = 1; i < n; i++) {
          pq.add(vector[i]);
        }

        for (int i = 0; i < n; i++) {
          vector[i] = pq.poll();
        }
    }
}
