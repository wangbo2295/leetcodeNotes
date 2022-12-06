package 剑指offer;

/**
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 */
public class CountDigitOne {
    /**
     * dp[i][isLimit][isNum] 表示前 i 位的数中一共有多少个 1，isLimit表示是否收到 n 的限制，isNum表示前面是否填数字
     */
//    int[][][] dp = new int[11][2][2];
//    String num;
//    int ans;
//    public int countDigitOne(int n) {
//        num = new StringBuilder(n).reverse().toString();
//        for (int i = 1; i <= num.length(); i++) {
//            for (int j = 0; j < 10; j++) {
//                if (j == 0) {
//                    dp[i][0][0] += dp[i - 1][0][0]; //跳过当前位
//                    dp[i][0][1] += dp[i - 1][0][0];
//                }
//                if (j == num.charAt(i - 1) - '0') {
//
//                }
//                dp[i][0][0] += dp[i - 1][0][1];
//                dp[i][0][1] += dp[i - 1][0][1];
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        CountDigitOne countDigitOne = new CountDigitOne();
//        countDigitOne.countDigitOne(12);
//    }
}
