package leetcode.dynamicPrograming.path;

/**
 * 最小路径和
 */
public class No64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && j == 0)   continue;
                int left = j > 0 ? grid[i][j - 1] : Integer.MAX_VALUE;
                int up = i > 0 ? grid[i - 1][j] : Integer.MAX_VALUE;
                grid[i][j] += Math.min(left, up);
            }
        }
        return grid[m - 1][n - 1];
    }
}
