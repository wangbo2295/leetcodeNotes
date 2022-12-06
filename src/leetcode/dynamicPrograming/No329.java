package leetcode.dynamicPrograming;

public class No329 {
    int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
    int m, n;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        Integer[][] dp = new Integer[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = dfs(matrix, i, j, dp);
            }
        }
        return max;
    }

    public Integer dfs(int[][] matrix, int x, int y, Integer[][] dp) {
        if (dp[x][y] != null)   return dp[x][y];
        int max = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[0][i];
            int ny = y + dir[1][i];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[x][y] < matrix[nx][ny]) {
                if (dp[nx][ny] != null) {
                    max = Math.max(dp[nx][ny] + 1, max);
                } else {
                    max = Math.max(max, dfs(matrix, nx, ny, dp));
                }
            }
        }
        dp[x][y] = max;
        if (x == 0 && y == 0) System.out.println(max);
        return max;
    }
}
