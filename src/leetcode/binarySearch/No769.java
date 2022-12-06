package leetcode.binarySearch;

import java.util.Arrays;

public class No769 {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int cnt = 0, max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            if (max == i)   ++cnt;
        }
        return cnt;
    }
}
