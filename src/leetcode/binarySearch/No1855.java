package leetcode.binarySearch;

public class No1855 {
    public int maxDistance(int[] nums1, int[] nums2) {
        int max = 0, n = nums1.length;
        for (int i = 0 ; i < n; i++) {
            int l = -1, r = n;
            while (l + 1 < r) {
                int m = l + r >> 1;
                if (nums2[m] >= nums1[i]) l = m;
                else r = m;
            }
            max = Math.max(max, l - i);
        }
        return max;
    }
}
