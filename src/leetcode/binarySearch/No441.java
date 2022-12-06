package leetcode.binarySearch;

public class No441 {
    /**
     * k * (k + 1) / 2 >= n
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        long left = 0, right = (long) n + 1;
        while (left + 1 < right) {
            long mid = left + (right - left >> 1);
            if (mid * (mid + 1) / 2 >= n) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return (int) right;
    }
}
