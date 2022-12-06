package leetcode.sort;

public class No327 {
    int cnt;
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] presum = new long[nums.length + 1];
        for (int i = 1; i < presum.length; i++) presum[i] = presum[i - 1] + nums[i - 1];
        merge(presum, 0, presum.length, lower, upper);
        return cnt;
    }
    void merge(long[] presum, int l, int r, int lower, int upper) {
        if (l + 1 == r) return;
        int m = l + r >> 1;
        merge(presum, l, m, lower, upper);
        merge(presum, m, r, lower, upper);
        for (int low = m, high = m, p1 = l; p1 < m; p1++) {
            while (low < r && presum[low] - presum[p1] < lower)    low++;
            while (high < r && presum[high] - presum[p1] <= upper) high++;
            cnt += high - low;
        }
        int n = r - l;
        long[] t = new long[n];
        for (int i = 0, p1 = l, p2 = m; i < n; i++) {
            if (p2 == r || p1 < m && presum[p1] < presum[p2]) t[i] = presum[p1++];
            else t[i] = presum[p2++];
        }
        System.arraycopy(t, 0, presum, l, n);
    }
}
