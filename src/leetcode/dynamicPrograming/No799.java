package leetcode.dynamicPrograming;

public class No799 {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][][] dp = new double[query_row + 1][query_row + 1][2];
        dp[0][0][0] = Math.min(1, poured);dp[0][0][1] = Math.max(0, poured - 1);
        for (int i = 1; i <= query_row; i++) {
            for (int j = 0; j <= i; j++) {
                double l = j - 1 >= 0 ? dp[i - 1][j - 1][1] / 2 : 0;
                double r = j < i ? dp[i - 1][j][1] / 2 : 0;
                dp[i][j][0] = Math.min(l + r, 1);
                dp[i][j][1] = Math.max(0, l + r - 1);
            }
        }
        return dp[query_row][query_glass][0];
    }

    /**
     * 状态压缩
     * 观察到只跟上一层的状态有关，所以只保留当前层的剩余量和溢出量状态
     * 由于当前层的剩余量跟上一层左边的和当前位置的溢出量有关，所以要从后往前遍历，以保证下一个杯子用的是未修改的值（也就是上一层的值）
     * @param poured
     * @param query_row
     * @param query_glass
     * @return
     */
    public double champagneTower2(int poured, int query_row, int query_glass) {
        double[] contains = new double[query_row + 1];
        double[] overflow = new double[query_row + 1];
        contains[0] = Math.min(1, poured);overflow[0] = Math.max(0, poured - 1);
        for (int i = 1; i <= query_row; i++) {
            for (int j = i; j >= 0; j--) {
                double l = j - 1 >= 0 ? overflow[j - 1] / 2 : 0;
                double r = j < i ? overflow[j] / 2 : 0;
                contains[j] = Math.min(l + r, 1);
                overflow[j] = Math.max(0, l + r - 1);
            }
        }
        return contains[query_glass];
    }
}
