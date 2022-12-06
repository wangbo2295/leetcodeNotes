package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 */
public class No560 {
    /**
     * 解法：
     * 由于是求连续子数组，就说明是区间和的问题
     * 如何快速得到一个区间的和？    前缀和
     * sum(t + 1, i) = presum[i] - presum[t]
     * 有了区间和，就可以快速判断一个区间的和是否为k
     * 是否需要一个个遍历每个区间？   不需要
     * 注意到上式可变形为 presum[i]  = sum(t + 1, i) + presum[t]
     * 对于一个presum[i]，只需要计算有多少个presum[t]，就有多少个满足要求的区间
     * 此时可以考虑用map实现快速查找
     * 由于 t <= i, 可以从前往后遍历，一边计算区间个数，一边将自身加入map。计算区间时，presum[j]被当作presum[i],加入map时被当作presum[t]
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(k, 1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += map.getOrDefault(nums[i], 0);
            map.put(nums[i] + k, map.getOrDefault(nums[i] + k, 0) + 1);
        }
        return ans;
    }
}
