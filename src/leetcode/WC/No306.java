package leetcode.WC;

import java.util.*;

public class No306 {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 2; i < n; i++) {
            for (int j = 2; j < n; j++) {
                int max = 0;
                for (int k = i - 2; k <= i; k++) {
                    for (int l = j - 2; l <= j; l++) {
                        max = Math.max(max, grid[k][l]);
                    }
                }
                ans[i - 2][j - 2] = max;
            }
        }
        return ans;
    }

    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[][] map = new long[n][2];
        for (int i = 0; i < edges.length; i++) {
            map[edges[i]][0] += i;
        }
        for (int i = 0; i < n; i++) {
            map[i][1] = i;
        }
        Arrays.sort(map, Comparator.comparingLong((long [] o)->o[0]).reversed().thenComparingLong((long [] o)->o[1]));
        return (int) map[0][1];
    }

    public String smallestNumber(String pattern) {
        int[] ans = new int[9];
        ans[0] = 1;
        int max = 1;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'D') {
                int j = i;
                for (; j > 0 && ans[j - 1] > ans[j]; j--) {
                    ans[j]++;
                }
                ans[j]++;
                max = ans[j];
                ans[i + 1] = ans[i] - 1;
            } else {
                ans[i + 1] = max + 1;
                max = ans[i + 1];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int an : ans) {
            if (an > 0) sb.append(an);
        }
        return sb.toString();
    }

    public int countSpecialNumbers(int n) {
        int ans = 9;
        int base = 9, j = 100;
        for (int i = 9; j < n; j *= 10, i--) {
            ans += base * i;
        }
        base = 1;j /= 10;
        int pre = 0;
        for (; j > 0; j /= 10) {
            if (j == 1) n++;
            base *= n / j - pre;
            pre = n / j;
            n = n % j;
        }
        return ans + base;
    }
}
