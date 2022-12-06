package leetcode.dynamicPrograming.subSequence;

/**
 * 问题函数：LCL(i, j) --- nums1 以 i 结尾的子数组和 nums2 以 j 结尾的子数组的最长相等子数组长度
 * 子问题最优解:
 * if nums1[i] == nums2[j]
 *  then LCL(i, j) = LCL(i - 1, j - 1) + 1
 */
public class No718 {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int maxLen = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums2[i] == nums1[0]) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(dp[i][j], maxLen);
                }
            }
        }
        return maxLen;
    }

    /**
     * 滚动数组实现
     * 要注意两点：
     * 一是内层遍历要从后往前，否则用dp[j - 1]的时候不是上层的结果而是当层覆盖后的结果。
     * 二是在判断不相等之后要将dp[j] 置为0，否则由于滚动数组的关系会沿用上层的结果。
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int maxLen = 0;
        int[] dp = new int[n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = n; j > 0; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    maxLen = Math.max(dp[j], maxLen);
                }else {
                    dp[j] = 0;
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        No718 no718 = new No718();
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};
        int length = no718.findLength2(nums1, nums2);
        System.out.println(length);
    }
}
