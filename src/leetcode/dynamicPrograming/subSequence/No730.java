package leetcode.dynamicPrograming.subSequence;

import java.util.Set;

/**
 * 给定一个字符串 s，返回 s中不同的非空「回文子序列」个数 。
 * 通过从 s中删除 0 个或多个字符来获得子序列。
 * 如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。
 * 如果有某个 i , 满足ai!= bi，则两个序列a1, a2, ...和b1, b2, ...不同。
 * 注意：
 * 结果可能很大，你需要对109+ 7取模 。
 */
public class No730 {
    boolean[] used;
    int ans;
    StringBuilder sb;
    Set<StringBuilder> set;
    public int countPalindromicSubsequences(String s) {
        int MOD = 1_000_000_007;
        return -1;
    }

    public void dfs(String s, int start) {
        if (start >= s.length()) return;
        for (int i = start; i < s.length(); i++) {
            if ((i > 0 && !used[i] && s.charAt(i) == s.charAt(i - 1)))  continue;
            sb.append(s.charAt(i));
            if (!set.contains(sb) && isPalindromic(sb)) ans++;
            dfs(s, ++start);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public boolean isPalindromic(StringBuilder sb) {
        for (int s = 0, e = sb.length() - 1; s < e; s++, e--) {
            if (sb.charAt(s) != sb.charAt(e))   return false;
        }
        return true;
    }
}
