package leetcode.binarySearch;

public class No363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] presum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                presum[i][j] = presum[i - 1][j] + presum[i][j - 1] + matrix[i - 1][j - 1] - presum[i - 1][j - 1];
            }
        }
        int maxless = (int) -1e5;
        //枚举矩形左上和右下两个顶点
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int i2 = 0; i2 < i; i2++) {
                    for (int j2 = 0; j2 < j; j2++) {
                        int sum = presum[i][j] - presum[i][j2] - presum[i2][j] + presum[i2][j2];
                        if (sum <= k) {
                            maxless = Math.max(maxless, sum);
                        }
                    }
                }
            }
        }
        return maxless;
    }
}
