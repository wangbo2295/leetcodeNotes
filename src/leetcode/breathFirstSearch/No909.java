package leetcode.breathFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

public class No909 {
    /**
     * 思路：广度优先搜索
     * 每轮将能到达的点都加入队列，但是遇到梯子和蛇可能会往回走，遇到这种情况的时候，如果往回到达的点是之前到过的点
     * 就不要再加入队列，这条路作废。所以需要标记每次到达的点防止往回走，否则会进入死循环。
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int steps = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[][] used = new boolean[board.length][board[0].length];
        int[] cor1 = num2Cor(1, n);
        used[cor1[0]][cor1[1]] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
                if (num == n * n)   return steps;
                for (int j = num + 1; j <= Math.min(num + 6, n * n); j++) {
                    int[] cor = num2Cor(j, n);
                    if (j == n * n || (board[cor[0]][cor[1]] == -1 && !used[cor[0]][cor[1]])) {
                        queue.offer(j);
                        used[cor[0]][cor[1]] = true;
                    }else if (board[cor[0]][cor[1]] != -1) {
                        int[] c = num2Cor(board[cor[0]][cor[1]], n);
                        if (!used[c[0]][c[1]]) {
                            used[c[0]][c[1]] = true;
                            queue.offer(board[cor[0]][cor[1]]);
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    /**
     * 坐标转换函数
     * @param num
     * @param n
     * @return
     */
    public int[] num2Cor(int num, int n) {
        int row = (num - 1) / n;
        int col;
        if ((row & 1) == 0) {
            col = ((num - 1) % n);
        }else {
            col = n - 1 - ((num - 1) % n);
        }
        return new int[] {n - 1 - row, col};
    }
}
