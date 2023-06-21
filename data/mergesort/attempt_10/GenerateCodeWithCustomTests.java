
public class MergeSort<E> implements SortingAlgorithm<E>{
    
    public MergeSort(){
    }

    public void sort(E[] vector, int n){
        if(n<=1) return;
        int mid = n/2;
        E[] v1 = Arrays.copyOfRange(vector, 0, mid);
        E[] v2 = Arrays.copyOfRange(vector, mid, n);
        sort(v1, mid);
        sort(v2, n-mid);

        int i = 0, j = 0, k = 0;
        while(i<mid && j<n-mid){
            if(((Comparable<E>)v1[i]).compareTo(v2[j])<=0){
                vector[k++] = v1[i++];
            }else{
                vector[k++] = v2[j++];
            }
        }
        while(i<mid){
            vector[k++] = v1[i++];
        }
        while(j<n-mid){
            vector[k++] = v2[j++];
        }
    }
}
