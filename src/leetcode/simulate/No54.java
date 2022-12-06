package leetcode.simulate;

import java.util.ArrayList;
import java.util.List;

class No54 {
    /**
     * 模拟螺旋数组， 遵循左闭右开原则。
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int layer = 0;
        int m = matrix.length, n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        while (2 * layer < m && 2 * layer < n) {
            if (2 * layer + 1 == m) {
                for (int i = layer; i < n - layer; i++) {
                    res.add(matrix[layer][i]);
                }
            }else if (2 * layer + 1 == n) {
                for (int i = layer; i < m - layer; i++) {
                    res.add(matrix[i][layer]);
                }
            }
            for (int i = layer; i < n - 1 - layer; i++) {
                res.add(matrix[layer][i]);
            }
            for (int i = layer; i < m - 1 - layer; i++) {
                res.add(matrix[i][n - 1 - layer]);
            }
            for (int i = n - 1 - layer; i > layer; i--) {
                res.add(matrix[m - 1 - layer][i]);
            }
            for (int i = m - 1 - layer; i > layer; i--) {
                res.add(matrix[i][layer]);
            }
            layer++;
        }
        for (int i: res) {
            System.out.print(i + ",");
        }
        return res;
    }

    public static void main(String[] args) {
        No54 no54 = new No54();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        no54.spiralOrder(matrix2);
    }
}