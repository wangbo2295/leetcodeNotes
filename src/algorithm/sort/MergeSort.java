package algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public int[] mergeSort(int[] arr){
        return merge(Arrays.copyOfRange(arr,0,arr.length>>1),
                Arrays.copyOfRange(arr,(arr.length>>1)+1,arr.length-1));
    }
    public int[] merge(int[] arr1,int[] arr2){
        int[] arr3 = new int[arr1.length+arr2.length];
        int i=0,j=0,k=0;
        while(i<arr1.length||j<arr2.length){
            if(arr1[i]<arr2[j]){
                arr3[k++] = arr1[i++];
            }else {
                arr3[k++] = arr2[j++];
            }
        }
        if (i==arr1.length){
            System.arraycopy(arr2,j,arr3,k,arr2.length-j);
        }else{
            System.arraycopy(arr1,i,arr3,k,arr1.length-i);
        }
        return arr3;
    }
}
