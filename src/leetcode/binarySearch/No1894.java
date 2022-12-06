package leetcode.binarySearch;

import java.util.ArrayList;
import java.util.Collections;

public class No1894 {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long[] sum = new long[n];
        sum[0] = chalk[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + chalk[i];
        }
        k %= sum[n - 1];
        int l = -1, r = n;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (sum[m] <= k)  l = m;
            else r = m;
        }
        return r;
    }
}
