package leetcode.binarySearch;

public class No1712 {
    public int waysToSplit(int[] nums) {
        int n = nums.length, MOD = 1_000_000_007;
        for (int i = 1; i < n; i++)   nums[i] += nums[i - 1];
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            int left = upperBound(nums, 2 * nums[i], i, n - 1);
            int right = upperBound(nums, (nums[i] + nums[n - 1] >> 1) + 1, i, n - 1);
            if (left < right && left > i)  ans = (ans + right - left) % MOD;
        }
        return ans;
    }

    public int upperBound(int[] nums, int tar, int l, int r) {
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (nums[m] < tar)  l = m;
            else r = m;
        }
        return r;
    }
}
