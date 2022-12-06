package leetcode.practice;
/**
 * 给定一个整数数组prices，其中 prices[i]表示第i天的股票价格 ；整数fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

/**
 * 看了好久终于悟了
 * 加入手续费的概念，就要考虑卖出的时机了
 * 如果仅仅在最低点买入最高点卖出，那多次的买入卖出消耗的手续费可能会使利润减小
 * 应当采取如下思路
 * 每次卖出的价格大于买入的价格加上手续费，说明能盈利
 * 那么每次能盈利的时候都计算利润，但是不卖出
 * 计算利润之后将买入价格改成卖出价格，后续有跟高的卖出价格还可以继续收割利润
 * 什么时候卖出？就是当股票价格加上手续费小于当前买入价格的时候，说明卖出再买入能赚取这中间的差价
 * 而如果股票价格加上手续费大于当前买入价格，说明卖出会赔掉这之间的差价，所以不卖，继续收割利润。
 */
class No714 {
    /**从代码上看，很难看出卖出价格时的区别，因为无论卖出还是收割利润都在更新买入价格
    实际上可以看成从头到尾都在收割利润，只是卖出的时候多收割了两次买卖之间的差价。**/
    public int maxProfit(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int sum = 0;
        for (int p : prices) {
            if (p + fee < buy) {//股票价格加上手续费大于买入价格，卖出，赚取差价
                buy = p + fee;
            } else if (p > buy){//股票价格大于买入价格，计算利润并更新买入价格，但是不卖出，继续收割利润
                sum += p - buy;
                buy = p;
            }
        }
        return sum;
    }

    /**
     * 动态规划解法
     * 每天有两种状态：持有和不持有股票
     * 持有的最大收益为昨天就持有的最大收益和今天买入的最大收益中较大者
     * 不持有的最大收益为昨天就不持有的最大收益和今天卖出的最大收益中较大者（注意今天卖出要减去手续费）
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit2(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        for(int i=1;i<len;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]-fee);
        }
        List<String> list = new ArrayList<>();
        String.join(".",list);
        return dp[len-1][1];
    }
}