package leetcode.WC;

import java.util.ArrayList;

public class No313 {
    /**
     * 给你一个仅由小写英文字母组成的字符串 s 。在一步操作中，你可以：
     * 删除 整个字符串 s ，或者
     * 对于满足 1 <= i <= s.length / 2 的任意 i ，如果 s 中的 前 i 个字母和接下来的 i 个字母 相等 ，删除 前 i 个字母。
     * 例如，如果 s = "ababc" ，那么在一步操作中，你可以删除 s 的前两个字母得到 "abc" ，因为 s 的前两个字母和接下来的两个字母都等于 "ab" 。
     * 返回删除 s 所需的最大操作数。
     *
     * todo dp + hash , 给自己蠢到了，能hash的为啥要kmp
     * T4 花了俩小时独立想出来的思路，值得记录和复习
     * 第一反应是 kmp 求最小循环单元，但细想之后发现最小循环单元不一定是最优解，遂放弃
     * 又想到动态规划，乍一看是可行的，但计算时间复杂度发现是 O(n ^ 3)
     * 显然每次计算前后缀的匹配是又很多重复计算的，一定需要优化，所以决定先把暴力解法写出来，再看看怎么优化
     * 记 dp[i] 为得到以 i 为前缀的子串最多可以操作多少次
     * dp[i] = max(dp[i], dp[i - j] + 1) , 其中 j 为以 i 为前缀，i - 1 为后缀的每个匹配子串
     * 判断匹配需要 O(n), 枚举 j 需要 O（n），计算 dp 需要O(n),因此时间复杂度 O（n ^ 3）
     * 显然判断匹配这一步需要优化，因此又回到 kmp
     * 观察到我们需要的前缀和后缀是在 i 附近，而 kmp 计算的前缀和后缀是在开始和结尾
     * 因此想到将 s 从 i 处切断，将开始和结尾拼接，就变成了一个以 i 开始， i - 1 结尾的字符串
     * 此时再计算出 s 的 next 数组即可
     * 计算 dp[i] 时，我们要得到所有匹配的前后缀，利用next数组特性递归地求 next[i - 1] 即可
     * 另外不能越过原字符串的边界，即 next[i - 1] <= min(n - i, i)
     */
    public int deleteString(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i < n; i++) {
            int[] next = next(s, i);
            for (int j = next[n]; j > 0; j = next[j]) {
                if (i + j <= n && j <= i && (i - j == 0 || dp[i - j] > 0)) {
                    dp[i] = Math.max(dp[i], dp[i - j] + 1);
                }
            }
        }
        int max = 0;
        for (int i : dp) max = Math.max(max, i);
        return max + 1;
    }

    public int[] next(String s, int start) {
        int n = s.length();
        int[] next = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int j = next[i - 1];
            char c = s.charAt((i - 1 + start) % n);
            while (j > 0 && s.charAt((j + start) % n) != c) j = next[j];
            if (s.charAt((j + start) % n) == c) j++;
            next[i] = j;
        }
        return next;
    }
}
