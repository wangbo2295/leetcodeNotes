package leetcode.dynamicPrograming;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 */
public class No907 {
    /**
     * 思路：超时
     */
    public int sumSubarrayMins(int[] arr) {
        int ans = 0;
        int MOD = 1000000007;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int cnt = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] >= min) {
                    cnt++;
                }else {
                    ans += min * cnt;
                    ans %= MOD;
                    min = arr[j];
                    cnt = 1;
                }
            }
        }
        return ans;
    }

    /**
     * 思路：
     */
    public int sumSubarrayMins2(int[] arr) {
        int ans = 0;
        int MOD = 1000000007;

        return ans;
    }
}
