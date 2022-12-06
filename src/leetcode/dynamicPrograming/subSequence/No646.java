package leetcode.dynamicPrograming.subSequence;

import java.util.Arrays;
import java.util.Comparator;

public class No646 {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(o->o[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(o->o[1]));
        int len = 1, pre = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > pre) {
                ++len;
                pre = pairs[i][1];
            }
        }
        return len;
    }
}
