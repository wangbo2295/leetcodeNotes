package leetcode.practice;

public class No868 {
    public int binaryGap(int n) {
        while ((n & 1) == 0) {
            n >>= 1;
        }
        if (n <= 1) return 0;
        int max = 1;
        while (n > 1) {
            int cur = 1;
            while (n > 1 && ((n >>= 1) & 1) == 0) {
                cur++;
            }
            max = Math.max(cur, max);
        }
        return max;
    }

    public static void main(String[] args) {
        No868 no868 = new No868();
        no868.binaryGap(6);


    }
}
