package 剑指offer;

public class PathInMatrix {
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        m = board.length;
        n = board[0].length;
        k = word.length();
        used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j]) {
                    used[i][j] = true;
                    if (exist(1, i, j)) return true;
                    used[i][j] = false;
                }
            }
        }
        return false;
    }

    boolean[][] used;
    char[][] board;
    String word;
    int m, n, k;
    int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private boolean exist(int index, int x, int y) {
        if (index == k) return true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !used[nx][ny] && word.charAt(index) == board[nx][ny]) {
                used[nx][ny] = true;
                if (exist(index + 1, nx, ny)) return true;
                used[nx][ny] = false;
            }
        }
        return false;
    }
}
