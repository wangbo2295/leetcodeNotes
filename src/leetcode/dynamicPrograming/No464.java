package leetcode.dynamicPrograming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class No464 {

    /**
     * 递归 + 备忘录。 还是超时
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        if (maxChoosableInteger >= desiredTotal) return true;
        Map<String, Boolean> map = new HashMap<>();
        int[] used = new int[maxChoosableInteger + 1];
        return canIWin(desiredTotal, used, map);
    }

    public boolean canIWin(int desiredTotal, int[] used, Map<String, Boolean> map) {
        String key = Arrays.toString(used);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        for (int i = 1; i < used.length; i++) {
            if (used[i] == 0) {
                used[i] = 1;
                if (desiredTotal - i <=0 || !canIWin(desiredTotal - i, used, map)) {
                    map.put(key, true);
                    used[i] = 0;
                    return true;
                }
                used[i] = 0;
            }
        }
        map.put(key, false);
        return false;
    }


    /**
     * 回溯 + 动态规划
     * 位运算记录数字的使用状态：
     * 二进制第 i 位记录数字 i 是否被使用
     * 对于数字 i ，转化为tmp = 1 << (i - 1), 生成的数字为二进制中第i位为1的数字，如果state & tmp == 0 ，说明i没有被使用
     * dp[]数组为Boolean[], 用包装类的原因是，当没有该状态的记录时为null，这样可以起到一个备忘录的作用
     * dp[]的空间为 (1 << max) - 1, 先移位再减1生产的数包含了所有state的数量，即2^max - 1 (不包括0)
     * 递归的过程中不分玩家，都看成当前玩家，如果当前玩家所有的选择都不能赢，则返回false
     * 对于赢的情况有两种，一是当前做出选择后对手无法获胜导致其返回false，二是当前选择某个数能超过target
     * 对于输的情况，当选择任何数都无法超过target并且选择任何数对手都返回true时，返回false。
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        Boolean[] dp = new Boolean[(1 << maxChoosableInteger) - 1];
        return dfs(0, desiredTotal, dp, maxChoosableInteger);
    }

    public boolean dfs(int state, int desiredTotal, Boolean[] dp, int maxChoosableInteger) {
        if (dp[state] != null) {
            return dp[state];
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            int tmp = 1 << (i - 1);
            if ((tmp & state) == 0) {
                if (desiredTotal - i <= 0 || !dfs(state | tmp, desiredTotal - i, dp, maxChoosableInteger)) {
                    dp[state] = true;
                    return true;
                }
            }
        }
        dp[state] = false;
        return false;
    }


}
