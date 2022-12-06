package leetcode.dynamicPrograming.stock;

/**
 * 买卖股票III
 * 只能买卖两次
 */
public class No123 {
    /**
     * 关于dp数组的初始化：
     * dp[0][0]: 第一天买入，-prices[0]
     * dp[0][1]: 第一天买入再卖出，0
     * dp[0][2]: 第一天买入卖出再买入，-prices[0]
     * dp[0][3]: 第一天买入卖出再买入再卖出，0
     * 最多完成两笔交易，也可以理解成第一天最多只能买卖一次，所以第二次买卖的状态跟第一次相同
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);  //第一次持有
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);//第一次卖出还未买进
            dp[i][2] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][2]);//第二次买进
            dp[i][3] = Math.max(dp[i - 1][2] + prices[i], dp[i - 1][3]);//第二次卖出后
        }
        return dp[prices.length - 1][3];
    }
}
