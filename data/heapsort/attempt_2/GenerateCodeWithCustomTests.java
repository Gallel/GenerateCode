
public class HeapSort<E> extends Object implements SortingAlgorithm<E> {

    public HeapSort() {
    }

    public HeapSort(E[] vector) {
    }

    @Override
    public void sort(int n) {
    }

    @Override
    public void sort(E[] vector, int n) {
        PriorityQueue<E> priorityQueue = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            priorityQueue.add(vector[i]);
        }
        for (int i = 0; i < n; i++) {
            vector[i] = priorityQueue.poll();
        }
    }
}
