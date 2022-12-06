package leetcode.WC;

import java.util.Arrays;

public class No299 {
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    if (grid[i][j] == 0)
                        return false;
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int countHousePlacements(int n) {
        int MOD = 1_000_000_007;
        int zero = 0, up = 0, down = 0, two = 0;
        int pz, pu, pd, pt;
        pz = pu = pd = pt = 1;
        if (n == 1) return 4;
        for (int i = 1; i < n; i++) {
            zero = ((pz + pu) % MOD + (pd + pt) % MOD) % MOD;
            up = (pz + pd) % MOD;
            down = (pz + pu) % MOD;
            two = pz % MOD;
            pz = zero;
            pu = up;
            pd = down;
            pt = two;
        }
        return ((zero + up) % MOD + (down + two) % MOD) % MOD;
    }

    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] merge = new int[n];
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        for (int i = 0; i < n; i++) {
            merge[i] = nums1[i] - nums2[i];
        }
        int sum1 = 0, max1 = Integer.MIN_VALUE;
        int sum2 = 0, max2 = Integer.MAX_VALUE;
        for (int fast = 0; fast < merge.length; fast++) {
            if (sum1 < 0) {
                sum1 = 0;
            }
            if (sum2 > 0) {
                sum2 = 0;
            }
            sum1 += merge[fast];
            max1 = Math.max(max1, sum1);
            sum2 += merge[fast];
            max2 = Math.min(max2, sum2);
        }
        return Math.max(s2 + max1, s1 - max2);
    }
}
