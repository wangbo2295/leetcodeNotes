package leetcode.bitSet;

public class No371 {
    public int getSum(int a, int b) {
        while (b != 0) {
            int c = a ^ b;
            int d = (a & b) << 1;
            a = c;
            b = d;
        }
        return a;
    }
}
