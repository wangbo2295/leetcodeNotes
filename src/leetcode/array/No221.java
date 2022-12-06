package leetcode.array;

public class No221 {
    /**
     * 固定正方形的左上角，大的一定包含小的，所以依次枚举左上角和边长，如果该左上角边长为len的正方形存在则边长加一，直到不满足为止。
     * 因为要找最大正方形，所以 len 只需一直增大，只要满足当前 len 的正方形，就扩大 len，最后得到的是预期最大 len ，需要减一才是实际最大 len
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] presum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                presum[i][j] = presum[i - 1][j] + presum[i][j - 1] - presum[i - 1][j - 1] + matrix[i - 1][j - 1] - '0';
            }
        }
        int len = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                while (i + len <= m && j + len <= n && presum[i + len][j + len] - presum[i][j + len] - presum[i + len][j] + presum[i][j] == len * len) {
                    len++;
                }
            }
        }
        return (len - 1) * (len - 1);
    }
}
