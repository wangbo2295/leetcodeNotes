package leetcode.array;

/**
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class No85 {
    /**
     * 思路：
     * 枚举矩形上下两条边 O(m ^ 2)，然后二维降一维，在一维数组中找最长连续子数组，其值为上下两条边之间的宽度，可以用滑动窗口O(n)
     * 总的时间复杂度为 O(m ^ 2 * n), m, n <= 200 , m ^ 2 * n <= 8 * 10 ^ 6
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0, m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for (int j = i; j < m; j++) {
                for (int k = 0; k < n; k++) sum[k] += matrix[j][k] - '0';
                int len = 0;
                for (int r = 0; r <= n; r++) {
                    if (r < n && sum[r] == j - i + 1) {
                        len++;
                    } else {
                        maxArea = Math.max(maxArea, (j - i + 1) * len);
                        len = 0;
                    }
                }
            }
        }
        return maxArea;
    }
}
