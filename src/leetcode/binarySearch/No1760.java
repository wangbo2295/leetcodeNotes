package leetcode.binarySearch;

public class No1760 {
    public int minimumSize(int[] nums, int maxOperations) {
        int l = 0, r = (int) 1e9 + 1;
        while (l + 1 < r) {
            int m = l + (r - l >> 1);
            if (check(nums, maxOperations, m)) r = m;
            else l = m;
        }
        return r;
    }

    public boolean check(int[] nums, int op, int cost) {
        int total = 0;
        for (int num : nums) {
            total += (num - 1) / cost;
        }
        return total <= op;
    }
}
