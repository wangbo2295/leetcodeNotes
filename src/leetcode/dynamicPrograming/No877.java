package leetcode.dynamicPrograming;

/**
 * Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i]。
 * 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
 * Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
 * 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回true，当 Bob 赢得比赛时返回false。
 */
public class No877 {
    /**
     * 博弈的技巧：虽然是两个玩家在玩，但任意轮可以看作当前玩家
     * 只记录当前玩家的相对分数，也就是当前玩家的得分减去对方的得分
     * 那么就只需一个数组记录当前玩家的相对分数，计算相对分数时用这轮的得分减去上一轮对方的相对分数就得到当前的相对分数
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        for (int i = piles.length - 1; i >= 0; i--) {
            for (int j = i; j < piles.length; j++) {
                if (j == i) {
                    dp[i][j] = piles[i];
                } else {
                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
                }
            }
        }
        return dp[0][piles.length - 1] > 0;
    }

    /**
     * 先回溯捋一下思路
     * 显然是超时的，不过可以用备忘录记录已得出的结果
     * 记录剩下(i, j)时当前玩家能否胜利
     * 但进一步的可以用动态规划
     * @param piles
     * @return
     */
    public boolean stoneGame2(int[] piles) {
        int max = dfs(piles, 0, piles.length - 1, 1, 0);
        return max > 0;
    }
    public int dfs(int[] piles, int left, int right, int turn, int scores) {
        if (left > right) {
            return scores * turn;
        }
        int sl = dfs(piles, left + 1, right, -turn, scores + turn * piles[left]);
        int sr = dfs(piles, left, right - 1, -turn, scores + turn * piles[right]);
        return Math.max(sl * turn, sr * turn) * turn;
    }
}
