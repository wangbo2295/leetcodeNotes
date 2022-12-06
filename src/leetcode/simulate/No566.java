package leetcode.simulate;

public class No566 {
    /**
     * 将二维数组从左到右、从上到下从0开始编号
     * 由于两个矩阵元素个数相等，所以可以对同一个编号映射到不同的下标
     * i = num / n, j = num % n     其中，i 和 j 分别是矩阵行和列，n是矩阵的列的尺寸
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        if (r * c != m * n || (r == m && c == n))    return mat;
        int[][] res = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            res[i / c][i % c] = mat[i / n][i % n];
        }
        return res;
    }
}
