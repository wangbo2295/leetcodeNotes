package leetcode.dynamicPrograming.rob;

public class No198 {
    /**
     * 先来一个暴力枚举, 显然是会超时的
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        return Math.max(rob(nums, 0, true), rob(nums, 0, false));
    }
    public int rob(int[] nums, int index, boolean stole) {
        if (index >= nums.length) {
            return 0;
        }
        int max;
        if (!stole) {
            int rob = rob(nums, index + 1, true);
            int norob = rob(nums, index + 1, false);
            max = Math.max(rob, norob);
        }else {
            max = nums[index] + rob(nums, index + 1, false);
        }
        return max;
    }

    /**
     * 动态规划：每个屋子都有偷与不偷两种状态，根据递归的递推公式，可以建立dp数组存储计算结果
     * dp[i][j]: 第 i 个房子偷和不偷的最大收益（j = 0， 1）
     * dp[i][0] = max(dp[i + 1][0], nums[i] + dp[i + 1][1]);
     * dp[i][1] = nums[i] + dp[i + 1][0];
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[nums.length - 1][1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1]);
            dp[i][1] = nums[i] + dp[i + 1][0];
        }
        return Math.max(dp[0][0], dp[0][1]);
    }

    /**
     * 注意到递推公式只跟上个状态有关，所以可以进行状态压缩
     */
    public int rob3(int[] nums) {
        int stole = nums[0];
        int keep = 0;
        for (int i = 1; i < nums.length; i++) {
            int t = keep;
            keep = Math.max(keep, stole);
            stole = nums[i] + t;
        }
        return Math.max(stole, keep);
    }
}
