package leetcode.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

public class No1818 {
    int[] nums1;
    int[] nums2;
    Integer[] ids;
    int n, before, after, decl;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        this.nums1 = nums1;this.nums2 = nums2;n = nums1.length;
        ids = new Integer[n];
        int MOD = (int) 1e9 + 7;
        for (int i = 0; i < n; i++) ids[i] = i;
        int[] init = new int[n];
        for (int i = 0; i < n; i++) init[i] = Math.abs(nums1[i] - nums2[i]);
        Arrays.sort(ids, Comparator.comparingInt(o->nums1[o]));
        for (int i = 0; i < n; i++) {
            int near = near(nums2[i]);
            int diff = Math.abs(nums1[ids[near]] - nums2[i]);
            if (init[i] - diff > decl) {
                decl = init[i] - diff;
                before = i;
                after = ids[near];
            }
        }
        nums1[before] = nums1[after];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(nums1[i] - nums2[i]) % MOD;
            ans %= MOD;
        }
        return ans;
    }

    public int near(int tar) {
        int l = -1, r = n;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (nums1[ids[m]] <= tar)   l = m;
            else r = m;
        }
        if (l < 0)  return r;
        if (r == n) return l;
        return nums1[ids[r]] - tar < tar - nums1[ids[l]] ? r : l;
    }
}
