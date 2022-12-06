package leetcode.dynamicPrograming;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 */
public class No413 {
    /**
     * 思考一下一个长度为n的等差数列有多少个子数列，然后统计数组中所有不同的等差数列。
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        List<Integer> ns = new ArrayList<>();
        int t = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                t++;
            }else if (t >= 3) {
                ns.add(t);
                t = 2;
            }
        }
        if (t != 2) ns.add(t);
        int ans = 0;
        for (int i: ns) {
            for (int j = 3; j <= i; j++) {
                ans += j - 3 + 1;
            }
        }
        return ans;
    }
}
