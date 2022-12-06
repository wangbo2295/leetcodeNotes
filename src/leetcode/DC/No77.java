package leetcode.DC;

public class No77 {
    public int countPrefixes(String[] words, String s) {
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            if (s.startsWith(words[i])) ++cnt;
        }
        return cnt;
    }

    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        long minDiff = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            long left = pre[i] / i;
            long right = i == n ? 0 : (pre[n] - pre[i]) / (n - i);
            long absDiff = Math.abs(left - right);
            if (absDiff < minDiff) {
                minDiff = absDiff;
                idx = i - 1;
            }
        }
        return idx;
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] board = new int[m][n];  //0-empty, 1-wall, 2-guard, 3-guarded
        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        for (int i = 0; i < walls.length; i++) {
            board[walls[i][0]][walls[i][1]] = 1;
        }
        for (int i = 0; i < guards.length; i++) {
            board[guards[i][0]][guards[i][1]] = 2;
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0)   continue;
                boolean isG = false;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dir[0][k], ny = j + dir[1][k];
                    while (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] != 1) {
                        if (board[nx][ny] == 2) {
                            isG = true;
                            break;
                        }
                        nx += dir[0][k];
                        ny += dir[1][k];
                    }
                }
                if (!isG) {
                    cnt++;
                    board[i][j] = 1;
                }
            }
        }
        return cnt;
    }
}
