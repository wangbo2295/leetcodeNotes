package leetcode.simulate;

public class No289 {
    /**
     * 生命游戏
     * 模拟细胞状态
     * 因为状态的转变是同时的，所以不能原地修改吗，要用一个矩阵存储结果
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int[][] next = new int[board.length][board[0].length];
        int[][] dir = {{-1,-1,-1,0,1,1,1,0},{-1,0,1,1,1,0,-1,-1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int alive = 0;
                for (int k = 0; k < 8; k++) {
                    int ni = i + dir[0][k];
                    int nj = j + dir[1][k];
                    if (ni >=0 && ni < board.length && nj >=0 && nj < board[0].length && board[ni][nj] == 1) {
                        alive++;
                    }
                }
                if (board[i][j] == 0 && alive == 3) {
                    next[i][j] = 1;
                }else if (board[i][j] == 1 && (alive > 3 || alive < 2)) {
                    next[i][j] = 0;
                }else {
                    next[i][j] = board[i][j];
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = next[i][j];
            }
        }
    }

    public static void main(String[] args) {
        No289 no289 = new No289();
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        no289.gameOfLife(board);
    }
}
