package leetcode.binarySearch;

public class No1802 {
    /**
     * 贪心 + 二分
     * 由题意知要使 index 处的值最大，那么其他位置的值就要尽量小，因为总和是限定的
     * 另外任意相邻数的差的绝对值不能超过 1 ，且必须为正整数，由此想到一种贪心策略：从 index 开始向两边递减，每次递减 1，最低为1
     * 贪心的正确性：对任意 index 以外的数如果不是相对 index 处的值依次递减得来，那一定会大于当前值，这是由相邻差的绝对值不超过 1 决定的
     * 如果大于当前值，那么不会使得结果更优，因为总和增大了，更加容易不满足条件。而在限定条件内尽量减小其他的数会使得总和更小，也就使得最大的数更大
     * 在给定 index 处的值的情况下我们可以用贪心算出满足限制条件的最小总和，通过与 maxSum 比较即可得知该值是否合法
     * 显然越大的值总和越大，因此我们可以二分 index 处的值
     */
    public int maxValue(int n, int index, int maxSum) {
        int l = 0, r = maxSum + 1;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (check(n, index, m, maxSum)) l = m;
            else r = m;
        }
        return l;
    }

    public boolean check(int n, int index, int val, int maxSum) {
        long sum = val;
        //index 左边
        if (index + 1 >= val) { //val 下降到 1 之后还有余位
            sum += (long) val * (val - 1) >> 1;
            sum += index + 1 - val;
        } else {                //val 下降到 1 之前已无余位
            int a0 = val - index;
            sum += (long) (a0 + val - 1) * index >> 1;
        }
        //index 右边
        if (index + val <= n) {
            sum += (long) val * (val - 1) >> 1;
            sum += n - index - val;
        } else {
            int a0 = val - n + 1 + index;
            sum += (long) (a0 + val - 1) * (n - 1 - index) >> 1;
        }
        return sum <= maxSum;
    }
}
