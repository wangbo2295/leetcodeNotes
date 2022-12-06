package leetcode.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class No532 {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        Set<Integer> pair = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num - k)) {
                pair.add(num);
            }
            set.add(num);
        }
        return pair.size();
    }

    public int findPairs2(int[] nums, int k) {
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            if (j > 0 && nums[j] == nums[j - 1])    continue;
            if (nums[i] + k == nums[j] && i != j) {
                cnt++;
            } else if (nums[i] + k < nums[j]) {
                i++;
            }
        }
        return cnt;
    }
}
