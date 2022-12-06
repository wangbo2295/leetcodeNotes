package leetcode.dynamicPrograming.stateCompression;

import java.util.Arrays;

public class No698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0)   return false;
        int m = sum / k;
        int n = nums.length;
        Arrays.sort(nums);
        if (nums[n - 1] > m) return false;
        int size = 1 << n;
        boolean[] dp = new boolean[size];
        int[] cursum = new int[size];
        dp[0] = true;
        for (int i = 0; i < size; i++) {
            if (!dp[i]) continue;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) != 0)  continue;
                int add = i | 1 << j;
                if (dp[add])    continue;
                if ((cursum[i] + nums[j]) % k <= m) {
                    cursum[add] = cursum[i] + nums[j];
                    dp[add] = true;
                } else {
                    break;
                }
            }
        }
        return dp[size - 1];
    }
}
