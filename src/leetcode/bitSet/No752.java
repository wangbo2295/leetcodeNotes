package leetcode.bitSet;

public class No752 {
    public int openLock(String[] deadends, String target) {
        int[][][][] dp = new int[10][10][10][10];
        int[][] dir = {{1, 0, 0, 0}, {-1, 0, 0, 0}, {0, 1, 0, 0}, {0, -1, 0, 0}, {0, 0, 1, 0}, {0, 0, -1, 0}, {0, 0, 0, 1}, {0, 0, 0, -1}};
        int[] tar = new int[4];
        for (int i = 0; i < 4; i++) tar[i] = target.charAt(i) - '0';
        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) for (int l = 0; l < 10; l++) {
                dp[i][j][k][l] = Integer.MAX_VALUE / 2;
            }
        }
        dp[0][0][0][0] = 0;
        for (String deadend : deadends) {
            for (int i = 0; i < 4; i++) {
                int x1 = deadend.charAt(0) - '0', x2 = deadend.charAt(1) - '0', x3 = deadend.charAt(2) - '0', x4 = deadend.charAt(3) - '0';
                dp[x1][x2][x3][x4] = -1;
            }
        }
        for (int k = 1; k <= 36; k++) {
            for (int i = 1; i < (int) 1e4; i++) {
                int[] x = new int[4];
                int t = i;
                for (int j = 0; j < 4; j++) {
                    x[j] = t % 10;
                    t /= 10;
                }
                if (dp[x[0]][x[1]][x[2]][x[3]] < 0) continue;
                for (int d = 0; d < 8; d++) {
                    int[] y = new int[4];
                    for (int jj = 0; jj < 4; jj++) {
                        y[jj] = (x[jj] + dir[d][jj] + 10) % 10;
                    }
                    if (dp[y[0]][y[1]][y[2]][y[3]] < 0 || dp[y[0]][y[1]][y[2]][y[3]] == Integer.MAX_VALUE / 2) continue;
                    dp[x[0]][x[1]][x[2]][x[3]] = Math.min(dp[x[0]][x[1]][x[2]][x[3]],  dp[y[0]][y[1]][y[2]][y[3]] + 1);
                }
            }
        }
        return dp[tar[0]][tar[1]][tar[2]][tar[3]] >= Integer.MAX_VALUE ? -1 : dp[tar[0]][tar[1]][tar[2]][tar[3]];
    }

    public static void main(String[] args) {
        No752 no752 = new No752();
        no752.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202");
    }
}
