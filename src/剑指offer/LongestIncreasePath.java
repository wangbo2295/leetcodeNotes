package 剑指offer;

public class LongestIncreasePath {
    int m, n;
    Integer[][] path;
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length; n = matrix[0].length;
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int x, int y) {
        int max = 0, res = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[x][y] < matrix[nx][ny]) {
                if (path[nx][ny] != null)   max = Math.max(max, path[nx][ny]);
                else max = Math.max(max, dfs(matrix, nx, ny));
            }
        }
        path[x][y] = max + res;
        return max + res;
    }
}
