package leetcode.divideAndConquer;

import java.util.Date;

/**
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class No240 {
    /**
     * Z 字型搜索，从右上角开始，变大只能往下，变小只能往左，一旦越界，返回false；
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0, j = matrix[0].length - 1;
        while (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
            if (matrix[i][j] < target) {
                i++;
            }else if (matrix[i][j] > target) {
                j--;
            }else {
                return true;
            }
        }
        return false;
    }
}
