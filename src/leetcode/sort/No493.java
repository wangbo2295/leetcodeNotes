package leetcode.sort;

public class No493 {

    int cnt;

    public int reversePairs(int[] nums) {
        merge(nums, 0, nums.length);
        return cnt;
    }

    public void merge(int[] nums, int l, int r) {
        if (l + 1 == r) return;
        int m = l + r >> 1;
        merge(nums, l, m);
        merge(nums, m, r);
        for (int p1 = l, p2 = m; p1 < m && p2 < r;) {
            while (p2 < r && (long) nums[p1] <= (long) 2 * nums[p2])   p2++;
            cnt += r - p2;
            p1++;
        }
        int len = r - l;
        int[] t = new int[len];
        for (int i = 0, p1 = l, p2 = m; i < len; i++) {
            if (p2 == r || p1 < m && nums[p1] > nums[p2])    t[i] = nums[p1++];
            else t[i] = nums[p2++];
        }
        System.arraycopy(t, 0, nums, l, len);
    }

    public static void main(String[] args) {
        No493 no493 = new No493();
        int[] nums = {1,3,2,3,1};
        no493.reversePairs(nums);
    }

}
