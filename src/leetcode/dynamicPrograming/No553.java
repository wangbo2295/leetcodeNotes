package leetcode.dynamicPrograming;


import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 最优除法
 * 给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如，[2,3,4] -> 2 / 3 / 4 。
 *
 * 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应该含有冗余的括号。
 */
public class No553 {
    /**
     * 思路对了，但是如何加括号没实现出来
     * 得出的经验：当动态规划要复原最优解的具体情况时，要另外记录下最优解，如果与子问题关联的字段比较多时
     * （如本题中的子问题为区间（i，j）内的最优除法，涉及到区间的除法最小值、最大值、以及其对应的表达式）
     * 这时就可以将子问题封装成一个对象，使得逻辑更加清晰。
     * 或者，如果子问题关系到的字段都是同一种属性，也可以考虑用多维数组存储。（如买卖股票中最多买卖两次的那道题，有四种状态）
     *
     * 本题中需要注意的几点：
     * 运算顺序是从左到右，所以合并表达式时，左表达式不需要加括号；右表达式加不加括号取决于其中有多少个数，如果只有一个数也不需要加括号
     * @param nums
     * @return
     */
    public String optimalDivision(int[] nums) {
        class Node {
            double max;
            double min;
            String maxStr;
            String minStr;
        }
        int n = nums.length;
        Node[][] dp = new Node[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new Node();
                if (i == j) {
                    dp[i][j].max = nums[i];
                    dp[i][j].min = nums[i];
                    dp[i][j].maxStr = "" + nums[i];
                    dp[i][j].minStr = "" + nums[i];
                }else {
                    dp[i][j].min = Integer.MAX_VALUE;
                }
            }
        }
        for (int k = 1; k < n; k++) {
            for (int left = 0; left < n - k; left++) {
                int right = left + k;
                for (int i = left; i < right; i++) {
                    if (dp[left][i].max / dp[i + 1][right].min >= dp[left][right].max) {
                        dp[left][right].max = dp[left][i].max / dp[i + 1][right].min;
                        if (right - i > 1) {
                            dp[left][right].maxStr = dp[left][i].maxStr + "/(" + dp[i + 1][right].minStr + ")";
                        }else {
                            dp[left][right].maxStr = dp[left][i].maxStr + "/" + dp[i + 1][right].minStr;
                        }
                    }
                    if (dp[left][i].min / dp[i + 1][right].max <= dp[left][right].min) {
                        dp[left][right].min = dp[left][i].min / dp[i + 1][right].max;
                        if (right - i > 1) {
                            dp[left][right].minStr = dp[left][i].minStr + "/(" + dp[i + 1][right].maxStr + ")";
                        }else {
                            dp[left][right].minStr = dp[left][i].minStr + "/" + dp[i + 1][right].maxStr;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1].maxStr;
    }

    /**
     * 数学的方法：
     * 数组中的元素都大于1，所以会越除越小
     * 设最后的表达式是 x / y，只要令x最大、y最小即可。到此为止和动态规划的思路一致
     * 无论如何加括号，显然x最大值就是nums[0]，因为一旦除了一个数就变小
     * 如何加括号使得y最小？
     * 要使得y最小，就是要让剩下的元素在某个运算顺序下每次都除以一个最大值，由上述结论知这个最大值就是第一个数
     * 因此y的最小值就是从第二个数开始依次除以后面的数。
     * 那么括号就加在第二个数到最后一个数之上
     * @param nums
     * @return
     */
    public String optimalDivision2(int[] nums) {
        StringBuilder sb = new StringBuilder();
        if (nums.length <= 2) {
            for (int i : nums) {
                sb.append(i);
                sb.append("/");
            }
            sb.deleteCharAt(sb.length() - 1);
        }else {
            for (int i = 0; i < nums.length; i++) {
                if (i == 1) {
                    sb.append("(");
                }
                sb.append(nums[i]);
                if (i == nums.length - 1) {
                    sb.append(")");
                }
                if (i != nums.length - 1) {
                    sb.append("/");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        No553 no553 = new No553();
        int[] nums = {1000,100,10,2};
        no553.optimalDivision(nums);
    }
}
