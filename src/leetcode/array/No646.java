package leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

public class No646 {
    int cnt;
    boolean[] used;
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(o->o[0]));
        used = new boolean[pairs.length];
        dfs(pairs, 0, pairs[0][1]);
        return cnt;
    }

    public void dfs(int[][] pairs, int k, int pre) {
        for (int i = 0; i < used.length; i++) {
            if (!used[i] && pairs[i][0] < pre) {
                dfs(pairs, k + 1, pairs[i][1]);
                cnt = Math.max(cnt, k + 1);
            }
        }
    }
}
