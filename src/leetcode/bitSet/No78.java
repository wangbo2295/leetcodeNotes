package leetcode.bitSet;

import java.util.ArrayList;
import java.util.List;

public class No78 {
    public List<List<Integer>> subsets(int[] nums) {
        //优化bit位查找
        int[] hash = new int[37];
        for (int i = 0; i < 31; i++) {
            hash[(1 << i) % 37] = i;
        }
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 1 << n - 1; i++) {
            List<Integer> e = new ArrayList<>();
            while (i > 0) {
                e.add(nums[hash[i & -i]]);
                i -= i & -i;
            }
            res.add(e);
        }
        return res;
    }
}
