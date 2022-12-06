package leetcode.breathFirstSearch;

/**
 * 给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 */
public class No542 {
    /**
     * 思路：
     * 从0开始往外扩散，每次加一步
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        int m = mat.length, n = mat[0].length;
        int[][] used = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] *= -1;
            }
        }
        for (int k = 0; k < m + n; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == k) {
                        for (int l = 0; l < 4; l++) {
                            int ni = i + dir[0][l];
                            int nj = j + dir[1][l];
                            if (ni >= 0 && ni < m && nj >= 0 && nj < n && used[ni][nj] == 0 && mat[ni][nj] != k) {
                                mat[ni][nj] = k + 1;
                            }
                        }
                        used[i][j] = 1;
                    }
                }
            }
        }
        return mat;
    }


}
