package leetcode.binarySearch;

import java.util.Arrays;

public class No768 {
    /**
     * 遍历过程中在线计算 hash 值，遇到分割点进行分割并重置 hash
     */
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] sorted = new int[n];
        System.arraycopy(arr, 0, sorted, 0, n);
        Arrays.sort(sorted);
        int cnt = 0, suma = 0, muta = 1, sums = 0, muts = 1;
        for (int i = 0; i < n; i++) {
            suma += arr[i]; muta *= arr[i];
            sums += sorted[i]; muts *= sorted[i];
            if (suma + muta == sums + muts) {
                ++cnt;
                suma = sums = 0;
                muta = muts = 1;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        No768 no768 = new No768();
        int[] arr = {2,1,4,4,3,4};
        no768.maxChunksToSorted(arr);
    }
}
