package leetcode.breathFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 题意：除了与边界上'O'相连的'O'，其他的'O'都替换为'X'
 */
public class No130 {
    /**
     * 思路：只要将与边界相连的'O'标记出来，再将未被标记的'O'改为'X'即可
     * @param board
     */
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        boolean[][] border = new boolean[m][n];
        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && !border[i][j] && board[i][j] == 'O') {
                    Queue<int[]> queue = new LinkedList<>();
                    border[i][j] = true;
                    queue.offer(new int[] {i, j});
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        int[] cor = queue.poll();
                        for (int k = 0; k < size; k++) {
                            for (int l = 0; l < 4; l++) {
                                int ni = cor[0] + dir[0][l];
                                int nj = cor[1] + dir[1][l];
                                if (ni >= 0 && ni < m && nj >= 0 && nj < n && board[ni][nj] == 'O' && !border[ni][nj]) {
                                    border[ni][nj] = true;
                                    queue.offer(new int[] {ni, nj});
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!border[i][j] && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
