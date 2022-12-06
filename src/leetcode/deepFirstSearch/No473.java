package leetcode.deepFirstSearch;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class No473 {
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0)   return false;
        sum /= 4;
        int[] edges = new int[4];
        Arrays.sort(matchsticks);
        boolean[] used = new boolean[matchsticks.length];
        return makesquare(edges, used, matchsticks, sum);
    }

    public boolean makesquare(int[] edges, boolean[] used, int[] matchsticks, int edge) {
        if (edges[0] == edges[1] && edges[1] == edges[2] && edges[2] == edges[3] && edges[3] == edge)   return true;
        boolean res = false;
        for (int j = 0; j < 4; j++) {
            for (int i = matchsticks.length - 1; i >= 0; i--) {
                if (!used[i] && matchsticks[i] + edges[j] <= edge) {
                    used[i] = true;
                    edges[j] += matchsticks[i];
                    res |= makesquare(edges, used, matchsticks, edge);
                    edges[j] -= matchsticks[i];
                    used[i] = false;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        No473 no473 = new No473();
        int[] matchsticks = {3,3,3,3,4};
        boolean makesquare = no473.makesquare(matchsticks);
        System.out.println(makesquare);
    }
}
