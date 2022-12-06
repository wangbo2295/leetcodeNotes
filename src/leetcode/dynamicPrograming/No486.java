package leetcode.dynamicPrograming;

public class No486 {

    /**
     * 预测赢家
     * 思路一：递归穷举法
     * 根据游戏规则，每个人都以最优策略选择，实际上就是每次选择都依赖于上次对方选择结果
     * 由此可以往递归的方向想，自底向上地考虑
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        return bottom2Top(nums, 0, nums.length - 1, 1) > 0;
    }
    //turn用来区别玩家一和玩家二，玩家二时turn为-1，所以最后返回的分数是玩家一的最大分数。
    public int bottom2Top(int[] nums, int start, int end, int turn) {
        if (start >= end) return nums[start] * turn;
        int startScore = nums[start] * turn + bottom2Top(nums, start + 1, end, -turn);
        int endScore = nums[end] * turn + bottom2Top(nums, start, end - 1, -turn);
        //由于玩家二的分数是负数，所以先转换为正数在比较大小，然后在转化为负数返回
        return Math.max(startScore * turn, endScore * turn) * turn;
    }

    /**
     * 思路二：注意到递归法中有很多重复计算，考虑用动态规划改进算法
     * 注意到重复子问题为子数组相同时的问题，所以定义dp[i][j] 为当剩下(i,j)时，当前玩家和对方的分数差的最大值
     * 由定义知，如果当前剩下（0, len - 1） 时dp[0][len - 1] >= 0,    玩家一就能赢
     * 状态转移方程：dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
     */
    public boolean PredictTheWinner2(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = nums.length; i >= 0; i--) {
            for (int j = i; j < nums.length; j++) {
                if (i == j) dp[i][j] = nums[i];
                else dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }
}
