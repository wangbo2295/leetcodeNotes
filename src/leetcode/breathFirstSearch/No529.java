package leetcode.breathFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个大小为 m x n 二维字符矩阵board ，表示扫雷游戏的盘面，其中：
 * 'M'代表一个 未挖出的 地雷，
 * 'E'代表一个 未挖出的 空方块，
 * 'B'代表没有相邻（上，下，左，右，和所有4个对角线）地雷的 已挖出的 空白方块，
 * 数字（'1' 到 '8'）表示有多少地雷与这块 已挖出的 方块相邻，
 * 'X'则表示一个 已挖出的 地雷。
 * 给你一个整数数组 click ，其中 click = [clickr, clickc] 表示在所有 未挖出的 方块（'M' 或者 'E'）中的下一个点击位置（clickr 是行下标，clickc 是列下标）。
 *
 * 根据以下规则，返回相应位置被点击后对应的盘面：
 *
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为'X' 。
 * 如果一个 没有相邻地雷 的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的 未挖出 方块都应该被递归地揭露。
 * 如果一个 至少与一个地雷相邻 的空方块（'E'）被挖出，修改它为数字（'1' 到 '8' ），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回盘面。
 */
public class No529 {
    /**
     * 如果是周围没有炸弹的方块，则递归地揭露周围的方块
     * 如果是周围有炸弹的方块，则修改其为周围炸弹数量
     * 如果是炸弹，改为'X'，返回结果
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] dir = {{1, 0, -1, 0, 1, -1, 1, -1}, {0, 1, 0, -1, 1, -1, -1, 1}};
        int m = board.length, n = board[0].length;
        boolean[][] vis = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        if (board[click[0]][click[1]] == 'E')
            queue.offer(click);
            vis[click[0]][click[1]] = true;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        while (!queue.isEmpty()) {
            int[] block = queue.poll();
            int boom = 0;
            for (int j = 0; j < 8; j++) {
                int nx = block[0] + dir[0][j];
                int ny = block[1] + dir[1][j];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] == 'M') {
                    boom++;
                }
            }
            if (boom != 0) {
                board[block[0]][block[1]] = (char) ('0' + boom);
            }else {
                board[block[0]][block[1]] = 'B';
                for (int j = 0; j < 8; j++) {
                    int nx = block[0] + dir[0][j];
                    int ny = block[1] + dir[1][j];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] == 'E' && !vis[nx][ny]) {
                        queue.offer(new int[] {nx, ny});
                        vis[nx][ny] = true;
                    }
                }
            }
        }
        return board;
    }

}
