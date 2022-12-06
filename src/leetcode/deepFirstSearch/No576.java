package leetcode.deepFirstSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 这题暴搜超时。。。
 * 显然要用dp
 */
public class No576 {
    int paths;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        dfs(m, n, startRow, startColumn, maxMove);
        return paths;
    }
    public void dfs(int m, int n, int x, int y, int move) {
        if ((x < 0 || x >= m || y < 0 || y >= n) && move >= 0) {
            paths = (paths + 1) % 1000000007;
            return;
        }else if (move <= 0) {
            return;
        }
        List sd = new ArrayList();
        dfs(m, n, x + 1, y, move - 1);
        dfs(m, n, x, y + 1, move - 1);
        dfs(m, n, x - 1, y, move - 1);
        dfs(m, n, x, y - 1, move - 1);
    }

    /**
     * dp思路
     * 结果与移动次数、x，y有关
     * 定义dp[i][j][k]为移动i次后到达（j, k）的路径条数
     * 每次移动都只能往四个方向移动一格，记移动后的坐标为(j1, k1)
     * if (j1, k1) in the matrix            如果移动后没出界，则将路径条数加到新的位置
     *      dp[i+1][j1][k1] += dp[i][j][k];
     * else                                 如果移动后出界，则将路径条数加到结果中
     *      res += dp[i][j][k];
     *
     * 初始化：起点初始化为1
     * @param m
     * @param n
     * @param maxMove
     * @param startRow
     * @param startColumn
     * @return
     */
    public int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        int[][][] dp = new int[maxMove + 1][m][n];
        dp[0][startRow][startColumn] = 1;
        int res = 0, mod = 1000000007;
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < 4; l++) {
                        int nX = j + dir[0][l];
                        int nY = k + dir[1][l];
                        if (nX >= 0 && nX < m && nY >= 0 && nY < n) {
                            dp[i + 1][nX][nY] = (dp[i + 1][nX][nY] + dp[i][j][k]) % mod;
                        }else {
                            res = (res + dp[i][j][k]) % mod;
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 状态压缩
     *
     * @param
     */
    public int findPaths3(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        int[][] dp = new int[m][n];
        dp[startRow][startColumn] = 1;
        int res = 0, mod = 1000000007;
        for (int i = 0; i < maxMove; i++) {
            int[][] dpNew = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < 4; l++) {
                        int nX = j + dir[0][l];
                        int nY = k + dir[1][l];
                        if (nX >= 0 && nX < m && nY >= 0 && nY < n) {
                            dpNew[nX][nY] = (dpNew[nX][nY] + dp[j][k]) % mod;
                        }else {
                            res = (res + dp[j][k]) % mod;
                        }
                    }
                }
            }
            dp = dpNew;
        }
        return res;
    }

    public static void main(String[] args) {
        No576 no576 = new No576();
        int paths = no576.findPaths3(8, 50, 23, 5, 26);
        System.out.println(paths);
    }
}
