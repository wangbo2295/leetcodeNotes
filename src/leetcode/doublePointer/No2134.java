package leetcode.doublePointer;

public class No2134 {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n * 2 + 1];
        System.arraycopy(nums, 0, arr, 1, n);
        System.arraycopy(nums, 0, arr, n + 1, n);
        int ans = n, cnt = 0;
        for (int i : nums) cnt += i;
        for (int i = 1; i <= 2 * n; i++) arr[i] += arr[i - 1];
        for (int i = cnt; i <= 2 * n; i++) {
            ans = Math.min(ans, cnt - arr[i] + arr[i - cnt]);
        }
        return ans;
    }
}
