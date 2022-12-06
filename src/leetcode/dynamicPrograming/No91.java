package leetcode.dynamicPrograming;

/**
 * 解码方法
 * 当出现两位数的时候，可能有两种解码方式
 * 所以分为组合两个数和不组合两个数两种状态
 * dp[i]: 考虑前 i 个数有多少种解码方式
 * if CODE(i - 1, i) is valid
 *      dp[i] = dp[i - 1] + dp[i];
 * else
 *      dp[i] = dp[i - 1];
 */
public class No91 {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            char pre = s.charAt(i - 1);
            char cur = s.charAt(i);
            if ((pre == '1' && cur >= '0' && cur <= '9')
                    || (pre == '2' && cur >= '0' && cur <= '6')) {
                dp[i] = dp[i - 2] + dp[i - 1];
            }else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length()];
    }
}
