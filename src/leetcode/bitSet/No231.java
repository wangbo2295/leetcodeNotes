package leetcode.bitSet;

public class No231 {
    public boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }
}
