package leetcode.doublePointer;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 */
public class No395 {
    /**
     * 如果一个字串中存在某个字符出现次数小于 k ，那么要求的最长字串就不可能包含该字符
     * 问题转化为在该字符左右字串中寻找最长字串，基于这一子问题性质，可以采用分治法求解
     */
    public int longestSubstring(String s, int k) {
        return dfs(s, 0, s.length(), k);
    }

    private int dfs(String s, int l, int r, int k) {
        if (l >= r) return 0;
        int[] cnt = new int[26];
        for (int i = l; i < r; i++) cnt[s.charAt(i) - 'a']++;
        int pre = l - 1, max = 0;
        boolean valid = true;
        for (int i = l; i < r; i++) {
            if (cnt[s.charAt(i) - 'a'] < k) {
                max = Math.max(max, dfs(s, pre + 1, i, k));
                pre = i;
                valid = false;
            }
        }
        if (!valid) max = Math.max(max, dfs(s, pre + 1, r, k));
        return valid ? r - l : max;
    }
}
