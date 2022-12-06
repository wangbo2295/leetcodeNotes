package leetcode.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

public class No826 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length, m = worker.length;
        int[][] profits = new int[n][2];
        for (int i = 0; i < n; i++) {
            profits[i][0] = difficulty[i];
            profits[i][1] = profit[i];
        }
        Arrays.sort(profits, Comparator.comparingInt(o->o[0]));
        for (int i = 1; i < n; i++) {
            profits[i][1] = Math.max(profits[i - 1][1], profits[i][1]);
        }
        int max = 0;
        for (int j : worker) {
            int l = -1, r = n;
            while (l + 1 < r) {
                int mid = l + r >> 1;
                if (profits[mid][0] <= j) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            if (l < 0) continue;
            max += profits[l][1];
        }
        return max;
    }
}
