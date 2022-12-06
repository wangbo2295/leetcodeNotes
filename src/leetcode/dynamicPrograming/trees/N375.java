package leetcode.dynamicPrograming.trees;

/**
 * 猜数字II
 * 给定1 - n，猜错了告诉你大了还是小了，并且罚数字大小的钱，直到猜对为止
 * 求一个猜测方案，使得保证猜对时的花费最小
 * 可以看成一个二叉搜索树，每个数对应的花费是从根开始路径上的值相加（除自己外）
 * 要求一个二叉搜索树，使得树中元素的最大花费最小。
 */
public class N375 {
    /**
     * C[left][right]: left ~ right 范围内的最小花费
     * C[left][right] = min(C[left][i - 1], C[i + 1][right]) + pi
     * pi is the cost of node i, left <= i <= right, left > right represents null.
     * C[i][i] == 0, which means found number i.
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        int[][] C = new int[n + 1][n + 1];
        for (int k = 1; k <= n - 1; k++) {  //k is right - left, different length of range
            for (int left = 1; left <= n - k; left++) {//for each range, calculate C[left][right], update the min value
                int right = left + k;
                int t = Integer.MAX_VALUE;
                for (int i = left; i <= right; i++) {   //for each i between left and right, calculate the max cost(worst condition)
                    int l_i = C[left][i - 1];
                    int i_r = i + 1 <= n ?C[i + 1][right] : 0;
                    t = Math.min(t, Math.max(l_i, i_r) + i);
                }
                C[left][right] = t;
            }
        }
        return C[1][n];
    }
}
