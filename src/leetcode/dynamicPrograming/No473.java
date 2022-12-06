package leetcode.dynamicPrograming;


import java.util.Arrays;

/**
 * 火柴拼正方形
 * 先求和再除以4，如果不为整数，返回false，否则开始分组
 * 思路：回溯，前三条边都能拼成则成功
 */
public class No473 {
    int border;
    Boolean[] dp = new Boolean[(1 << 15) - 1];
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int i : matchsticks) {
            sum += i;
        }
        if (sum % 4 != 0)   return false;
        sum /= 4;
        border = sum;
        Arrays.sort(matchsticks);
        return makesquare(matchsticks, 0, 1, sum, true);
    }
    private boolean makesquare(int[] matchsticks, int used, int k, int target, boolean isHead) {
        if (dp[used] != null)   return dp[used];
        if (k == 4) return true;
        if (target == 0) {
            return makesquare(matchsticks, used, k + 1, border, true);
        }else if (target < 0) {
            return false;
        }else {
            for (int i = matchsticks.length - 1; i >= 0 ; i--) {
                int state = 1 << i;
                if ((used & state) != 0)    continue;
                boolean check = makesquare(matchsticks, used | state, k, target - matchsticks[i], false);
                if (check)  return true;
                else if (!check && isHead)  return false;
            }
        }
        return false;
    }
}
