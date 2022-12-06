package leetcode.dynamicPrograming.memorialSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class IsScramble {

    Boolean[][] dp;
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        dp = new Boolean[n][n + 1];
        return dfs(s1, s2, 0, n);
    }

    boolean dfs(String s1, String s2, int x, int y) {
        if (x + 1 == y) {
            return s1.charAt(x) == s2.charAt(x);
        }
        if (dp[x][y] != null) return dp[x][y];
        for (int i = x + 1; i < y; i++) {
            if (dfs(s1, s2, x, i) && dfs(s1, s2, i, y)) {
                return dp[x][y] = true;
            }
            String s11 = s1.substring(0, x) + s1.substring(i, y) + s1.substring(x, i) + s1.substring(y, s1.length());
            System.out.println(s1 + ", " + s11 + ", " + i);
            if (dfs(s11, s2, x, x + y - i) && dfs(s11, s2, x + y - i, y)) {
                return dp[x][y] = true;
            }
        }
        return dp[x][y] = false;
    }

    public static void main(String[] args) {
        IsScramble scramble = new IsScramble();
        scramble.isScramble("great", "rgeat");
    }
}
