package 剑指offer;

public class ReversePairs {
    int cnt;
    public int reversePairs(int[] nums) {
        if (nums.length < 2)   return 0;
        mergeAndCount(nums, 0, nums.length);
        return cnt;
    }

    void mergeAndCount(int[] nums, int l, int r) {
        if (l + 1 == r) return;
        int m = l + r >> 1;
        mergeAndCount(nums, l, m);
        mergeAndCount(nums, m, r);
        int[] t = new int[r - l];
        for (int i = 0, p1 = l, p2 = m; i < t.length; i++) {
            if (p2 == r || (p1 < m && nums[p1] > nums[p2])) {
                cnt += r - p2;
                t[i] = nums[p1++];
            } else if (p1 == m || (p2 < r && nums[p2] >= nums[p1])){
                t[i] = nums[p2++];
            }
        }
        System.arraycopy(t, 0, nums, l, r - l);
    }
}
