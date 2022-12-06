package leetcode.dynamicPrograming;

import java.util.List;

public class No139 {
    /**
     * 回溯, 超时
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, wordDict, 0);
    }
    public boolean wordBreak(String s, List<String> wordDict, int start) {
        if (start >= s.length()) {
            return true;
        }
        boolean res = false;
        for (String word: wordDict) {
            if (s.startsWith(word, start)) {
                res |= wordBreak(s, wordDict, start + word.length());
            }
        }
        return res;
    }
    /**
     * 动态规划，完全背包
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < wordDict.size(); j++) {
                String word = wordDict.get(j);
                int startIndex = i - word.length() + 1;
                if (startIndex - 1 >= 0 && s.startsWith(word, startIndex)) {
                    dp[i] |= dp[startIndex - 1];
                }else if (startIndex == 0 && s.startsWith(word, startIndex)) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length() - 1];
    }
}
