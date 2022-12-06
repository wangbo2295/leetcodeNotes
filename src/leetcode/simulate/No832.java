package leetcode.simulate;

public class No832 {
    /**
     * 思路：
     * 对称的看，如果两个元素不同，那么翻转之后是不变的，如果两个元素相同，则都反转。
     * 如果列的个数为奇数，则另外将左右对称轴上的数反转
     * @param image
     * @return
     */
    public int[][] flipAndInvertImage(int[][] image) {
        int m = image.length, n = image[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n >> 1; j++) {
                if (image[i][j] == image[i][n - j - 1]) {
                    int k = (image[i][j] + 1) & 1;
                    image[i][j] = k; image[i][n - j - 1] = k;
                }
            }
        }
        if ((image[0].length & 1) == 1) {
            for (int i = 0; i < m; i++) {
                image[i][n >> 1] = (image[i][n >> 1] + 1) & 1;
            }
        }
        return image;
    }

    /**
     * 写法二，改进了奇数情况下的反转
     * 利用双指针的特性
     * @param image
     * @return
     */
    public int[][] flipAndInvertImage2(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            int left = 0, right = image[0].length - 1;
            while (left < right) {
                if (image[i][left] == image[i][right]) {
                    int n = (image[i][left] + 1) & 1;
                    image[i][left] = n; image[i][right] = n;
                }
            }
            if (left == right) {
                image[i][left] = (image[i][left] + 1) & 1;
            }
        }
        return image;
    }
}
