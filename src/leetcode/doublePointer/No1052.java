package leetcode.doublePointer;

public class No1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length, tot = 0;
        int[] angry = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            angry[i] = angry[i - 1];
            if (grumpy[i - 1] == 1) angry[i] += customers[i - 1];
            if (grumpy[i - 1] == 0) tot += customers[i - 1];
        }
        int ma = 0;
        for (int i = minutes; i <= n; i++) {
            ma = Math.max(ma, angry[i] - angry[i - minutes]);
        }
        return tot + ma;
    }
}
