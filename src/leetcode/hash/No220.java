package leetcode.hash;


import java.util.Set;
import java.util.TreeSet;

public class No220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        int n = nums.length;
        for (int i = 0; i < Math.min(n, k); i++) {
            Long floor = set.floor((long) (nums[i]) + t);
            if (floor != null && floor >= (long)nums[i] - t)   return true;
            set.add((long)nums[i]);
        }
        for (int i = k; i < n; i++) {
            Long floor = set.floor((long) (nums[i]) + t);
            if (floor != null && floor >= (long)nums[i] - t)   return true;
            set.add((long)nums[i]);
            set.remove((long) nums[i - k]);
        }
        return false;
    }
}
