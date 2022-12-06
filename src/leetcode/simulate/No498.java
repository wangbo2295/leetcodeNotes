package leetcode.simulate;

public class No498 {
    /**
     * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
     * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,4,7,5,3,6,8,9]
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int[][] dir = {{-1, 1}, {1, -1}};
        int n = mat.length * mat[0].length;
        int[] res = new int[n];
        int i = 0, j = 0;
        int k = 0;
        while (k < n) {
            while (i >= 0 && i < mat.length && j >= 0 && j < mat[0].length) {
                res[k++] = mat[i][j];
                i += dir[0][0];
                j += dir[1][0];
            }
            if (j < mat[0].length) {
                i++;
            }else {
                i += 2;
                j--;
            }
            while (i >= 0 && i < mat.length && j >= 0 && j < mat[0].length) {
                res[k++] = mat[i][j];
                i += dir[0][1];
                j += dir[1][1];
            }
            if (i < mat.length) {
                j++;
            }else {
                j += 2;i--;
            }
        }
        return res;
    }
}
