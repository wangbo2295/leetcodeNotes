package leetcode.binarySearch;

public class No633 {
    public boolean judgeSquareSum(int c) {
        long l = 0, r = (long) Math.sqrt(c);
        while (l <= r) {
            long t = l * l + r * r;
            if (t > c) {
                r--;
            } else if (t < c) {
                l++;
            }else {
                return true;
            }
        }
        return false;
    }
}
