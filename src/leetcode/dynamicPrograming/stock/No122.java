package leetcode.dynamicPrograming.stock;

/**
 * 买卖股票II
 * 多次买卖
 * 思路： 每天两种状态，持有和不持有
 */
public class No122 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[prices.length - 1][0];
    }

    /**
     * 状态压缩
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int hold = -prices[0];
        int notHold = 0;
        for (int i = 1; i < prices.length; i++) {
            int t = notHold;
            notHold = Math.max(hold + prices[i], notHold);
            hold = Math.max(notHold - prices[i], hold);
        }
        return notHold;
    }
}
